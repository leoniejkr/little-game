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
 * Wird angezeigt, wenn ein Level abgeschlossen ist.
 * Macht einen State des GamePanels aus
 * @author leonie & hannah
 *
 */
public class CompletedMessage extends State{

	static int x;
	static int y;
	
	static int quitButtonx = Game.GAME_WIDTH/4 -50;
	static int quitButtony = 700;
	
	static int exitButtonx = 2*Game.GAME_WIDTH/4 -50;
	static int exitButtony = 700;
	
	static int resumeButtonx = 3*Game.GAME_WIDTH/4 -50;
	static int resumeButtony = 700;

	
	static int ButtonHeight = 37;
	static int ButtonLenght = 140;
	
	static Color background = new Color(140, 64, 100);
	
	private static boolean exitButtonSelected;
	private static boolean resumeButtonSelected;
	private static boolean quitButtonSelected;

	/**
	 * Konstruktor
	 * @param name
	 * @param x breite
	 * @param y hoehe
	 */
	 public CompletedMessage(String name, int x, int y) {
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
    public static Rectangle resumeButton = new Rectangle(resumeButtonx, resumeButtony, ButtonLenght, ButtonHeight);
	
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
        g.drawString("CONGRATS", Game.GAME_WIDTH/2 -70, 230);

        g.setFont(fnt2);
        g.setColor(Color.white);

        g.drawString("du hast das Level bestanden!",Game.GAME_WIDTH/2 -53, 280); // x koordinate, y koordinate für Fenster
  
        g.setFont(fnt1);
        g.drawString("Home", exitButton.x + 22+4, exitButton.y + 28);
            g2d.draw(exitButton);
        g.drawString("Quit", quitButton.x + 30+4, quitButton.y + 28);
            g2d.draw(quitButton);
        g.drawString("Continue", resumeButton.x + 10, resumeButton.y + 28);
            g2d.draw(resumeButton);
            
        drawSelectedButtons(g, g2d);

    }
    
	private static void drawSelectedButtons(Graphics g, Graphics2D g2d) {
        drawSelectedResumeButton(g,g2d);
        drawSelectedExitButton(g, g2d);
        drawSelectedQuitButton(g,g2d);
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

	private static void drawSelectedResumeButton(Graphics g, Graphics2D g2d) {
		if(resumeButtonSelected) {
    		Rectangle button2 = new Rectangle(resumeButtonx-1, resumeButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
            g.setColor(Color.white);
            g2d.draw(button2);
        }
        else {
        	Rectangle button2 = new Rectangle(resumeButtonx-1, resumeButtony-1, ButtonLenght+2, ButtonHeight+2);   // (Erster Punkt x,y & dann länge & Breite) die Buttons erstellen
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
	 * gibt in Form eines Arrays Information uber die X- & Y-Koordinate des ResumeButtons
	 * @return Array mit Info
	 */
	public static int[] getResumeButtonInfo() {
		int[] info = {resumeButtonx, resumeButtony};
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
	 * setzt ob der ResumeButton ausgewaehlt ist
	 * @param bol
	 */
	public static void setResumeButtonSelected(boolean bol) {
		resumeButtonSelected=bol;
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
	
	/**
	 * gibt an, ob der ResumeButton ausgewaehlt ist
	 * @return true, falls schon
	 */
	public static boolean isResumeButtonSelected() {
		return resumeButtonSelected;
	}

    
}
