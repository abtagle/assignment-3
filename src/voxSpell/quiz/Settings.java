package voxSpell.quiz;

/*
 * Class representing the user interface of the settings page where users can change the festival voice
 * used in quizes
 * 
 * Class by Aimee Tagle for Assignment 3
 * Last Modified: 19 September, 2016
 * 
 */

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
		_voiceName = "kal_diphone";
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
		sample.execute();
		setVoice(originalVoiceName);
	}
}
