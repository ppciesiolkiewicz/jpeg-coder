package DataObjects;

import java.util.Iterator;

public interface ArrayTileInterface<E extends Number> extends Iterable<E> {
		public void setVal(int pos, E val);
		public E getVal(int pos);
		public E[] toArray();
		public int getLength();
		public Iterator<E> iterator();
}
