import java.awt.image.BufferedImage;

import Factories.AbstractJpegEncoderDecoderFactory;
import ImageLoader.DevelopTimeImageLoader;
import ImageLoader.ImageLoaderInterface;


public class ConsoleApplication implements Application {
	private ImageLoaderInterface imgLoader;
	private AbstractJpegEncoderDecoderFactory jpegAlgorithmFactory;
	
	
	
	public void run() {
		imgLoader = new DevelopTimeImageLoader();
		BufferedImage img = imgLoader.getImage();
	}
	
}
