package jpeg;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import DataObjects.Tile;

public class ZigZagIterator_TC {
	Integer[][] testArray;
	Tile<Integer> tiletest;

	@Before
	public void setUp() {
		testArray = new Integer[][] { { 1, 2, 6, 7, 15, 16, 28, 29 },
				{ 3, 5, 8, 14, 17, 27, 30, 43 },
				{ 4, 9, 13, 18, 26, 31, 42, 44 },
				{ 10, 12, 19, 25, 32, 41, 45, 54 },
				{ 11, 20, 24, 33, 40, 46, 53, 55 },
				{ 21, 23, 34, 39, 47, 52, 56, 61 },
				{ 22, 35, 38, 48, 51, 57, 60, 62 },
				{ 36, 37, 49, 50, 58, 59, 63, 64 } };
		tiletest = new Tile<Integer>(testArray);
	}

	@Test
	public void simpleTestIteration() {
		Iterator<Integer> it = tiletest.zigZagIterator();
		Integer i = 1;
		while (it.hasNext()) {
			assertEquals(i++, it.next());
		}
	}

	@Test
	public void simpleTestRemove() {
		Iterator<Integer> it = tiletest.zigZagIterator();

		while (it.hasNext()) {
			int temp = it.next();
			it.remove();
		}

		Integer array_[] = tiletest.toArray();
		Integer[] expArray = new Integer[64];
		for (int i = 0; i < 64; i++)
			expArray[i] = null;
		assertEquals(expArray, array_);
	}

	@Test
	public void removeHalf() {
		Iterator<Integer> it = tiletest.zigZagIterator();

		int i = 0;
		while (it.hasNext() && i++ < 32) {
			int temp = it.next();
			it.remove();
		}

		Integer array_[] = tiletest.toArray();
		Integer[] expArray = { 1, 2, 6, 7, 15, 16, 28, 29, 3, 5, 8, 14, 17, 27,
				30, 43, 4, 9, 13, 18, 26, 31, 42, 44, 10, 12, 19, 25, 32, 41,
				45, 54, 11, 20, 24, 33, 40, 46, 53, 55, 21, 23, 34, 39, 47, 52,
				56, 61, 22, 35, 38, 48, 51, 57, 60, 62, 36, 37, 49, 50, 58, 59,
				63, 64 };
		for (i = 0; i < 64; i++) {
			if (expArray[i] <= 32)
				expArray[i] = null;
		}
		assertArrayEquals(expArray, array_);
	}

	@Test
	public void removeEven() {
		Iterator<Integer> it = tiletest.zigZagIterator();
		int i = 0;
		while (it.hasNext()) {
			int temp = it.next();
			if (temp % 2 == 0)
				it.remove();
		}

		Integer array_[] = tiletest.toArray();
		Integer[] expArray = { 1, 2, 6, 7, 15, 16, 28, 29, 3, 5, 8, 14, 17, 27,
				30, 43, 4, 9, 13, 18, 26, 31, 42, 44, 10, 12, 19, 25, 32, 41,
				45, 54, 11, 20, 24, 33, 40, 46, 53, 55, 21, 23, 34, 39, 47, 52,
				56, 61, 22, 35, 38, 48, 51, 57, 60, 62, 36, 37, 49, 50, 58, 59,
				63, 64 };
		for (i = 0; i < 64; i++) {
			if (expArray[i] % 2 == 0)
				expArray[i] = null;
		}
		assertArrayEquals(expArray, array_);
	}

}