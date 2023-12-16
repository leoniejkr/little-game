package main;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowFocusListener;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.io.InputStream;

//import javax.imageio.ImageIO;
import javax.swing.JPanel;
//import javax.swing.plaf.nimbus.State;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import states.CompletedMessage;
import states.GameOverMessage;
import states.HelpPage;
import states.Menu;
import states.VictoryMessage;
import utilz.AudioPlayer;

//import static utilz.Constants.PlayerConstants.*;
//import static utilz.Constants.Directions.*;


/**
 * kuemmert sich um das look & feel (Grafik und audio) der Spiel-Umgebung. Setzt in welchem Spielzustand
 * sich befindet und stellt entsprechenden State ein.
 * @author leonie & hannah
 *
 */
public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;
	private Menu menu;
	private HelpPage help;
	private CompletedMessage compmes;
	private VictoryMessage vicmes;
	private GameOverMessage gomes;
	
	private AudioPlayer audioPlayer;
	
	private STATE state;
	
	public static enum STATE {MENU, GAME, HELP, COMPLETED, VICTORY, GAMEOVER};  // determinieren wo wir uns beim spielen befinden

	/**
	 * Konstruktor
	 * @param game
	 */
	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;

		this.audioPlayer = game.getAudioPlayer();
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
		menu = new Menu("hello", game.GAME_WIDTH, game.GAME_HEIGHT);
		help = new HelpPage("hello", game.GAME_WIDTH, game.GAME_HEIGHT);
		compmes = new CompletedMessage("hello", game.GAME_WIDTH, game.GAME_HEIGHT);
		vicmes = new VictoryMessage("hello", game.GAME_WIDTH, game.GAME_HEIGHT);
		gomes= new GameOverMessage("hello", game.GAME_WIDTH, game.GAME_HEIGHT);
		
		setState(GamePanel.STATE.MENU);
		
	}

	private void setPanelSize() {
		Dimension size = new Dimension(game.GAME_WIDTH, game.GAME_HEIGHT);
		setPreferredSize(size);
	}

	/**
	 * setzt den State der Klasse
	 * @param state
	 */
	public void setState(STATE state) {
		this.state = state;
		setMusik();
	}
	
	/**
	 * gibt den STate der Klasse zurueck
	 * @return
	 */
	public STATE getState() {
		return this.state;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (state ==  STATE.GAME) {
			game.render(g);
		}
		if (state ==  STATE.MENU) {
			menu.render(g);
		}
		if (state == STATE.HELP) {
			help.render(g);
		}		
		if (state == STATE.COMPLETED) {
			compmes.render(g);
		}
		if (state == STATE.VICTORY) {
			vicmes.render(g);
		}
		if (state == STATE.GAMEOVER) {
			gomes.render(g);
		}
	}
	

	private void setMusik() {
		if (state ==  STATE.GAME) {
			this.audioPlayer.playSong(AudioPlayer.GAME);
		}
		if (state ==  STATE.VICTORY) {
			this.audioPlayer.playSong(AudioPlayer.LEVEL_COMPLETED);
		}
		
		if (state == STATE.COMPLETED) {
			this.audioPlayer.playSong(AudioPlayer.LEVEL_COMPLETED);
		}
		
		if (state == STATE.GAMEOVER) {
			this.audioPlayer.playSong(AudioPlayer.GAME_OVER);
		}
		if (state ==  STATE.MENU) {
			this.audioPlayer.playSong(AudioPlayer.MENU);
		}
		if (state ==  STATE.HELP) {
			this.audioPlayer.playSong(AudioPlayer.HELP);
		}

	}

	/**
	 * gibt das Spiel zurueck
	 * @return Game
	 */
	public Game getGame() {
		return game;
	}

}