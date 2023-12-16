package level;

//import main.Game;
//import utilz.Img;

import java.awt.Rectangle;
//import java.util.ArrayList;

import entities.Goal;
import entities.Player;

/**
 * der FlowManager reguliert den Kontrollfluss der Verschiedenen States des gamePanels, waehrend es Spielens.
 * Dies bedeutet, dass er die Beziehung zwischen Spieler & Ziel sowie Spieler & Hindernissen beobachtet und
 * je nachdem Anstoeße an den LevelManager gibt, dass jener entweder alle Vortschritte zuruecksetzt oder das naechste Level laedt
 * @author leonie & hannah..
 *
 */
public class FlowManager {
	
	private Player player;
	private Goal flag;
	
	private int cnt = 0;
	private int dyingTime = 111;
	
	private boolean completed;
	private boolean death;

	LevelManager levelManager;
	
	/**
	 * Konstruktor
	 * @param levelManager
	 * @param player
	 * @param flag
	 */
	public FlowManager(LevelManager levelManager, Player player, Goal flag) {
		this.levelManager = levelManager;
		this.player = player;
		this.flag = flag;
	}

	/**
	 * update Methode beobachtet die Beziehung von Player zu Hindernissen & Player zum Ziel und gibt Erkenntnisse an
	 * den LevelManager weiter
	 */
	public void update() {
		ckeckCompleted();
		checkPlayerHealth();
		
		if(completed) {
			levelManager.update(true);
		}
		
		if (death) {
			levelManager.update(false);
		}
		
		completed = false;
		death = false;
	}
	
	private void ckeckCompleted() {
		Rectangle hitboxP = player.getHitbox();
		Rectangle hitboxF = flag.getHitbox();
		
		// infos des Players
		int tlxp = (int) hitboxP.getX();  //topleft x
		int tlyp = (int) hitboxP.getY();  //topleft y
		int brxp = (int) hitboxP.getWidth() + tlxp; // bottom right x
		int bryp = (int) hitboxP.getHeight() +tlyp; // bottom right y 
		
		// infos der Flagge
		int tlxf = (int) hitboxF.getX();  //topleft x
		int tlyf = (int) hitboxF.getY();  //topleft y
		int brxf = (int) hitboxF.getWidth() + tlxf; // bottom right x
		int bryf = (int) hitboxF.getHeight() +tlyf; // bottom right y 
	
		boolean inGoal = false;
		
		if (tlxf < tlxp && brxp < brxf && tlyf < tlyp && bryp< bryf)  
			inGoal = true;
		
		completed = inGoal;
	}
	
	private void checkPlayerHealth() {
		if(player.isInDanger()) {
			cnt++;
			if(cnt >= dyingTime) {
				death = true;
				}}
		else {
			cnt = 0;
			death = false; }
	}
	
}
