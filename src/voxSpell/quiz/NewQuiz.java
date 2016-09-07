package voxSpell.quiz;

public class NewQuiz extends Quiz {
	public NewQuiz(int level) {
		super(level);
	}

	@Override
	protected void spellAloud(String word) {
		_wordNumberInt++;
		_attemptNumber = 1;
		quizQuestion();
	}

	@Override
	protected void showStats() {
		// TODO Auto-generated method stub
		
	}

}
