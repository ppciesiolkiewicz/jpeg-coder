package JpegMath.ImageToArrayConverter2;

import java.awt.image.BufferedImage;

import DataObjects.Tile;



public class ImageToYCbCrArray implements ImageToArrayConverterInterface {
	public int[][] convert(BufferedImage image) {
		ImageToArrayConverterInterface conv= new FastImageToArrayConverter();
		int[][] image_array = conv.convert(image);
		
		
		return image_array;
	}

}
