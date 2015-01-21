package JpegMath.Image_ArrayConverter;

import java.awt.image.BufferedImage;

import JpegMath.ImageToArrayConverter.ImageArrayConverterInterface;

public class Image_YCbCrArrayConverter implements ImageArrayConverterInterface {

	// according to JFIF specification
	public Integer[][][] convert(BufferedImage image) {
		// Image_ArrayConverterInterface rgb = new SimpleImage_ArrayConverter();
		Integer[][][] imgArray = null;// rgb.convert(image);

		int width = image.getWidth();
		int height = image.getHeight();

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int r = imgArray[0][row][col], g = imgArray[1][row][col], b = imgArray[2][row][col];
				imgArray[0][row][col] = (int) (0 + 0.299 * r + 0.587 * g + 0.114 * b) - 128; // Y
				imgArray[1][row][col] = (int) (128 - 0.168736 * r - 0.331264
						* g + 0.5 * b) - 128; // Cb
				imgArray[2][row][col] = (int) (128 + 0.5 * r - 0.418688 * g - 0.081312 * b) - 128; // Cr
			}
		}

		return imgArray;
	}

	public BufferedImage convert(Integer[][][] arr) {
		BufferedImage img = new BufferedImage(arr[0][0].length, arr[0].length,
				BufferedImage.TYPE_INT_ARGB);

		for (int row = 0; row < img.getHeight(); row++) {
			for (int col = 0; col < img.getWidth(); col++) {
				int Y = arr[0][row][col], Cb = arr[1][row][col], Cr = arr[2][row][col];
				System.out.println(Y+" "+Cb+" "+Cr);
				int r = (int) (Y + 128 + 1.402 * (Cr));
				int g = (int) (Y + 128 - 0.34414 * (Cb) - 0.71414 * (Cr));
				int b = (int) (Y + 128 + 1.772 * (Cb));
				// System.out.println(Y + " " + Cb + " " + Cr);
				// System.out.println(r+" "+g+" "+b);
				// System.out.println(Integer.toBinaryString((-11)&0xff));
				// System.out.println(Integer.toBinaryString((-11)));

				int rgb = ((r << 24) & 0xff) | ((g << 16) & 0xff)
						| ((b << 8) & 0xff);
				img.setRGB(col, row, rgb);
			}
		}

		return img;
	}
}
