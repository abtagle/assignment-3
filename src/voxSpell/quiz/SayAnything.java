package voxSpell.quiz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.SwingWorker;

/**
 * Swingworker class in responsible for making Festival calls to say words aloud via .scm files.
 * 
 * Taken from Softeng 206 Assignment 2 submission by Aimee Tagle (originally an inner class)
 * @author osboxes
 * Last Modified: 21 September, 2016
 */
class SayAnything extends SwingWorker<Void, Void>{
	private String _phrase = null;
	private String _fileName = ".say.scm";
	
	Process _process;
	public SayAnything(String anything){
		_phrase = anything;
		//Create the .scm file
		PrintWriter writer;
		_phrase = anything;
		try {
			writer = new PrintWriter(_fileName);
			writer.println("(voice_" + Settings.getInstance().getVoice() + ") ;;");
			writer.println("(SayText \"" + _phrase + "\")");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//If the phrase being said is a word, use a different .scm file
	public SayAnything(String anything, boolean isWord){
		if(isWord){
			_fileName = ".word.scm";
		}
		PrintWriter writer;
		_phrase = anything;
		try {
			writer = new PrintWriter(_fileName);
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
		ProcessBuilder sayBuilder = new ProcessBuilder("/bin/bash", "-c", "festival -b " + _fileName);
		try {
			_process = sayBuilder.start();
			_process.waitFor();
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