package JpegMath.Coders;

import java.util.HashMap;
import java.util.Iterator;

import DataObjects.EncodedTile;
import DataObjects.Binary;
import DataObjects.Tile;
import DataObjects.ZigZagTileIterator;
import DataObjects.Huffman.FrequencyTable;
import DataObjects.Huffman.HuffmanTree;
import DataObjects.Huffman.HuffmanTreeList;


public class HuffmanCoding<E extends Number> implements CodingInterface {

	public EncodedTile<Binary> encode(EncodedTile<Integer> t) {
		
		EncodedTile<Binary> outTile = new EncodedTile<Binary>();
		
//		copy quantization table
		
		outTile.quantTable = t.quantTable;
		
				
		FrequencyTable<Integer> table = new FrequencyTable<Integer>();
		Iterator<Integer> tileIterator = t.tile.zigZagIterator();
			
		
//		count occurrences
		while(tileIterator.hasNext()){
			table.insert(tileIterator.next());
		}	
		
		
		HuffmanTreeList<Integer> list = new HuffmanTreeList<Integer>();
		
//		create linked list of tree elements		
		for(Integer key : table.freq.keySet()){
			list.add(key, table.getWeight(key));
		}
		
		HuffmanTreeList<Integer> staticList = (HuffmanTreeList<Integer>) list.clone();
		
		
//		as long there are 2 elements, take the 2 smallest, create a subtree with them and readd
		
		while (list.size()>1){
			HuffmanTree<Integer> first = list.getSmallestWeightElement();
			HuffmanTree<Integer> second = list.getSmallestWeightElement();
			
			list.add(new HuffmanTree<Integer>(first, second));
		}
		

		outTile.huffTable = new HashMap<Integer,Binary>();
//		generate code for each base element
		for(HuffmanTree<Integer> leaf : staticList){
			t.huffTable.put((Integer) leaf.getValue(), leaf.getCode());
		}
		
////	update tile

		outTile.tile=new Tile<Binary>(new Binary("0"));
		
//		just another iterator
		Iterator<Integer> quantizationIterator = t.tile.zigZagIterator();
		ZigZagTileIterator<Binary>  outputIterator = outTile.tile.zigZagIterator();
			
		while(quantizationIterator.hasNext()){
			if (outputIterator.hasNext()){
				outputIterator.setNext(outTile.huffTable.get(quantizationIterator.next()));
			}
		}	
		
		return outTile;
		
	}

	public EncodedTile<Integer> decode(EncodedTile<Binary> t) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
