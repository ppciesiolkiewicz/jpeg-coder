package Jpeg2000Decoder;

import JpegInterfaces.DecoderInterface;
import JpegInterfaces.EncoderInterface;
import jj2000.j2k.decoder.CmdLnDecoder;
import jj2000.j2k.encoder.CmdLnEncoder;

import java.awt.image.BufferedImage;

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

	public void decode(BufferedImage img) {
		String[] args = new String[] { "-i", input, "-o", output };
		CmdLnDecoder.main(args);
	}

}
