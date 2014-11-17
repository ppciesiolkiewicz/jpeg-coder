package DataObjects.Huffman;

import DataObjects.Binary;

public class HuffmanTree<E extends Number>{

	private int weight;
	private E value;
	private Binary code;
	private HuffmanTree<E> left, right;
	
	public HuffmanTree (E value, int weight){
		this.value=value;
		this.code=new Binary("");
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
		code.value= binary+code.value;
	}
	
	public Binary getCode(){
		return code;
	}
	
	public E getValue(){
		return value;
	}
}