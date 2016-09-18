package voxSpell.quiz;

import voxSpell.gui.QuizScreen;

public class Review extends Quiz {

	public Review(int level, QuizScreen screen) {
		super(level, screen, true);
	}
	//hook method implemented to spell word aloud if the word is failed, and give another opportunity to spell it
	protected void spellAloud(String word) {
		_attemptNumber = 3;
		char[] wordAsCharArray = _wordlist.get(_wordNumberInt-1).toCharArray();
		try {
			new SayAnything(_wordlist.get(_wordNumberInt-1)+" is spelt.").execute();
			for(char i : wordAsCharArray){
				if(i=='a'){
					new SayAnything("ay").execute();
				} else {
					new SayAnything(i+"").execute();
				}
			}
			new SayAnything("Please spell").execute();
			quizQuestion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

