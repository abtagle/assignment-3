package voxSpell.quiz;

import javax.swing.SwingWorker;

//Says words in background (unnecessary SwingWorker, but implemented before I realised timing issues with festival
//Hopefully can find a way to adapt this
class SayAnything extends SwingWorker<Void, Void>{
	private String _phrase = null;
	public SayAnything(String anything){
		_phrase = anything;
	}

	@Override
	protected Void doInBackground() throws Exception {
		String sayCommand = "echo " + _phrase + "." + " | festival --tts";
		ProcessBuilder sayBuilder = new ProcessBuilder("/bin/bash", "-c", sayCommand);
		Process process = sayBuilder.start();
		process.waitFor();
		return null;
	}

}