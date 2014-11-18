package DataObjects.Huffman;

import DataObjects.Binary;

public class HuffmanTree<E extends Number> {

	@Override
	public String toString() {
		return "[weight=" + weight + ", value=" + value + ", code="
				+ code + "]";
	}

	private int weight;
	private E value;
	private Binary code;
	private HuffmanTree<E> left, right;
<<<<<<< HEAD

	public HuffmanTree(E value, int weight) {
		this.value = value;
		this.code = "";
		this.weight = weight;
=======
	
	public HuffmanTree (E value, int weight){
		this.value=value;
		this.code=new Binary("");
		this.weight=weight;
>>>>>>> f28afa62e7f7c922fdbc8c29cfd5667cb343760b
	}

	public HuffmanTree(HuffmanTree<E> leftChild, HuffmanTree<E> rightChild) {
		left = leftChild;
		right = rightChild;
		weight = left.getWeight() + right.getWeight();

		left.addToCode('0');
		right.addToCode('1');
	}

	public int getWeight() {
		return weight;
	}
<<<<<<< HEAD

	public void addToCode(char binary) {
		code = binary + code;
	}

	public String getCode() {
=======
	
	public void addToCode(char binary){
		code.value= binary+code.value;
	}
	
	public Binary getCode(){
>>>>>>> f28afa62e7f7c922fdbc8c29cfd5667cb343760b
		return code;
	}

	public E getValue() {
		return value;
	}
<<<<<<< HEAD

	public PreOrder<HuffmanTree<E>> iterator() {
		return new PreOrder<HuffmanTree<E>>(this);
	}

	private class PreOrder<T extends HuffmanTree<E>> implements
			Iterator<HuffmanTree<E>> {

		Stack<HuffmanTree<E>> stack = new Stack<HuffmanTree<E>>();

		public PreOrder(HuffmanTree<E> root) {

			stack.push(root);
		}

		public boolean hasNext() {
			return !stack.isEmpty();
		}

		public HuffmanTree<E> next() {
			return null;
		}

		public void remove() {
			throw new UnsupportedOperationException();

		}
	}

}
=======
}
>>>>>>> f28afa62e7f7c922fdbc8c29cfd5667cb343760b
