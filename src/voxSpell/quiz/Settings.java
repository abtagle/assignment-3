package voxSpell.quiz;

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
		sample.doInBackground();
		setVoice(originalVoiceName);
	}
}
