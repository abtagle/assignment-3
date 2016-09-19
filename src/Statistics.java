package statsonly;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class Statistics {
	private Lists _listOfWords;
	private WordList _wordList;
	private boolean _doesContain;
	private int _level;
	private int _attempts;

	public Statistics() {
		_wordList = _listOfWords.getWordList(_level);
		_doesContain = false;
	}

	public void getLevel(int level) {
		_level = level;
	}
	
	public FinalResult next(int numberToRead) {
		FinalResult result = null;
		if (numberToRead == _listOfWords.getNumberOfLevels()) {
			return null;
		}
		String line = _wordList.getWord(numberToRead);

		if (line == null) {
			//nothing
			
		} else {
			
			String word = line;
			int failed = numberOfFails(word);
			int faulted = numberOfFaults(word);
			int mastered = numberOfMasters(word);
			
			result = new FinalResult(word, _attempts, failed, faulted, mastered);
		}

		return result;
	}

	public int numberOfFails(String word) {
		WordList failedOnly = _listOfWords.getFailed();
		int count = 0;
		if (failedOnly.contains(word)) {
			_doesContain = true;
			for (int i = 0; i < _wordList.length(); i++) {
				if (_wordList.getWord(i).equals(word)) {
					count++;
					_attempts++;
				}
			}
		}
		return count;
	}

	public int numberOfFaults(String word) {
		WordList faultedOnly = _listOfWords.getFaulted();
		int count = 0;
		if (faultedOnly.contains(word)) {
			_doesContain = true;
			for (int i = 0; i < _wordList.length(); i++) {
				if (_wordList.getWord(i).equals(word)) {
					count++;
					_attempts++;
				}
			}
		}
		return count;
	}

	public int numberOfMasters(String word) {
		WordList masteredOnly = _listOfWords.getMastered();
		int count = 0;
		if (masteredOnly.contains(word)) {
			_doesContain = true;
			for (int i = 0; i < _wordList.length(); i++) {
				if (_wordList.getWord(i).equals(word)) {
					count++;
					_attempts++;
				}
			}
		}
		return count;
	}

}
