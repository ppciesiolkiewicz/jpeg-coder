package jpeg;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import DataObjects.Tile;
import DataObjects.Huffman.FrequencyTable;

public class FrequencyTable_TC {
	FrequencyTable freq;
	
	@Before
	public void setUp() throws Exception {
		freq = new FrequencyTable();
	}

	@Test
	public void allZeros() {
		Tile<Integer> t = new Tile<Integer>(8,8,0);
		Iterator<Integer> it = t.zigZagIterator();
		
		while(it.hasNext())
			freq.insert(it.next());
		
		Set<Integer> keys = freq.getKeys();
		for(Integer key : keys)
			freq.getWeight(key);
		
		assertEquals(64, freq.getWeight(0));
	}
	
	@Test
	public void everyNumberDifferent() {
		Tile<Integer> t = new Tile<Integer>(8,8,0);
		Iterator<Integer> it = t.zigZagIterator();
		
		for(int i = 0; i < 64; i++)
			t.setVal(i, i);
		
		while(it.hasNext())
			freq.insert(it.next());
		
		Set<Integer> keys = freq.getKeys();
		for(Integer key : keys)
			assertEquals(1, freq.getWeight(key));
		
	}

}
