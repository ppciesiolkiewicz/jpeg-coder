package DataObjects.Huffman;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class HuffmanTreeList<E extends Number> extends LinkedList<HuffmanTree<E>>{
	
	public void add (E value,int weight){
		this.add(new HuffmanTree<E>(value, weight));
	}
	
	public HuffmanTree<E> getSmallestWeightElement(){
		int min = Integer.MAX_VALUE;
		HuffmanTree<E> smallest = null;
		
		for(HuffmanTree<E> tree : this){
			if (tree.getWeight() < min){
				min= tree.getWeight();
				smallest = tree;
			}
		}
		
		this.remove(smallest);
		return smallest;
	}
	
	
	

}
