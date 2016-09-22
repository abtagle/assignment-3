
package voxSpell.gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;


/**
 * Class taken from ACP and modified to meet specification for Assignment 3 of Softeng 206
 * Video player to display the video reward
 * 
 * @author  Nasser Giacaman, Caprica, atag549
 * Last Modified: 20 September, 2016
 *
 */
public class VideoReward extends JFrame{
	EmbeddedMediaPlayer _video;

	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

	public VideoReward() {
		super("Video Reward");

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

		_video = mediaPlayerComponent.getMediaPlayer();

		setContentPane(mediaPlayerComponent);

		setLocation(100, 100);
		setSize(1050, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(mediaPlayerComponent, BorderLayout.CENTER);
		setContentPane(panel);
		_video.canPause();

		JButton btnMute = new JButton("Mute");
		panel.add(btnMute, BorderLayout.NORTH);
		btnMute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_video.mute();
			}
		});


		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		panel.add(btnPause, BorderLayout.SOUTH);

		setLocation(100, 100);
		setSize(1050, 600);
		setVisible(true);
		//Read in the video
		
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();

		if(!path.contains(":")){
			path = path+"/big_buck_bunny_1_minute.avi";	
		} else{
			path = SelectLevel.class.getResource("/big_buck_bunny_1_minute.avi").getPath();
		}
		//URL url = SelectLevel.class.getResource("/big_buck_bunny_1_minute.avi");
		_video.playMedia(path);
		NativeLibrary.addSearchPath(
				RuntimeUtil.getLibVlcLibraryName(), "/Applications/vlc-2.0.0/VLC.app/Contents/MacOS/lib"
				);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			}
		});
	}
	
	@Override
	public void dispose(){
		_video.pause();
		_video.stop();
		super.dispose();
	}
	private void pause(){
		if(_video.isPlaying()){
			_video.pause();
		} else {
			_video.play();
		}
	}

}