package JpegDecoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ImageLoader.ImageLoaderInterface;
import ImageLoader.SimpleImageLoader;
import JpegInterfaces.DecoderInterface;

public class JpegDecoder implements DecoderInterface {
	String inputPath;
	String outputPath;

	public JpegDecoder(String inputPath_, String outputPath_) {
		outputPath = outputPath_;
	}

	public void decode() {
		File inputFile = new File(inputPath);
		ImageLoaderInterface imgLoader = new SimpleImageLoader();
		BufferedImage img = imgLoader.getImage(inputFile);
		
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
