package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entities.Goal;
import entities.Player;
import level.LevelManager;
import main.Game;
import main.GamePanel;
import utilz.AudioPlayer;

import java.util.ArrayList;

/**
 * TestKlasse zur Game-Klasse (Genereller aufbau des Spiels)
 * @author leonie & hannah
 *
 */
public class GameTest {

	private Player player;
	private Goal flag;
	private Game game;
	private GamePanel gamePanel;
	private AudioPlayer audioPlayer;

	private LevelManager levelManager;
	
	private final String DORNEN = "/test/DornenTest.png";
	private final String FLAGGE = "/test/FlaggeTest.png";
	private final String BORDER = "/test/BigBorder.png";


	public GameTest() {
		init();
	}

	@BeforeEach
	private void init() {	
		game = new Game();
		gamePanel = game.getGamePanel();
		levelManager = game.getLevelManager();
		audioPlayer = game.getAudioPlayer();
		
		player = game.getPlayer();
		player.setX(100);
		player.setY(200);
		flag = game.getGoal();
	}

	private void updateEntityLevel() {
		flag.loadLvlData(levelManager.getCurrentLevel().getFlagData()); 
		player.loadLvlData(levelManager.getCurrentLevel().getData());
	}
	
	@Test
	public void victoryTest() {
		levelManager.loadLvlData(FLAGGE);
		updateEntityLevel();		
		
		gamePanel.setState(GamePanel.STATE.GAME);
		while (gamePanel.getState() == GamePanel.STATE.GAME)
			game.update();
	
		assertTrue(gamePanel.getState() == GamePanel.STATE.VICTORY);
	}
	
	@Test
	public void completedLevel() {
		ArrayList<String> levels = new ArrayList<String>();
		levels.add(FLAGGE);
		levels.add(FLAGGE);
		levels.add(FLAGGE);
		levels.add(FLAGGE);
		levels.add(FLAGGE);
		levels.add(FLAGGE);
		
		levelManager.loadLvlData(levels);
		updateEntityLevel();		
		
		gamePanel.setState(GamePanel.STATE.GAME);
		while (gamePanel.getState() == GamePanel.STATE.GAME)
			game.update();
	
		assertTrue(gamePanel.getState() == GamePanel.STATE.COMPLETED);
		
		gamePanel.setState(GamePanel.STATE.GAME);
		while (gamePanel.getState() == GamePanel.STATE.GAME)
			game.update();
		
		assertTrue(gamePanel.getState() == GamePanel.STATE.COMPLETED);
	}
	
	@Test
	public void gameOverTest() {
		levelManager.loadLvlData(DORNEN);
		updateEntityLevel();		
		
		gamePanel.setState(GamePanel.STATE.GAME);
		while (gamePanel.getState() == GamePanel.STATE.GAME)
			game.update();
	
		assertTrue(gamePanel.getState() == GamePanel.STATE.GAMEOVER);
	}
	
	@Test
	public void expandableLevelTest() {
		int borderx = game.GAME_WIDTH;
		int bordery = game.GAME_HEIGHT;
		
		levelManager.loadLvlData(BORDER);
		updateEntityLevel();		
		
		gamePanel.setState(GamePanel.STATE.GAME);
		 
		// Pruefen x-Achse verlaengerung (erweiterung nach rechts)
		int xOffset = game.getXOffset(); // 0
		player.setRight(true);
		while(player.getX() < borderx) {
			game.update();
		}
		game.update();
		while(game.getXOffset() > xOffset) {
			xOffset = game.getXOffset();
			game.update();
		}
		assertTrue(player.getX()+xOffset > borderx);  // koennen ueber "Rand" hinaus gehen
		
		
		// Pruefen y-Achse verlaengerung (erweiterung nach unten)
		player.setRight(false);
		player.setGrafityFactor(0);
		player.setX(200);
		player.setY(bordery/2);
		
		int yOffset = game.getYOffset();
		player.setDown(true);
		
		while(player.getY() < bordery) {
			game.update();
		}
		game.update();
		while(game.getYOffset() > yOffset) {
			yOffset = game.getYOffset();
			game.update();
		}

		assertTrue(player.getY()+yOffset > bordery);  // koennen ueber "Rand" hinaus gehen

		}
	
	
	@Test
	public void TestSogs() {
		gamePanel.setState(GamePanel.STATE.GAME);
		while(!audioPlayer.isSongPlaying(AudioPlayer.GAME))
			player.update();
		assertTrue(audioPlayer.isSongPlaying(AudioPlayer.GAME));

		gamePanel.setState(GamePanel.STATE.MENU);
		while(!audioPlayer.isSongPlaying(AudioPlayer.MENU))
			player.update();
		assertTrue(audioPlayer.isSongPlaying(AudioPlayer.MENU));

		gamePanel.setState(GamePanel.STATE.COMPLETED);
		while(!audioPlayer.isSongPlaying(AudioPlayer.LEVEL_COMPLETED))
			player.update();		
		assertTrue(audioPlayer.isSongPlaying(AudioPlayer.LEVEL_COMPLETED));

		gamePanel.setState(GamePanel.STATE.VICTORY);
		while(!audioPlayer.isSongPlaying(AudioPlayer.LEVEL_COMPLETED))
			player.update();	
		assertTrue(audioPlayer.isSongPlaying(AudioPlayer.LEVEL_COMPLETED));

		gamePanel.setState(GamePanel.STATE.HELP);
		while(!audioPlayer.isSongPlaying(AudioPlayer.HELP))
			player.update();	
		assertTrue(audioPlayer.isSongPlaying(AudioPlayer.HELP));

		gamePanel.setState(GamePanel.STATE.GAMEOVER);
		while(!audioPlayer.isSongPlaying(AudioPlayer.GAME_OVER))
			player.update();	
		assertTrue(audioPlayer.isSongPlaying(AudioPlayer.GAME_OVER));
		
	}
	}
	
	

