package Factories;

import ArgParser.ArgInfo;
import JpegDecoder.JpegDecoder;
import JpegEncoder.AbstractJpegEncoder;
import JpegEncoder.JpegEncoder;
import JpegInterfaces.DecoderInterface;
import JpegInterfaces.EncoderInterface;

public class JpegEncoderDecoderFactory implements AbstractJpegEncoderDecoderFactory {
	Integer quality;
	String outputPath;
	String inputPath;
	
	public JpegEncoderDecoderFactory(ArgInfo info) {
		quality = info.quality;
		outputPath = info.output;
		inputPath = info.input;
	}
	
	public EncoderInterface getEncoder() {
		return new JpegEncoder(inputPath, outputPath, quality);
	}

	public DecoderInterface getDecoder() {
		return new JpegDecoder(inputPath, outputPath);
	}

}
