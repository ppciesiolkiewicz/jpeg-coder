package JpegMath.Coders;

import java.util.HashMap;
import java.util.Iterator;

import DataObjects.EncodedTile;
import DataObjects.Huffman.FrequencyTable;
import DataObjects.Huffman.HuffmanEncodingTable;
import DataObjects.Huffman.HuffmanTree;
import DataObjects.Huffman.HuffmanTreeList;


public class HuffmanCoding<E extends Number> implements CodingInterface {

	public void encode(EncodedTile<Integer> t) {
		
		HuffmanEncodingTable<Integer, String> encTable;
		
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
		

		t.huffTable = new HashMap<Integer,String>();
//		generate code for each base element
		for(HuffmanTree<Integer> leaf : staticList){
			t.huffTable.put((Integer) leaf.getValue(), leaf.getCode());
		}
		
	}

	public void decode(EncodedTile<Integer> t) {
		// TODO Auto-generated method stub
		
	}

}
