package DataObjects.Huffman;

import java.util.Iterator;
import java.util.Stack;

public class HuffmanTree<E extends Number>{

	private int weight;
	private E value;
	private String code;
	private HuffmanTree<E> left, right;
	
	public HuffmanTree (E value, int weight){
		this.value=value;
		this.code="";
		this.weight=weight;
	}
	
	public HuffmanTree (HuffmanTree<E> leftChild, HuffmanTree<E> rightChild){
		left=leftChild;
		right=rightChild;
		weight=left.getWeight()+right.getWeight();

		left.addToCode('0');
		right.addToCode('1');
	}
	
	public int getWeight(){
		return weight;
	}
	
	public void addToCode(char binary){
		code= binary+code;
	}
	
	public String getCode(){
		return code;
	}
	
	public E getValue(){
		return value;
	}

	public PreOrder<HuffmanTree<E>> iterator() {
		return new PreOrder<HuffmanTree<E>>(this);
	}
	
	private class PreOrder<HuffmanTree<E>> implements Iterator<HuffmanTree<E>>{
		
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
