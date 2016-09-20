package voxSpell.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	public void compute(int[] series) {
		double average = getAverage(series);
		int median = getMedian(series);
		
		/*
		 * Update the textfields with the new statistics values.
		 */
		_averageField.setText(formatNumber(average));
		_medianField.setText(formatNumber(median));
	}


	/**
	 * Returns the average of a series of int values.
	 */
	private double getAverage(int[] series) {
		double total = 0;

		if (series.length == 0) {
			return 0;
		}

		for (int i = 0; i < series.length; i++) {
			total += series[i];
		}
		return total / series.length;
	}

	/**
	 * Returns the median value for a series of values.
	 */
	private int getMedian(int[] series) {
		if (series.length == 0) {
			return 0;
		}

		/* Sort the series. */
		Arrays.sort(series);
		return series[series.length / 2];
	}

	/**
	 * Returns a String representation of a decimal number. The representation
	 * is to two decimal places.
	 */
	private String formatNumber(double number) {
		DecimalFormat format = new DecimalFormat("0.##");
		return format.format(number);
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
