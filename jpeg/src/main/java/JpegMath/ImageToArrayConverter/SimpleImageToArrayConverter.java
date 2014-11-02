package JpegMath.ImageToArrayConverter;

import java.awt.image.BufferedImage;


/* getRGB() ->
 * 11111111111111110000000000000000 R
 * 11111111000000001111111100000000 G
 * 11111111000000000000000011111111 B
 * 11111111111111111111111111111111 Black
 * 11111111000000000000000000000000 White
 * 
 * [0=r,1=g,2=b][row][col]
 */
public class SimpleImageToArrayConverter implements ImageToArrayConverterInterface {
	
	public Integer[][][] convert(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		Integer[][][] result = new Integer[3][height][width];

		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int rgb = image.getRGB(col, row);
				for(int component = 0; component < 3; component++)
					result[2-component][row][col] =  (rgb>>(component)*8 & 0xff);
			}
		}

		return result;
	}
}
