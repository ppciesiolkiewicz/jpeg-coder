package JpegEncoder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DataObjects.JpegInfo;
import DataObjects.Tile;
import ImageLoader.ImageLoaderInterface;
import ImageLoader.SimpleImageLoader;
import JpegInterfaces.EncoderInterface;
import JpegMath.Coders.HuffmanCoding;
import JpegMath.ImageToArrayConverter.ImageArrayConverterInterface;
import JpegMath.ImageToArrayConverter.ImageYCbCrArrayConverter;
import JpegMath.Quantiziers.JpegUniformQuantizier;
import JpegMath.Tilers.JpegTiler;
import JpegMath.Tilers.TilerInterface;

public abstract class AbstractJpegEncoder implements EncoderInterface {
	String inputPath;
	String outputPath;
	Integer quality;

	FileOutputStream fileOutput;
	HuffmanCoding encoder;
	JpegFileWriter writer;
	JpegUniformQuantizier quant;

	public AbstractJpegEncoder(String inputPath_, String outputPath_,
			Integer quality_) {
		quality = quality_;
		outputPath = outputPath_;
		inputPath = inputPath_;

		encoder = new HuffmanCoding();
		quant = new JpegUniformQuantizier(quality);
		writer = new JpegFileWriter(quant, encoder);
		try {
			fileOutput = new FileOutputStream(outputPath);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot create output file");
			System.exit(-1);
		}
	}

	public void encode() {
		ImageLoaderInterface imgLoader = new SimpleImageLoader();
		BufferedImage img = imgLoader.getImage(inputPath);

		JpegInfo info = new JpegInfo(img);

		// [color component][tile y position][tile x position]
		Tile[][][] tiles = preprocessing(img);
		List<List<Tile<Double>>> transformedTiles = transform(tiles);
		List<List<Tile<Integer>>> quantiziedTiles = quantize(transformedTiles);
		ByteArrayOutputStream os = entropyCoding(quantiziedTiles);

		writer.writeHeaders(fileOutput, info);
		try {
			os.writeTo(fileOutput);
			writer.writeEOI(fileOutput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected Tile<Integer>[][][] preprocessing(BufferedImage image) {
		Integer[][][] subpixels = image2Array(image);
		Tile<Integer>[][][] tiles = tile(subpixels);
		return tiles;
	}

	public Integer[][][] image2Array(BufferedImage image) {
		ImageArrayConverterInterface image2Array = new ImageYCbCrArrayConverter();
		Integer[][][] subpixels = image2Array.convert(image);
		return subpixels;
	}

	public Tile<Integer>[][][] tile(Integer[][][] subpixels) {
		System.out.println("przed tile:"+subpixels.length + " " + subpixels[0].length + " "
				+ subpixels[0][0].length);// ###
		JpegTiler tiler = new JpegTiler();
		Tile<Integer>[][][] tiles = (Tile<Integer>[][][]) new Tile[3][][];
		int component = 0;
		// for Y[][], Cb[][], Cr[][]
		for (Integer[][] componentArray : subpixels)
			tiles[component++] = tiler.tile(componentArray);

		return tiles;
	}

	protected abstract List<List<Tile<Double>>> transform(
			Tile<Integer>[][][] tiles);

	protected List<List<Tile<Integer>>> quantize(List<List<Tile<Double>>> tiles) {
		List<List<Tile<Integer>>> out = new ArrayList<List<Tile<Integer>>>();
		for (List<Tile<Double>> t : tiles) {
			List<Tile<Integer>> comp = new ArrayList<Tile<Integer>>();

			Integer tableNo = 0;
			for (Tile<Double> component : t) {
				comp.add(quant.quantize(component, tableNo));
				tableNo = 1; // 2nd. and 3rd. component should be quantizied
								// with chrominance table
			}
			out.add(comp);
		}

		return out;

	}

	protected ByteArrayOutputStream entropyCoding(
			List<List<Tile<Integer>>> tiles) {
		return encoder.encodeImage(tiles);
	}

	public void setQuality(Integer quality_) {
		quality = quality_;
	}

	public Integer getQuality() {
		return quality;
	}

}
