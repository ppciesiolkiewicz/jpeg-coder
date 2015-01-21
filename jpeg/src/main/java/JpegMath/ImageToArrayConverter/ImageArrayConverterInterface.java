package JpegMath.ImageToArrayConverter;

import java.awt.image.BufferedImage;

public interface ImageArrayConverterInterface {
	//[Components][y][x], 
	public Integer[][][] convert(BufferedImage image);
	public BufferedImage convert(Integer[][][] arr);
	
}
