package voxSpell.stats;

public interface WordListener {

	
	void WordsHaveChanged(WordBox word);
	
	
	void WordsHaveChanged(WordBox word, FinalResult result);


}
