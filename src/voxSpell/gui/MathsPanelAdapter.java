package voxSpell.gui;

import java.util.Iterator;

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
		int[] overallMarks = new int[word.size()];

		int index = 0;
		for (Iterator<FinalResult> i = word.iterator(); i.hasNext(); index++) {
			FinalResult result = i.next();
			overallMarks[index] = result.getAssessmentElement(FinalResult.AssessmentElement.Mastered);
		}
		_adaptee.compute(overallMarks);
	}
	
	@Override 
	public void WordsHaveChanged(WordBox word, FinalResult result) {
		WordsHaveChanged(word);
	}

}
