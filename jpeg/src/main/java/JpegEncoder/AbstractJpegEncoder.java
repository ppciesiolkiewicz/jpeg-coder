package JpegEncoder;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import DataObjects.Tile;
import JpegMath.Coders.CodingInterface;
import JpegMath.Quantiziers.QuantizierInterface;
import JpegMath.Tilers.Tiler;

public abstract class AbstractJpegEncoder {
	int quality;

	public AbstractJpegEncoder(int quality_) {
		quality=quality_;
	}
	
	public BufferedImage encode(BufferedImage img) {
		Tile[][] tiles = preprocessing(img);
		transform(tiles);
		quantize(tiles);
		code(tiles);
		img = compose(tiles);
		return img;
	}

	protected Tile[][] preprocessing(BufferedImage img) {
		Tile[][] tiles = tile(img);
		transformTileColor(tiles);
		return null;
	}

	protected Tile[][] tile(BufferedImage img) {
		Tiler tiler;
		return null;
	}
	
	protected void transformTileColor(Tile[][] tiles) {
		
	}
	
	protected abstract void transform(Tile[][] tiles);
	
	
	protected void quantize(Tile[][] tiles) {
		QuantizierInterface quant;
		
	}

	protected void code(Tile[][] tiles) {
		CodingInterface coder;
			
	}
	

	protected BufferedImage compose(Tile[][] tiles) {
		/*WritableRaster raster = mergeToRaster(tiles);
		addHeader(raster); //dunnos*/
		return null;
	}
	/*
	private WritableRaster mergeToRaster(Tile[][] tiles) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	private void addHeader(WritableRaster raster) {
		// TODO Auto-generated method stub
		
	}

	public void setQuality(int quality_) {
		quality = quality_;
	}
	
	public int getQuality() {
		return quality;	
	}
}
