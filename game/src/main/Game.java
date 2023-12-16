package main;

import java.awt.Graphics;

import entities.Player;
import entities.Goal;
import level.FlowManager;
import level.LevelManager;
import utilz.AudioPlayer;

/**
 * Zentrale Klasse welche jegliche Informationen ueber das Spiel besitzt. Reguliert das Zusammenspielen
 * der einzelnen Klassen und stoeßt gezieht updates auf die Logik & neu-malen fuer die Grafik an, sodass das Spiel-Erlebnis
 * ermoeglicht wird
 * @author leonie & hannah
 *
 */
public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	public final static float SCALE = 2.0f;
	
	public final static int TILE_DEFAULT= 20;
	public final static int NUMBER_TILES_WIDTH = 40;  // Anzahl an sichtbaren Kacheln (breite)
	public final static int NUMBER_TILES_HEIGHT = 24;  // Anzahl an sichtbaren Kacheln (höhe)
	public final static int TILE_SIZE = (int) (TILE_DEFAULT * SCALE);

	public final static int GAME_WIDTH = TILE_SIZE * NUMBER_TILES_WIDTH;  //
	public final static int GAME_HEIGHT = TILE_SIZE * NUMBER_TILES_HEIGHT;

	private Player player;
	private Goal flag;
	private LevelManager levelManager;
	private FlowManager flowManager;
	private AudioPlayer audioPlayer;
	
	private int xOffset;  // beim Bewegen des Spielers --> ab wann wir unser Level malen (position wird dynamisch angepasst)
	private int leftBorder = (int) (0.2 * GAME_WIDTH); // falls in Zone --> mehr Kacheln nach Links malen
	private int rightBorder = (int) (0.8 * GAME_WIDTH); // falls in Zone --> mehr Kacheln nach Rechts malen
	private int xWidth;
	private int maxPosXOffset;
	
	private int yOffset;  // beim Bewegen des Spielers --> ab wann wir unser Level malen (position wird dynamisch angepasst)
	private int upperBorder = (int) (0.2 * GAME_HEIGHT); // falls in Zone --> mehr Kacheln nach Links malen
	private int lowerBorder = (int) (0.8 * GAME_HEIGHT); // falls in Zone --> mehr Kacheln nach Rechts malen
	private int yHeight;
	private int maxPosYOffset;
	
	private int xPlayerPos = 100;
	private int yPlayerPos = 500;
	private int widthPlayer =(int) (27 * SCALE);
	private int heightPlayer =(int) (27 * SCALE);
	private int widthGoal =(int) (60*SCALE);
	private int heightGoal =(int) (60*SCALE);

	private double timePerFrame = (1000000000.0 / FPS_SET);
	private double timePerUpdate = 1000000000.0 / UPS_SET;
	
	/**
	 * Konstruktor
	 */
	public Game() {
		levelManager = new LevelManager(this);
		audioPlayer = new AudioPlayer();
		resetCondition();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();
	}

	private void setConstraints() {
		xWidth = (levelManager.getCurrentLevel().getData())[0].length;  // anzahl Kaestchen in breite insgesamt
		maxPosXOffset = (xWidth - NUMBER_TILES_WIDTH)* TILE_SIZE; // anzahl Kaestchen ohne sehbare
	
		yHeight = (levelManager.getCurrentLevel().getData()).length;
		maxPosYOffset = (yHeight - NUMBER_TILES_HEIGHT)* TILE_SIZE; // anzahl Kaestchen ohne sehbare
	}
	
	/**
	 * Die Methode positioniert den Player & die Flagge bezueglich des aktuellen Levels
	 */
	public void resetCondition()  {
		player = new Player(xPlayerPos, yPlayerPos, widthPlayer, heightPlayer);   // Spieler erstellen + Startpos festhalten
		player.loadLvlData(levelManager.getCurrentLevel().getData());
		flag = new Goal(-100,-100, widthGoal, heightGoal);
		flag.loadLvlData(levelManager.getCurrentLevel().getFlagData()); // setzt flag an richtige position
		flowManager = new FlowManager(levelManager, player, flag);
		setConstraints();
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * stoeßt Updates auf wichtige bestandteile des Spiels an.
	 * Spieler & Kontrollfluss & Musik wird geupdated
	 */
	public void update() {
		player.update();
		flowManager.update();
		checkBorder();
		audioPlayer.update();
	}

	private void checkBorder() {
		int playerXPos = (int) player.getHitbox().x; // xposition des spielers
		int diff = playerXPos - xOffset;
		
		if (diff > rightBorder) 
			xOffset += diff - rightBorder;
		else if (diff < leftBorder) 
			xOffset += diff - leftBorder;

		if (xOffset > maxPosXOffset)
			xOffset = maxPosXOffset;
		if (xOffset < 0)
			xOffset = 0;
		
		
		int playerYPos = (int) player.getHitbox().y; // xposition des spielers
		int diff2 = playerYPos - yOffset;
		
		if (diff2 > lowerBorder) {
			yOffset += diff2 - lowerBorder;}
		
		else if (diff2 < upperBorder) {
			yOffset += diff2 - upperBorder;}

		if (yOffset > maxPosYOffset)
			yOffset = maxPosYOffset;
		if (yOffset < 0) 
			yOffset = 0;
	}
	
	/**
	 * Methode welche genutzt werden kann, um die komplette Umgebung des Spielfeldes zu malen.
	 * Hierbei wird der Spieler & Die Spielumgebung mit Ziel gemalt.
	 */
	public void render(Graphics g) {
		levelManager.drawBackground(g);
		player.render(g, xOffset, yOffset);
		levelManager.draw(g, xOffset, yOffset);  // Reihenfolge legt fest, was im vordergrund waere
		flag.render(g, xOffset, yOffset);
	}

	@Override
	public void run() {

		long previousTime = System.nanoTime();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) { // Aktionen Ausfuehren
				update();
				deltaU--;  }

			if (deltaF >= 1) { // neu Malen
				gamePanel.repaint();
				deltaF--;  
			}
		}
	}

	/**
	 * setzt Logik auf Startzustand zurueck, sofern es Fehler gibt
	 */
	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	/**
	 * gibt den Spieler des Spiels zurueck
	 * @return Spieler
	 */
	public Player getPlayer() {
		return player;
	}	
	
	/**
	 * gibt das Ziel des Spiels zurueck
	 * @return Ziel
	 */
	public Goal getGoal() {
		return flag;
	}	
	
	/**
	 * gibt den LevelManager des Spiels zurueck
	 * @return LevelManager
	 */
	public LevelManager getLevelManager() {
		return levelManager;
	}	

	/**
	 * gibt das GamePanel des Spiels zurueck
	 * @return GamePanel
	 */
	public GamePanel getGamePanel() {
		return gamePanel;
	}	
	
	/**
	 * gibt den dezeitigen XOffset des Spielers zur Umgebung zurueck
	 * @return XOffset
	 */
	public int getXOffset() {
		return xOffset;
	}
	
	/**
	 * gibt den dezeitigen YOffset des Spielers zur Umgebung zurueck
	 * @return XOffset
	 */
	public int getYOffset() {
		return yOffset;
	}
	
	/**
	 * setzt jeglichen Fortschritt zurueck
	 */
	public void resetAchievement() {
		levelManager.resetLevel();
		resetCondition();
	}

	/**
	 * setzt den State des GamePanels auf Completed
	 */
	public void setCompletedState() {
		if(gamePanel.getState() == GamePanel.STATE.GAME) {
			gamePanel.setState(GamePanel.STATE.COMPLETED);
		}
	}
	
	/**
	 * setzt den State des GamePanels auf Victory
	 */
	public void setVictoryState() {
		if(gamePanel.getState() == GamePanel.STATE.GAME) {
			gamePanel.setState(GamePanel.STATE.VICTORY);
		}
	}
	
	/**
	 * setzt den State des GamePanels auf GameOver
	 */
	public void setKilledState() {
		if(gamePanel.getState() == GamePanel.STATE.GAME) {
			gamePanel.setState(GamePanel.STATE.GAMEOVER);
		}
	}
	
	/**
	 * gibt den AudioPlayer des Spiels zureuck
	 * @return AudioPlayer
	 */
	public AudioPlayer getAudioPlayer() {
		return audioPlayer;
	}
	
	

}