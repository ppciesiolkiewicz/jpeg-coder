package JpegEncoder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DataObjects.JpegInfo;
import DataObjects.Tile;
import JpegMath.Coders.HuffmanCoding;
import JpegMath.ImageToArrayConverter.ImageToArrayConverterInterface;
import JpegMath.ImageToArrayConverter.ImageToYCbCrArray;
import JpegMath.Quantiziers.JpegUniformQuantizier;
import JpegMath.Tilers.JpegTiler;
import JpegMath.Tilers.TilerInterface;

public abstract class AbstractJpegEncoder implements EncoderInterface {
	Integer quality;
	FileOutputStream fileOutput;
	HuffmanCoding encoder;
	WriteJpeg writer;
	JpegUniformQuantizier quant;

	public AbstractJpegEncoder(Integer quality_) {
		quality = quality_;
		try {
			fileOutput = new FileOutputStream("output.jpg");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		encoder = new HuffmanCoding();
		quant = new JpegUniformQuantizier(quality);
		writer = new WriteJpeg(quant,encoder);
	}

	public BufferedImage encode(BufferedImage img) {
		JpegInfo info = new JpegInfo(img);
		writer.writeHeaders(fileOutput, info);

		// [color component][tile y position][tile x position]
		Tile[][][] tiles = preprocessing(img);
		System.out.println(tiles[0][0][0].toString() + "\n\n\n");
		List<List<Tile<Double>>> transformedTiles = transform(tiles);
		System.out
				.println(transformedTiles.get(0).get(0).toString() + "\n\n\n");
		List<List<Tile<Integer>>> quantiziedTiles = quantize(transformedTiles);
		System.out.println(quantiziedTiles.get(0).get(0).toString() + "\n\n\n");
		ByteArrayOutputStream os = entropyCoding(quantiziedTiles);

		try {
			os.writeTo(fileOutput);
			writer.writeEOI(fileOutput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Iterator<EncodedTile<Binary>> it = encodedTiles.iterator();
		// while(it.hasNext())
		// System.out.println(it.next().tile.toString()+"\n");
		// this image part probably will not be used
		// img = compose(tiles);
		return img;
	}

	protected Tile<Integer>[][][] preprocessing(BufferedImage image) {
		Integer[][][] subpixels = image2Array(image);
		Tile<Integer>[][][] tiles = tile(subpixels);
		return tiles;
	}

	private Integer[][][] image2Array(BufferedImage image) {
		ImageToArrayConverterInterface image2Array = new ImageToYCbCrArray();
		Integer[][][] subpixels = image2Array.convert(image);
		return subpixels;
	}

	protected Tile<Integer>[][][] tile(Integer[][][] subpixels) {
		TilerInterface<Integer> tiler = new JpegTiler();
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
				tableNo = 1; //2nd. and 3rd. component should be quantizied with chrominance table
			}

			out.add(comp);
		}

		return out;

	}

	protected ByteArrayOutputStream entropyCoding(
			List<List<Tile<Integer>>> tiles) {
		return encoder.encodeImage(tiles);
	}

	// TODO ++JpegInfo?
	protected BufferedImage compose(Tile[][][] tiles) {
		/*
		 * WritableRaster raster = mergeToRaster(tiles); addHeader(raster);
		 * //dunnos dunnos dunnos
		 */
		return null;
	}

	public void setQuality(Integer quality_) {
		quality = quality_;
	}

	public Integer getQuality() {
		return quality;
	}
	
}
