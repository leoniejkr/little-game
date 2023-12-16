package level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import utilz.Img;

/**
 * der LavelManager verwaltet die im Spiel zur Verfuegungstehenden Levels.
 * Ueber Informationen des FlowManagers setzt jener entweder das nachste Level oder loescht jeglichen Fortschritt. 
 * Je nachdem, in welchem Level sich befinden wird & ob das vorige erfolgreich oder erfolglos abgeschlossen wurde,
 * setzt der LevelManager dann dann den Zustand (STate) des GamePanels (=GameOver, CompletedSTate, VictorySTate)
 * @author leonie & hannah
 *
 */
public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprite;
	private BufferedImage background;
	private ArrayList<Level> level;
	private Level currentLevel;;

	/**
	 * Konstruktor
	 * @param game
	 */
	public LevelManager(Game game) {
		this.game = game;
		importOutsideSprites();
		initlevelArray();
	}

	private void initlevelArray() {
		Level levelOne = new Level(Img.getLevelData(Img.LEVEL_ONE_DATA), Img.getLevelFlagData(Img.LEVEL_ONE_DATA));
		Level levelTwo = new Level(Img.getLevelData(Img.LEVEL_TWO_DATA), Img.getLevelFlagData(Img.LEVEL_TWO_DATA));
		Level levelThree = new Level(Img.getLevelData(Img.LEVEL_THREE_DATA), Img.getLevelFlagData(Img.LEVEL_THREE_DATA));
		Level levelFour = new Level(Img.getLevelData(Img.LEVEL_FOUR_DATA), Img.getLevelFlagData(Img.LEVEL_FOUR_DATA));
		Level levelFive = new Level(Img.getLevelData(Img.LEVEL_FIVE_DATA), Img.getLevelFlagData(Img.LEVEL_FIVE_DATA));

		level = new ArrayList<Level>();
		
		level.add(levelOne);
		level.add(levelTwo);
		level.add(levelThree);
		level.add(levelFour);
		level.add(levelFive);
		
		resetLevel();
	}

	private void importOutsideSprites() {
		BufferedImage img = Img.getImages(Img.LEVEL);
		levelSprite = new BufferedImage[95];  // 19:  breite * 5 hoehe
		for (int j = 0; j < 5; j++)  // hoehe
			for (int i = 0; i < 19; i++) {   // fuer eine Zeile alle Kaestchen bis in Breite speichern
				int index = j * 19 + i;              //s_breite/11       //s_hoehe/9
				levelSprite[index] = img.getSubimage(   i * 8,           j * 8,        8,  8);
			}
		background =  Img.getImages(Img.BACKGROUND);
	}

	/**
	 * Methode welche genutzt werden kann, um die Umgebung auf einer Grafik zu malen
	 * @param g Grafik
	 * @param xOffset um wie viel sich die Position entlangd er x achse aendern soll
	 * @param yOffset um wie viel sich die Position entlang der y achse aendern soll
	 */
	public void draw(Graphics g, int xOffset, int yOffset) {
//		g.drawImage(background, 0,0, Game.GAME_WIDTH , Game.GAME_HEIGHT, null);
		for (int j = 0; j < currentLevel.getData().length; j++)
			for (int i = 0; i < currentLevel.getData()[0].length; i++) {
				int index = currentLevel.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], (int)(i*game.TILE_SIZE) - xOffset,(int)(j*game.TILE_SIZE)  - yOffset, (int)(game.TILE_SIZE),  (int)(game.TILE_SIZE),null);
			}
	}
	
	/**
	 * Methode welche genutzt werden kann, um den Hintergrund auf einer Grafik zu malen
	 * @param g Grafik
	 */
	public void drawBackground(Graphics g) {
		g.drawImage(background, 0,0, Game.GAME_WIDTH , Game.GAME_HEIGHT, null);
	}

	/**
	 * stellt das naechste Level, je nachdem ob das derzeitige beendet wurde
	 * @param completed sagt an, ob ein Level erfolgreich beendet wurde
	 */
	public void update(boolean completed) {
		if(completed) {
			nextLevel();
		} else {
			finishLevel();
		}
	}

	/**
	 * gibt das aktuelle Level, in welchem sich der spieler befindet
	 * @return
	 */
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	/**
	 * setzt das aktuelle Level auf Level1
	 */
	public void resetLevel() {
		currentLevel = level.get(0);
	}
	
	private void nextLevel() {
		int index = 0;
		for(int i = 0; i < level.size() - 1; i++) {  // alle Level bis zum letzten
			if(level.get(i) == currentLevel) {
				index = i +1; 
				System.out.println(index);
			}
		}
	
		currentLevel = level.get(index);
		
		if (index > 0) { // naechstes Level
			game.resetCondition();
			game.setCompletedState();
		}
		else if (currentLevel == level.get(0)) { // gewonnen! 
			game.resetCondition();
			game.setVictoryState();
		}
	}

	private void finishLevel() { //	stellt alle Fortschritte zurück & beginnt naechstes Level
		resetLevel();
		game.resetCondition();
		game.setKilledState();
	}
	
	/**
	 * nimmt eine Liste an Strings entgegen, welche als die neuen Level dienen
	 * @param data die Liste an Strings
	 */
	public void loadLvlData(ArrayList <String> data) {
		level = new ArrayList<Level>();
		for ( String dat: data) {
			Level lev = new Level(Img.getLevelData(dat), Img.getLevelFlagData(dat));
			level.add(lev);
		}
		resetLevel();
	}
	
	/**
	 * nimmt einen String entgegen, welcher als das neue Level dient
	 * @param data der String
	 */
	public void loadLvlData(String data) {
		level = new ArrayList<Level>();
		Level lev = new Level(Img.getLevelData(data), Img.getLevelFlagData(data));
		level.add(lev);
		resetLevel();
	}

}
