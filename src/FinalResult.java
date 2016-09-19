package statsonly;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.zip.DataFormatException;


public class FinalResult {
	
	public enum AssessmentElement {
		Tried, Failed, Faulted, Mastered
	};

	public final String _word;
	private int _tried;
	private int _failed;
	private int _faulted;
	private int _mastered;

	/**
	 * Creates a word result object with the given arguments.
	 */
	public FinalResult(String word,int tried, int failed, int faulted, int mastered) {
		this._word = word;
		this._tried = tried;
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
			return _tried;
		}
	}

	/**
	 * Returns a String representation of a StudentResult object.
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer(_word);

		buffer.append(", Tried:");
		buffer.append(_tried);
		buffer.append(", failed: ");
		buffer.append(_failed);
		buffer.append(", faulted: ");
		buffer.append(_faulted);
		buffer.append(", mastered: ");
		buffer.append(_mastered);
		return buffer.toString();
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
			_tried = mark;
		}

	}

}
