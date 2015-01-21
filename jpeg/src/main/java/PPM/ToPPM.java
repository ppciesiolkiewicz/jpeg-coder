package PPM;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import ImageLoader.ImageLoaderInterface;
import ImageLoader.SimpleImageLoader;
import JpegMath.ImageToArrayConverter.ImageToArrayConverterInterface;
import JpegMath.ImageToArrayConverter.SimpleImageToArrayConverter;

/**
 * Created by krzaczek on 19.01.15.
 */
public class ToPPM {

	public static void saveAsPpm(String imagePath, String outputPath)
			throws IOException {
		ImageLoaderInterface loader = new SimpleImageLoader();
		BufferedImage bufferedImage = loader.getImage(imagePath);

		int height = bufferedImage.getHeight();
		int width = bufferedImage.getWidth();

		ImageToArrayConverterInterface rgb = new SimpleImageToArrayConverter();
		Integer[][][] img = rgb.convert(bufferedImage);
		byte[] data = new byte[3 * width * height];

		for (int row = 0; row < height; row++)
			for (int col = 0; col < width; col++) {
				data[3*(col + width * row)] = (byte) (img[0][row][col] & 0xff);
				data[3*(col + width * row)+1] = (byte) (img[1][row][col] & 0xff);
				data[3*(col + width * row)+2] = (byte) (img[2][row][col] & 0xff);
			}
		FileOutputStream fos = new FileOutputStream(outputPath);
		String header = "P6\n" + width + " " + height + "\n255\n";
		fos.write(header.getBytes());
		fos.write(data);
		fos.close();
	}
}
