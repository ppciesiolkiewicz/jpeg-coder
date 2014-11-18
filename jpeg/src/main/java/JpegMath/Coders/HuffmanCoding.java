package JpegMath.Coders;

import java.util.Iterator;

import DataObjects.Binary;
import DataObjects.EncodedTile;
import DataObjects.Huffman.FrequencyTable;
import DataObjects.Huffman.HuffmanTable;
import DataObjects.Huffman.HuffmanTree;
import DataObjects.Huffman.HuffmanTreeList;

public class HuffmanCoding<E extends Number> implements CodingInterface {

	public EncodedTile<Binary> encode(EncodedTile<Integer> t) {
		FrequencyTable<E> freqTable = countOccurences(t);
		HuffmanTreeList<E> list = createListOfTreeElements(freqTable);
		buildTree(list);
		HuffmanTable<E, String> codeMap = createCodeMap(list);

		return null;

		// generate code for each base element

		// return encoding table

	}

	protected HuffmanTable<E, String> createCodeMap(HuffmanTreeList<E> list) {
		HuffmanTable<E, String> codeMap = new HuffmanTable<E, String>();
		Iterator<HuffmanTree<E>> it = list.getFirst().iterator();

		while (it.hasNext()) {
			HuffmanTree<E> elem = it.next();
			if (elem.getValue() != null) {
				codeMap.put(elem.getValue(), elem.getCode());
			}
		}
		return codeMap;
	}

	protected void buildTree(HuffmanTreeList<E> list) {
		// as long there are 2 elements, take the 2 smallest, create a subtree
		// with them and readd
		while (list.size() > 1) {
			HuffmanTree<E> first = list.getSmallestWeightElement();
			HuffmanTree<E> second = list.getSmallestWeightElement();

			list.add(new HuffmanTree<E>(first, second));
		}
	}

	protected HuffmanTreeList<E> createListOfTreeElements(
			FrequencyTable<E> table) {
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

	public EncodedTile<Integer> decode(EncodedTile<Binary> t) {
		// TODO Auto-generated method stub
		return null;
	}

}
