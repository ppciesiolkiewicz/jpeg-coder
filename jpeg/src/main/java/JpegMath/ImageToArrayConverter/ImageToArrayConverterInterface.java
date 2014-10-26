package JpegMath.ImageToArrayConverter;

import java.awt.image.BufferedImage;

public interface ImageToArrayConverterInterface {
	//[Components][y][x], byte is always signed [problem?]
	public int[][][] convert(BufferedImage image);
	
}
