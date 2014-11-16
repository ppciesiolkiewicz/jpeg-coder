package DataObjects;

import java.util.HashMap;

public class EncodedTile<E extends Number> {
	public ArrayTileInterface<E> tile;
	public Tile<Double> quantTable;
	public HashMap<E,String> huffTable;
	
}
