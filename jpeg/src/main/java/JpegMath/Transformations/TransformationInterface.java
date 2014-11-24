package JpegMath.Transformations;

import DataObjects.Tile;

public interface TransformationInterface {

	public Tile<Double> transfom(Tile<Integer> t);
	public Tile<Integer> inverseTransform(Tile<Double> t);
}
