package DataObjects;

import java.util.HashMap;

import DataObjects.Huffman.HuffmanTable;

public class EncodedTile<E extends Number> {
	public ArrayTileInterface<E> tile;
	public Tile<Double> quantTable;
	public HuffmanTable<Integer,Binary> huffTable;
	
}
