package JpegMath.Transformations;

import DataObjects.Tile;

public class Irreversible9_7WaveletTransformation implements TransformationInterface {

	static final double dwt_norms[][] = {
        {1.000, 1.965, 4.177, 8.403, 16.90, 33.84, 67.69, 135.3, 270.6, 540.9},
        {2.022, 3.989, 8.355, 17.04, 34.27, 68.63, 137.3, 274.6, 549.0},
        {2.022, 3.989, 8.355, 17.04, 34.27, 68.63, 137.3, 274.6, 549.0},
        {2.080, 3.865, 8.307, 17.18, 34.71, 69.59, 139.3, 278.6, 557.2}
	};
	
	public Tile transfom(Tile t) {
		throw new RuntimeException("not implemented");
		///return null;
	}

	public Tile inverseTransform(Tile t) {
		// TODO Auto-generated method stub
		return null;
	}

}
