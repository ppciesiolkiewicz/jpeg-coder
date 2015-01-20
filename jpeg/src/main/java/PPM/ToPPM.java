package PPM;

import ImageLoader.ImageLoaderInterface;
import ImageLoader.SimpleImageLoader;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by krzaczek on 19.01.15.
 */
public class ToPPM {

	public static void saveAsPpm(String imagePath, String outputPath) throws IOException {
		ImageLoaderInterface loader = new SimpleImageLoader();
		BufferedImage bufferedImage = loader.getImage(imagePath);
		
		int height = bufferedImage.getHeight();
		int width = bufferedImage.getWidth();

		PrintWriter out = new PrintWriter(outputPath);
		out.println("P3");
		out.println(width + " " + height);
		out.println("255");
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				printPixelARGB(bufferedImage.getRGB(i,j), out);
			}
			out.println("");
			
		}
		out.close();
	}

	private static void printPixelARGB(int pixel , PrintWriter out) {
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		out.print(red + " " + green + " " + blue + " ");
	}

}
