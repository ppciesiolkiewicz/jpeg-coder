import java.awt.image.BufferedImage;
import java.util.Iterator;

import DataObjects.Tile;
import Factories.AbstractJpegEncoderDecoderFactory;
import ImageLoader.DevelopTimeImageLoader;
import ImageLoader.ImageLoaderInterface;
import JpegMath.ImageToArrayConverter.FastImageToArrayConverter;
import JpegMath.ImageToArrayConverter.ImageToArrayConverterInterface;

public class ConsoleApplication implements Application {
	private ImageLoaderInterface imgLoader;
	private AbstractJpegEncoderDecoderFactory jpegAlgorithmFactory;

	public void run() {
		imgLoader = new DevelopTimeImageLoader();
		BufferedImage img = imgLoader.getImage();

		ImageToArrayConverterInterface c = new FastImageToArrayConverter();
		int[][] imageArray = c.convert(img);
		for (int[] ii : imageArray) {
			for (int i : ii)
				System.out.print(Integer.toBinaryString(i) + " ");
			System.out.println();

		}
	}

}
