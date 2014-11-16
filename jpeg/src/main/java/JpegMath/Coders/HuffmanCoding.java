package JpegMath.Coders;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import DataObjects.EncodedTile;
import DataObjects.Tile;
import DataObjects.Huffman.FrequencyTable;
import DataObjects.Huffman.HuffmanEncodingTable;
import DataObjects.Huffman.HuffmanTree;
import DataObjects.Huffman.HuffmanTreeList;


public class HuffmanCoding<E extends Number> implements CodingInterface {

	public void encode(EncodedTile<Integer> t) {
		
		FrequencyTable<E> table = new FrequencyTable<E>();
		Iterator<E> tileIterator = (Iterator<E>) t.tile.zigZagIterator();
			
		
//		count occurrences
		while(tileIterator.hasNext()){
			table.insert(tileIterator.next());
		}	
		
		
		HuffmanTreeList<E> list = new HuffmanTreeList<E>();
		
//		create linked list of tree elements		
		for(E key : table.freq.keySet()){
			list.add(key, table.getWeight(key));
		}
		
		
//		as long there are 2 elements, take the 2 smallest, create a subtree with them and readd
		
		while (list.size()>2){
			HuffmanTree<E> first = list.getSmallestWeightElement();
			HuffmanTree<E> second = list.getSmallestWeightElement();
			
			list.add(new HuffmanTree<E>(first, second));
		}
		

//		generate code for each base element
		
		
//		return encoding table
		
		
		
		
		
		
		
		
		
		t.huffTable = new HuffmanEncodingTable();

		
	}

	public void decode(EncodedTile<Integer> t) {
		// TODO Auto-generated method stub
		
	}

}
