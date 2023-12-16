package level;

/**
 * Speichert Daten eines Levels in Form zweier 2D Arrays, wobei eins fuer Informationen der Umgebung (Info fuer Spieler + Gestaltung)
 * und eines fuer Informationen der Flagge genutzt wird.
 * In den Arrays sind Sprite-Idices gespeichert, die stellvertretend fuer Subimages stehen
 * @author leonie & hannah
 *
 */
public class Level {
	
	private int[][] data;
	private int[][] flagData;

	/**
	 * Konstuktor
	 * @param data
	 * @param flagData
	 */
	public Level(int[][] data, int[][] flagData) {
		this.data = data;
		this.flagData = flagData;
	}

	/**
	 * gibt den SpriteIndex zurueck, welcher an einer angegebenen Position (X- & Y-Koordinate) auf dem Fenster wiederzufinden ist
	 * @param x X-Position
	 * @param y Y-Position
	 * @return SpriteIndex
	 */
	public int getSpriteIndex(int x, int y) {
		return data[y][x];
	}

	/**
	 * gibt das 2D Array zurueck, welches die Daten des Levels mit Informationen der Umgebung besitzt.
	 * @return
	 */
	public int[][] getData() {
		return data;
	}
	
	/**
	 * gibt das 2D Array zureuck, welches die Daten des Levels mit Informationen bezueglich der Goal-Position besitzt.
	 * @return
	 */
	public int[][] getFlagData() {
		return flagData;
	}
	
	/**
	 * gibt den den Wert wieder, welcher an angegebener Position (X- & Y-Koordinate) im 2D-Array, mit informationen der Flagge, liegt
	 * @param x X-Position
	 * @param y Y-Position
	 * @return Wert an der Position 
	 * 		70, falls eine Flagge an der Position steht
	 * 		0, falls nicht
	 */
	public int getFlagSpriteIndex(int x, int y) {
		return flagData[y][x];
	}
	
}