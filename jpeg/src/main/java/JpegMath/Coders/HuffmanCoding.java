package JpegMath.Coders;

import java.util.Iterator;

import DataObjects.EncodedTile;
<<<<<<< HEAD
=======
import DataObjects.Binary;
import DataObjects.Tile;
import DataObjects.ZigZagTileIterator;
>>>>>>> f28afa62e7f7c922fdbc8c29cfd5667cb343760b
import DataObjects.Huffman.FrequencyTable;
import DataObjects.Huffman.HuffmanTable;
import DataObjects.Huffman.HuffmanTree;
import DataObjects.Huffman.HuffmanTreeList;

public class HuffmanCoding<E extends Number> implements CodingInterface {

<<<<<<< HEAD
	public void encode(EncodedTile<Integer> t) {
		FrequencyTable<E> table = countOccurences(t);
		HuffmanTreeList<E> list = createListOfTreeElements(table);
		buildTree(list);

		// generate code for each base element

		// return encoding table

		t.huffTable = new HuffmanEncodingTable();

	}

	protected void buildTree(HuffmanTreeList<E> list) {
		// as long there are 2 elements, take the 2 smallest, create a subtree
		// with them and readd
		while (list.size() > 2) {
			HuffmanTree<E> first = list.getSmallestWeightElement();
			HuffmanTree<E> second = list.getSmallestWeightElement();

			list.add(new HuffmanTree<E>(first, second));
		}
	}

	protected HuffmanTreeList<E> createListOfTreeElements(FrequencyTable<E> table) {
		HuffmanTreeList<E> list = new HuffmanTreeList<E>();
		for (E key : table.getKeys()) {
			list.add(key, table.getWeight(key));
		}
		return list;
	}

	protected FrequencyTable<E> countOccurences(EncodedTile<Integer> t) {
		FrequencyTable<E> table = new FrequencyTable<E>();
		Iterator<E> tileIterator = (Iterator<E>) t.tile.zigZagIterator();

		while (tileIterator.hasNext())
			table.insert(tileIterator.next());
		return table;
	}

	public void decode(EncodedTile<Integer> t) {
		// TODO Auto-generated method stub

=======
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
		

		outTile.huffTable = new HuffmanTable<Integer,Binary>();
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
				outputIterator.setNext(outTile.huffTable.getCode(quantizationIterator.next()));
			}
		}	
		
		return outTile;
		
	}

	public EncodedTile<Integer> decode(EncodedTile<Binary> t) {
		
		EncodedTile<Integer> outTile = new EncodedTile<Integer>();
		outTile.tile=new Tile<Integer>(0);

		Iterator<Binary> huffmanCodeIterator = t.tile.zigZagIterator();
		ZigZagTileIterator<Integer>  outputIterator = outTile.tile.zigZagIterator();
			
		while(huffmanCodeIterator.hasNext()){
			if (outputIterator.hasNext()){
				outputIterator.setNext(outTile.huffTable.getWord(huffmanCodeIterator.next()));
			}
		}	
		
		return outTile;
>>>>>>> f28afa62e7f7c922fdbc8c29cfd5667cb343760b
	}

}
