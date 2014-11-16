package JpegMath.Coders;

import DataObjects.EncodedTile;
import DataObjects.Tile;

public interface CodingInterface {
	public void encode(EncodedTile<Integer> t);
	public void decode(EncodedTile<Integer> t);
}
