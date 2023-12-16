package utilz;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * AudioPlayer erlubt es Sounds innerhalb des Spielverlaufes abspielen zu lassen.
 * Es gibt eine Unterscheidung zwischen Musik (Hintergrundmusik, die konstant laeuft)
 * und SoundEffekten (welche kurzzeitig abspielen)
 * @author leonie & hannah
 *
 */
public class AudioPlayer {

	public static int MENU = 0;	
	public static int GAME = 1;	
	public static int HELP = 2;	
	public static int GAME_OVER = 3; //landonstone2
	public static int LEVEL_COMPLETED = 4; //bushrushing1

	
	public static int ATTACK = 0; 
	public static int DYING = 1; //stepstone3
	public static int JUMP = 2; //stepdirt2


	private Clip[] songs,effects;
	private int currentSongId;
	private int currentEffectId;
	private float songVolume = 0.5f; 
	private float effVolume = 0.55f; //Lautstaerke Effekt
	
	/**
	 * Konstruktor
	 */
	public AudioPlayer() {
		loadSongs();
		loadEffects();
	}
	
	/**
	 * laesst den gerade laufenden Song auf dauerschleife abspielen
	 */
	public void update() {
		if (!isSongPlaying(currentSongId)) {
			songs[currentSongId].setMicrosecondPosition(0);
			songs[currentSongId].start();}
	}
	
	private void loadSongs() {
		String[] names = {"musik1","musik2","musik3", "gameover", "lvlcompleted"};
		songs = new Clip[names.length];
		
		for (int i = 0; i < songs.length; i++) {
			songs[i] = getClip(names[i]);
		}
	}
	
	private void loadEffects() {
		String[] names = {"fight", "die", "jumpp"};
		effects = new Clip[names.length];
		
		for (int i = 0; i < effects.length; i++) {
			effects[i] = getClip(names[i]);
		}
	}
	
	private Clip getClip(String name) {
		URL url = getClass().getResource("/audio/sound/" + name + ".wav");
		AudioInputStream audio;
		
		try {
			audio = AudioSystem.getAudioInputStream(url);
			Clip c = AudioSystem.getClip();
			c.open(audio);
			
			return c;
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * laesst einen Effekt abspielen ueber verwendung der Klassen-Konstanten
	 * @param effect
	 */
	public void playEffect(int effect) {
		if (effects[currentEffectId].isActive())
			effects[currentEffectId].stop();
		currentEffectId = effect;
		effects[currentEffectId].setMicrosecondPosition(0);
		effects[currentEffectId].start();
		
		updateEffectsVolume();
	}
	
	/**
	 * gibt die ID des derzeit Spielenden Effekts zureuck
	 * @return ID
	 */
	public int getCurrentEffectId() {
		return currentEffectId;
	}
	
	/**
	 * laesst einen Song mit der SongId song spielen
	 * @param song
	 */
	public void playSong(int song) {
		if (songs[currentSongId].isActive())
			songs[currentSongId].stop();
		currentSongId = song;
		songs[currentSongId].setMicrosecondPosition(0);
		songs[currentSongId].start();
		
		updateSongVolume();
	}
	
	/**
	 * gibt an, ob der Song mit der SongId song spielt.
	 * @param song
	 * @return true, falls schon
	 */
	public boolean isSongPlaying(int song) {
		return songs[song].isActive();
	}
	/**
	 * gibt an, ob der Effekt mit der EffektId song spielt.
	 * @param eff
	 * @return true, falls schon
	 */
	public boolean isEffectPlaying(int eff) {
		return effects[eff].isActive();
	}
	
	/**
	 * setzt die Lautstaerke der Musik
	 * @param volume
	 */
	public void setSongVolume(float volume) {
		this.songVolume = volume;
		updateSongVolume();
	}
	/**
	 * setzt die Lautstaerke der Effekte
	 * @param volume
	 */
	public void setEffektVolume(float volume) {
		this.effVolume = volume;
		updateEffectsVolume();
	}
	
	private void updateSongVolume() {
		FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (range * songVolume) + gainControl.getMinimum();
		gainControl.setValue(gain);
	}
	
	private void updateEffectsVolume() {
		for (Clip c : effects) {
			FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (range * effVolume) + gainControl.getMinimum();
			gainControl.setValue(gain);
		}
	}
}
