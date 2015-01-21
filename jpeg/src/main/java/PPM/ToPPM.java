package PPM;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

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

		PrintWriter out = new PrintWriter(outputPath, "UTF-8");
		out.println("P3");
		out.println(width + " " + height);
		out.println("255");
		ImageToArrayConverterInterface rgb = new SimpleImageToArrayConverter();
		Integer[][][] img = rgb.convert(bufferedImage);
		
		for (int row = 0; row < height; row++)
			for (int col = 0; col < width; col++) {
				int r = img[0][row][col], g = img[1][row][col], b = img[2][row][col];
				printPixelARGB(r,g,b, out);
		}

		out.close();
	}
	
	private static void printPixelARGB(int r, int g, int b, PrintWriter out) {
		out.print(r + " " + g + " " + b + " ");
	}
}
