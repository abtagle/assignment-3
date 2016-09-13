package voxSpell.quiz;

import java.io.IOException;

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
	protected String getVoice(){
		return _voiceName;
	}
	public void setVoice(String voiceName){
		 _voiceName = voiceName;
	}
	public void saySampleVoicePhrase(String voiceName){
		_voiceName = voiceName;
		SayAnything sample= new SayAnything("This is the voice");
		try {
			sample.doInBackground();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_voiceName = voiceName;
	}
}
