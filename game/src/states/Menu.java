package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.nimbus.State;

/**
 * Wird beim Starten des Spiels & ggf nach Abrechen von Levels/ Abschließen des Kompletten Spiels angezeigt.
 * Macht einen State des GamePanels aus
 * @author leonie & hannah
 *
 */
public class Menu extends State {
	static int x;
	static int y;
	
	
	static int playButtonx = 30;
	static int playButtony = 55+50;
	
	static int helpButtonx = 30;
	static int helpButtony = 107+50;
	
	static int quitButtonx = 30;
	static int quitButtony = 159+50;
	
	static int ButtonHeight = 37;
	static int ButtonLenght = 100;
	
	static Color background = new Color(255,150,150);
	
	private static boolean playButtonSelected;
	private static boolean helpButtonSelected;
	private static boolean quitButtonSelected;

	/**
	 * Konstruktor
	 * @param name
	 * @param x breite
	 * @param y hoehe
	 */
    public Menu(String name, int x, int y) {
		super(name);
		this.x = x;
		this.y = y;
    }

	public static Rectangle playButton = new Rectangle(playButtonx, playButtony, ButtonLenght, ButtonHeight);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
    public static Rectangle helpButton = new Rectangle(helpButtonx, helpButtony, ButtonLenght, ButtonHeight);
    public static Rectangle quitButton = new Rectangle(quitButtonx, quitButtony, ButtonLenght, ButtonHeight);

    /**
     * malt das Menu
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
        g.drawString("it's a me", 10, 40);

        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 19, playButton.y + 28);
            g2d.draw(playButton);
        g.drawString("Help", helpButton.x + 19, helpButton.y + 28);
            g2d.draw(helpButton);
        g.drawString("Quit", quitButton.x + 19, quitButton.y + 28);
            g2d.draw(quitButton);
            
        drawSelectedButtons(g, g2d);


        g.setFont(fnt2);
        g.setColor(Color.white);

        g.drawString("my lil fun project",1100,750); // x koordinate, y koordinate für Fenster
    }

	private static void drawSelectedButtons(Graphics g, Graphics2D g2d) {
 
        drawSelectedPlayButton(g,g2d);
        drawSelectedHelpButton(g, g2d);
        drawSelectedQuitButton(g,g2d);
        
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

	private static void drawSelectedPlayButton(Graphics g, Graphics2D g2d) {
	       if(playButtonSelected) {
	    		Rectangle button2 = new Rectangle(playButtonx-1, playButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
	            g.setColor(Color.white);
	            g2d.draw(button2);
	        }
	        else {
	        	Rectangle button2 = new Rectangle(playButtonx-1, playButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
	            g.setColor(background);
	            g2d.draw(button2);
	        }
	}

	private static void drawSelectedHelpButton(Graphics g, Graphics2D g2d) {
        if(helpButtonSelected) {
    		Rectangle button2 = new Rectangle(helpButtonx-1, helpButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
            g.setColor(Color.white);
            g2d.draw(button2);
        }
        else {
        	Rectangle button2 = new Rectangle(helpButtonx-1, helpButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
            g.setColor(background);
            g2d.draw(button2);
        }
		
	}

	@Override
	protected boolean isInState(JComponent c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * gibt in Form eines Arrays Information uber die X- & Y-Koordinate des PlayButtons
	 * @return Array mit Info
	 */
	public static int[] getPlayButtonInfo() {
		int[] info = {playButtonx, playButtony};
		return info;
	}
	
	/**
	 * gibt in Form eines Arrays Information uber die X- & Y-Koordinate des HelpButtons
	 * @return Array mit Info
	 */
	public static int[] getHelpButtonInfo() {
		int[] info = {helpButtonx, helpButtony};
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
	 * setzt ob der PlayButton ausgewaehlt ist
	 * @param bol
	 */
	public static void setPlayButtonSelected(boolean bol) {
		playButtonSelected=bol;
	}
	/**
	 * setzt ob der QUitButton ausgewaehlt ist
	 * @param bol
	 */
	public static void setQuitButtonSelected(boolean bol) {
		quitButtonSelected=bol;
	}
	/**
	 * setzt ob der HelpButton ausgewaehlt ist
	 * @param bol
	 */
	public static void setHelpButtonSelected(boolean bol) {
		helpButtonSelected=bol;
	}
	
	/**
	 * gibt an, ob der PlayButton ausgewaehlt ist
	 * @return true, falls schon
	 */
	public static boolean isPlayButtonSelected() {
		return playButtonSelected;
	}
	/**
	 * gibt an, ob der QuitButton ausgewaehlt ist
	 * @return true, falls schon
	 */
	public static boolean isQuitButtonSelected() {
		return quitButtonSelected;
	}
	/**
	 * gibt an, ob der HelpButton ausgewaehlt ist
	 * @return true, falls schon
	 */
	public static boolean isHelpButtonSelected() {
		return helpButtonSelected;
	}
	


}
