package jpeg;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import DataObjects.*;
import DataObjects.Tile.IllegalTileSizeException;
import DataObjects.Tile.IllegalTileSizeException;

public class Tile_TC {

	@Test
	public void tileBasics() {
		Integer[][] vals = new Integer[][]{{1,2},{3,4}};
		Tile t = new Tile<Integer>(vals);
		assertArrayEquals(vals, t.getValues());
		
		assertEquals(1, t.getVal(0, 0));
		assertEquals(2, t.getVal(0, 1));
		assertEquals(3, t.getVal(1, 0));
		assertEquals(4, t.getVal(1, 1));
		
		assertEquals(2, t.getSizeX());
		assertEquals(2, t.getSizeY());
		
		vals = new Integer[][]{{1,2,3},{4,5,6},{7,8,9}};
		t = new Tile<Integer>(vals);
		assertArrayEquals(vals, t.getValues());
		assertEquals(3, t.getSizeX());
		assertEquals(3, t.getSizeY());
		
		

	}		
		
	@Test(expected = IllegalTileSizeException.class) 
	public void illegalTileSize() {	
		Integer[][] vals = new Integer[][]{{1,2,3},{4,5},{7,8,9}};
		Tile t = new Tile<Integer>(vals);
	}

	@Test(expected = IllegalTileSizeException.class) 
	public void nullTileSizeX() {	
		Integer[][] vals = new Integer[][]{};
		Tile t = new Tile<Integer>(vals);
	}
	
	@Test(expected = IllegalTileSizeException.class) 
	public void nullTileSizeY() {	
		Integer[][] vals = new Integer[][]{{},{}};
		Tile t = new Tile<Integer>(vals);
	}
}
