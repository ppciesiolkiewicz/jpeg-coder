package JpegMath.Coders;

import DataObjects.EncodedTile;
import DataObjects.HuffmanEncodingTable;
import DataObjects.Tile;


public class HuffmanCoding implements CodingInterface {

	public void encode(EncodedTile<Integer> t) {
		t.huffTable = new HuffmanEncodingTable();

		
	}

	public void decode(EncodedTile<Integer> t) {
		// TODO Auto-generated method stub
		
	}

}
