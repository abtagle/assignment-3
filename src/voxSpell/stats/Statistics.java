package voxSpell.stats;

import voxSpell.quiz.Lists;
import voxSpell.quiz.WordList;

public class Statistics {
	private Lists _listOfWords;
	private WordList _wordList;
	
	public Statistics() {
	}
	
	public int numberOfFails(String word, int level) {
		_wordList = _listOfWords.getWordList(level);
		WordList failedOnly = _listOfWords.getFailed();
		int count = 0;
		if (failedOnly.contains(word)) {
			for (int i = 0; i < _wordList.length(); i++) {
				if (_wordList.getWord(i).equals(word)) {
					count++;
				}
			}
		}
		return count;
	}
	
	public int numberOfFaulted(String word, int level) {
		_wordList = _listOfWords.getWordList(level);
		WordList faultedOnly = _listOfWords.getFaulted();
		int count = 0;
		if (faultedOnly.contains(word)) {
			for (int i = 0; i < _wordList.length(); i++) {
				if (_wordList.getWord(i).equals(word)) {
					count++;
				}
			}
		}
		return count;
	}
	
	public int numberOfMastered(String word, int level) {
		_wordList = _listOfWords.getWordList(level);
		WordList masteredOnly = _listOfWords.getMastered();
		int count = 0;
		if (masteredOnly.contains(word)) {
			for (int i = 0; i < _wordList.length(); i++) {
				if (_wordList.getWord(i).equals(word)) {
					count++;
				}
			}
		}
		return count;
	}
	
}
