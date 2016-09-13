package voxSpell.quiz;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import voxSpell.gui.GUI;
import voxSpell.gui.QuizScreen;

public abstract class Quiz{
	protected String _name;
	protected int _score;
	protected boolean _isReview = false;
	protected ArrayList<String> _wordlist = null;
	protected JButton _submit = null;
	protected int _attemptNumber;
	protected int _wordNumberInt;
	protected QuizScreen _screen;

	public Quiz(int level, QuizScreen screen){
		_wordNumberInt = 1;
		_attemptNumber = 1;
		_screen = screen;
		//check which list to get words from
		if(_isReview){
			_wordlist = Lists.getInstance().getLastFailed().returnTestlist();
		}else{
			_wordlist = Lists.getInstance().getWordList(level).returnTestlist();
		}


	}
	public final void checkSpelling(String rawSpelling){
		if( rawSpelling.trim().equals("") == false){
			//make trim string
			String spelling = rawSpelling.trim().toLowerCase();
			if(containsInvalidCharacters(spelling) == false){
				//first attempts
				try{
					if(_attemptNumber == 1){
						if(spelling.equals(_wordlist.get(_wordNumberInt-1).toLowerCase())){
							Lists.getInstance().getMastered().addWord(_wordlist.get(_wordNumberInt-1));
							if(Lists.getInstance().getLastFailed().contains(_wordlist.get(_wordNumberInt-1))){
								Lists.getInstance().getLastFailed().remove(_wordlist.get(_wordNumberInt-1));
							}
							_wordNumberInt++;
							_score++;
							new SayAnything("Correct").doInBackground();
							updateWordNumberInGUI();
						} else{
							_attemptNumber++;
							new SayAnything("Incorrect. Please try again.").doInBackground();
						}
						quizQuestion();
						//Second attempt- failed or faulted
					} else if (_attemptNumber == 2){
						if(spelling.equals(_wordlist.get(_wordNumberInt-1).toLowerCase())){
							new SayAnything("Correct").doInBackground();
							Lists.getInstance().getFaulted().addWord(_wordlist.get(_wordNumberInt-1));
							if(Lists.getInstance().getLastFailed().contains(_wordlist.get(_wordNumberInt-1))){
								Lists.getInstance().getLastFailed().remove(_wordlist.get(_wordNumberInt-1));
							}
							_score++;
							_attemptNumber = 1;
							_wordNumberInt++;
							updateWordNumberInGUI();
							quizQuestion();
						} else{
							new SayAnything("Incorrect.").doInBackground();
							Lists.getInstance().getFailed().addWord(_wordlist.get(_wordNumberInt-1));
							if(Lists.getInstance().getLastFailed().contains(_wordlist.get(_wordNumberInt-1))==false){
								Lists.getInstance().getLastFailed().addWord(_wordlist.get(_wordNumberInt-1));
							}
							spellAloud(_wordlist.get(_wordNumberInt-1));
						}
						//Third attempt for review - no change to word status
					} else{
						if(spelling.equals(_wordlist.get(_wordNumberInt-1).toLowerCase())){
							new SayAnything("Correct").doInBackground();
						} else{
							new SayAnything("Incorrect").doInBackground();
						}
						_attemptNumber = 1;
						_wordNumberInt++;
						updateWordNumberInGUI();
						quizQuestion();
					}
				} catch (Exception e){

				}
			}else {
				JOptionPane.showMessageDialog(null, "Only alphabetical characters (a-z/A-Z) may be used for spelling.", "Review", JOptionPane.ERROR_MESSAGE);
			}

		} 
	}
	public final void quizQuestion(){
		//Only quiz if there are words left to quiz
		if(_wordNumberInt <=_wordlist.size()){
			try {
				sayWord();
				if(_attemptNumber == 2){
					sayWord();
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error saying word", "Quiz Error", JOptionPane.ERROR_MESSAGE);
			}
			//If there are no words left to quiz, show results
		} else {
			if(_isReview == false){
				if(_score >= 9 && GUI.getLevel()!=GUI.NUMBER_OF_LEVELS){
					//From http://stackoverflow.com/questions/8396870/joptionpane-yes-or-no-window
					int reply = JOptionPane.showConfirmDialog(null, "Congratulations! You scored " + _score + " out of " + _wordlist.size() + ". Would you like to LEVEL UP?", "Level up!", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						GUI.increaseLevel();
						JOptionPane.showMessageDialog(null, "You have now LEVELED UP", "Level up!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			Lists.getInstance().addScore(_score);
			showStats();
		}	
	}

	protected abstract void showStats();

	protected void updateWordNumberInGUI(){
		_screen.updateWordNumber(_wordNumberInt, _wordlist.size());
	}

	//Returns true if string has characters which are not letters
	protected final boolean containsInvalidCharacters(String word){
		char[] wordArray = word.trim().toCharArray();
		for	(char i : wordArray){
			if(i < 'a' || i > 'z'){
				return true;
			}
		}
		return false;
	}

	public void sayWord(){
		SayAnything word = new SayAnything(_wordlist.get(_wordNumberInt-1));
		try {
			word.doInBackground();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Hook method for spelling aloud implementation
	 */
	protected abstract void spellAloud(String word);

	public int getNumberOfWords(){
		return _wordlist.size();
	}



}
