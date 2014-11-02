package JpegEncoder;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import DataObjects.Tile;
import JpegMath.Coders.CodingInterface;
import JpegMath.ImageToArrayConverter.ImageToArrayConverterInterface;
import JpegMath.ImageToArrayConverter.ImageToYCbCrArray;
import JpegMath.Quantiziers.QuantizierInterface;
import JpegMath.Tilers.JpegTiler;
import JpegMath.Tilers.TilerInterface;

public abstract class AbstractJpegEncoder implements EncoderInterface {
	int quality;

	public AbstractJpegEncoder(int quality_) {
		quality = quality_;
	}

	public BufferedImage encode(BufferedImage img) {
		//[color component][tile y position][tile x position]
		Tile[][][] tiles = preprocessing(img);
		tiles = transform(tiles);
		tiles = quantize(tiles);
		tiles = encode(tiles);
		
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

	protected abstract Tile<Float>[][][] transform(Tile<Integer>[][][] tiles);

	protected Tile<Integer>[][][] quantize(Tile<Float>[][][] tiles) {
		QuantizierInterface quant = null;
		return null;

	}

	protected Tile<Integer>[][][] encode(Tile<Integer>[][][] tiles) {
		CodingInterface encoder = null;
		return null;
	}

	//++JpegInfo?
	protected BufferedImage compose(Tile[][][] tiles) {
		/*
		 * WritableRaster raster = mergeToRaster(tiles); addHeader(raster);
		 * //dunnos dunnos dunnos
		 */
		return null;
	}


	public void setQuality(int quality_) {
		quality = quality_;
	}

	public int getQuality() {
		return quality;
	}
}
