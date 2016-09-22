package voxSpell.gui;

import voxSpell.stats.FinalResult;
import voxSpell.stats.WordBox;
import voxSpell.stats.WordListener;

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
