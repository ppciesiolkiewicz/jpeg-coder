package PPM;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by krzaczek on 19.01.15.
 */
public class ToPPM {

	public static void convert(BufferedImage bufferedImage) throws IOException {
		byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
		int height = bufferedImage.getHeight();
		int width = bufferedImage.getWidth();

		PrintWriter out = new PrintWriter("tmp.ppm");
		out.println("3P");
		out.println(width + " " + height);
		for (int i = 0; i < height; i++) {
			String bitline = "";
			for (int j = 0; j < width * 3; j++) {
				bitline += pixels[i * width * 3 + j] + " ";
			}
			out.println(bitline.trim());
		}
	}
}
