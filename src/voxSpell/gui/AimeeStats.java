package voxSpell.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import voxSpell.quiz.Lists;

public class AimeeStats {
	JFrame _frame;
	private JPanel _leftPanel;
	private JPanel _rightPanel;
	JLabel _title;
	JButton _refresh;
	private TreeSet<String> _testedWordsSet;
	
	public AimeeStats(){
		//Get set of all words
		_testedWordsSet = new TreeSet<String>();
		_testedWordsSet.addAll(Lists.getInstance().getFailed().returnArrayList());
		_testedWordsSet.addAll(Lists.getInstance().getFaulted().returnArrayList());
		_testedWordsSet.addAll(Lists.getInstance().getMastered().returnArrayList());
		
		_leftPanel = new JPanel();
		_leftPanel.setLayout(new GridLayout(GUI.NUMBER_OF_LEVELS+1,0));
		_rightPanel = new JPanel();
		_rightPanel.setLayout(new GridLayout(1,0));
		_frame = new JFrame("Statistics");
		_frame.setSize(600, 200);
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setVisible(true);
		
		addComponents(_frame.getContentPane());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			}
		});
	}
	private void addComponents(Container pane){
		pane.removeAll();
		pane.setLayout(new GridLayout(0,2));
		_title = new JLabel("Statistics", JLabel.CENTER);
		_leftPanel.add(_title);
		for(int i = 0; i < GUI.NUMBER_OF_LEVELS; i++){
			_leftPanel.add(new JLabel("Level " + (i+1) + " Average Quiz Score: " + Lists.getInstance().getAverageScore(i)));
		}
		//Set up mastered/faulted/failed table and add to right panel
		_rightPanel.add(new JScrollPane(table()));
		
		pane.add(_leftPanel);
		pane.add(_rightPanel);
	}
	private JTable table(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 50;
		c.gridx = 50;
		c.gridy = 1;
		String[] columnNames = {"Word", "Mastered Frequency", "Faulted Frequency", "Failed Frequency"};
		String[][] statisticsArray = new String[_testedWordsSet.size()][4];
		int row = 0;
		for(String i :_testedWordsSet){
			statisticsArray[row][0] = i;
			statisticsArray[row][1] = ""+Lists.getInstance().getMastered().frequencyOf(i);
			statisticsArray[row][2] = ""+Lists.getInstance().getFaulted().frequencyOf(i);
			statisticsArray[row][3] = ""+Lists.getInstance().getFailed().frequencyOf(i);
			row++;
		}
		TableModel statisticsModel = new DefaultTableModel(statisticsArray, columnNames);
		//From: http://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
		JTable statisticsTable = new JTable(statisticsModel){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		return statisticsTable;
}
}
