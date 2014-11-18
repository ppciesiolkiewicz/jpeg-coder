package JpegMath.Coders;

import java.util.Iterator;

import DataObjects.EncodedTile;
import DataObjects.Huffman.FrequencyTable;
import DataObjects.Huffman.HuffmanEncodingTable;
import DataObjects.Huffman.HuffmanTree;
import DataObjects.Huffman.HuffmanTreeList;

public class HuffmanCoding<E extends Number> implements CodingInterface {

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

	}

}
