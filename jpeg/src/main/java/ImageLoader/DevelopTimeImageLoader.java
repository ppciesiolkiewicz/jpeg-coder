package ImageLoader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import JpegMath.ImageToArrayConverter.FastImageToArrayConverter;
import JpegMath.ImageToArrayConverter.ImageToArrayConverterInterface;
import JpegMath.ImageToArrayConverter.SimpleImageToArrayConverter;

public class DevelopTimeImageLoader implements ImageLoaderInterface {

	public BufferedImage getImage() {
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File("example_bmp/example2.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageToArrayConverterInterface c = new FastImageToArrayConverter();
		int[][] imageArray = c.convert(img);
		for(int[] ii : imageArray) {
			for(int i : ii)
				System.out.print(Integer.toBinaryString(i)+" ");
			System.out.println();
		}

		return img;
	}

}
