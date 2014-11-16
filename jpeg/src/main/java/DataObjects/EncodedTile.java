package DataObjects;

import DataObjects.Huffman.HuffmanEncodingTable;

public class EncodedTile<E extends Number> {
	public ArrayTileInterface<E> tile;
	public Tile<Double> quantTable;
	public HuffmanEncodingTable huffTable;
	
}
