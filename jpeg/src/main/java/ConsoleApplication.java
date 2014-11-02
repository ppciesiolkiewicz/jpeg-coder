import java.awt.image.BufferedImage;
import java.util.Iterator;

import DataObjects.Tile;
import Factories.AbstractJpegEncoderDecoderFactory;
import ImageLoader.DevelopTimeImageLoader;
import ImageLoader.ImageLoaderInterface;

public class ConsoleApplication implements Application {
	private ImageLoaderInterface imgLoader;
	private AbstractJpegEncoderDecoderFactory jpegAlgorithmFactory;

	public void run() {
		int a = -65536;
		System.out.println(Integer.toBinaryString(a));
		for(int i = 0 ; i < 32; i++)
		System.out.println(Integer.toBinaryString(a<<i));
		
		imgLoader = new DevelopTimeImageLoader();
		BufferedImage img = imgLoader.getImage(null);

		JpegMath.ImageToArrayConverter2.ImageToArrayConverterInterface c1 = new JpegMath.ImageToArrayConverter2.FastImageToArrayConverter();
		int[][] imageArray1 = c1.convert(img);
		for (int[] ii : imageArray1) {
			for (int i : ii) {
				//System.out.print(i + " ");
				System.out.print(Integer.toBinaryString(i) + " ");
			}
			System.out.println();

		}

	}

}
