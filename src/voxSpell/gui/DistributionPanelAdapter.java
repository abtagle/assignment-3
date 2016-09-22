package voxSpell.gui;


import voxSpell.quiz.Lists;
import voxSpell.stats.FinalResult;
import voxSpell.stats.WordBox;
import voxSpell.stats.WordListener;

/**
 * Referenced from Ian Warren's "Course Analyzer" program from SOFTENG251.
 * 
 * This reads the changes of the list(failed/faulted/mastered) and the average
 * and computes the changes to the bar graph
 * @author Minha
 *
 */
public class DistributionPanelAdapter implements WordListener {

	private DistributionPanel _adaptee;

	public DistributionPanelAdapter(DistributionPanel view) {
		_adaptee = view;
	}

	@Override
	public void WordsHaveChanged(WordBox word) {
		double[] distribution = new double [ Lists.getInstance().getNumberOfLevels() ];
		/* Adds the number of averages relative to their level. */
		for (int i = 0; i < Lists.getInstance().getNumberOfLevels(); i++)  {
				distribution[i] = Lists.getInstance().getAverageDoubleScore(i);
		}
		
		_adaptee.compute(distribution);
	}
	/* Goes to the method above */
	@Override 
	public void WordsHaveChanged(WordBox course, FinalResult result) {
		WordsHaveChanged(course);
	}

}
