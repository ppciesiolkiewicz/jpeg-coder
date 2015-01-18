package Jpeg2000Encoder;

import JpegEncoder.EncoderInterface;
import jj2000.j2k.encoder.CmdLnEncoder;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by krzaczek on 18.01.15.
 */
public class Jpeg2000Encoder implements EncoderInterface {
	@Override public BufferedImage encode(BufferedImage img) {
		String[] args =  new String[]{"arg"};
		new CmdLnEncoder(args);
		return null;
	}
}
