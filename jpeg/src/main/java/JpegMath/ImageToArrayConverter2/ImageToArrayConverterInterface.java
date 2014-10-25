package JpegMath.ImageToArrayConverter2;

import java.awt.image.BufferedImage;

public interface ImageToArrayConverterInterface {
	//TODO public byte[][][] = [Components][y][x]
	public int[][] convert(BufferedImage image);
	
}
