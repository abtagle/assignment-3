package voxSpell.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import voxSpell.quiz.Lists;

public class AimeeStats {
	JFrame _frame;
	JLabel _title;
	JButton _refresh;
	public AimeeStats(){
		_frame = new JFrame("Statistics");
		_frame.setSize(300, 200);
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
		pane.setLayout(new GridLayout(GUI.NUMBER_OF_LEVELS+1,0));
		pane.removeAll();
		_title = new JLabel("Statistics", JLabel.CENTER);
		pane.add(_title);
		for(int i = 0; i < GUI.NUMBER_OF_LEVELS; i++){
			pane.add(new JLabel("Level " + (i+1) + " Average Quiz Score: " + Lists.getInstance().getAverageScore(i)));
		}
		_refresh = new JButton("Refresh Statistics");
	}
}
