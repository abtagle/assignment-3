package voxSpell.stats;

/**
 * Referenced from Ian Warren's "Course Analyzer" program from SOFTENG251.
 * 
 * This creates a FinalResult object with assessment elements of attempted and results
 * @author Minha
 *
 */
public class FinalResult {
	
	public enum AssessmentElement {
		Attempted, Failed, Faulted, Mastered
	};

	public final String _word;
	private int _attempted;
	private int _failed;
	private int _faulted;
	private int _mastered;

	/**
	 * Creates a word result object with the given arguments.
	 */
	public FinalResult(String word,int attempt, int failed, int faulted, int mastered) {
		this._word = word;
		this._attempted = attempt;
		this._failed = failed;
		this._faulted = faulted;
		this._mastered = mastered;
	}

	/**
	 * Returns a word specified assessment element.
	 */
	public int getAssessmentElement(AssessmentElement element) {
		if (element == AssessmentElement.Failed)
			return _failed;
		else if (element == AssessmentElement.Faulted)
			return _faulted;
		else if (element == AssessmentElement.Mastered)
			return _mastered;
		else {
			return _attempted;
		}
	}

	/**
	 * Operation to set a student's specified assessment element.
	 */
	void setAssessmentElement(AssessmentElement element, int mark, int level) {
		if (element == AssessmentElement.Failed)
			_failed = mark;
		else if (element == AssessmentElement.Faulted)
			_faulted = mark;
		else if (element == AssessmentElement.Mastered)
			_mastered = mark;
		else {
			_attempted = mark;
		}

	}

}
