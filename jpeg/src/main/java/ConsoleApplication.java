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

		JpegMath.ImageToArrayConverter.ImageToArrayConverterInterface c = new JpegMath.ImageToArrayConverter.SimpleImageToArrayConverter();
		int[][][] imageArray = c.convert(img);
		for (int row = 0; row < imageArray[0].length; row++) {
			for (int col = 0; col < imageArray[0][0].length; col++) {
				System.out.print("(");
				for (int component = 0; component < 3; component++)
					System.out.print(imageArray[component][row][col] + " ");
				System.out.print(")\t");
			}
			System.out.print("\n");
		}

	}

}
