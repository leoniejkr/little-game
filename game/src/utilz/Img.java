package utilz;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

/**
 * Die Img-Klasse verwaltet die verschiedenen Bilder, welche fuer Animationen & Umgebungsgestalltung benoetigt werden.
 * Ebenfalls laesst sie jene Informationen in Form eines Arrays abspreichern, welche in anderen Klassen aufgegriffen werden.
 * @author leonie & hannah
 *
 */
public class Img {
	
	public static final String SPIELER = "/character/AnimationSheet_Character.png";
	public static final String FLAG = "/flag/flag_animation.png";
	public static final String LEVEL = "/level/forest/tileset.png";
	public static final String BACKGROUND = "/level/forest/background.png";
	public static final String LEVEL_ONE_DATA = "/level/levelOne.png";
	public static final String LEVEL_TWO_DATA = "/level/levelTwoDornen.png";
	public static final String LEVEL_THREE_DATA = "/level/levelThreeDornen.png";
	public static final String LEVEL_FOUR_DATA = "/level/levelFourDornen.png";
	public static final String LEVEL_FIVE_DATA = "/level/levelFive.png";

	/**
	 * liefert gespeichertes Bild zurück ueber Verwendung eines Stings, welcher den Dateipfad angibt
	 * @param file
	 * @return
	 */
	public static BufferedImage getImages(String file) {
		BufferedImage img = null;
		InputStream is = Img.class.getResourceAsStream(file);
		try {
			img = ImageIO.read(is);
//			System.out.println(img.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return img;

	}
	
	/**
	 * gibt Daten ueber die Gestalltung in Form eines int Arrays zurueck. 
	 * Dabei wird ein Bild als Input genommen und deren Rot-Wert pro Pixel als Indiz 
	 * fuer eine Stelle des Bild-Arrays im LevelManager
	 * @return int Array
	 */
	public static int[][] getLevelData(String level)  {
		BufferedImage img = getImages(level);
		int [][] data = new int[img.getHeight()][img.getWidth()];

		for ( int j = 0; j < img.getHeight(); j++) {
			for ( int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int val = color.getRed();
				if (val >= 5*19) {
					val = 0;
				}
				data[j][i] = val;
			}
		}
		return data;
	}
	
	/**
	 * gibt Daten ueber die Gestalltung in Form eines int Arrays zurueck. 
	 * Dabei wird ein Bild als Input genommen und deren Rot-Wert pro Pixel als Indiz 
	 * fuer eine Stelle des Bild-Arrays im LevelManager
	 * @return int Array
	 */
	public static int[][] getLevelFlagData(String level)  {
		BufferedImage img = getImages(level);
		int [][] data = new int[img.getHeight()][img.getWidth()];

		for (int j = 0; j < img.getHeight(); j++) {
			for ( int i = 0; i < img.getWidth(); i++) {
				int val = 0;
				int red = new Color(img.getRGB(i, j)).getRed();
				if (red == 245) {
					val = 70;
//					System.out.println(i);
				}
				data[j][i] = val;
			}
		}
		return data;
	}



}
