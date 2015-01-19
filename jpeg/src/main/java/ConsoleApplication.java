import java.awt.image.BufferedImage;
import java.io.File;

import Factories.AbstractJpegEncoderDecoderFactory;
import Factories.Jpeg2000EncoderDecoderFactory;
import Factories.JpegEncoderDecoderFactory;
import ImageLoader.DevelopTimeImageLoader;
import ImageLoader.ImageLoaderInterface;
import JpegInterfaces.DecoderInterface;
import JpegInterfaces.EncoderInterface;

public class ConsoleApplication implements Application {
	private ImageLoaderInterface imgLoader;
	private AbstractJpegEncoderDecoderFactory jpegAlgorithmFactory;

	public void run() {
		
	}

	public void run(ArgInfo info) {
		File inputFile = new File(info.input);
		imgLoader = new DevelopTimeImageLoader();
		BufferedImage img = imgLoader.getImage(inputFile);
		
		AbstractJpegEncoderDecoderFactory factory = null;
		if( info.isJpeg() ) {
			factory = new JpegEncoderDecoderFactory(info.quality, info.output);
		}
		else if( info.isJpeg2000() ) {
			factory = new Jpeg2000EncoderDecoderFactory(info.quality, info.output);
		}
		
		if( info.doEncode() ) {
			EncoderInterface enc = factory.getEncoder();
			enc.encode(img);
		}
		else {
			DecoderInterface dec = factory.getDecoder();
			dec.decode(img);
		}
	}

}
