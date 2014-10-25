package JpegMath.ImageToArrayConverter;

import java.awt.image.BufferedImage;

public interface ImageToArrayConverterInterface {
	//[Components][y][x]
	public int[][][] convert(BufferedImage image);
	
}
