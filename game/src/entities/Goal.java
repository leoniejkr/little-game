package entities;

import static utilz.Constants.FlagConstants.GetSpriteAmount;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import utilz.Img;

/**
 * Goal-Klasse fasst Eigenschaften des Ziels eines Levels zusammen
 * @author leonie & hannah
 *
 */
public class Goal extends Entity {

	private int flagAction = 0;
	private int aniTick, aniIndex, aniSpeed = 25;
	private BufferedImage[][] animations;
	private int[][] levlData;
	
	private int size = 40;
	

	/**
	 * Konstruktor
	 * @param x x-Position
	 * @param y y-Position
	 * @param width Breite
	 * @param height Hoehe
	 */
	public Goal(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
	}
	
	
	private void loadAnimations() {
		BufferedImage img = Img.getImages(Img.FLAG);
		animations = new BufferedImage[1][5];
		for (int j = 0; j < animations.length; j++)  // hoehe
			for (int i = 0; i < animations[j].length; i++)  // breite
				animations[j][i] = img.getSubimage(i * 60, j * 60, 60, 60);
				// berechnen: breite des bildes dividiert mit anzahl sprites in breite
				//            hoehe genauso
	}
	
	/**
	 * Methode welche genutzt werden kann, um eine Animation auf einer Graphik zu malen
	 * @param g Grafik
	 * @param xOffset um wie viel sich die Position entlangd er x achse aendern soll
	 * @param yOffset um wie viel sich die Position entlang der y achse aendern soll
	 */
	public void render(Graphics g, int xOffset, int yOffset) {
		g.drawImage(animations[flagAction][aniIndex], (int) x - xOffset, (int) y - yOffset, width, height, null);
		updateAnimationTick();
//		drawHitbox(g, xOffset);
	}
	
	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(flagAction)) {
				aniIndex = 0;
			}
		}
	}
	
	/**
	 * laedt Daten des Levels & speichert es in form eines
	 * 2D Arrays ab und positioniert das Ziel (Goal) einher Informationen des 2DArrays 
	 * @param lvlData
	 */
	public void loadLvlData(int[][] lvlData) {
		this.levlData = lvlData;
		for(int j = 0; j < levlData.length; j++) {
			for(int i = 0; i < levlData[0].length; i++) {
				if (levlData[j][i] == 70) {
					x = i*size  ; y=j * size +7;
					hitbox = new Rectangle((int)x+5,(int)y+7,width -40,height-10);
				}
			}
		}
	}

}
