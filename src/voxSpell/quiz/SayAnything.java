package voxSpell.quiz;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.SwingWorker;

//Says words in background (unnecessary SwingWorker, but implemented before I realised timing issues with festival
//Hopefully can find a way to adapt this
class SayAnything extends SwingWorker<Void, Void>{
	private String _phrase = null;
	Process _process;
	public SayAnything(String anything){
		_phrase = anything;
	}

	@Override
	protected Void doInBackground() throws IOException, InterruptedException {
		//executeCommand("festival; (" + Settings.getInstance().getVoice() + "); (SayText \"" + _phrase + "\"); (exit)");
		String sayCommand = "echo " + _phrase + "." + " | festival --tts";
		ProcessBuilder sayBuilder = new ProcessBuilder("/bin/bash", "-c", sayCommand);
		_process = sayBuilder.start();
		_process.waitFor();
		//Adapted from http://www.hiteshagrawal.com/java/text-to-speech-tts-in-java/
		/*Runtime rt = Runtime.getRuntime();
		Process process = rt.exec("festival --pipe");
		OutputStream output = process.getOutputStream();
		output.write(("("+Settings.getInstance().getVoice()+")").getBytes());
		output.write(("(SayText \"" + _phrase + "\")").getBytes());
		output.flush();*/

		//rt.exec("(SayWord " + _phrase + " -o -eval '(" + Settings.getInstance().getVoice() + ")')");
		return null;
	}
	

}