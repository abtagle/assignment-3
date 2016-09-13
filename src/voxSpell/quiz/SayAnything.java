package voxSpell.quiz;

import java.io.IOException;

import javax.swing.SwingWorker;

//Says words in background (unnecessary SwingWorker, but implemented before I realised timing issues with festival
//Hopefully can find a way to adapt this
class SayAnything extends SwingWorker<Void, Void>{
	private String _phrase = null;
	public SayAnything(String anything){
		_phrase = anything;
	}

	@Override
	protected Void doInBackground() throws IOException, InterruptedException {
		//executeCommand("festival; (" + Settings.getInstance().getVoice() + "); (SayText \"" + _phrase + "\"); (exit)");
		executeCommand("echo \"" + _phrase + "\"| festival --tts");
		return null;
	}
	
	private void executeCommand(String command) throws IOException, InterruptedException{
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
		Process process = builder.start();
		process.waitFor();
	}

}