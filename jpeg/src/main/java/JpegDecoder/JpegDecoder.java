package JpegDecoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import DataObjects.Tile;
import ImageLoader.ImageLoaderInterface;
import ImageLoader.SimpleImageLoader;
import JpegInterfaces.DecoderInterface;
import JpegMath.Coders.HuffmanCoding;
import JpegMath.ImageToArrayConverter.ImageArrayConverterInterface;
import JpegMath.Image_ArrayConverter.Image_YCbCrArrayConverter;
import JpegMath.Quantiziers.JpegUniformQuantizier;
import JpegMath.Tilers.JpegTiler;
import JpegMath.Transformations.DctTransformation;
import JpegMath.Transformations.TransformationInterface;

public class JpegDecoder implements DecoderInterface {
	String inputPath;
	String outputPath;
	DctTransformation dct;
	JpegUniformQuantizier quant;
	JpegTiler tiler;
	public int imgWidth, imgHeight;

	HuffmanCoding huf;

	public JpegDecoder(String inputPath_, String outputPath_) {
		outputPath = outputPath_;
		inputPath = inputPath_;
		huf = new HuffmanCoding();
		quant = new JpegUniformQuantizier(80);
		dct = new DctTransformation();
		tiler = new JpegTiler();

		ImageLoaderInterface l = new SimpleImageLoader();
		BufferedImage img = l.getImage(inputPath);
		imgWidth = img.getWidth();
		imgHeight = img.getHeight();
	}

	public void decode() {
		List<List<Tile<Double>>> huffmanDecoded = huf.decodeImage(inputPath);
		// List<List<Tile<Double>>> dequantized = dequantize(huffmanDecoded);
		Tile<Integer>[][][] inversedDct = inverseTransform(huffmanDecoded);

		BufferedImage img = postprocessing(inversedDct);

		try {
			int dot = outputPath.indexOf(".");
			System.out.println("Saving file");
			boolean status = ImageIO.write(img,
					outputPath.substring(dot + 1, outputPath.length()),
					new File(outputPath));
			if (status == false) {
				System.out
						.println("Sorry something went wrong with ImageIO, try to use different output format");
				System.exit(-1);
			}
		} catch (Exception e) {
			System.err.println("Error while saving file");
			System.exit(-1);
		}
	}

	protected BufferedImage postprocessing(Tile<Integer>[][][] subpixels) {
		Integer[][][] tiles = detile(subpixels);
		BufferedImage img = array2Image(tiles);
		return img;
	}

	public BufferedImage array2Image(Integer[][][] array) {
		BufferedImage img = new BufferedImage(array.length, array[0].length,
				BufferedImage.TYPE_INT_ARGB);
		ImageArrayConverterInterface image2Array = new Image_YCbCrArrayConverter();
		img = image2Array.convert(array);
		return img;
	}

	public Integer[][][] detile(Tile<Integer>[][][] subpixels) {
		Integer[][][] out = new Integer[3][imgHeight][imgWidth];
		int component = 0;
		// for Y[][], Cb[][], Cr[][]
		for (Tile<Integer>[][] componentArray : subpixels) {
			out[component++] = tiler.connect(componentArray, imgHeight,
					imgWidth);
		}
		return out;
	}

	protected List<List<Tile<Double>>> dequantize(
			List<List<Tile<Integer>>> tiles) {
		List<List<Tile<Double>>> out = new ArrayList<List<Tile<Double>>>();
		for (List<Tile<Integer>> t : tiles) {
			List<Tile<Double>> comp = new ArrayList<Tile<Double>>();

			Integer tableNo = 0;
			for (Tile<Integer> component : t) {
				comp.add(quant.dequantize(component, tableNo));
				tableNo = 1; // 2nd. and 3rd. component should be quantizied
								// with chrominance table
			}
			out.add(comp);
		}

		return out;
	}

	protected Tile<Integer>[][][] inverseTransform(
			List<List<Tile<Double>>> tiles) {
		TransformationInterface dct = new DctTransformation();
		int y = (int) Math.ceil(imgHeight / 8d);
		int x = (int) Math.ceil(imgWidth / 8d);
		Tile<Integer>[][][] out = new Tile[3][y][x];

		for (int comp = 0; comp < 3; comp++) {
			for (int row = 0; row < y; row++)
				for (int col = 0; col < x; col++) {
					out[comp][row][col] = dct.inverseTransform(tiles.get(
							col + row * x).get(comp));
				}
		}

		return out;
	}
}
