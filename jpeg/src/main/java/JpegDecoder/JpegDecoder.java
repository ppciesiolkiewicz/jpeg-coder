package JpegDecoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.jtransforms.utils.IOUtils;

import DataObjects.Tile;
import JpegInterfaces.DecoderInterface;
import JpegMath.Coders.HuffmanCoding;
import JpegMath.ImageToArrayConverter.ImageToArrayConverterInterface;
import JpegMath.ImageToArrayConverter.ImageToYCbCrArray;
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

	HuffmanCoding huf;

	public JpegDecoder(String inputPath_, String outputPath_) {
		outputPath = outputPath_;
		inputPath = inputPath_;
		huf = new HuffmanCoding();
		quant = new JpegUniformQuantizier(80);
		dct = new DctTransformation();
		tiler = new JpegTiler();
	}

	public void decode() {
		List<List<Tile<Integer>>> huffmanDecoded = huf.decodeImage(inputPath);
		List<List<Tile<Double>>> dequantized = dequantize(huffmanDecoded);
		Tile<Integer>[][][] inversedDct = transform(dequantized);
		postprocessing(inversedDct);
	}
	
	protected BufferedImage postprocessing(Tile<Integer>[][][] subpixels) {
		BufferedImage img = null;
		Integer[][][] tiles = detile(subpixels);
		//Integer[][][] subpixels = image2Array(image);
		
		return img;
	}
	
	private BufferedImage Array2Image(Integer[][][] array) {
		BufferedImage img = null;
		img = new BufferedImage(array.length, array[0].length, BufferedImage.TYPE_INT_RGB); //TODO
		//ImageToArrayConverterInterface image2Array = new ImageToYCbCrArray();
		//Integer[][][] subpixels = image2Array.convert(image);
		//return subpixels;
		return img;
	}

	protected Integer[][][] detile(Tile<Integer>[][][] subpixels) {
		Integer[][][] out = new Integer[3][subpixels[0].length*8][subpixels[0][0].length*8];
		int component = 0;
		// for Y[][], Cb[][], Cr[][]
		for (Tile<Integer>[][] componentArray : subpixels)
			out[component++] = tiler.connect(componentArray);

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

	protected Tile<Integer>[][][] transform(List<List<Tile<Double>>> tiles) {
		TransformationInterface dct = new FastDctTransformation();
		Tile<Integer>[][][] out = new Tile[tiles.size()][tiles.get(0).size()][];

		for (int j = 0; j < tiles.size(); j++)
			for (int k = 0; k < tiles.get(0).size(); k+=3) {
				Tile<Integer>[] components = new Tile[3]; // hardcoded 3 :(
				components[0] = dct.inverseTransform(tiles.get(j).get(k));
				components[0] = dct.inverseTransform(tiles.get(j).get(k+1));
				components[0] = dct.inverseTransform(tiles.get(j).get(k+2));
				out[j][k]=components;
			}
		return out;
	}
}
