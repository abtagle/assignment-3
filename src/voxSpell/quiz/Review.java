package voxSpell.quiz;

import voxSpell.gui.QuizScreen;

public class Review extends Quiz {

	public Review(int level, QuizScreen screen) {
		super(level, screen);
	}
	//hook method implemented to spell word aloud if the word is failed, and give another opportunity to spell it
	protected void spellAloud(String word) {
		_attemptNumber = 3;
		char[] wordAsCharArray = _wordlist.get(_wordNumberInt-1).toCharArray();
		try {
			new SayAnything(_wordlist.get(_wordNumberInt-1)+" is spelt.").doInBackground();
			for(char i : wordAsCharArray){
				if(i=='a'){
					new SayAnything("ay").doInBackground();
				} else {
					new SayAnything(i+"").doInBackground();
				}
			}
			new SayAnything("Please spell").doInBackground();
			quizQuestion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void showStats() {
		// TODO Auto-generated method stub
		
	}

}

