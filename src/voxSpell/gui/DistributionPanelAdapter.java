package voxSpell.gui;


import voxSpell.quiz.Lists;
import voxSpell.stats.FinalResult;
import voxSpell.stats.WordBox;
import voxSpell.stats.WordListener;


public class DistributionPanelAdapter implements WordListener {

	private DistributionPanel _adaptee;

	public DistributionPanelAdapter(DistributionPanel view) {
		_adaptee = view;
	}

	@Override
	public void WordsHaveChanged(WordBox word) {
		double[] distribution = new double [ Lists.getInstance().getNumberOfLevels() ];
		
		for (int i = 0; i < Lists.getInstance().getNumberOfLevels(); i++)  {
				distribution[i] = Lists.getInstance().getAverageDoubleScore(i);
		}
		
		_adaptee.compute(distribution);
	}
	
	@Override 
	public void WordsHaveChanged(WordBox course, FinalResult result) {
		WordsHaveChanged(course);
	}

}
