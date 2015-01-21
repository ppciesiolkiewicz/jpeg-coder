package JpegDecoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import DataObjects.Tile;
import JpegInterfaces.DecoderInterface;
import JpegMath.Coders.HuffmanCoding;
import JpegMath.Quantiziers.JpegUniformQuantizier;
import JpegMath.Tilers.JpegTiler;
import JpegMath.Tilers.TilerInterface;
import JpegMath.Transformations.DctTransformation;
import JpegMath.Transformations.FastDctTransformation;
import JpegMath.Transformations.TransformationInterface;

public class JpegDecoder implements DecoderInterface {
	String inputPath;
	String outputPath;
	DctTransformation dct;
	JpegUniformQuantizier quant;
	TilerInterface<Integer> tiler;
	int width, height;

	HuffmanCoding huf;

	public JpegDecoder(String inputPath_, String outputPath_) {
		outputPath = outputPath_;
		inputPath = inputPath_;
		huf = new HuffmanCoding();
		quant = new JpegUniformQuantizier(80);
		dct = new DctTransformation();
		tiler = new JpegTiler();
		width = 34; // TODO
		height = 16;
	}

	public void decode() {
		List<List<Tile<Integer>>> huffmanDecoded = huf.decodeImage(inputPath);
		System.out.println("hufDec:" + huffmanDecoded.size() + " "
				+ huffmanDecoded.get(0).size());
		List<List<Tile<Double>>> dequantized = dequantize(huffmanDecoded);
		System.out.println("dequan:" + dequantized.size() + " "
				+ dequantized.get(0).size());
		Tile<Integer>[][][] inversedDct = inverseTransform(dequantized);
		System.out.println("inversedDct:" + inversedDct.length + " "
				+ inversedDct[0].length);
		BufferedImage img = postprocessing(inversedDct);
		File outputfile = new File(outputPath);
		try {
			ImageIO.write(img, "bmp", outputfile);
		} catch (IOException e) {
			System.err.println("Error while saving file to " + outputfile);
			System.exit(-1);
		}
	}

	protected BufferedImage postprocessing(Tile<Integer>[][][] subpixels) {
		Integer[][][] tiles = detile(subpixels);
		System.out.println("detile:" + subpixels.length + " "
				+ subpixels[0].length + " " + subpixels[0][0].length);
		BufferedImage img = array2Image(tiles);

		return img;
	}

	private BufferedImage array2Image(Integer[][][] array) {
		BufferedImage img = null;
		img = new BufferedImage(array.length, array[0].length,
				BufferedImage.TYPE_INT_RGB); // TODO
		// ImageToArrayConverterInterface image2Array = new ImageToYCbCrArray();
		// Integer[][][] subpixels = image2Array.convert(image);
		// return subpixels;
		return img;
	}

	protected Integer[][][] detile(Tile<Integer>[][][] subpixels) {
		Integer[][][] out = new Integer[3][][];

		int component = 0;
		// for Y[][], Cb[][], Cr[][]
		for (Tile<Integer>[][] componentArray : subpixels) { // TODO
			out[component++] = tiler.connect(componentArray);
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
		TransformationInterface dct = new FastDctTransformation();
		// Tile<Integer>[][][] out = new
		// Tile[tiles.size()][tiles.get(0).size()][];
		Tile<Integer>[][][] out = new Tile[3][][]; // hardcoded 3 :(
		
		
		for (int i = 0; i < out.length; i++) {
			Tile<Integer>[][] component;
			for (int j = 0; j < tiles.size(); j++)
				for (int k = 0; k < tiles.get(0).size(); k += 3) {
					//component[][] = dct.inverseTransform(tiles.get(j).get(k));
					//out[i] = component;
				}
		}

		/*for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.size(); j++)
				for (int k = 0; k < tiles.get(0).size(); k += 3) {
					Tile<Integer>[][] component = new Tile[j][]; // hardcoded 3
																	// :(

					/*
					 * component[0] = dct.inverseTransform(tiles.get(j).get(k));
					 * component[1] = dct.inverseTransform(tiles.get(j).get(k +
					 * 1)); component[2] =
					 * dct.inverseTransform(tiles.get(j).get(k + 2)); out[0] =
					 * components; out[1] = ; out[2] = ;
					 */
		/*		}
		}*/
		return out;
	}
}
