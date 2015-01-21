package jpeg;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import ArgParser.ArgInfo;
import DataObjects.Tile;
import Factories.JpegEncoderDecoderFactory;
import JpegDecoder.JpegDecoder;
import JpegEncoder.JpegEncoder;

public class Jpeg_Decoder_inverts_Encoder_TC {
	private JpegEncoder enc;
	private JpegDecoder dec;


	@Before
	public void setUp() {
		ArgInfo info = new ArgInfo();
		info.input = "example_bmp/small.bmp";
		JpegEncoderDecoderFactory f = new JpegEncoderDecoderFactory(info);
		dec = (JpegDecoder) f.getDecoder();
		
		info.input = "example_bmp/small.jpg";
		f = new JpegEncoderDecoderFactory(info);	
		enc = (JpegEncoder) f.getEncoder();
	}
	
	@Test
	public void simpleTiling() {

		
		Integer[][][] subpixels = new Integer[3][16][8];
		Tile<Integer>[][][] tiles = enc.tile(subpixels);
		dec.imgHeight = 16;
		dec.imgWidth = 8;
		Integer[][][] restoredSubpixels = dec.detile(tiles);
		
		assertEquals(subpixels.length, restoredSubpixels.length);
		assertEquals(subpixels[0].length, restoredSubpixels[0].length);
		assertEquals(subpixels[0][0].length, restoredSubpixels[0][0].length);
		
		for(int i = 0; i < subpixels.length; i++)
			for(int j = 0; j < subpixels[0].length; j++)
				for(int k = 0; k < subpixels[0][0].length; k++)
					assertEquals(subpixels[i][j][k], restoredSubpixels[i][j][k]);

		
	}
	
	@Test
	public void arrayImageConversion() {
		Integer[][][] imgArr = new Integer[3][10][20];
		
		for(int i = 0; i < imgArr.length; i++)
			for(int j = 0; j < imgArr[0].length; j++)
				for(int k = 0; k < imgArr[0][0].length; k++) {
					imgArr[i][j][k] = i+k+j;
				}
		
		BufferedImage image = dec.array2Image(imgArr);
		Integer[][][] restoredImgArr = enc.image2Array(image);
		
		assertEquals(imgArr.length, restoredImgArr.length);
		assertEquals(imgArr[0].length, restoredImgArr[0].length);
		assertEquals(imgArr[0][0].length, restoredImgArr[0][0].length);
		
		for(int i = 0; i < imgArr.length; i++)
			for(int j = 0; j < imgArr[0].length; j++)
				for(int k = 0; k < imgArr[0][0].length; k++) {
					//System.out.println(imgArr[i][j][k] +" "+ restoredImgArr[i][j][k]);
					assertEquals(imgArr[i][j][k], restoredImgArr[i][j][k],5);
				}

		
	}

}
