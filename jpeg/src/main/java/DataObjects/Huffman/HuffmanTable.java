package DataObjects.Huffman;

import java.util.HashMap;
import java.util.Map;

public class HuffmanTable<Word, Code> {
	Map<Word, Code> wordCode;
	Map<Code, Word> codeWord; 

	public HuffmanTable() {
		wordCode = new HashMap<Word, Code>();
		codeWord = new HashMap<Code, Word>();
	}
	
	public void put(Word word, Code code) {
		wordCode.put(word, code);
		codeWord.put(code, word);
	}

	public Code getCode(Word word) {
		return wordCode.get(word);
	}

	public Word getWord(Code code) {
		return codeWord.get(code);
	}

	@Override
	public String toString() {
		String s = "";

		for (Word k : wordCode.keySet())
			s += k.toString() + " " + wordCode.get(k) + "\n";
		return s;
	}
}
