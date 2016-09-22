package voxSpell.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import voxSpell.quiz.Lists;
import voxSpell.stats.FinalResult;
import voxSpell.stats.LevelBox;
import voxSpell.stats.WordAdapter;
import voxSpell.stats.WordBox;

/**
 * Referenced from Ian Warren's "Course Analyzer" program from SOFTENG251.
 * 
 * This is the main JFrame for the statistics, created every time the viewStats in the WelcomeScreen
 * class is called.
 * @author Minha
 *
 */
@SuppressWarnings("serial")
public class StatisticsFrame extends JFrame{
private LevelBox _boxOfLevels;
private WordBox _boxOfWords;
public static int _levelSelected;
private TreeSet<String> _wordsTested;

	/*
	 * Creates and initializes the components and panels into the frame
	 */
	public StatisticsFrame() {
		super("Statistics");
		
		//words from level
		_boxOfWords = new WordBox();
		
		//level checks
		_boxOfLevels = new LevelBox();
		//set default policy
		_boxOfWords.setAssessmentPolicy(_boxOfLevels.getDefaultLevel());
		
		final DistributionPanel distributionPanel = new DistributionPanel();
		DistributionPanelAdapter graphView = new DistributionPanelAdapter(distributionPanel);
		
		final MathsPanel statsPanel = new MathsPanel();
		MathsPanelAdapter statsView = new MathsPanelAdapter(statsPanel);

		WordAdapter tableModel = new WordAdapter(_boxOfWords);
		
		_boxOfWords.addWordListener(graphView);
		_boxOfWords.addWordListener(statsView);
		_boxOfWords.addWordListener(tableModel);
		final JTable tableView = new JTable(tableModel);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				buildGUI(distributionPanel, statsPanel, tableView, new LevelPanel());
			}
		});
		
		DataLoader worker = new DataLoader();
		worker.execute();
		
	}
	/* Builds GUI frame and adds component to the frame */
	public void buildGUI(JPanel distribution, JPanel statsPanel, JTable tableView, JPanel levelPanel) {
		JPanel right = new JPanel();
		right.setBorder(BorderFactory.createTitledBorder("Results and Distribution of Levels"));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(distribution);
		right.add(Box.createRigidArea(new Dimension(10, 0)));
		right.add(statsPanel);
		
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(tableView);
		scroll.setBorder(BorderFactory.createTitledBorder("Words"));
		left.add(scroll);
		left.add(Box.createRigidArea(new Dimension(10, 0)));
		left.add(levelPanel);
		
		JPanel mainPane = new JPanel();
		mainPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.X_AXIS));
		mainPane.add(left);
		mainPane.add(Box.createRigidArea(new Dimension(10, 0)));
		mainPane.add(right);
		
		add(mainPane);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	/* creates a tree set of the words */
	private void getAllStats() {
		_wordsTested = new TreeSet<String>();
		_wordsTested.addAll(Lists.getInstance().getFailed().returnArrayList());
		_wordsTested.addAll(Lists.getInstance().getFaulted().returnArrayList());
		_wordsTested.addAll(Lists.getInstance().getMastered().returnArrayList());
	}
	
	/* Using SwingWorker, the words in the tree set are print out with their respective results */
	private class DataLoader extends SwingWorker<Void, FinalResult> {
		@Override
		protected Void doInBackground() {
			
			FinalResult insideResult = null;
			getAllStats();
			
			for(String i :_wordsTested){
				insideResult = null;
				int failed = Lists.getInstance().getFailed().frequencyOf(i);
				int faulted = Lists.getInstance().getFaulted().frequencyOf(i);
				int mastered = Lists.getInstance().getMastered().frequencyOf(i);
				int attempts = failed + faulted + mastered;
							
				insideResult = new FinalResult(i, attempts, failed, faulted, mastered);
				publish(insideResult);
			}
		
			return null;
		}
		
		
		 @Override
	     protected void process(List<FinalResult> results) {
			 for(FinalResult result : results) {
				 _boxOfWords.addResult(result);
				
			 }
		 }
	}
	
	

	//inner class to call level jcombobox
	private class LevelPanel extends JPanel {

		public LevelPanel() {
			ComboBoxModel<Integer> levels = new DefaultComboBoxModel<Integer>() {
				{//change to get no of levels
					for (int i = 1; i <= Lists.getInstance().getNumberOfLevels(); i++) {
						addElement(i);
					}
				}
			};

		final JComboBox<Integer> levelComboBox = new JComboBox<Integer>(levels);
		
		levelComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int levelNumberSelected = (int) levelComboBox.getSelectedItem();
				_levelSelected = _boxOfLevels.getLevel(levelNumberSelected);
				_boxOfWords.setAssessmentPolicy(_levelSelected);
				
				DataLoader worker = new DataLoader();
				worker.execute();
				
			}
		});
		
		this.setBorder(BorderFactory.createTitledBorder("Level"));
		this.add(levelComboBox);
		}
	}
	
}
