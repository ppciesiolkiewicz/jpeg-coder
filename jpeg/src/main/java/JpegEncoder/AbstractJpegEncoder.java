package JpegEncoder;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import DataObjects.EncodedTile;
import DataObjects.Tile;
import JpegMath.Coders.CodingInterface;
import JpegMath.Coders.HuffmanCoding;
import JpegMath.ImageToArrayConverter.ImageToArrayConverterInterface;
import JpegMath.ImageToArrayConverter.ImageToYCbCrArray;
import JpegMath.Quantiziers.JpegUniformQuantizier;
import JpegMath.Quantiziers.QuantizierInterface;
import JpegMath.Tilers.JpegTiler;
import JpegMath.Tilers.TilerInterface;

public abstract class AbstractJpegEncoder implements EncoderInterface {
	Double quality;

	public AbstractJpegEncoder(Double quality_) {
		quality = quality_;
	}

	public BufferedImage encode(BufferedImage img) {
		//[color component][tile y position][tile x position]
		Tile[][][] tiles = preprocessing(img);
		List<Tile<Double>> transformedTiles = transform(tiles);
		List<EncodedTile<Integer>> quantiziedTiles = quantize(transformedTiles);
		List<EncodedTile<Integer>> encodedTiles = entropyCoding(quantiziedTiles);
		
		//this image part probably will not be used
		img = compose(tiles);
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
		Tile<Integer>[][][] tiles = (Tile<Integer>[][][])new Tile[3][][];
		int component = 0;
		// for Y[][], Cb[][], Cr[][]
		for (Integer[][] componentArray : subpixels)
			tiles[component++] = tiler.tile(componentArray);
		
		return tiles;
	}

	protected abstract List<Tile<Double>> transform(Tile<Integer>[][][] tiles);

	protected List<EncodedTile<Integer>> quantize(List<Tile<Double>> tiles) {
		JpegUniformQuantizier quant = new JpegUniformQuantizier(quality);
		
		List<EncodedTile<Integer>> out = new ArrayList<EncodedTile<Integer>>();
		for(Tile<Double> t : tiles)
			out.add(quant.quantize(t));
		
		return out;

	}

	protected List<EncodedTile<Integer>> entropyCoding(List<EncodedTile<Integer>> tiles) {
		HuffmanCoding encoder = new HuffmanCoding<Integer>();
		List<EncodedTile<Integer>> out = new ArrayList<EncodedTile<Integer>>();
		
		for(EncodedTile<Integer> t : tiles)
			out.add(encoder.encode(t));
		
		return null;
	}

	//TODO ++JpegInfo?
	protected BufferedImage compose(Tile[][][] tiles) {
		/*
		 * WritableRaster raster = mergeToRaster(tiles); addHeader(raster);
		 * //dunnos dunnos dunnos
		 */
		return null;
	}


	public void setQuality(Double quality_) {
		quality = quality_;
	}

	public Double getQuality() {
		return quality;
	}
}
