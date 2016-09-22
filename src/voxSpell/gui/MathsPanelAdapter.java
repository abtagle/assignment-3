package voxSpell.gui;

import voxSpell.stats.FinalResult;
import voxSpell.stats.WordBox;
import voxSpell.stats.WordListener;

/**
 * Referenced from Ian Warren's "Course Analyzer" program from SOFTENG251.
 * 
 * This MathsAdapter listens to the change in words/quizzes and computes
 * @author Minha
 *
 */
public class MathsPanelAdapter implements WordListener {

	private MathsPanel _adaptee;

	public MathsPanelAdapter(MathsPanel view) {
		_adaptee = view;
	}

	@Override
	public void WordsHaveChanged(WordBox word) {
		_adaptee.compute();
	}
	
	@Override 
	public void WordsHaveChanged(WordBox word, FinalResult result) {
		WordsHaveChanged(word);
	}

}
