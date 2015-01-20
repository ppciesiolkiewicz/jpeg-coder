package Factories;

import ArgParser.ArgInfo;
import Jpeg2000Decoder.Jpeg2000Decoder;
import Jpeg2000Encoder.Jpeg2000Encoder;
import JpegInterfaces.DecoderInterface;
import JpegInterfaces.EncoderInterface;

public class Jpeg2000EncoderDecoderFactory implements
		AbstractJpegEncoderDecoderFactory {
	Integer quality;
	String outputPath;
	String inputPath;

	public Jpeg2000EncoderDecoderFactory(ArgInfo info) {
		quality = info.quality;
		outputPath = info.output;
		inputPath = info.input;
	}

	public EncoderInterface getEncoder() {
		return new Jpeg2000Encoder(inputPath, outputPath, quality);
	}

	public DecoderInterface getDecoder() {
		return new Jpeg2000Decoder(inputPath, outputPath, quality);
	}
}
