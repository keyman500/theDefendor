import javax.sound.sampled.AudioInputStream;		// for playing sound clips
import javax.sound.sampled.*;
import java.io.*;

import java.util.HashMap;				// for storing sound clips


public class SoundManager {				// a Singleton class
	HashMap<String, Clip> clips;

   	Clip hitClip = null;				// played when bat hits ball
   	Clip appearClip = null;				// played when ball is re-generated
   	Clip backgroundClip = null;			// played continuously after ball is created

	private static SoundManager instance = null;	// keeps track of Singleton instance

	private SoundManager () {
		clips = new HashMap<String, Clip>();

		Clip clip;
	//	clips.put("background", clip);		// background theme sound

		clip = loadClip("sounds/fireball.wav");
		clips.put("fireball", clip);		
						


		clip = loadClip("sounds/Concrete2.wav");
	clips.put("walk", clip);		// played when a special sprite 
							//   makes an appearance

		clip = loadClip("sounds/speak.wav");
		clips.put("speak",clip);

		clip = loadClip("sounds/bg1.wav");
		clips.put("bg1",clip);

		clip = loadClip("sounds/Edie.wav");
		clips.put("Edie",clip);

		clip = loadClip("sounds/Pdamage.wav");
		clips.put("Pdamage",clip);

		clip = loadClip("sounds/poweron.wav");
		clips.put("poweron",clip);

		clip = loadClip("sounds/Rattack.wav");
		clips.put("Rattack",clip);

		clip = loadClip("sounds/Rgethit.wav");
		clips.put("Rgethit",clip);

		clip = loadClip("sounds/Rtalk.wav");
		clips.put("Rtalk",clip);

		clip = loadClip("sounds/Rwalk1.wav");
		clips.put("Rwalk1",clip);

		clip = loadClip("sounds/Rwalk2.wav");
		clips.put("Rwalk2",clip);

		clip = loadClip("sounds/stick.wav");
		clips.put("stick",clip);

	}


	public static SoundManager getInstance() {	// class method to get Singleton instance
		if (instance == null)
			instance = new SoundManager();
		
		return instance;
	}		


	public Clip getClip (String title) {

		return clips.get(title);		// gets a sound by supplying key
	}


    	public Clip loadClip (String fileName) {	// gets clip from the specified file
 		AudioInputStream audioIn;
		Clip clip = null;

		try {
    			File file = new File(fileName);
    			audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL()); 
    			clip = AudioSystem.getClip();
    			clip.open(audioIn);
		}
		catch (Exception e) {
 			System.out.println ("Error opening sound files: " + e);
		}
    		return clip;
    	}


    	public void playSound(String title, Boolean looping) {
		Clip clip = getClip(title);
		if (clip != null) {
			clip.setFramePosition(0);
			if (looping)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.start();
		}
    	}


    	public void stopSound(String title) {
		Clip clip = getClip(title);
		if (clip != null) {
			clip.stop();
		}
    	}

}