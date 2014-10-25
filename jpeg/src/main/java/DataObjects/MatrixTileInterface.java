package DataObjects;


public interface MatrixTileInterface<E extends Number> {
		public E getVal(int x, int y);
		public void setVal(int x, int y, E newVal);
		public E[][] toMatrix();
		public int getSizeX();
		public int getSizeY();
}
