package jpeg;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import JpegMath.ImageToArrayConverter.ImageArrayConverterInterface;
import JpegMath.ImageToArrayConverter.ImageYCbCrArrayConverter;
import JpegMath.Image_ArrayConverter.Image_YCbCrArrayConverter;

public class Image_YCbCrArrayConverter_TC {

	@Test
	public void convertBothWaysGivesInput() {
		ImageArrayConverterInterface c = new ImageYCbCrArrayConverter();
		Integer[][][] input = new Integer[3][11][32];

		for (int i = 0; i < input.length; i++)
			for (int j = 0; j < input[0].length; j++)
				for (int k = 0; k < input[0][0].length; k++) {
					switch (i) {
					case 0:
						input[i][j][k] = 60;
						break;
					case 1:
						input[i][j][k] = 88;
						break;
					case 2:
						input[i][j][k] = 216;
						break;
					}
				}
		BufferedImage image = c.convert(input);

		try {
			File outputfile = new File("TEST.bmp");
			ImageIO.write(image, "bmp", outputfile);
		} catch (IOException e) {
			System.err.println("Error while saving file");
			System.exit(-1);
		}

		Integer[][][] restoredInput = c.convert(image);

		for (int i = 0; i < input.length; i++)
			for (int j = 0; j < input[0].length; j++)
				for (int k = 0; k < input[0][0].length; k++)
					assertEquals(input[i][j][k], restoredInput[i][j][k], 4);

	}

}
