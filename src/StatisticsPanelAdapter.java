package statsonly;

import java.util.Iterator;

public class StatisticsPanelAdapter implements WordListener {

	private StatisticsPanel _adaptee;

	public StatisticsPanelAdapter(StatisticsPanel view) {
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
