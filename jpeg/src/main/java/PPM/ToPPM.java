package PPM;

import ImageLoader.SimpleImageLoader;
import org.omg.CORBA.portable.OutputStream;
import sun.nio.cs.StandardCharsets;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * Created by krzaczek on 19.01.15.
 */
public class ToPPM {

	public static void convert(BufferedImage bufferedImage) throws IOException {
		int height = bufferedImage.getHeight();
		int width = bufferedImage.getWidth();
		PrintWriter out = new PrintWgiriter("/tmp/tmp.ppm", "UTF-8");
		out.println("P3");
		out.println(width + " " + height);
		out.println("255");
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				printPixelARGB(bufferedImage.getRGB(j,i), out);
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
