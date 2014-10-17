import java.awt.image.BufferedImage;

import Factories.AbstractJpegEncoderDecoderFactory;
import ImageLoader.ImageLoaderInterface;
import ImageLoader.TestImageLoader;


public class ConsoleApplication implements Application {
	private ImageLoaderInterface imgLoader;
	private AbstractJpegEncoderDecoderFactory jpegAlgorithmFactory;
	
	
	
	public void run() {
		imgLoader = new TestImageLoader();
		BufferedImage img = imgLoader.getImage();
	}
	
}
