package voxSpell.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import voxSpell.stats.FinalResult;
import voxSpell.stats.LevelBox;
import voxSpell.quiz.Lists;
import voxSpell.stats.Statistics;
import voxSpell.stats.WordAdapter;
import voxSpell.stats.WordBox;
import voxSpell.gui.DistributionPanel;
import voxSpell.gui.DistributionPanelAdapter;
import voxSpell.gui.StatisticsPanel;
import voxSpell.gui.StatisticsPanelAdapter;

@SuppressWarnings("serial")
public class GUIStats extends JFrame{
private LevelBox _boxOfLevels;
private WordBox _boxOfWords;
private Lists _list;
private int _levelSelected;
private Statistics _stats;


	public GUIStats() {
		super("Statistics");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//words from level
		_boxOfWords = new WordBox();
		
		//level 
		_boxOfLevels = new LevelBox();
		_boxOfWords.setAssessmentPolicy(_boxOfLevels.getDefaultLevel());
		
		final DistributionPanel distributionPanel = new DistributionPanel();
		DistributionPanelAdapter graphView = new DistributionPanelAdapter(distributionPanel);
		
		final StatisticsPanel statsPanel = new StatisticsPanel();
		StatisticsPanelAdapter statsView = new StatisticsPanelAdapter(statsPanel);

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
		
		
	}
	
	public void buildGUI(JPanel distribution, JPanel statsPanel, JTable tableView, JPanel levelPanel) {
		JPanel right = new JPanel();
		right.setBorder(BorderFactory.createTitledBorder("Statistics"));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(distribution);
		right.add(Box.createRigidArea(new Dimension(10, 0)));
		right.add(statsPanel);
		
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(tableView);
		scroll.setBorder(BorderFactory.createTitledBorder("Results"));
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
	
	
	private class DataLoader extends SwingWorker<Void, FinalResult> {
		
		
		@Override
		protected Void doInBackground() {
			int level = _levelSelected;
			final int numberOfWordsToRead = _list.getWordList(level).length();
			int numberOfWordsRead = 0;
			
			setProgress(0);
			
			FinalResult result = null;
			
			do {
				try {
					//result = _stats.getAllStats;
					numberOfWordsRead++;
					
					if (result != null) {
						publish(result);
						
						/* Update the bound variable relating to the task's 
						 * progress. 
						 */
						setProgress( (int)((double)numberOfWordsRead / (double)numberOfWordsToRead * 100));
					}
					
				
					Thread.sleep(100);
				} catch(InterruptedException e) {
					// No action required.
				}
			} while (result != null);
		
			return null;
		}
		
		
		 @Override
	     protected void process(List<FinalResult> results) {
			 for(FinalResult result : results) {
				 _boxOfWords.addResult(result);
 
			 }
		 }
	}
	
	
	private class ProgressDialog extends JDialog implements PropertyChangeListener {
		private JProgressBar _progressBar;
		
		public ProgressDialog(JFrame parent) {
			super(parent);
			_progressBar = new JProgressBar(0,100);
			_progressBar.setPreferredSize(new Dimension(300,20));
			_progressBar.setString("Loading");
			_progressBar.setStringPainted(true);
			_progressBar.setValue(0);
			
			JPanel centre = new JPanel();
			centre.setBorder(BorderFactory.createEmptyBorder());
			centre.add(_progressBar);
			add(centre);
			pack();
			
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			setAlwaysOnTop(true);
			setLocationRelativeTo(null);
			setVisible(true);
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(evt.getPropertyName().equals("progress")) {
				int progress = (Integer)evt.getNewValue();
				_progressBar.setValue(progress);
				
				if(progress == 100) {
					this.dispose();
				}
			}
		}
	}
	
	
	//inner class to call level jcombobox
	private class LevelPanel extends JPanel {

		public LevelPanel() {
			ComboBoxModel<Integer> levels = new DefaultComboBoxModel<Integer>() {
				{//change to get no of levels
					for (int i = 1; i <= 10; i++) {
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
			}
		});
		
		this.setBorder(BorderFactory.createTitledBorder("Level"));
		this.add(levelComboBox);
		}
	}
	
	public int getLevel(int level) {
		return level;
	}
	
}