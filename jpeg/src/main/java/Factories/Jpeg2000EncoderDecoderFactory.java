package Factories;


import JpegDecoder.DecoderInterface;
import JpegEncoder.EncoderInterface;

public class Jpeg2000EncoderDecoderFactory implements AbstractJpegEncoderDecoderFactory {
	Integer quality;
	String outputPath;
	
	public Jpeg2000EncoderDecoderFactory(Integer quality_, String outputPath_) {
		quality = quality_;
		outputPath = outputPath_;
	}
	public EncoderInterface getEncoder() {
		throw new RuntimeException("not implemented");
		//return null;
	}

	public DecoderInterface getDecoder() {
		throw new RuntimeException("not implemented");
		//return null;
	}
}
