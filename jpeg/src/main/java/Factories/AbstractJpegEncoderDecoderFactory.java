package Factories;

import JpegDecoder.AbstractJpegDecoder;
import JpegEncoder.AbstractJpegEncoder;

public interface AbstractJpegEncoderDecoderFactory {   
	public abstract AbstractJpegEncoder getEncoder();
	public abstract AbstractJpegDecoder getDecoder();
}
