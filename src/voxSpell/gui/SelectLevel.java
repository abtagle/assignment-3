package voxSpell.gui;

//======================================================
//Source code generated by jvider v1.8.1 UNREGISTERED version.
//http://www.jvider.com/
//
//Modified by Aimee Tagle 13/09/2016
//		Made to actually fit purpose- tool just used to get components in correct position
//======================================================
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
public class SelectLevel {

	private JPanel _panel;
	private JLabel _selectMessage;
	private JComboBox<Integer> _selectBox;
	private JButton _selectButton;
	private JLabel _image;

	public SelectLevel(){
		_panel = new JPanel();
		_panel.setPreferredSize(GUI.getFrameSize());
		_panel.setBorder(BorderFactory.createTitledBorder("Select your starting level"));
		GridBagLayout gb_panel = new GridBagLayout();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		_panel.setLayout(gb_panel);

		_selectMessage = new JLabel();
		GUI.getInstance();
		_selectMessage.setFont(GUI.TITLE_FONT);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		gbc_panel.gridwidth = 20;
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.NORTH;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 1;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_selectMessage, gbc_panel);
		_panel.add(_selectMessage);

		DefaultComboBoxModel<Integer> selectModel = new DefaultComboBoxModel<Integer>();
		for(int i = 1; i <= GUI.NUMBER_OF_LEVELS; i++){
			selectModel.addElement(i);
		}
		_selectBox = new JComboBox<Integer>(selectModel);
		((JLabel)_selectBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 14;
		gbc_panel.gridwidth = 20;
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 0;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_selectBox, gbc_panel);
		_panel.add(_selectBox);

		_selectButton = new JButton("Select Level");
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 17;
		gbc_panel.gridwidth = 20;
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 0;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_selectButton, gbc_panel);
		_selectButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event) {
			GUI.getInstance().setLevel(_selectBox.getSelectedIndex());
			//Choose file with Level words
			/*JOptionPane.showMessageDialog(null, "Please select a file to read words from", "Select a wordlist", JOptionPane.INFORMATION_MESSAGE);
		    //From: https://docs.oracle.com/javase/7/docs/api/javax/swing/JFileChooser.html
			JFileChooser chooser = new JFileChooser();
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		            Lists.getInstance().setWordList(chooser.getSelectedFile());
		    }*/
		    new WelcomeScreen();
		}
		});
		_panel.add(_selectButton);
		//from: http://stackoverflow.com/questions/25635636/eclipse-exported-runnable-jar-not-showing-images
		URL url = SelectLevel.class.getResource("/welcomeMini.gif");
		ImageIcon icon = new ImageIcon(url);
		//From http://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
		_image = new JLabel(" ");
		JLabel picLabel = new JLabel(icon);
		_image.add(picLabel);
		GridBagLayout gb_image = new GridBagLayout();
		_image.setLayout(gb_image);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		gbc_panel.gridwidth = 20;
		gbc_panel.gridheight = 11;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.weightx = 1;
		gbc_panel.weighty = 0;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gb_panel.setConstraints(_image, gbc_panel);
		_panel.add(_image);

		GUI.getInstance().getFrame().setContentPane(_panel);
		GUI.getInstance().getFrame().pack();
		GUI.getInstance().getFrame().setVisible(true);
	} 
} 