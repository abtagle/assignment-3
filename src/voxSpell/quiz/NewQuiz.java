package voxSpell.quiz;

import voxSpell.gui.QuizScreen;
import voxSpell.gui.TestStats;

public class NewQuiz extends Quiz {
	public NewQuiz(int level, QuizScreen screen) {
		super(level, screen);
	}

	@Override
	protected void spellAloud(String word) {
		_wordNumberInt++;
		_attemptNumber = 1;
		updateWordNumberInGUI();
		quizQuestion();
	}

	@Override
	protected void showStats() {
		new TestStats(_score);
	}

}
