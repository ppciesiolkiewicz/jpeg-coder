package Jpeg2000Encoder;

import JpegEncoder.EncoderInterface;
import jj2000.j2k.encoder.CmdLnEncoder;

import java.awt.image.BufferedImage;

/**
 * Created by krzaczek on 18.01.15.
 */
public class Jpeg2000Encoder implements EncoderInterface {
	public BufferedImage encode(BufferedImage img) {
		String[] args =  new String[]{"-i", "/home/krzaczek/PWr/JPEG_coder_decoder/jpeg/src/main/java/Jpeg2000Encoder/relax.jp2", "-o", "/home/krzaczek/Desktop/tmp.pgx"};
		new CmdLnEncoder(args);
		return null;
	}

	public static void main(String[] args) {
		new Jpeg2000Encoder().encode(null);
	}
}
