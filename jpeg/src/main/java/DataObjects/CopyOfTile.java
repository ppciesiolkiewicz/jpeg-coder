package DataObjects;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CopyOfTile<E extends Number> implements ArrayTileInterface<E>, MatrixTileInterface<E> {
	/*
	 * {{1,2,3},{4,5,6}} ->
	 * | 1 2 3 |
	 * | 4 5 6 |
	 */
	private List<E> values = new LinkedList<E>();
	private Integer sizeX, sizeY;

	public CopyOfTile(E initVal) {
		this(8, 8, initVal);
	}

	public CopyOfTile(E[][] vals) {
		this.setValues(vals);
	}
	
	public CopyOfTile(int sizeX, int sizeY, E initVal) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		values = new LinkedList<E>((Collection<? extends E>) Collections.nCopies(sizeX*sizeY, initVal));
	}
	
	public CopyOfTile(E[] vals, int sizeX, int sizeY) {
		this.setValues(vals, sizeX, sizeY);
	}
	
	public E getVal(int x, int y) {
		return values.get(y*sizeX+x);
	}
	
	public void setVal(int x, int y, E newVal) {
		values.set(y*sizeX+x, newVal);
	}
	
	public void setVal(int pos, E val) {
		values.set(pos, val);
	}
	
	public E getVal(int pos) {
		return values.get(pos);
	}
	
	public E[] toArray() {
		@SuppressWarnings("unchecked")
		final E[] array = (E[]) Array.newInstance(values.get(0).getClass(), getSizeX()*getSizeY());

		TileIterator<E> it = iterator();
		int i = 0;
		while(it.hasNext())
			array[i++] = it.next();
		return array;
    }

	
	public E[][] toMatrix() {
		@SuppressWarnings("unchecked")
		final E[][] array = (E[][]) Array.newInstance(values.get(0).getClass(), getSizeY(), getSizeX());
		for(int y = 0; y < array.length; y++)
			for(int x = 0; x < array[0].length; x++)
				array[y][x] = getVal(x,y);

		return array;
	}
	
	protected void setValues(E[][] values_) {
		if(values_.length == 0 || values_[0].length == 0)
			throw new ZeroTileSizeException();
		for(E[] v : values_)
        	if(v.length != values_[0].length)
        		throw new InvalidTileSizeException();

		sizeY=values_.length;
		sizeX=values_[0].length;
		
		for(int y = 0; y < sizeY; y++)
			for(int x = 0; x < sizeX; x++)
				values.add(values_[y][x]);
	}
	
	protected void setValues(E[] values_, int sizeX, int sizeY) {
		if(sizeX == 0 || sizeY == 0)
			throw new ZeroTileSizeException();
		if(values_.length != sizeX*sizeY)
			throw new InvalidTileSizeException();
		
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		values = Arrays.asList(values_);
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}


	public int getLength() {
		return getSizeX()*getSizeY();
	}
	
	public TileIterator<E> iterator() {
		return new TileIterator<E>(this);
	}
	
	public static class TileIterator<T extends Number> implements Iterator<T> {
		protected CopyOfTile tile;
		protected ListIterator it;
		protected Integer curPos;
		
		protected TileIterator(CopyOfTile<T> t) {
			it = t.values.listIterator();
			tile = t;
			curPos = 0;
		}
				
		public boolean hasNext() {
			return it.hasNext();
		}

		public T next() {
			curPos++;
			return (T) it.next();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		protected void setVal(T newVal) {
			tile.setVal(curPos, newVal);
		}
		
		protected int getCurrentY() {
			return curPos/tile.getSizeX();
		}
		
		protected int getCurrentX() {
			return curPos%tile.getSizeX();
		}
	}
	
	public static class InvalidTileSizeException extends RuntimeException {
		private static final long serialVersionUID = 3122434195001134670L;
	}
	
	public static class ZeroTileSizeException extends RuntimeException {
		private static final long serialVersionUID = 2195901446734926722L;
		
		
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int y = 0; y < sizeY; y++) {
			s+="|\t";
			for(int x = 0; x < sizeX; x++) {
				s+=getVal(x, y)+"\t";
			}
			s+="|\n";
		}
		return s;
	}
}