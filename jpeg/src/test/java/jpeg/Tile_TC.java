package jpeg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import DataObjects.Tile;
import DataObjects.Tile.InvalidTileSizeException;
import DataObjects.Tile.TileIterator;
import DataObjects.Tile.ZeroTileSizeException;

public class Tile_TC {

	@Test
	public void tileBasics() {
		Integer[][] vals = new Integer[][] { { 1, 2, 6 }, { 3, 4, 5 } };
		Tile<Integer> t = new Tile<Integer>(vals);

		assertEquals(new Integer(1), t.getVal(0, 0));
		assertEquals(new Integer(2), t.getVal(1, 0));
		assertEquals(new Integer(3), t.getVal(0, 1));
		assertEquals(new Integer(4), t.getVal(1, 1));

		assertEquals(3, t.getSizeX());
		assertEquals(2, t.getSizeY());

		vals = new Integer[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		t = new Tile<Integer>(vals);

		assertEquals(3, t.getSizeX());
		assertEquals(3, t.getSizeY());
	}

	@Test(expected = InvalidTileSizeException.class)
	public void illegalTileSize2dConstructor() {
		Integer[][] vals = new Integer[][] { { 1, 2, 3 }, { 4, 5 }, { 7, 8, 9 } };
		Tile<Integer> t = new Tile<Integer>(vals);
	}

	@Test(expected = ZeroTileSizeException.class)
	public void nullTileSizeX2dConstructor() {
		Integer[][] vals = new Integer[][] {};
		Tile<Integer> t = new Tile<Integer>(vals);
	}

	@Test(expected = ZeroTileSizeException.class)
	public void nullTileSizeY2dConstructor() {
		Integer[][] vals = new Integer[][] { {}, {} };
		Tile<Integer> t = new Tile<Integer>(vals);
	}
	@Test(expected = InvalidTileSizeException.class)
	public void illegalTileSize1dConstructor() {
		Integer[] vals = new Integer[] { 1, 2, 3, 4, 5, 6 };
		Tile<Integer> t = new Tile<Integer>(vals, 3, 3);
	}

	@Test(expected = ZeroTileSizeException.class)
	public void nullTileSizeX1dConstructor() {
		Integer[] vals = new Integer[] {};
		Tile<Integer> t; 
		t = new Tile<Integer>(vals, 0, 1);
	}
	
	@Test(expected = ZeroTileSizeException.class)
	public void nullTileSizeY1dConstructor() {
		Integer[] vals = new Integer[] {};
		Tile<Integer> t; 
		t = new Tile<Integer>(vals, 1, 0);
	}
	
	
	@Test
	public void tileIteratorIteration() {
		Integer[][] vals = new Integer[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		Tile<Integer> t = new Tile<Integer>(vals);

		TileIterator<Integer> it = t.iterator();
		Integer expected = 1;
		while (it.hasNext()) {
			Integer nextVal = it.next();
			assertEquals(nextVal, expected++);
			if (expected == 7)
				assertEquals(it.hasNext(), false);
		}
	}

	@Test(expected=UnsupportedOperationException.class)
	public void tileIteratorRemove() {
		Integer[][] vals = new Integer[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		Tile<Integer> t = new Tile<Integer>(vals);

		TileIterator<Integer> it = t.iterator();
		it.next();
		it.remove();
	}

	@Test
	public void tileToArray() {
		Integer[][] vals = new Integer[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		Tile<Integer> t = new Tile<Integer>(vals);

		Integer[] arrayFromTile = t.toArray();

		for (int i = 0; i < arrayFromTile.length; i++)
			assertEquals(new Integer(i + 1), arrayFromTile[i]);

		// arrayFromTile is independent to Tile
		arrayFromTile[0] = 100;
		for (int y = 0; y < t.getSizeY(); y++)
			for (int x = 0; x < t.getSizeX(); x++)
				assertEquals(new Integer(y * t.getSizeX() + x + 1),
						t.getVal(x, y));
	}

	@Test
	public void tileToMatrix() {
		Integer[][] vals = new Integer[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		Tile<Integer> t = new Tile<Integer>(vals);

		Integer[][] matrixFromTile = t.toMatrix();

		for (int y = 0; y < matrixFromTile.length; y++)
			for (int x = 0; x < matrixFromTile[0].length; x++)
				assertEquals(new Integer(y * matrixFromTile[0].length + x + 1),
						matrixFromTile[y][x]);

		// matrixFromTile is independent to Tile
		matrixFromTile[0][0] = 100;
		for (int y = 0; y < t.getSizeY(); y++)
			for (int x = 0; x < t.getSizeX(); x++)
				assertEquals(new Integer(y * t.getSizeX() + x + 1),
						t.getVal(x, y));
	}
	
	@Test
	public void instantiateFrom1dArray() {
		Integer[] vals = new Integer[] {1,2,3,4,5,6};
		Tile<Integer> t = new Tile<Integer>(vals, 3, 2);
		
	
		TileIterator<Integer> it = t.iterator();
		Integer expected = 1;
		while (it.hasNext()) {
			Integer nextVal = it.next();
			assertEquals(nextVal, expected++);
			if (expected == 7)
				assertEquals(it.hasNext(), false);
		}
		
		assertEquals(3, t.getSizeX());
		assertEquals(2, t.getSizeY());
	}

}
