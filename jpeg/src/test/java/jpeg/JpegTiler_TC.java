package jpeg;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import DataObjects.Tile;
import JpegMath.Tilers.JpegTiler;
import JpegMath.Tilers.TilerInterface;

public class JpegTiler_TC {

	@Test
	public void onlyOneTile() {
		TilerInterface<Integer> tiler = new JpegTiler();
		Integer[][] pixels = new Integer[8][8];
		
		for(int i = 0; i < pixels.length; i++)
			for(int j = 0; j < pixels[0].length; j++)
				pixels[i][j] = j+i*pixels.length;
		Tile<Integer>[][] tiles = tiler.tile(pixels);
		
		assertEquals(1, tiles.length);
		assertEquals(1, tiles[0].length);
		
		assertArrayEquals(pixels, tiles[0][0].toMatrix());
	}

	
	@Test
	public void moreTiles() {
		TilerInterface<Integer> tiler = new JpegTiler();
		Integer[][] pixels = new Integer[16][16];
		
		for(int i = 0; i < pixels.length; i++)
			for(int j = 0; j < pixels[0].length; j++)
				pixels[i][j] = j+i*pixels.length;
		Tile<Integer>[][] tiles = tiler.tile(pixels);
		
		assertEquals(2, tiles.length);
		assertEquals(2, tiles[0].length);
		
		throw new RuntimeException("Unimplemented test");
		//test values
		//assertArrayEquals(Arrays.copyOfRange(pixels, 0, 8), tiles[0][0].toMatrix());
	}
	
	@Test
	public void unequalSize() {
		TilerInterface<Integer> tiler = new JpegTiler();
		Integer[][] pixels = new Integer[10][12];
		
		for(int i = 0; i < pixels.length; i++)
			for(int j = 0; j < pixels[0].length; j++)
				pixels[i][j] = j+i*pixels.length;
		Tile<Integer>[][] tiles = tiler.tile(pixels);

		assertEquals(2, tiles.length);
		assertEquals(2, tiles[0].length);
		
		throw new RuntimeException("Unimplemented test");
		//test values - change JpegTiler to copy values
	}
	
}
