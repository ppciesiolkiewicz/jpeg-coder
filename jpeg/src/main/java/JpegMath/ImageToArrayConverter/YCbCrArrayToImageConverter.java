package JpegMath.ImageToArrayConverter;

import java.awt.image.BufferedImage;

public class YCbCrArrayToImageConverter {

	// according to JFIF specification
	public BufferedImage convert(Integer[][][] arr) {
		BufferedImage img = new BufferedImage(arr[0][0].length, arr[0].length,
				BufferedImage.TYPE_INT_RGB);

		for (int row = 0; row < img.getHeight(); row++)
			for (int col = 0; col < img.getWidth(); col++) {
				int y = arr[0][row][col], cB = arr[1][row][col], cR = arr[2][row][col];
				int r = (int) (1.0 * y + 0 * cB + 1.402 * cR) + 127;
				int g = (int) (1.0 * y - 0.344136 * cB - 0.714136 * cR) + 127;
				int b = (int) (1.0 * y + 1.772 * cB + 0 * cR) + 127;

				int rgb = (r & 0xff) + (g >> 8 & 0xff) * +(b >> 16 & 0xff);
				img.setRGB(col, row, rgb);

			}

		return img;
	}
}
