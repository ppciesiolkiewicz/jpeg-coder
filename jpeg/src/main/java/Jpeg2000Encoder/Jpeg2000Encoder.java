package Jpeg2000Encoder;

import JpegInterfaces.EncoderInterface;
import jj2000.j2k.encoder.CmdLnEncoder;

import java.awt.image.BufferedImage;

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

	public void encode(BufferedImage img) {
		String[] args = new String[] { "-i", input, "-o", output };
		CmdLnEncoder.main(args);
	}
}
