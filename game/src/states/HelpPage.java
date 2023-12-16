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
 * HelpPage Seite, die ueber das Menu erreichbar ist & Tipps
 * ueber das Spiel gibt.
 * Macht einen State des GamePanels aus
 * @author leonie & hannah
 *
 */
public class HelpPage extends State  {
	
	static int x;
	static int y;
	
	static int quitButtonx = 1*Game.GAME_WIDTH/3 -50;
	static int quitButtony = 700; 
	
	static int exitButtonx = 2*Game.GAME_WIDTH/3 -50;
	static int exitButtony = 700;
	
	static int ButtonHeight = 37; 
	static int ButtonLenght = 100;
	
	static Color background = new Color(130,130,235);
	
	private static boolean exitButtonSelected;
	private static boolean quitButtonSelected;

	/**
	 * Konstruktur
	 * @param name
	 * @param x
	 * @param y
	 */
	public HelpPage(String name, int x, int y) {
			super(name);
			this.x = x;   
			this.y = y;
	    }

	@Override
	protected boolean isInState(JComponent c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static Rectangle exitButton = new Rectangle(exitButtonx, exitButtony, ButtonLenght, ButtonHeight);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
    public static Rectangle quitButton = new Rectangle(quitButtonx, quitButtony, ButtonLenght, ButtonHeight);
	
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
	        Font fnt3 = new Font("arial", Font.BOLD, 10);
	        
	        g.setFont(fnt0);
	        g.setColor(Color.white);
	        g.drawString("HELP", Game.GAME_WIDTH/2 -120, 130);

	        g.setFont(fnt1);
	        g.setColor(Color.white);

	        g.drawString("hilfreiche informationen",100,185+20); // x koordinate, y koordinate für Fenster
	        
	        g.setColor(Color.yellow);
	        
	        g.drawString("W",180,240+5+20); // x koordinate, y koordinate für Fenster
	        g.drawString("A",180,240+40+5+20); // x koordinate, y koordinate für Fenster
	        g.drawString("S",180,240+40+40+5+20); // x koordinate, y koordinate für Fenster
	        g.drawString("D",180,240+40+40+40+5+20); // x koordinate, y koordinate für Fenster
	        
	        g.setFont(fnt3);
	        g.drawString("space",180,240+40+40+40+40+5+10); // x koordinate, y koordinate für Fenster
	        g.drawString("Shift",180,240+40+40+40+40+5+10+40); // x koordinate, y koordinate für Fenster

	        g.setFont(fnt1);
	        g.drawString("*",180+20,240+40+40+40+40+5+60+20+40); // x koordinate, y koordinate für Fenster

	        
	        g.setFont(fnt2);
	        g.setColor(Color.white);

	        g.drawString("Zeichen: ",100,240+20); // x koordinate, y koordinate für Fenster
	        g.drawString("fliegen",225,240+20); // x koordinate, y koordinate für Fenster
	        
	        g.drawString("Zeichen: ",100,240+40+20); // x koordinate, y koordinate für Fenster
	        g.drawString("laufen links",225,240+40+20); // x koordinate, y koordinate für Fenster

	        g.drawString("Zeichen: ",100,240+40+40+20); // x koordinate, y koordinate für Fenster
	        g.drawString("ducken",225,240+40+40+20); // x koordinate, y koordinate für Fenster

	        g.drawString("Zeichen: ",100,240+40+40+40+20); // x koordinate, y koordinate für Fenster
	        g.drawString("laufen rechts",225,240+40+40+40+20); // x koordinate, y koordinate für Fenster

	        g.drawString("Zeichen: ",100,240+40+40+40+40+20); // x koordinate, y koordinate für Fenster
	        g.drawString("springen",225,240+40+40+40+40+20); // x koordinate, y koordinate für Fenster
	        
	        g.drawString("Zeichen: ",100,240+40+40+40+40+20+40); // x koordinate, y koordinate für Fenster
	        g.drawString("sprinten",225,240+40+40+40+40+20+40); // x koordinate, y koordinate für Fenster

	        
	        g.drawString("Mausinput: ",100,240+40+40+40+40+60+20+40); // x koordinate, y koordinate für Fenster
	        g.drawString("attacke",225,240+40+40+40+40+60+20+40); // x koordinate, y koordinate für Fenster

	        g.drawString("Ziel des Spiels ist es, eine im Level versteckte Flagge zu erreichen, indem durch die Welt navigiert wird! ",100,240+40+40+40+40+60+150); // x koordinate, y koordinate für Fenster
	        g.drawString("Hierbei muss Hindernissen (Dornen) ausgewichen werden. ",100,240+40+40+40+40+60+150+25); // x koordinate, y koordinate für Fenster

	        
	        g.setFont(fnt1);
	        g.drawString("Home", exitButton.x + 12, exitButton.y + 28);
	            g2d.draw(exitButton);
	        g.drawString("Quit", quitButton.x + 19, quitButton.y + 28);
	            g2d.draw(quitButton);
	            
	        drawSelectedButtons(g, g2d);

	    }
	 
		private static void drawSelectedButtons(Graphics g, Graphics2D g2d) {
	        drawSelectedQuitButton(g, g2d);
	        drawSelectedExitButton(g,g2d);
	}

		private static void drawSelectedQuitButton(Graphics g, Graphics2D g2d) {
			if(quitButtonSelected) {
	    		Rectangle button2 = new Rectangle(quitButtonx-1, quitButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
	            g.setColor(Color.white);
	            g2d.draw(button2);
	        }
	        else {
	        	Rectangle button2 = new Rectangle(quitButtonx-1, quitButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
	            g.setColor(background);
	            g2d.draw(button2);
	        }
						
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
		 * gibt in Form eines Arrays Information uber die X- & Y-Koordinate des QUitButtons
		 * @return Array mit Info
		 */
		public static int[] getQuitButtonInfo() {
			int[] info = {quitButtonx, quitButtony};
			return info;
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
		 * setzt ob der QUitButton ausgewaehlt ist
		 * @param bol
		 */
		public static void setQuitButtonSelected(boolean bol) {
			quitButtonSelected=bol;
		}
		/**
		 * setzt ob der ExitButton (HomeButton) ausgewaehlt ist
		 * @param bol
		 */
		public static void setExitButtonSelected(boolean bol) {
			exitButtonSelected=bol;
		}
		
		/**
		 * gibt an, ob der ExitButton (HomeButton) ausgewaehlt ist
		 * @return true, falls schon
		 */
		public static boolean isExitButtonSelected() {
			return exitButtonSelected;
		}
		/**
		 * gibt an, ob der QuitButton ausgewaehlt ist
		 * @return true, falls schon
		 */
		public static boolean isQuitButtonSelected() {
			return quitButtonSelected;
		}
}
