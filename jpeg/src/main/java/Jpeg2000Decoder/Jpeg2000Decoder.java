package Jpeg2000Decoder;

import ImageLoader.SimpleImageLoader;
import JpegInterfaces.DecoderInterface;
import PPM.ToPPM;
import jj2000.j2k.decoder.CmdLnDecoder;

import java.io.IOException;

/**
 * Created by krzaczek on 18.01.15.
 */
public class Jpeg2000Decoder implements DecoderInterface {
	String input;
	String output;
	Integer quality;

	public Jpeg2000Decoder(String input, String output, Integer quality) {
		super();
		this.input = input;
		this.output = output;
		this.quality = quality;
	}

	public void decode() throws IOException {
		String[] args = new String[] { "-i", "/tmp/tmp.ppm", "-o", output };
		CmdLnDecoder.main(args);
	}

}
