package JpegMath.Coders;

import DataObjects.Binary;
import DataObjects.EncodedTile;
import DataObjects.Tile;

public interface CodingInterface {
	public EncodedTile<Binary> encode(Tile<Integer> t, Integer lastDcValue, Integer dcTableNum, Integer acTableNum);
	public EncodedTile<Integer> decode(Tile<Binary> t);
}
