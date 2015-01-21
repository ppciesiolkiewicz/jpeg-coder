package Jpeg2000Encoder;

import ImageLoader.ImageLoaderInterface;
import ImageLoader.SimpleImageLoader;
import JpegInterfaces.EncoderInterface;
import PPM.ToPPM;
import jj2000.j2k.encoder.CmdLnEncoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by krzaczek on 18.01.15.
 */
public class Jpeg2000Encoder implements EncoderInterface {
	String input;
	String output;
	Integer quality;

	public Jpeg2000Encoder(String input, String output, Integer quality) {
		super();
		this.input = input;
		this.output = output;
		this.quality = quality;
	}

	public void encode() {
	    String property = "java.io.tmpdir";
	    String tempDir = System.getProperty(property);
	    String tempPpm = tempDir + File.separator + "tmp.ppm";
	    
		try {
			ToPPM.saveAsPpm("example_bmp/example1.bmp", tempPpm);
		} catch (IOException e) {
			System.err.println("Error while converting");
			System.exit(-1);
		}
		String[] args = new String[] { "-i", tempPpm, "-o", output };
		CmdLnEncoder.main(args);
	}
}
