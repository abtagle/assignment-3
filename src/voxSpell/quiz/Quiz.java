package voxSpell.quiz;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import voxSpell.gui.GUI;

public abstract class Quiz{
	protected String _name;
	protected boolean _isReview = false;
	protected ArrayList<String> _wordlist = null;
	protected JButton _submit = null;
	protected int _attemptNumber;
	protected int _wordNumberInt;

	public Quiz(int level){
		_wordNumberInt = 1;
		_attemptNumber = 1;
		//check which list to get words from
		if(_isReview){
			_wordlist = Lists.getInstance().getLastFailed().returnTestlist();
		}else{
			_wordlist = Lists.getInstance().getWordList(level).returnTestlist();
		}
		
		
	}
	public final void checkSpelling(String rawSpelling, JLabel wordLabel){
		if( rawSpelling.trim().equals("") == false){
			//make trim string
			String spelling = rawSpelling.trim();
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
							new SayAnything("Correct").doInBackground();
							wordLabel.setText("Spell word "+ _wordNumberInt + " of " +GUI.NUMBER_OF_LEVELS);
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
							_attemptNumber = 1;
							_wordNumberInt++;
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
						wordLabel.setText("Spell word "+ _wordNumberInt + " of " + GUI.NUMBER_OF_LEVELS);
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
			//If there are no words left to quiz, go back to main menu
		} else {
			showStats();
		}
	}
	
	protected abstract void showStats();

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

	//Hook method for spelling aloud implementation
	protected abstract void spellAloud(String word);

	//Says words in background (unnecessary SwingWorker, but implemented before I realised timing issues with festival
	//Hopefully can find a way to adapt this
	class SayAnything extends SwingWorker<Void, Void>{
		private String _word = null;
		private Process _process;
		public SayAnything(String anything){
			_word = anything;
		}

		@Override
		protected Void doInBackground() throws Exception {
			String sayCommand = "echo " + _word + "." + " | festival --tts";
			ProcessBuilder sayBuilder = new ProcessBuilder("/bin/bash", "-c", sayCommand);
			_process = sayBuilder.start();
			if(_wordNumberInt!=1 || _attemptNumber!=1){
				try {
					_process.waitFor();
				} catch (InterruptedException e) {
				}
			}
			return null;
		}

	}
}
