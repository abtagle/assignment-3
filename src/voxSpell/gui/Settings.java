package voxSpell.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Settings {
	private JLabel _title;
	
	public Settings(){
		GUI.getInstance().getContentPane().setVisible(false);
		setLayout();
		_title = new JLabel("Settings");
		_title.setFont(GUI.TITLE_FONT);
		_title.setHorizontalAlignment(JLabel.CENTER);
		GUI.getInstance().getContentPane().add(_title);

		JLabel SelectVoice = new JLabel("Select the voice you want to use");
		_title.setHorizontalAlignment(JLabel.CENTER);
		GUI.getInstance().getContentPane().add(SelectVoice);
		
		//Get the names of folders dictating available voices
		//Code modified from: http://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
		File pathToVoices = new File("/usr/share/festival/voices/english");
		File[] listOfVoices = pathToVoices.listFiles();
		//Create a model for JComboBox to select voice
		DefaultComboBoxModel<String> selectModel = new DefaultComboBoxModel<String>();
		for (File i : listOfVoices) {
			selectModel.addElement(i.getName());
		}
		JComboBox<String> select = new JComboBox<String>(selectModel);
		//select.addActionListener(arg0);
		GUI.getInstance().getContentPane().add(select);
		GUI.getInstance().getFrame().repaint();
		GUI.getInstance().getContentPane().setVisible(true);
	}
	
	private void setLayout(){
		Container pane = GUI.getInstance().getContentPane();
		pane.removeAll();
		pane.setLayout(new GridLayout(5,0));
	}

}
