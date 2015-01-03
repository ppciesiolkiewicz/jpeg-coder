package jpeg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import DataObjects.ArrayTileInterface;
import DataObjects.EncodedTile;
import DataObjects.Tile;
import JpegMath.Quantiziers.JpegUniformQuantizier;
public class JpegUniformQuantizier_TC {
	
	Tile<Double> testInput;
	Tile<Integer> quantiziedExpected;
	
	@Before
	public void setUp() {
		Double[][] mIn = {
				{ 16.0, 11.0, 10.0, 16.0, 24.0, 40.0, 51.0, 61.0 },
				{ 12.0, 12.0, 14.0, 19.0, 26.0, 58.0, 60.0, 55.0 },
				{ 14.0, 13.0, 16.0, 24.0, 40.0, 57.0, 69.0, 56.0 },
				{ 14.0, 17.0, 22.0, 29.0, 51.0, 87.0, 80.0, 62.0 },
				{ 18.0, 22.0, 37.0, 56.0, 68.0, 109.0, 103.0, 77.0 },
				{ 24.0, 35.0, 55.0, 64.0, 81.0, 104.0, 113.0, 92.0 },
				{ 49.0, 64.0, 78.0, 87.0, 103.0, 121.0, 120.0, 101.0 },
				{ 72.0, 92.0, 95.0, 98.0, 112.0, 100.0, 103.0, 99.0 },
		};
		
		testInput = new Tile<Double>(mIn);
		
		Integer[][] mOut = {
				{-26 , -3 , -6 , 2 , 2 , -1 , 0 , 0 },
				{0 , -2 , -4 , 1 , 1 , 0 , 0 , 0 },
				{-3 , 1 , 5 , -1 , -1 , 0 , 0 , 0 },
				{-3 , 1 , 2 , -1 , 0 , 0 , 0 , 0 },
				{1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
				{0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
				{0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
				{0 , 0 , 0 , 0 , 0 , 0 , 0 , 0}
		};
		quantiziedExpected = new Tile<Integer>(mOut);
	}

	@Test
	public void test() {
		JpegUniformQuantizier q = new JpegUniformQuantizier(80);
		Tile<Integer> output = q.quantize(testInput, 0);
		System.out.println(output.toString());
		//System.out.println(quantiziedExpected.toString());
		//assertEquals(output, quantiziedExpected);
	}

}
