package Factories;


import javax.management.RuntimeErrorException;

import JpegDecoder.AbstractJpegDecoder;
import JpegEncoder.AbstractJpegEncoder;
import JpegEncoder.JpegEncoder;

public class Jpeg2000EncoderDecoderFactory implements AbstractJpegEncoderDecoderFactory {
	Integer quality;
	String outputPath;
	
	public Jpeg2000EncoderDecoderFactory(Integer quality_, String outputPath_) {
		quality = quality_;
		outputPath = outputPath_;
	}
	public AbstractJpegEncoder getEncoder() {
		throw new RuntimeException("not implemented");
		//return null;
	}

	public AbstractJpegDecoder getDecoder() {
		throw new RuntimeException("not implemented");
		//return null;
	}
}
