package voxSpell.gui;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import voxSpell.quiz.Lists;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class SelectLevel {
	private JLabel _title;

	protected SelectLevel(){	
		setLayout();
		_title = new JLabel("Select a spelling level to start on");
		_title.setFont(GUI.TITLE_FONT);
		_title.setHorizontalAlignment(JLabel.CENTER);
		GUI.getInstance().getContentPane().add(_title);
		DefaultComboBoxModel<Integer> selectModel = new DefaultComboBoxModel<Integer>();
		for(int i = 1; i <= GUI.NUMBER_OF_LEVELS; i++){
			selectModel.addElement(i);
		}
		JComboBox<Integer> select = new JComboBox<Integer>(selectModel);
		select.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
		JButton submitLevel = new JButton("Choose Level");
		submitLevel.setMaximumSize(new Dimension(500,100));
		GUI.getInstance().getContentPane().add(select);
		GUI.getInstance().getContentPane().add(submitLevel);
		
		submitLevel.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event) {
			GUI.getInstance().setLevel(select.getSelectedIndex());
			//Choose file with Level words
			JOptionPane.showMessageDialog(null, "Please select a file to read words from", "Select a wordlist", JOptionPane.INFORMATION_MESSAGE);
		    //From: https://docs.oracle.com/javase/7/docs/api/javax/swing/JFileChooser.html
			JFileChooser chooser = new JFileChooser();
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		            Lists.getInstance().setWordList(chooser.getSelectedFile());
		    }
		    new WelcomeScreen();
		}
		});
		
	}
	private void setLayout(){
		Container pane = GUI.getInstance().getContentPane();
		pane.setLayout(new GridLayout(3,0));
	}

}