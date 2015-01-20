package Factories;

import JpegInterfaces.DecoderInterface;
import JpegInterfaces.EncoderInterface;

public interface AbstractJpegEncoderDecoderFactory {   
	public abstract EncoderInterface getEncoder();
	public abstract DecoderInterface getDecoder();
}
