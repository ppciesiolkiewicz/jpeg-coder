package Factories;

import JpegDecoder.AbstractJpegDecoder;
import JpegEncoder.AbstractJpegEncoder;
import JpegEncoder.JpegEncoder;

public class JpegEncoderDecoderFactory implements AbstractJpegEncoderDecoderFactory {
	Integer quality;
	public JpegEncoderDecoderFactory(Integer quality_) {
		quality = quality_;
	}
	public AbstractJpegEncoder getEncoder() {
		return new JpegEncoder(quality);
	}

	public AbstractJpegDecoder getDecoder() {
		// TODO Auto-generated method stub
		return null;
	}

}
