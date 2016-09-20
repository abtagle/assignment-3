package voxSpell.stats;



import voxSpell.quiz.Lists;
import voxSpell.quiz.WordList;

public class Statistics {
	private Lists _listOfWords;
	private WordList _wordList;
	private int _level;
	private int _attempts;


	public Statistics() {
		_wordList = _listOfWords.getWordList(_level);

	}

	public void getLevel(int level) {
		_level = level;
	}
	
	public FinalResult next(int numberToRead) {
		FinalResult result = null;
		
		if (numberToRead >= _listOfWords.getNumberOfLevels()) {
			return null;
		}
		String line = _wordList.getWord(numberToRead);

		if (line == null) {
			//do nothing
			
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
