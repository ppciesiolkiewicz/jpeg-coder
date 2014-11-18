package jpeg;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import DataObjects.Huffman.HuffmanTree;

public class PreOrderIterator_TC {

	@Test
	public void singleElement() {
		HuffmanTree<Integer> root = new HuffmanTree<Integer>(10,11);

		Iterator<HuffmanTree<Integer>> it = root.iterator();
		while (it.hasNext()) {
			HuffmanTree<Integer> e = it.next();
			assertEquals(new Integer(10), e.getValue());
			assertEquals(11, e.getWeight());
		}
	}
	
	@Test
	public void completeTree3Levels() {
		HuffmanTree<Integer> root = new HuffmanTree<Integer>
		(new HuffmanTree<Integer>(new HuffmanTree<Integer>(0,1), new HuffmanTree<Integer>(1, 10)), 
		new HuffmanTree<Integer>(new HuffmanTree<Integer>(2,100), new HuffmanTree<Integer>(3, 1000)));
		
		
		Integer[] expectedWeights = {1111, 11,1,10,1100,100,1000};
		int i = 0;
		Iterator<HuffmanTree<Integer>> it = root.iterator();
		while (it.hasNext()) {
			HuffmanTree<Integer> e = it.next();
			assertEquals(expectedWeights[i++].intValue(), e.getWeight());
		}
	}

}
