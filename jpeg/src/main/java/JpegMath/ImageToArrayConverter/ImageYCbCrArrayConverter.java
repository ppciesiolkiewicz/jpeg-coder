package JpegMath.ImageToArrayConverter;

import java.awt.image.BufferedImage;

import DataObjects.Tile;



public class ImageYCbCrArrayConverter implements ImageArrayConverterInterface {

	//according to JFIF specification
	public Integer[][][] convert(BufferedImage image) {
		ImageArrayConverterInterface rgb = new SimpleImageToArrayConverter();
		Integer[][][] img = rgb.convert(image);
		
		int width = image.getWidth();
		int height = image.getHeight();

		
		for (int row = 0; row < height; row++)
			for (int col = 0; col < width; col++) {
				int r = img[0][row][col], g = img[1][row][col], b = img[2][row][col];
				img[0][row][col] = (int)(0 + 0.299*r + 0.587*g + 0.114*b)-127; //Y
				img[1][row][col] = (int) (128 - 0.168736*r - 0.331264*g +0.5*b)-127; //Cb
				img[2][row][col] = (int) (128 + 0.5*r - 0.418618*g - 0.081312*b )-127; //Cr
		}
		
		return img;
	}

	public BufferedImage convert(Integer[][][] arr) {
		// TODO Auto-generated method stub
		return null;
	}

}
