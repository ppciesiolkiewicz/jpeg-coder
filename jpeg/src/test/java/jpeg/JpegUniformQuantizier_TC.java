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
				{-415.38 , -30.19 , -61.20 , 27.24 , 56.12 , -20.10 , -2.39 , 0.46 },
				{4.47 , -21.86 , -60.76 , 10.25 , 13.15 , -7.09 , -8.54 , 4.88 },
				{-46.83 , 7.37 , 77.13 , -24.56 , -28.91 , 9.93 , 5.42 , -5.65 },
				{-48.53 , 12.07 , 34.10 , -14.76 , -10.24 , 6.30 , 1.83 , 1.95 },
				{12.12 , -6.55 , -13.20 , -3.95 , -1.87 , 1.75 , -2.79 , 3.14 },
				{-7.73 , 2.91 , 2.38 , -5.94 , -2.38 , 0.94 , 4.30 , 1.85 },
				{-1.03 , 0.18 , 0.42 , -2.42 , -0.88 , -3.02 , 4.12 , -0.66 },
				{-0.17 , 0.14 , -1.07 , -4.19 , -1.17 , -0.10 , 0.50 , 1.68}
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
		JpegUniformQuantizier q = new JpegUniformQuantizier(50d);
		EncodedTile<Integer> output = q.quantize(testInput);

		System.out.println(output.tile.toString());
		System.out.println(quantiziedExpected.toString());
		assertEquals(output.tile, quantiziedExpected);
	}

}
