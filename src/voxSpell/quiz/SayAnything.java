package voxSpell.quiz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.SwingWorker;

//Says words in background (unnecessary SwingWorker, but implemented before I realised timing issues with festival
//Hopefully can find a way to adapt this
class SayAnything extends SwingWorker<Void, Void>{
	private String _phrase = null;
	Process _process;
	public SayAnything(String anything){
		_phrase = anything;
		//Create the .scm file
		PrintWriter writer;
		try {
			writer = new PrintWriter(".say.scm");
			writer.println("(voice_" + Settings.getInstance().getVoice() + ") ;;");
			writer.println("(SayText \"" + _phrase + "\")");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected Void doInBackground(){
		ProcessBuilder sayBuilder = new ProcessBuilder("/bin/bash", "-c", "festival -b .say.scm");
		try {
			_process = sayBuilder.start();
			_process.waitFor();
			//Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//executeCommand("festival; (" + Settings.getInstance().getVoice() + "); (SayText \"" + _phrase + "\"); (exit)");
		/*String sayCommand = "echo " + _phrase + "." + " | festival --tts";
		ProcessBuilder sayBuilder = new ProcessBuilder("/bin/bash", "-c", sayCommand);
		_process = sayBuilder.start();
		_process.waitFor();*/
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
	
	protected void setPhrase(String phrase){
		_phrase = phrase;
	}
}