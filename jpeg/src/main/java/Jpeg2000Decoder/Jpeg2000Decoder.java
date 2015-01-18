package Jpeg2000Decoder;

import JpegDecoder.DecoderInterface;
import JpegEncoder.EncoderInterface;
import jj2000.j2k.decoder.CmdLnDecoder;
import jj2000.j2k.encoder.CmdLnEncoder;

import java.awt.image.BufferedImage;

/**
 * Created by krzaczek on 18.01.15.
 */
public class Jpeg2000Decoder implements DecoderInterface {

	@Override public BufferedImage decode(BufferedImage img) {
		String[] args =  new String[]{"-i", "/home/krzaczek/PWr/JPEG_coder_decoder/jpeg/src/main/java/Jpeg2000Encoder/relax.jp2", "-o", "/home/krzaczek/Desktop/tmp.pgm", "-rate", "2"};
		new CmdLnDecoder(args);
		return null;
	}

	public static void main(String[] args) {
		new Jpeg2000Decoder().decode(null);
	}
}
