package Factories;

import JpegDecoder.DecoderInterface;
import JpegDecoder.JpegDecoder;
import JpegEncoder.AbstractJpegEncoder;
import JpegEncoder.EncoderInterface;
import JpegEncoder.JpegEncoder;

public class JpegEncoderDecoderFactory implements AbstractJpegEncoderDecoderFactory {
	Integer quality;
	String outputPath;
	
	public JpegEncoderDecoderFactory(Integer quality_, String outputPath_) {
		quality = quality_;
		outputPath = outputPath_;
	}
	public EncoderInterface getEncoder() {
		return new JpegEncoder(quality, outputPath);
	}

	public DecoderInterface getDecoder() {
		return new JpegDecoder(outputPath);
	}

}
