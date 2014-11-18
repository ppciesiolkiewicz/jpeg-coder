package DataObjects.Huffman;

import java.util.HashMap;
import java.util.Map;

public class HuffmanTable<Word,Code> {
	Map<Word,Code> wordCode = new HashMap<Word,Code>();
	Map<Code,Word> codeWord = new HashMap<Code,Word>();
	
	public void put(Word word,Code code){
		wordCode.put(word, code);
		codeWord.put(code, word);
	}

	public Code getCode (Word word){
		return wordCode.get(word);
	}
	
	public Word getWord (Code code){
		return codeWord.get(code);
	}
}
