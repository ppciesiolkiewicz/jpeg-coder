package JpegDecoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import JpegInterfaces.DecoderInterface;

public class JpegDecoder implements DecoderInterface {
	String outputPath;

	public JpegDecoder(String outputPath_) {
		outputPath = outputPath_;
	}

	public void decode(BufferedImage img) {
		int dotPosition = outputPath.indexOf(".");
		try {
			ImageIO.write(img, outputPath.substring(dotPosition+1, outputPath.length()), 
					new File(outputPath));
		} catch (IOException e) {
			System.err.println("Error while saving file");
			System.exit(-1);
		}
	}

}
