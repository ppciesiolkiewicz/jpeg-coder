package Factories;

import JpegDecoder.DecoderInterface;
import JpegEncoder.EncoderInterface;

public interface AbstractJpegEncoderDecoderFactory {   
	public abstract EncoderInterface getEncoder();
	public abstract DecoderInterface getDecoder();
}
