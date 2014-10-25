package JpegMath.ImageToArrayConverter2;

import java.awt.image.BufferedImage;


/*
 * 11111111111111110000000000000000 R
 * 11111111000000001111111100000000 G
 * 11111111000000000000000011111111 B
 * 11111111111111111111111111111111 Black
 * 11111111000000000000000000000000 White
 */
public class SimpleImageToArrayConverter implements ImageToArrayConverterInterface {
	
	public int[][] convert(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int[][] result = new int[height][width];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				result[row][col] = image.getRGB(col, row);
			}
		}

		return result;
	}
}
