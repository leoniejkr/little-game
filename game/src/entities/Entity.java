package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Entity-Klasse faesst Eigenschaften eines Objekts zusammen
 * @author leonie & hannah
 *
 */
public abstract class Entity {
	
	protected float x,y;  // position 
	protected int width, height; // groeße
	protected Rectangle hitbox;  // damit unser Charakter nicht durch waende etc gehen kann

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		initHitbox();
	
	}

	protected void drawHitbox(Graphics g, int xOffset, int yOffset) {
		// debug hitbox --> wissen wie groß das ding sein sollte
		g.setColor(Color.yellow);
//		g.drawRect(hitbox.x -xOffset , hitbox.y - yOffset, hitbox.width, hitbox.height);
	}
	
	private void initHitbox() {
		hitbox = new Rectangle((int)x+9,(int)y+8,width-21,height-4);
	}
	
	// wenn spieler sich bewegt, wird die hitbox wieder angepasst
	protected void updateHitbox() {
		hitbox.x = (int)x+9;
		hitbox.y = (int)y+5;
	}
	
	/**
	 * gibt die Hitbox zurueck
	 * @return Hitbox
	 */
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	/**
	 * gibt die Breite der Hitbox zurueck
	 * @return Breite
	 */
	public int getWidt() {
		return (int) hitbox.getWidth();
	}
	
	/**
	 * gibt die Hoehe der Hitbox zurueck
	 * @return Hoehe
	 */
	public int getHeight() {
		return (int) hitbox.getHeight();
	}

	

}
