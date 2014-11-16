package DataObjects.Huffman;

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
}