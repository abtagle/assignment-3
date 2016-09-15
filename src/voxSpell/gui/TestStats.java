package voxSpell.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class TestStats {
	private JLabel _title;
	private JLabel _score;
	private JButton _videoReward;
	private JButton _home;
	private JButton _stats;

	public TestStats(int score, int total){
		Container pane = GUI.getInstance().getContentPane();
		pane.setVisible(false);
		pane.setLayout(new GridLayout(5,0));
		pane.removeAll();
		_title = new JLabel("Congratulations", JLabel.CENTER);
		pane.add(_title);
		//From: http://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
		_title.setFont(GUI.TITLE_FONT);
		_score = new JLabel("You scored "+ score +" out of "+total, JLabel.CENTER);
		pane.add(_score);
		_stats = new JButton("View Stats");
		GUI.getInstance().getFrame().getRootPane().setDefaultButton(_stats);
		pane.add(_stats);
		_stats.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event) {
			new WelcomeScreen();			
		}
		});
		_home = new JButton("Return to Menu");
		GUI.getInstance().getFrame().getRootPane().setDefaultButton(_home);
		pane.add(_home);
		_home.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event) {
			new WelcomeScreen();			
		}
		});
		if(score >=9 ){
			_videoReward = new JButton("Play Video Reward");
			_videoReward.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event) {
				new VideoReward();
			}
			});
			pane.add(_videoReward);
		}
		GUI.getInstance().getFrame().repaint();

		pane.setVisible(true);
	}
}
