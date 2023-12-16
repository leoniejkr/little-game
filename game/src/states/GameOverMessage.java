package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.nimbus.State;

import main.Game;
/**
 * Wird angezeigt, wenn ein Level verloren wurde.
 * Macht einen State des GamePanels aus
 * @author leonie & hannah
 *
 */
public class GameOverMessage extends State {

	
	static int x;
	static int y;
	
	static int exitButtonx = 2*Game.GAME_WIDTH/4 -50;
	static int exitButtony = 700;
	
	static int ButtonHeight = 37;
	static int ButtonLenght = 100;
	
	static Color background = new Color(85, 170, 45);
	
	private static boolean exitButtonSelected;

	/**
	 * Konstruktor
	 * @param name
	 * @param x breite
	 * @param y hoehe
	 */
	public GameOverMessage(String name, int x, int y) {
			super(name);
			this.x = x;
			this.y = y;
	    }
	 
	public static Rectangle exitButton = new Rectangle(exitButtonx, exitButtony, ButtonLenght, ButtonHeight);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen

    /**
     * malt auf Grafik
     * @param g
     */
	 public static void render(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;

	        g2d.setColor(background);
	        g2d.fillRect(0, 0, x, y);
	        
	        Font fnt0 = new Font("arial", Font.BOLD, 45);
	        Font fnt1 = new Font("arial", Font.BOLD, 27);
	        Font fnt2 = new Font("arial", Font.BOLD, 15);
	        
	        g.setFont(fnt0);
	        g.setColor(Color.white);
	        g.drawString("GAME OVER", Game.GAME_WIDTH/2 -120, 230);

	        g.setFont(fnt2);
	        g.setColor(Color.white);

	        g.drawString("du hast verloren! ",Game.GAME_WIDTH/2 -50, 280); // x koordinate, y koordinate für Fenster
	        g.drawString("Viel Glueck naechstes Mal ",Game.GAME_WIDTH/2 -90, 310); // x koordinate, y koordinate für Fenster
	  
	        g.setFont(fnt1);
	        g.drawString("Home", exitButton.x + 12, exitButton.y + 28);
	            g2d.draw(exitButton);

	            
	        drawSelectedButtons(g, g2d);

	    }

	private static void drawSelectedButtons(Graphics g, Graphics2D g2d) {
        drawSelectedExitButton(g, g2d);
		
	}

	private static void drawSelectedExitButton(Graphics g, Graphics2D g2d) {
		if(exitButtonSelected) {
    		Rectangle button2 = new Rectangle(exitButtonx-1, exitButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
            g.setColor(Color.white);
            g2d.draw(button2);
        }
        else {
        	Rectangle button2 = new Rectangle(exitButtonx-1, exitButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
            g.setColor(background);
            g2d.draw(button2);
        }			
	}
	
	/**
	 * gibt in Form eines Arrays Information uber die X- & Y-Koordinate des ExitButtons (HomeButton)
	 * @return Array mit Info
	 */
	public static int[] getExitButtonInfo() {
		int[] info = {exitButtonx, exitButtony};
		return info;
	}
	
	/**
	 * setzt ob der ExitButton (HomeButton) ausgewaehlt ist
	 * @param bol
	 */
	public static void setExitButtonSelected(boolean bol) {
		exitButtonSelected=bol;
	}

	@Override
	protected boolean isInState(JComponent c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * gibt in Form eines Arrays Information uber die Hoehe und Breite jeglicher Buttons
	 * @return Array mit Info
	 */
	public static int[] getButtonSizeInfo() {
		int[] info = {ButtonHeight, ButtonLenght};
		return info;
	}
	
	/**
	 * gibt an, ob der ExitButton (HomeButton) ausgewaehlt ist
	 * @return true, falls schon
	 */
	public static boolean isExitButtonSelected() {
		return exitButtonSelected;
	}
	
}
