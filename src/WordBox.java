package statsonly;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import statsonly.FinalResult.AssessmentElement;
/**
 * Class to represent a collection of StudentResult instances.
 * 
 * @author Ian Warren
 * 
 */
public class WordBox {
	private Hashtable<String, FinalResult> _results;
	private List<FinalResult> _indexedResults;
	private List<WordListener> _listeners;
	private int _level;

	public WordBox() {
		_results = new Hashtable<String, FinalResult>();
		_indexedResults = new ArrayList<FinalResult>();
		_listeners = new ArrayList<WordListener>();
	}

	public void addResult(FinalResult result) {
		_results.put(new String(result._word), result);
		_indexedResults = new ArrayList<FinalResult>(_results.values());
		
		// Notify listeners.
		for(WordListener listener : _listeners) {
			listener.WordsHaveChanged(this);
		}
	}

	public void updateStudentResult(FinalResult result, AssessmentElement element, int mark) {
		result.setAssessmentElement(element, mark, _level);
		
		// Notify listeners.
		for(WordListener listener : _listeners) {
			listener.WordsHaveChanged(this, result);
		}
	}

	public FinalResult getResult(int studentID) {
		Integer key = new Integer(studentID);
		return _results.get(key);
	}

	public FinalResult getResultAt(int index) {
		if (index < 0 || index >= _results.size()) {
			return null;
		} else {
			return _indexedResults.get(index);
		}
	}

	public int indexOf(FinalResult result) {
		return _indexedResults.indexOf(result);
	}

	public Iterator<FinalResult> iterator() {
		return _indexedResults.iterator();
	}

	public int size() {
		return _results.size();
	}

	public void setAssessmentPolicy(int level) {
		this._level = level;
		
		// Necessary?
		for(WordListener listener : _listeners) {
			listener.WordsHaveChanged(this);
		}
	}

	public void addWordListener(WordListener listener) {
		_listeners.add(listener);
	}

	public void removeWordListener(WordListener listener) {
		_listeners.remove(listener);
	}
}