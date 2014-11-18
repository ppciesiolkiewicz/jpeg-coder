package DataObjects.Huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FrequencyTable<E extends Number> {

	class MutableInt {
		int value = 1; // note that we start at 1 since we're counting

		public void increment() {
			++value;
		}

		public int get() {
			return value;
		}
	}

	private Map<E, MutableInt> freq = new HashMap<E, MutableInt>();

	public void insert(E key) {

		MutableInt count = freq.get(key);
		if (count == null) {
			freq.put(key, new MutableInt());
		} else {
			count.increment();
		}

	}

	public int getWeight(E key) {
		return freq.get(key).get();
	}
	
	public Set<E> getKeys() {
		return freq.keySet();
	}

}
