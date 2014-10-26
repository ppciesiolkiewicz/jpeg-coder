package JpegMath.ImageToArrayConverter;

import java.awt.image.BufferedImage;

import DataObjects.Tile;



public class ImageToYCbCrArray implements ImageToArrayConverterInterface {

	public int[][][] convert(BufferedImage image) {
		ImageToArrayConverterInterface rgb = new SimpleImageToArrayConverter();
		int[][][] img = rgb.convert(image);
	
		int width = image.getWidth();
		int height = image.getHeight();

		
		for (int row = 0; row < height; row++)
			for (int col = 0; col < width; col++) {
				int r = img[0][row][col], g = img[1][row][col], b = img[2][row][col];
				img[0][row][col] = (int)(0 + 0.299*r + 0.587*g + 0.114+b); //Y
				img[1][row][col] = (int) (128 - 0.168736*r - 0.331264*g +0.5*b); //Cb
				img[2][row][col] = (int) (128 + 0.5*r - 0.418618*g - 0.081312*b ); //Cr
				//TODO CHECK
		}
		
		return img;
	}

}
