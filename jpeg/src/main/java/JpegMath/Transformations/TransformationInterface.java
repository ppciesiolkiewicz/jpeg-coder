package JpegMath.Transformations;

import DataObjects.Tile;

public interface TransformationInterface {

	public <O extends Number, I extends Number> Tile<O> transfom(Tile<I> t);
	public <O extends Number, I extends Number> Tile<O> inverseTransform(Tile<I> t);
}
