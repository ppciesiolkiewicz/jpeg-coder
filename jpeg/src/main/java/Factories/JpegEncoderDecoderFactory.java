package Factories;

import JpegDecoder.AbstractJpegDecoder;
import JpegEncoder.AbstractJpegEncoder;
import JpegEncoder.JpegEncoder;

public class JpegEncoderDecoderFactory implements AbstractJpegEncoderDecoderFactory {
	Integer quality;
	String outputPath;
	
	public JpegEncoderDecoderFactory(Integer quality_, String outputPath_) {
		quality = quality_;
		outputPath = outputPath_;
	}
	public AbstractJpegEncoder getEncoder() {
		return new JpegEncoder(quality, outputPath);
	}

	public AbstractJpegDecoder getDecoder() {
		throw new RuntimeException("not implemented");
		//return null;
	}

}
