
package voxSpell.gui;

/*
 * Example downloaded from the 
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

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
 * Class taken from ACP and modified
 * 
 * Last Modified: 8 September. 2016
 * Author: Nasser Giacaman, Caprica, Aimee Tagle (atag549)
 *
 */
public class VideoReward {
	
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
  
    public VideoReward() {
        JFrame frame = new JFrame("Video Reward");

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        final EmbeddedMediaPlayer video = mediaPlayerComponent.getMediaPlayer();
        
        frame.setContentPane(mediaPlayerComponent);

        frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(mediaPlayerComponent, BorderLayout.CENTER);
        frame.setContentPane(panel);
        video.canPause();
        
        JButton btnMute = new JButton("Mute");
        panel.add(btnMute, BorderLayout.NORTH);
        btnMute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.mute();
			}
		});
        
        JButton btnSkip = new JButton(">>");
        btnSkip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.skip(5000);
			}
		});
        panel.add(btnSkip, BorderLayout.EAST);

        JButton btnSkipBack = new JButton("<<");
        btnSkipBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				video.skip(-5000);
			}
		});
        panel.add(btnSkipBack, BorderLayout.WEST);
        
        JButton btnPause = new JButton("Pause");
        btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(video.isPlaying()){
					video.pause();
				} else {
					video.play();
				}
			}
		});
        panel.add(btnPause, BorderLayout.SOUTH);
        
        frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        frame.setVisible(true);
        URL url = SelectLevel.class.getResource("/big_buck_bunny_1_minute.avi");
        //String filename = "big_buck_bunny_1_minute.avi";
        video.playMedia(url.getPath());
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

}