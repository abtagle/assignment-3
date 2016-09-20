package voxSpell.gui;

import java.util.Iterator;

import voxSpell.quiz.Lists;
import voxSpell.stats.FinalResult;
import voxSpell.stats.WordBox;
import voxSpell.stats.WordListener;


public class DistributionPanelAdapter implements WordListener {
	private Lists _lists;

	private DistributionPanel _adaptee;

	public DistributionPanelAdapter(DistributionPanel view) {
		_adaptee = view;
	}

	@Override
	public void WordsHaveChanged(WordBox word) {
		int[] distribution = new int [ _lists.getNumberOfLevels() ];
		
		for (Iterator<FinalResult> i = word.iterator(); i.hasNext();) {
			FinalResult result = i.next();
			int attempts = result.getAssessmentElement(FinalResult.AssessmentElement.Attempted);

			if (attempts == 10) {
				distribution[9]++;
			} else {
				distribution[attempts]++;
			}
		}
		
		_adaptee.compute(distribution, word.size());
	}
	
	@Override 
	public void WordsHaveChanged(WordBox course, FinalResult result) {
		WordsHaveChanged(course);
	}

}
