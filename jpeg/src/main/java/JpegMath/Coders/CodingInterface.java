package JpegMath.Coders;

import DataObjects.Tile;

public interface CodingInterface {
	public Tile encode(Tile t);
	public Tile decode(Tile t);
}
