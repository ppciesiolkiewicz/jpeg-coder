package JpegDecoder;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import JpegEncoder.JpegFileWriter;
import JpegMath.Coders.HuffmanCoding;
import JpegMath.Quantiziers.JpegUniformQuantizier;

public class JpegDecoder implements DecoderInterface {

	FileOutputStream fileOutput;
	String outputPath;

	public JpegDecoder(String outputPath_) {
		outputPath = outputPath_;

		try {
			fileOutput = new FileOutputStream(outputPath);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot create output file");
			System.exit(-1);
		}
	}

	public void decode(BufferedImage img) {

	}

}
