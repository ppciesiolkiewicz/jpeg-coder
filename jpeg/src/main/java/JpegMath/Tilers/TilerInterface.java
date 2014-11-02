package JpegMath.Tilers;

import DataObjects.Tile;

public interface TilerInterface <E extends Number> {
	public Tile<E>[][] tile(E[][] pixelMatrix);
	public E[][] connect(Tile<E>[][] tiles);
}
