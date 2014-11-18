package jpeg;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import DataObjects.EncodedTile;
import DataObjects.Tile;
import DataObjects.Huffman.FrequencyTable;
import DataObjects.Huffman.HuffmanTree;
import DataObjects.Huffman.HuffmanTreeList;
import JpegMath.Coders.HuffmanCoding;

public class HuffmanCoding_TC {

	public class TestHuffmanCoding<E extends Number> extends HuffmanCoding<E> {
		public void buildTree(HuffmanTreeList<E> list) {
			super.buildTree(list);
		}

		public HuffmanTreeList<E> createListOfTreeElements(
				FrequencyTable<E> table) {
			return super.createListOfTreeElements(table);
		}

		public FrequencyTable<E> countOccurences(EncodedTile<Integer> t) {
			return super.countOccurences(t);
		}
	}

	TestHuffmanCoding<Integer> hc;

	@Before
	public void setUp() throws Exception {
		hc = new TestHuffmanCoding<Integer>();
	}

	@Test
	public void niceValues() {
		EncodedTile<Integer> t = new EncodedTile<Integer>();
		t.tile = new Tile<Integer>(8,8,5);
		
		for(int i = 0; i < 4; i++)
			t.tile.setVal(i, 1);
		for(int i = 4; i < 8; i++)
			t.tile.setVal(i, 2);
		for(int i = 8; i < 16; i++)
			t.tile.setVal(i, 3);
		for(int i = 16; i < 32; i++)
			t.tile.setVal(i, 4);
		
		FrequencyTable<Integer> freqTable = hc.countOccurences(t);
		for(Integer k : freqTable.getKeys()) {
			if( k == 1)
				assertEquals(4, freqTable.getWeight(k));
			if( k == 2)
				assertEquals(4, freqTable.getWeight(k));
			if( k == 3)
				assertEquals(8, freqTable.getWeight(k));
			if( k == 4)
				assertEquals(16, freqTable.getWeight(k));
			if( k == 5)
				assertEquals(32, freqTable.getWeight(k));
		}
			
		
		HuffmanTreeList<Integer> list = hc.createListOfTreeElements(freqTable);
		Iterator<HuffmanTree<Integer>> it = list.iterator();
		while(it.hasNext())
			System.out.println(it.next().toString());
		
		System.out.println("\n\n");
		
		hc.buildTree(list);

		it = list.iterator();
		while(it.hasNext())
			System.out.println(it.next().toString());
		
		System.out.println(t.tile);
		System.out.println(t.huffTable);
	}
}
