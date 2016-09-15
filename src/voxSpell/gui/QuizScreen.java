package voxSpell.gui;
//======================================================
//Source code generated by jvider v1.8.1 UNREGISTERED version.
//http://www.jvider.com/
//======================================================
import voxSpell.quiz.NewQuiz;
import voxSpell.quiz.Quiz;
import voxSpell.quiz.Review;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class QuizScreen{

	private String _name;
	private boolean _isReview;
	private Quiz _currentQuiz;
	private JPanel _panel;
	JLabel _progressWords;
	JTextField _spellingBar;
	JLabel _spellBelow;
	JButton _repeat;
	JButton _viewStats;
	JButton _submit;
	public QuizScreen(String name, boolean review){
		GUI.getInstance().getFrame().setVisible(false);
		_name = name;
		_isReview = review;
		if (_isReview){
			_currentQuiz = new Review(GUI.getLevel(), this);
		} else{
			_currentQuiz = new NewQuiz(GUI.getLevel(), this);
		}
		_panel = new JPanel();
		_panel.setPreferredSize(GUI.getFrameSize());
		addComponents();
		GUI.getInstance().getFrame().setContentPane(_panel);
		GUI.getInstance().getFrame().pack();
		GUI.getInstance().getFrame().setVisible(true);
		GUI.getInstance().getFrame().getRootPane().setDefaultButton(_submit);
		_currentQuiz.quizQuestion();
	} 
	private void addComponents(){
		_panel.setBorder(BorderFactory.createTitledBorder(_name));
		GridBagLayout gb_panel = new GridBagLayout();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		_panel.setLayout(gb_panel);

		_progressWords = new JLabel();
		updateWordNumber(1, _currentQuiz.getNumberOfWords());
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		gbc_panel.gridwidth = 20;
		gbc_panel.gridheight = 1;
		gbc_panel.fill = GridBagConstraints.NONE;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 1;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_progressWords, gbc_panel);
		_panel.add(_progressWords);

		_spellingBar = new JTextField();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 7;
		gbc_panel.gridwidth = 15;
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 0;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_spellingBar, gbc_panel);
		_panel.add(_spellingBar);
		
		_repeat = new JButton("Repeat");
		_repeat.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setUneditable();
				_currentQuiz.sayWord();
				setEditable();
			}
			
		});
		
		gbc_panel.gridx = 15;
		gbc_panel.gridy = 7;
		gbc_panel.gridwidth = 3;
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 0;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_repeat, gbc_panel);
		_panel.add(_repeat);

		_spellBelow = new JLabel("Spell Word Below");
		_spellBelow.setFont(GUI.TITLE_FONT);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		gbc_panel.gridwidth = 20;
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 1;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_spellBelow, gbc_panel);
		_panel.add(_spellBelow);

		_submit = new JButton("Submit Spelling");
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 10;
		gbc_panel.gridwidth = 20;
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 0;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_submit, gbc_panel);
		_submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setUneditable();
				_currentQuiz.checkSpelling(_spellingBar.getText());
				_submit.setEnabled(true);
				setEditable();
				_spellingBar.setText("");
			}
			
		});
		_panel.add(_submit);

		_viewStats = new JButton("View Stats");
		gbc_panel.gridx = 14;
		gbc_panel.gridy = 13;
		gbc_panel.gridwidth = 6;
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 0;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_viewStats, gbc_panel);
		_panel.add(_viewStats);

	}
	private void setUneditable(){
		_spellingBar.setEnabled(false);
		_repeat.setEnabled(false);
		_viewStats.setEnabled(false);
	}
	private void setEditable(){
		_spellingBar.setEnabled(true);
		_repeat.setEnabled(true);
		_viewStats.setEnabled(true);
	}
	public void updateWordNumber(int number, int total){
		_progressWords.setText("Word " + number+ " of " + _currentQuiz.getNumberOfWords());
	}
} 