package voxSpell.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import voxSpell.quiz.Lists;

/**
 * Referenced from Ian Warren's "Course Analyzer" program from SOFTENG251.
 * 
 * This creates a Statistics Panel Object which calculates the average of the chosen
 * level and overall median of the quizzed word.
 * @author Minha
 *
 */
@SuppressWarnings("serial")
public class MathsPanel extends JPanel {
	/* TextFields to display the statistics. */
	private JTextField _averageField, _medianField;

	/**
	 * Creates a StatisticsPanel object.
	 */
	public MathsPanel() {
		setupPanel();
	}

	public void compute() {
		double average = getAverage();
		String median = getMedian();
		
		/*
		 * Update the textfields with the new statistics values.
		 */
		_averageField.setText(average + "%");
		_medianField.setText(median);
	}


	/**
	 * Returns the average of a series of int values.
	 */
	private double getAverage() {
		int level = StatisticsFrame._levelSelected;
		if (level - 1 < 0) {
			return Lists.getInstance().getAverageDoubleScore(level);
		}
		else {
			return Lists.getInstance().getAverageDoubleScore(level - 1);
		}
	}

	/**
	 * Returns the median value for a series of values.
	 */
	private String getMedian() {
		int failed = Lists.getInstance().getFailed().length();
		int faulted = Lists.getInstance().getFaulted().length();
		int mastered = Lists.getInstance().getMastered().length();

		if (failed == 0 && faulted == 0 && mastered == 0) {
			return "No median overall";
		}
		else if(failed > faulted && failed > mastered){
			return "Most failed overall";
		}else if(faulted > mastered && faulted > failed){
			return "Most faulted overall";
		}else{
			return "Most mastered overall";
		}
		
		
	}
	
	/**
	 * Sets up the GUI for this StatisticsView object.
	 */
	private void setupPanel() {
		JLabel averageLabel = new JLabel("Average");
		JLabel medianLabel = new JLabel("Median");

		_averageField = new JTextField();
		_averageField.setEditable(false);
		_medianField = new JTextField();
		_medianField.setEditable(false);

		JPanel labelPane = new JPanel();
		labelPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		labelPane.setLayout(new GridLayout(0, 1));
		labelPane.add(averageLabel);
		labelPane.add(medianLabel);

		JPanel valuePane = new JPanel();
		valuePane.setLayout(new GridLayout(0, 1));
		valuePane.add(_averageField);
		valuePane.add(_medianField);

		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new BorderLayout());
		this.add(labelPane, BorderLayout.WEST);
		this.add(valuePane, BorderLayout.CENTER);
	}
}
