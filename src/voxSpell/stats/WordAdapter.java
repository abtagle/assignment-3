package voxSpell.stats;

import javax.swing.table.AbstractTableModel;
import voxSpell.stats.FinalResult.AssessmentElement;


@SuppressWarnings("serial")
public class WordAdapter  extends AbstractTableModel implements WordListener {

	/* Ordered column header names. */
	private static String[] columnNames = { "Word", "Attempts", "Failed", "Faulted", "Mastered" };

	private WordBox _words;

	public WordAdapter(WordBox words) {
		_words = words;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return _words.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		FinalResult result = (FinalResult) _words.getResultAt(row);
		Object value = null;

		/*
		 * Get the Result attribute at the specified column.
		 */
		switch (col) {
		case 0: // word
			value = result._word;
			break;
		case 1: // tried.
			value = result
					.getAssessmentElement(FinalResult.AssessmentElement.Attempted);
			break;
		case 2: // failed.
			value = result
					.getAssessmentElement(FinalResult.AssessmentElement.Failed);
			break;
		case 3: // faulted.
			value = result
					.getAssessmentElement(FinalResult.AssessmentElement.Faulted);
			break;
		case 4: // mastered.
			value = result
					.getAssessmentElement(FinalResult.AssessmentElement.Mastered);
			break;
		}
		return value;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}



	@Override
	public Class<?> getColumnClass(int col) {
		Class<?> columnClass = null;

		if (col == 0) {
			columnClass = String.class;
		}
		if (col == 1 || col == 2 || col == 3 || col == 4) {
			columnClass = Integer.class;
		}
		return columnClass;
	}
	
	/**
	 * Updates the value of an exam, test or assignment mark for a particular
	 * StudentResult.
	 * @param value the new mark value, expected to be a Percentage object.
	 * @param row the row within the table that identifies the StudentResult
	 *        that is to be changed.
	 * @param col the column within the table that identifies the mark to be
	 *        changed (exam, test or assignment).
	 */
	@Override
	public void setValueAt(Object value, int row, int col) {
		if(value instanceof Integer) {
			int newMark = (int)value;
			FinalResult result = _words.getResultAt(row);
			
			switch(col) {
			case 3:
				_words.updateStudentResult(result, AssessmentElement.Failed, newMark);
				System.out.println("Processing mark edit");
				break;
			case 4:
				_words.updateStudentResult(result, AssessmentElement.Faulted, newMark);
				break;
			case 5:
				_words.updateStudentResult(result, AssessmentElement.Mastered, newMark);
				break;
			}
		}
	}
	
	@Override
	public void WordsHaveChanged(WordBox word) {

		fireTableDataChanged();
	}

	@Override 
	public void WordsHaveChanged(WordBox word, FinalResult result) {
		int index = _words.indexOf(result);
		this.fireTableRowsUpdated(index, index);
	}

}
