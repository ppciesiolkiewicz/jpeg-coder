package JpegMath.Coders;

import DataObjects.Binary;
import DataObjects.EncodedTile;
import DataObjects.Tile;

public interface CodingInterface {
	public EncodedTile<Binary> encode(EncodedTile<Integer> t);
	public EncodedTile<Integer> decode(EncodedTile<Binary> t);
}
