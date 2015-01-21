package Application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ArgParser.ArgInfo;
import Factories.AbstractJpegEncoderDecoderFactory;
import Factories.Jpeg2000EncoderDecoderFactory;
import Factories.JpegEncoderDecoderFactory;
import ImageLoader.SimpleImageLoader;
import ImageLoader.ImageLoaderInterface;
import JpegInterfaces.DecoderInterface;
import JpegInterfaces.EncoderInterface;

public class ConsoleApplication implements Application {
	private AbstractJpegEncoderDecoderFactory jpegAlgorithmFactory;

	public void run() {
		throw new RuntimeException("Cannot do this");
	}

	public void run(ArgInfo info) {
		System.out.println(info.toString());
		AbstractJpegEncoderDecoderFactory factory = null;
		
		if( info.isJpeg() ) {
			System.out.println("JPEG");
			factory = new JpegEncoderDecoderFactory(info);
		}
		else if( info.isJpeg2000() ) {
			System.out.println("JPEG2000");
			factory = new Jpeg2000EncoderDecoderFactory(info);
		}
		
		if( info.doEncode() ) {
			System.out.println("Encoding");
			EncoderInterface enc = factory.getEncoder();
			enc.encode();
		}
		else {
			System.out.println("Decoding");
			DecoderInterface dec = factory.getDecoder();
			try {
				dec.decode();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Finished sucessfully");
	}

}
