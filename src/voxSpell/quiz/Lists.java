package voxSpell.quiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import voxSpell.gui.GUI;
import voxSpell.gui.SelectLevel;

public class Lists {
	public static final String MASTERED = ".mastered";
	public static final String FAULTED = ".faulted";
	public static final String FAILED = ".failed";
	public static final String LAST_FAILED = ".lastFailed";
	public static final String WORDLIST = "/wordlist.txt";
	private ArrayList<WordList> _wordLists = null;
	private WordList _mastered;
	private WordList _faulted;
	private WordList _failed;
	private WordList _lastFailed;
	private ArrayList<ArrayList<Integer>> _scores;
	private static Lists _thisList = null;

	private Lists(){
		//Reads in all the statistics storing lists if they already  exist
		_thisList = this;

		//No need to save data between sessions for assignment 3
		_mastered = new WordList();
		_faulted =  new WordList();
		_failed =  new WordList();
		_lastFailed =  new WordList();

		/*_mastered = readInFile(MASTERED);
		_faulted = readInFile(FAULTED);
		_failed = readInFile(FAILED);
		_lastFailed = readInFile(LAST_FAILED);*/
		//URL url = Lists.class.getResource(WORDLIST);
		//from: stackoverflow.com/questions/15359702/get-location-of-jar-file
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();

		if(path.contains(":")){
			URL url = Lists.class.getResource(WORDLIST);
			setWordList(new File(url.getPath()));
		} else{
			setWordList(new File(path+WORDLIST));
		}
	}

	public static Lists getInstance(){
		if (_thisList == null){
			return new Lists();
		} else{
			return _thisList;
		}
	}

	private WordList readInFile(String filename){
		WordList words = new WordList();
		File wordList = new File(filename);
		if(wordList.exists()){
			try{
				BufferedReader wordListRead = new BufferedReader(new FileReader(wordList));
				String word;
				while((word = wordListRead.readLine()) != null){
					if((word.equals("") == false && (word.equals("\\s+") == false))){
						words.addWord(word);
					}
				}
				wordListRead.close();	
				Collections.sort(words.returnArrayList(), String.CASE_INSENSITIVE_ORDER);

			} catch (FileNotFoundException e){
				JOptionPane.showMessageDialog(null, "Error: unable to load " + filename + ".");

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error: unable to read from word list " + filename + ".");
			}
		}
		return words;
	}
	//Reads in the wordlist file specified by the user
	public void setWordList(File file){
		_wordLists = new ArrayList<WordList>();
		if(file.exists()){
			try{
				BufferedReader wordListRead = new BufferedReader(new FileReader(file));
				String word;
				int currentList =-1;
				while((word = wordListRead.readLine()) != null){
					if((word.equals("") == false && (word.equals("\\s+") == false)&& word.charAt(0)!='%')){
						_wordLists.get(currentList).addWord(word);
					}else if (word.charAt(0)=='%'){
						_wordLists.add(new WordList());
						currentList++;
					}
				}
				wordListRead.close();	

			} catch (FileNotFoundException e){
				JOptionPane.showMessageDialog(null, "Error: unable to load " + file.getName() + ".");

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error: unable to read from word list " + file.getName() + ".");
			}
		}
	}

	protected void addScore(int score){
		_scores.get(GUI.getLevel()).add(score);
	}

	protected double getAverageScore(int level){
		int total = 0;
		if ((_scores.get(level)).size() == 0){
			return -1;
		}
		for(int i : _scores.get(level)){
			total += i;
		}
		return (double)total / (_scores.get(level).size());
	}

	public WordList getWordList(int level){
		return _wordLists.get(level);
	}
	public WordList getMastered(){
		return _mastered;
	}
	public WordList getFaulted(){
		return _faulted;
	}
	public WordList getFailed(){
		return _failed;
	}
	public WordList getLastFailed(){
		return _lastFailed;
	}
	public int getNumberOfLevels(){

		return _wordLists.size();
	}
	public void setUpScores(){
		_scores = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i<GUI.NUMBER_OF_LEVELS; i++){
			_scores.add(new ArrayList<Integer>());
		}
	}
	public void clearStats(){
		_mastered = new WordList();
		_faulted = new WordList();
		_failed = new WordList();
		_lastFailed = new WordList();
	}
	public void writeAllStats(){
		try {
			writeListToFiles(_mastered.returnArrayList(), MASTERED);
			writeListToFiles(_faulted.returnArrayList(), FAULTED);
			writeListToFiles(_failed.returnArrayList(), FAILED);
			writeListToFiles(_lastFailed.returnArrayList(), LAST_FAILED);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writeListToFiles(ArrayList<String> list, String filename) throws FileNotFoundException, UnsupportedEncodingException{
		//From: http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java
		PrintWriter writer = new PrintWriter(filename);
		for(String i : list){
			writer.println(i);
		}
		writer.close();

	}

}
