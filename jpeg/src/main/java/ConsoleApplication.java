import java.awt.image.BufferedImage;
import java.io.File;
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
		
	}

	public void run(ArgInfo info) {
		// TODO Auto-generated method stub
		File inputFile = new File(info.input);
		
		imgLoader = new DevelopTimeImageLoader();
		BufferedImage img = imgLoader.getImage(inputFile);
		JpegEncoderDecoderFactory factory = 
				new JpegEncoderDecoderFactory(info.quality, info.output);
		AbstractJpegEncoder enc = factory.getEncoder();
		enc.encode(img);
	}

}
