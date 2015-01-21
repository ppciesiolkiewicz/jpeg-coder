package JpegMath.Transformations;

import DataObjects.Tile;
import org.jtransforms.dct.DoubleDCT_2D;

public class FastDctTransformation implements TransformationInterface {
	private DoubleDCT_2D dct;

	public FastDctTransformation() {
		dct = new DoubleDCT_2D(8, 8);
	}

	public Tile<Double> transfom(Tile<Integer> t) {
		double[] matrix = new double[64];
		for (int i = 0; i < 64; i++)
				matrix[i] = t.getVal(i).doubleValue();
		dct.forward(matrix, true);
		
		
		Tile<Double> out = new Tile<Double>(0d);
		for (int i = 0; i < 64; i++)
				out.setVal(i, matrix[i]);
		
		return out;
	}

	public Tile<Integer> inverseTransform(Tile<Double> t) {
		// TODO Auto-generated method stub
		return null;
	}

}
