package voxSpell.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


/**
 * Class to graphically present, using a bar chart, the distribution of a set of
 * data points. A DistributionPanel instance shows the relative number of points
 * across a number of bands (e.g. 10 percentage bands 0-10%, 11-20%, ... 90-100%).
 * 
 * DistributionPanel is very basic but it represents a general charting 
 * component that can be used in different applications.
 * 
 * @author Ian Warren
 * 
 */
@SuppressWarnings("serial")
public class DistributionPanel extends JPanel {

	/* The width of a bar in the bar chart. */
	private static final int BAR_WIDTH = 20;

	/* The maximum height of any bar. */
	private static final int BAR_CEILING = 120;

	/* The gap between bars. */
	private static final int HORIZONTAL_GAP = 5;

	/*
	 * Left, right, top and bottom margin size; the border around the bar chart.
	 */
	private static final int MARGIN = 10;
	
	private static final int DEFAULT_NUMBER_OF_BARS = 10;

	/*
	 * The number to multiply the height of bars by to ensure that the highest
	 * bar uses the full height of the area allocated for drawing. The highest
	 * bar should be BAR_CEILING pixels high.
	 */
	private double _barHeightMultiplier;

	private int _numberOfBars;
	
	private int[] _distribution;

	/**
	 * Creates a DistributionPanel object.
	 * 
	 */
	public DistributionPanel(int numberOfBars) {
		_numberOfBars = numberOfBars;
	}
	
	public DistributionPanel() {
		this(DEFAULT_NUMBER_OF_BARS);
	}

	/**
	 * Returns the preferred size of this JComponent. The preferred size is the
	 * maximum drawing area required to display the bar chart.
	 */
	public Dimension getPreferredSize() {
		int width = (HORIZONTAL_GAP * (_numberOfBars - 1)) + (BAR_WIDTH * _numberOfBars) + (MARGIN * 2);
		int height = (MARGIN * 2) + BAR_CEILING;
		return new Dimension(width, height);
	}

	public void compute(int[] distribution, int numberOfDataPoints) {
		_distribution = distribution;
		repaint();
	}
	
	/**
	 * Refreshes the appearance of this JComponent. This method performs the
	 * actual drawing of the bar chart.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(_distribution == null) {
			return;
		}

		/* Normalise distribution. */
		int highestNormalisedValue = 0;
		for (int i = 0; i < _distribution.length; i++) {
			_distribution[i] = (int) ((double) _distribution[i] / 84 * 100);
			if (i == 0) {
				highestNormalisedValue = _distribution[0];
			} else {
				highestNormalisedValue = Math.max(highestNormalisedValue,
						_distribution[i]);
			}
		}
		_barHeightMultiplier = (BAR_CEILING / (double) highestNormalisedValue);

		int x = MARGIN;

		for (int i = 0; i < _distribution.length; i++) {
			g.setColor(Color.red);
			g.fillRect(x, BAR_CEILING + MARGIN
					- (int) (_distribution[i] * _barHeightMultiplier), BAR_WIDTH,
					(int) (_distribution[i] * _barHeightMultiplier));

			g.setColor(Color.black);
			g.drawLine(x, BAR_CEILING + MARGIN, x + BAR_WIDTH - 1, BAR_CEILING
					+ MARGIN);

			x += BAR_WIDTH + HORIZONTAL_GAP;
		}
	}

}