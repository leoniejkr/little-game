package utilz;

import main.Game;

/**
 * Klasse, welche hilfreiche Methoden fuer saemliche Klassen zur verfuegung stellt.
 * Zentral liegt die Hauptaufgabe dadrinnen bestimmen zu koennen, ob die Umgebung fest ist, sodass 
 * durch die Welt (in Form des Spielers) navigiert werden kann.
 * @author leonie & hannah
 *
 */
public class HelpfulMethods {

	/**
	 * gibt an, ob die Hitbox eines Spielers mit der Umgebung sich ueberschneitet
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param data
	 * @return true, falls es keine ueberschneidungen gibt
	 */
	public static boolean isNoCollison(float x, float y, int width, int height, int[][] data) {
		return !inGroundDetection(x, y, width, height, data) && !inCeilingDetection(x, y, width, height, data) && !inLeftWallDetection(x, y, width, height, data)
				&& !inRightWallDetection(x, y, width, height, data);
	}
	
	/**
	 * ueberprueft fuer einen Punkt, ob sich jener innerhalb von Objekten der Umgebung befindet
	 * @param x
	 * @param y
	 * @param data
	 * @return true, falls an dem Punkt ein Objekt sich befindet
	 */
	private static boolean isPointSolid(float x, float y, int[][] data) {
		int maxWidth = data[0].length * Game.TILE_SIZE;  // die breite unseres Levels nicht überschreiten
		int maxHeight = data.length * Game.TILE_SIZE;  // die breite unseres Levels nicht überschreiten
		if (x<0 || x > maxWidth || y < 0 || y > maxHeight)
			return true;
		
		// berechnen, in welchem Abschnitt der LevelStruktur der Spieler sich befindet
		float xIndex = x/Game.TILE_SIZE;
		float yIndex = y/Game.TILE_SIZE;
		
		int val = data[(int)yIndex][(int)xIndex];
		
		if(val == 89 || val ==88 || val==69||val==70) // dornen 
			return false;
		
		if(val < 0 || val >= 95 || val != 0) {   // gucken ob es ueberhaupt einen Gegenstand geben koennte
			// val == 0 ist transparent
			return true;
		}
		return false;
	}
	
	/**
	 * ueberprueft fuer einen Punkt, ob sich jener innerhalb von Objekten (unten) befindet
	 * @param x
	 * @param y
	 * @param data
	 * @return true, falls an dem Punkt ein Objekt sich befindet
	 */
	public static boolean inGroundDetection(float x, float y, int width, int height, int[][] data) {
		boolean inground = false;;
		for (int i = (int) x; i < x+width; i++)
			inground = isPointSolid(i, y+height, data) || inground;
		return inground;
	}
	
	/**
	 * ueberprueft fuer einen Punkt, ob sich jener innerhalb von Objekten (oben) befindet
	 * @param x
	 * @param y
	 * @param data
	 * @return true, falls an dem Punkt ein Objekt sich befindet
	 */
	public static boolean inCeilingDetection(float x, float y, int width, int height, int[][] data) {
		boolean inceiling = false;;
		for (int i = (int) x; i < x+width; i++)
			inceiling = isPointSolid(i, y, data) || inceiling;
		return inceiling;
	}
	
	/**
	 * ueberprueft fuer einen Punkt, ob sich jener innerhalb von Objekten (links) befindet
	 * @param x
	 * @param y
	 * @param data
	 * @return true, falls an dem Punkt ein Objekt sich befindet
	 */
	public static boolean inLeftWallDetection(float x, float y, int width, int height, int[][] data) {
		boolean inleftwall = false;;
		for (int i = (int) y; i < y+height; i++)
			inleftwall = isPointSolid(x, i, data) || inleftwall;
		return inleftwall;
	}
	
	/**
	 * ueberprueft fuer einen Punkt, ob sich jener innerhalb von Objekten (rechts) befindet
	 * @param x
	 * @param y
	 * @param data
	 * @return true, falls an dem Punkt ein Objekt sich befindet
	 */
	public static boolean inRightWallDetection(float x, float y, int width, int height, int[][] data) {
		boolean inrightwall = false;;
		for (int i = (int) y; i < y+height; i++)
			inrightwall = isPointSolid(x+width, i, data) || inrightwall;
		return inrightwall;
	}
	
	/**
	 * prueft fuer den Mittel-Punkt eines Objektes, ob sich jener innerhalb der Dornen befindet
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param data
	 * @return true, falls sich innerhalb dornen befindet
	 */
	public static boolean dangerDetection(float x, float y, int width, int height, int[][] data) {
		return isPointDangerous(x + width/2, y +height/2, data);
	}
	
	
	/**
	 * prueft fuer einen Punkt, ob sich jener innerhalb der Dornen befindet
	 * @param x
	 * @param y
	 * @param data
	 * @return true, falls innerhalb Dornen
	 */
	private static boolean isPointDangerous(float x, float y, int[][] data) {
		float xIndex = x/Game.TILE_SIZE;
		float yIndex = y/Game.TILE_SIZE;
		
		int val = data[(int)yIndex][(int)xIndex]; 	// berechnen, in welchem Abschnitt der LevelStruktur der Spieler sich befindet

		if(val == 89 || val ==88 || val==69||val==70) // dornen 
			return true;
		
		return false;
	}
	
	/**
	 * gibt an, ob sich der Charakter innerhelb von 1 Pixel im Boden befinden würde 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param data
	 * @return true, falls sich der Charakter im Boden befinden wuerde
	 */
	public static boolean onGroundDetection(float x, float y, int width, int height, int[][] data) {
		boolean onGround = isPointSolid(x, y+height+1, data) || isPointSolid(x+width, y+height+1, data);
		return onGround;
	}

}
