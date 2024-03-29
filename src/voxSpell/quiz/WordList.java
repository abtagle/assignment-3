package voxSpell.quiz;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing a word list, storing words with a backing ArrayList<String>
 * Convenient due to the operations required to occur on lists of words.
 * 
 * Taken from Softeng 206 Assignment 2 submission by Aimee Tagle
 * Last Modified: During 19 September, 2016
 * 
**/
public class WordList {
	public static final int QUIZ_SIZE = 10;
	private ArrayList<String> _words;
	
	public WordList(){
		_words = new ArrayList<String>();
	}
	
	public void addWord(String word){
		_words.add(word);
	}
	
	public ArrayList<String> returnTestlist(){
		@SuppressWarnings("unchecked")
		ArrayList<String> shufWords = (ArrayList<String>) _words.clone();
		Collections.shuffle(shufWords);
		ArrayList<String> returnList = new ArrayList<String>();
		int i = 0;
		while(i < QUIZ_SIZE && i < shufWords.size()){
			returnList.add(shufWords.get(i));
			i++;
		}
		return returnList;
	}
	public boolean contains(String word){
		return _words.contains(word);
	}
	public void remove(String word){
		_words.remove(word);
	}
	public ArrayList<String> returnArrayList(){
		return _words;
	}
	public int length(){
		return _words.size();
	}
	public int frequencyOf(String word){
		return Collections.frequency(_words, word);
	}
	public String getWord(int i){
		return _words.get(i);
	}
}
