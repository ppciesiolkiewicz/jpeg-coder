package DataObjects;
import java.util.Iterator;

public class ZigZagTileIterator<T extends Number> implements Iterator<T> {
		protected Tile<T> tile;
		protected Integer curPos;
		
		protected static Integer[] zigZagOrder = 
				{0,  1,  8, 16,  9,  2,  3, 10,
		         17, 24, 32, 25, 18, 11,  4,  5,
		         12, 19, 26, 33, 40, 48, 41, 34,
		         27, 20, 13,  6,  7, 14, 21, 28,
		         35, 42, 49, 56, 57, 50, 43, 36,
		         29, 22, 15, 23, 30, 37, 44, 51,
		         58, 59, 52, 45, 38, 31, 39, 46,
		         53, 60, 61, 54, 47, 55, 62, 63 };
		
		protected ZigZagTileIterator(Tile<T> t) {
			tile = t;
			curPos = -1;
		}
				
		public boolean hasNext() {
			int i = curPos;
			while(++i < zigZagOrder.length && tile.getVal(zigZagOrder[i]) == null)
				if(zigZagOrder[i] == tile.getLength())
					return false;
			
			return i < zigZagOrder.length;
		}

		public T next() {
			while(++curPos < zigZagOrder.length && tile.getVal(zigZagOrder[curPos]) == null)
				if(zigZagOrder[curPos]  >= tile.getLength())
					throw new RuntimeException();
			return tile.getVal(zigZagOrder[curPos]);
		}
		
		public void setNext(T value) {
			while(++curPos < zigZagOrder.length && tile.getVal(zigZagOrder[curPos]) == null)
				if(zigZagOrder[curPos]  >= tile.getLength())
					throw new RuntimeException();
			tile.setVal(zigZagOrder[curPos], value);
		}

		public void remove() {
			tile.setVal(zigZagOrder[curPos], null);
		}
	}