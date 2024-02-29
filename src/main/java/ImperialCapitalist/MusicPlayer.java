package ImperialCapitalist;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

	// clip variable is the one music file
	private static Clip clip;
	
	// store songs index
	private int songIndex;
	
	// String array for song names
	private String[] songFile = new String[5];
	
	// variable for volume
	private double Dvolume;
	
	// on if playing, off if false
	private boolean playing;
	
	// read song names into string array, set playing to true
	public MusicPlayer() {
		songFile[1] = "CapitalistMusic.wav";
		songFile[2] = "imperial_march.wav";
		songFile[0] = "cantina.wav";
		songFile[3] = "Duel Of The Fates.wav";
		songFile[4] = "BinarySunset.wav";
		
		playing = true;
	}

	/*
	 * Gets the song index and saves it, creates a clip with the correct song in it.
	 * Plays the loop continuously (until clip.stop();).
	 */
	public void startClip(int song) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		songIndex = song;
		AudioInputStream songs;
		// Get a sound clip resource.
		clip = AudioSystem.getClip();

		// get song as audio input stream
		URL url = this.getClass().getResource("/music/"+songFile[songIndex]);
		songs = AudioSystem.getAudioInputStream(url);

		// Open audio clip and load samples from the audio input stream.
		clip.open(songs);

		// play the clip continuously until it is stopped by user
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	// stops the music
	public void stopClip() {
		clip.stop();
	}
	
	// changes the music volume
	public void clipVolume(double volume) {
		Dvolume = volume;
		volume /= 100;
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
		gainControl.setValue(20f * (float) Math.log10(volume));
	}
	
	// return song name
	public String getSong() {
		return songFile[songIndex];
	}
	
	// return song index
	public int getSongIndex() {
		return songIndex;
	}
	
	// return volume
	public int getVolume() {
		if (Dvolume == 0) {
			Dvolume = 100;
			// won't show 100 at the start for some reason, this fixes that
		}
		return (int) Dvolume;
	}
	
	// change playing boolean value
	public void setPlaying(boolean isPlaying) {
		playing = isPlaying;
	}
	
	// return boolean is song is playing
	public boolean getPlaying() {
		return playing;
	}
}
