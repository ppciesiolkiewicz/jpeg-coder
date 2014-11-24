import java.awt.image.BufferedImage;
import java.util.Iterator;

import DataObjects.Tile;
import Factories.AbstractJpegEncoderDecoderFactory;
import Factories.Jpeg2000EncoderDecoderFactory;
import Factories.JpegEncoderDecoderFactory;
import ImageLoader.DevelopTimeImageLoader;
import ImageLoader.ImageLoaderInterface;
import JpegEncoder.AbstractJpegEncoder;

public class ConsoleApplication implements Application {
	private ImageLoaderInterface imgLoader;
	private AbstractJpegEncoderDecoderFactory jpegAlgorithmFactory;

	public void run() {
		imgLoader = new DevelopTimeImageLoader();
		BufferedImage img = imgLoader.getImage(null);
		JpegEncoderDecoderFactory factory = new JpegEncoderDecoderFactory(100);
		AbstractJpegEncoder enc = factory.getEncoder();
		enc.encode(img);
	}

}
