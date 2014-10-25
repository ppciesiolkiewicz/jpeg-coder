package DataObjects;

import java.lang.reflect.Array;
import java.util.Iterator;


public class Tile<E extends Number> implements ArrayTileInterface<E>, MatrixTileInterface<E> {
	/*
	 * {{1,2,3},{4,5,6}} ->
	 * | 1 2 3 |
	 * | 4 5 6 |
	 * 
	 * | values[0][0] values[0][1] values[0][2] |
	 * | values[1][0] values[1][1] values[1][2] |
	 * values[y][x]
	 */
	private E[][] values;
	
	public Tile(E[][] vals) {
		this.setValues(vals);
	}
	
	public Tile(E[] vals, int sizeX, int sizeY) {
		this.setValues(vals, sizeX, sizeY);
	}

	public Tile(Tile<E> t) {
		this.setValues(t.getValues());
	}
	
	public E getVal(int x, int y) {
		return values[y][x];
	}
	
	public void setVal(int x, int y, E newVal) {
		values[y][x] = newVal;
	}
	
	public void setVal(int pos, E val) {
		int x = pos%getSizeX();
		int y = pos/getSizeX();
		setVal(x, y, val);
	}
	
	public E getVal(int pos) {
		int x = pos%getSizeX();
		int y = pos/getSizeX();	
		return getVal(x,y);
	}
	
	protected E[][] getValues() {
		return values.clone();
	}
	
	public E[] toArray() {
		@SuppressWarnings("unchecked")
		final E[] array = (E[]) Array.newInstance(values[0][0].getClass(), getSizeX()*getSizeY());
		TileIterator<E> it = iterator();
		
		int i = 0;
		while(it.hasNext())
			array[i++] = it.next();
		
		return array;
	}
	
	public E[][] toMatrix() {
		@SuppressWarnings("unchecked")
		final E[][] array = (E[][]) Array.newInstance(values[0][0].getClass(), getSizeY(), getSizeX());
		for(int y = 0; y < array.length; y++)
			for(int x = 0; x < array[0].length; x++)
				array[y][x] = values[y][x];

		return array;
	}
	
	protected void setValues(E[][] values_) {
		if(values_.length == 0 || values_[0].length == 0)
			throw new ZeroTileSizeException();
		for(E[] v : values_)
			if(v.length != values_[0].length)
				throw new InvalidTileSizeException();
		this.values = values_.clone();
	}
	
	protected void setValues(E[] values_, int sizeX, int sizeY) {
		if(sizeX == 0 || sizeY == 0)
			throw new ZeroTileSizeException();
		if(values_.length != sizeX*sizeY)
			throw new InvalidTileSizeException();
		
		instantiateValuesArray(values_, sizeX, sizeY);

		for(int y = 0; y < sizeY; y++)
			for(int x = 0; x < sizeX; x++) 
				setVal(x,y,values_[y*sizeX+x]);
	}

	@SuppressWarnings("unchecked")
	private void instantiateValuesArray(E[] vals, int sizeX, int sizeY) {
		values = (E[][]) Array.newInstance(vals[0].getClass(), sizeY, sizeX);
	}

	public int getSizeX() {
		return values[0].length;
	}

	public int getSizeY() {
		return values.length;
	}


	public int getLength() {
		return getSizeX()*getSizeY();
	}
	
	public TileIterator<E> iterator() {
		return new TileIterator<E>(this);
	}
	
	public static class TileIterator<T extends Number> implements Iterator<T> {
		protected Tile<T> tile;
		protected int cur;
		
		protected TileIterator(Tile<T> t) {
			cur = -1;
			tile = t;
		}
				
		public boolean hasNext() {
			return cur < tile.getSizeX()*tile.getSizeY()-1;
		}

		public T next() {
			cur++;
			return tile.values[getCurrentY()][getCurrentX()];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		protected void setVal(T newVal) {
			tile.values[getCurrentY()][getCurrentX()] = newVal;
		}
		
		protected int getCurrentY() {
			return cur/tile.getSizeX();
		}
		
		protected int getCurrentX() {
			return cur%tile.getSizeX();
		}
	}
	
	public static class InvalidTileSizeException extends RuntimeException {
		private static final long serialVersionUID = 3122434195001134670L;
	}
	
	public static class ZeroTileSizeException extends RuntimeException {
		private static final long serialVersionUID = 2195901446734926722L;
	}
}
