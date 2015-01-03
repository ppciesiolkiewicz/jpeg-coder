package jpeg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import DataObjects.Tile;
import JpegMath.Transformations.DctTransformation;

public class DctTransformation_TC {
	DctTransformation dct;
	
	@Before
	public void setUp() {
		dct = new DctTransformation();
	}
	
	@Test
	public void forwardInverseTransformNoFrequency() {
		Integer[][] inputMatrix = { { 100, 100, 100, 100, 100, 100, 100, 100 },
				{ 100, 100, 100, 100, 100, 100, 100, 100 },
				{ 100, 100, 100, 100, 100, 100, 100, 100 },
				{ 100, 100, 100, 100, 100, 100, 100, 100 },
				{ 100, 100, 100, 100, 100, 100, 100, 100 },
				{ 100, 100, 100, 100, 100, 100, 100, 100 },
				{ 100, 100, 100, 100, 100, 100, 100, 100 },
				{ 100, 100, 100, 100, 100, 100, 100, 100 }, };

		Double[][] expectedTransform = { { 800d, 0d, 0d, 0d, 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d },
				{ 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d }, };

		testTransformation(inputMatrix, expectedTransform, 1.1);	
	}

	@Test
	public void forwardInverseTransform() {
		Integer[][] inputMatrix = { { -122, 49, 66, 41, 41, 43, 40, 38 },
				{ -121, 49, 31, 45, 35, 50, 41, 24 },
				{ -122, 40, 45, 105, 31, -66, 18, 87 },
				{ -94, 52, 42, 47, -122, -122, 8, 51 },
				{ -119, -23, 53, 51, 45, 70, 61, 42 },
				{ -64, -122, -25, -26, 33, 15, 6, 12 },
				{ -76, -80, -64, -122, 53, 64, 38, -122 },
				{ -78, -74, -84, -122, 57, 43, 41, -53 }, };

		Double[][] expectedTransform = {
				{ -27.500, -213.468, -149.608, -95.281, -103.750, -46.946,
						-58.717, 27.226 },
				{ 168.229, 51.611, -21.544, -239.520, -8.238, -24.495, -52.657,
						-96.621 },
				{ -27.198, -31.236, -32.278, 173.389, -51.141, -56.942, 4.002,
						49.143 },
				{ 30.184, -43.070, -50.473, 67.134, -14.115, 11.139, 71.010,
						18.039 },
				{ 19.500, 8.460, 33.589, -53.113, -36.750, 2.918, -5.795,
						-18.387 },
				{ -70.593, 66.878, 47.441, -32.614, -8.195, 18.132, -22.994,
						6.631 },
				{ 12.078, -19.127, 6.252, -55.157, 85.586, -0.603, 8.028,
						11.212 },
				{ 71.152, -38.373, -75.924, 29.294, -16.451, -23.436, -4.213,
						15.624 }, };

		testTransformation(inputMatrix, expectedTransform, 1.1);	
	}
	

	private void testTransformation(Integer[][] inputMatrix,
			Double[][] expectedTransform, Double delta) {
		
		Tile<Integer> inputTile = new Tile<Integer>(inputMatrix);
		Tile<Double> forwardTransform = dct.transfom(inputTile);
		Tile<Integer> inverseTransform = dct.inverseTransform(forwardTransform);

		for (int y = 0; y < inputMatrix.length; y++)
			for (int x = 0; x < inputMatrix[0].length; x++)
				assertEquals(expectedTransform[y][x], forwardTransform.getVal(x, y), delta);
		
		for (int y = 0; y < inputMatrix.length; y++)
			for (int x = 0; x < inputMatrix[0].length; x++)
				assertEquals(inputMatrix[y][x], inverseTransform.getVal(x, y), delta);
	}

}
