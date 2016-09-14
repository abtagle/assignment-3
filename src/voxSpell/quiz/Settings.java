package voxSpell.quiz;

import java.io.IOException;
import java.io.OutputStream;

public class Settings {
	static Settings _settings = null;
	String _voiceName;
	public static Settings getInstance(){
		if(_settings == null){
			_settings = new Settings();
		}
		return _settings;
		
	}
	private Settings(){
		String _voiceName = "kal_diphone";
	}
	public void setVoice(String voiceName){
		_voiceName = voiceName;		
	}
	protected String getVoice(){
		return _voiceName;
	}
	public void saySampleVoicePhrase(String voiceName){
		String originalVoiceName = _voiceName;
		setVoice(voiceName);
		SayAnything sample= new SayAnything("This is the voice");
		try {
			sample.doInBackground();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVoice(originalVoiceName);
	}
}
