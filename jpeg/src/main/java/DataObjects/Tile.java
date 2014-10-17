package DataObjects;


public class Tile<E extends Number> {
	protected E[][] values;
	
	public Tile(E[][] vals) {
		if(vals.length == 0 || vals[0].length == 0)
			throw new IllegalTileSizeException();
		for(E[] v : vals)
			if(v.length != vals[0].length)
				throw new IllegalTileSizeException();
		this.setValues(vals);
	}

	
	public Tile(Tile<E> t) {
		this.setValues(t.getValues());
	}
	
	public E getVal(int x, int y) {
		return values[x][y];
	}
	
	public void setVal(int x, int y, E newVal) {
		
	}
	
	public E[][] getValues() {
		return values;
	}
	
	protected void setValues(E[][] values_) {
		this.values = values_;
	}

	public int getSizeX() {
		return values.length;
	}

	public int getSizeY() {
		return values[0].length;
	}
	
	public static class IllegalTileSizeException extends RuntimeException {
		private static final long serialVersionUID = 3122434195001134670L;

	}
}
