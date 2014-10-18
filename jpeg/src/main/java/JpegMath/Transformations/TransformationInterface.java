package JpegMath.Transformations;

import DataObjects.Tile;

public interface TransformationInterface {

	public Tile transfom(Tile t);
	public Tile inverseTransform(Tile t);
}
