package voxSpell.quiz;

import voxSpell.gui.QuizScreen;

public class NewQuiz extends Quiz {
	public NewQuiz(int level, QuizScreen screen) {
		super(level, screen, false);
	}

	@Override
	protected void spellAloud(String word) {
		new SayAnything("Incorrect. ");
		_wordNumberInt++;
		_attemptNumber = 1;
		updateWordNumberInGUI();
		quizQuestion();
	}

}
