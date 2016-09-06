package voxSpell.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import voxSpell.quiz.NewQuiz;
import voxSpell.quiz.Quiz;
import voxSpell.quiz.Review;

public class QuizScreen {
	protected String _name;
	protected boolean _isReview = false;
	protected JLabel _wordNumber = null;
	protected JLabel _title = null;
	protected JTextField _spellingBar = null;
	protected JButton _submit = null;
	protected JLabel _correct = null;
	protected int _wordNumberInt;
	protected Quiz _currentQuiz = null;
	
	public QuizScreen(String name, boolean review){
		_name = name;
		_isReview = review;
		
		addComponentsToPane();
		if (_isReview){
			_currentQuiz = new Review(GUI.getLevel());
		} else{
			_currentQuiz = new NewQuiz(GUI.getLevel());
		}
	}

	protected void addComponentsToPane() {
		Container pane = GUI.getInstance().getContentPane();
		pane.removeAll();
		pane.setLayout(new GridLayout(5,0));
		_title = new JLabel(_name, JLabel.CENTER);
		pane.add(_title);
		//From: http://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
		_title.setFont(GUI.TITLE_FONT);
		_wordNumber = new JLabel("", JLabel.CENTER);
		pane.add(_wordNumber);
		_spellingBar = new JTextField();
		_spellingBar.setText("Spell words here");
		pane.add(_spellingBar);
		_correct = new JLabel();
		_submit = new JButton("Check Spelling");
		GUI.getInstance().getFrame().getRootPane().setDefaultButton(_submit);
		pane.add(_submit);
		GUI.getInstance().getFrame().repaint();
		_submit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event) {
			GUI.getInstance().getFrame().setVisible(false);
			GUI.getInstance().getContentPane().removeAll();
			new WelcomeScreen();
			}
		});

	}
}
