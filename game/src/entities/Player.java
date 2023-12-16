package entities;

import static utilz.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.HelpfulMethods;
import utilz.AudioPlayer;
import utilz.Constants;
import utilz.Img;

/**
 * Klasse welche Informationen bezueglich des Spielers speichert
 * @author leonie & hannah
 *
 */
public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	
	private int playerAction = STEHEN;
	private boolean moving = false, attacking;
	private boolean left, up, right, down, jumpStart, inDanger, speed, jumping;
	private float playerSpeed = 1.6f;
	
	private boolean inAir = true;
	private int jumpTime = 0;
	
	private int gravityFct = 2; // wie stark die Schwerkraft wirkt
	private float speedFct = 1.6f; // wie stark schneller gelaufen wird
	private int jumpPower = 4; 
	private float floatPower = 1f;
	private int jumpLength = 80;
	
	private int[][] levlData;
	
	private AudioPlayer audioPlayer;
	
	/**
	 * Konstruktor
	 * @param x x-Position
	 * @param y y-Position
	 * @param width Breite
	 * @param height Hoehe
	 */
	public Player(float x, float y,  int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		audioPlayer = new AudioPlayer();
	}

	/**
	 * Updated Position, Animation, Soundeffekt und den Gesundheitsstatus des Spielers
	 */
	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
		updateDanger();
	}

	/**
	 * Methode welche genutzt werden kann, um eine Animation auf einer Graphik zu malen
	 * @param g Grafik
	 * @param xOffset um wie viel sich die Position entlangd er x achse aendern soll
	 * @param yOffset um wie viel sich die Position entlang der y achse aendern soll
	 */
	public void render(Graphics g, int xOffset, int yOffset) {
		g.drawImage(animations[playerAction][aniIndex], (int) x - xOffset, (int) y - yOffset, width, height, null);
		drawHitbox(g, xOffset, yOffset);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
		}}
		
		if(moving && down) {   // wollen immer unten bleiben
			aniIndex = 3;
		}

	}

	/**
	 * gibt an, in welcher Animation sich der SPieler gerade befindet
	 * @return Aktion des Spielers
	 */
	public int getAnimation() {
		return playerAction;
	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving && down)
			playerAction = DUCKEN;
		else if (moving && up)
			playerAction = FLIEGEN;
		else if(jumping) {
			playerAction = SPRINGEN;
		}
		else if (moving && left && right)
			playerAction = STEHEN;
		else if (speed &&(moving && left || moving && right))
			playerAction = LAUFEN;
		else if (moving)
			playerAction = GEHEN;
		
		else
			playerAction = STEHEN;

		if (attacking) {
			playerAction = ATTACKE;}

		if(inDanger) {
			playerAction = STERBEN;}
		
		if (startAni != playerAction) {
			resetAniTick();
			updateSoundEffect();
		}
	}
	
	private void updateSoundEffect() {
		if(playerAction == STERBEN)
			audioPlayer.playEffect(AudioPlayer.DYING);
		
		if(playerAction == SPRINGEN && jumping)
			audioPlayer.playEffect(AudioPlayer.JUMP);
		
		if(playerAction == ATTACKE)
			audioPlayer.playEffect(AudioPlayer.ATTACK);
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {  // bewegt die figur auf dem bildschirm
		moving = false;
    	
		updateAir();
		//SCHWERKRAFT
		if(inAir) {
	    	if (!isOnGround()) {  // nicht im Boden versinken
	    		if (HelpfulMethods.isNoCollison(hitbox.x, hitbox.y+gravityFct, hitbox.width, hitbox.height, levlData)){	
	    			this.y = this.y + gravityFct;
	    			updateHitbox();}}
	    }
		
		//HUEPFEN
		if (jumpTime > 0 && jumpTime < jumpLength) {
			goUp();
			jumpTime++;
			if (jumpTime == jumpLength -1) {
				jumpTime = 0;
				jumping = false;
			}
		}
				
		if (jumpStart && isOnGround()) { // nur falls auf Boden --> Springen
			jumping = true;
			goUp();
			jumpStart = false;
			jumpTime = 1;
		}
	
		// KEIN INPUT
		if (!left && !right && !up && !down)
			return;
		
		float xSpeed = 0; float ySpeed = 0;
		
		// BEWEGUNGEN (LINKS/RECHTS)
		if (left && !right) {
			xSpeed = -playerSpeed;
		}  else if (right && !left) {
			xSpeed = playerSpeed;
		}
		
		// Geschwindigkeitserhoehung
		if (speed)
			xSpeed *= speedFct;
		
		// BEWEGUNGEN (FLIEGEN/DUCKEN)
		if (up && !down) {
			ySpeed = -(floatPower+gravityFct);
		} else if (down && !up) {
			ySpeed = playerSpeed;
		}
		
		updateHitbox();
		//BEWEGUNGEN AUSFUEHREN
		// falls es keine Uberschenidungen mit der Umgebung durch Bewegung geben wuerde --> pos aendern
		if (HelpfulMethods.isNoCollison(hitbox.x+xSpeed, hitbox.y+ySpeed, hitbox.width, hitbox.height, levlData)){
			this.x = x + xSpeed;
			this.y = y + ySpeed;
		}	
		
		checkAccidentalCollision(); // fixen bei fehlern
		updateHitbox();
		moving = true;

	}

	private void checkAccidentalCollision() { // es kann zu fehlerhaften positionieren der figur kommen, weil die isNoCollision-Methode
		// nicht ALLE Pixel entlang der Seiten des Objektes pruefen kann
		// falls es zu fehlern kommt, rueckt diese methode sie richtig
		// UND Rundungsfehler in isPointSolid Methode koennen dazu fuehren
		
		if (HelpfulMethods.inCeilingDetection(hitbox.x, hitbox.y, hitbox.width, hitbox.height, levlData)) {
			this.y = y +1;}
		
		if (HelpfulMethods.inGroundDetection(hitbox.x, hitbox.y, hitbox.width, hitbox.height, levlData)) {
			this.y = y-1;}
		
		if (HelpfulMethods.inLeftWallDetection(hitbox.x, hitbox.y, hitbox.width, hitbox.height, levlData)) {
			this.x = x+1;}
		
		if (HelpfulMethods.inRightWallDetection(hitbox.x, hitbox.y, hitbox.width, hitbox.height, levlData)) {
			this.x = x-1;}
	}

	private void updateAir() {
		inAir = !HelpfulMethods.onGroundDetection(hitbox.x, hitbox.y, hitbox.width, hitbox.height, levlData);	
	}
	

	private void updateDanger() {
		inDanger = HelpfulMethods.dangerDetection(hitbox.x, hitbox.y, hitbox.width, hitbox.height, levlData);
	}

	private void loadAnimations() {
		BufferedImage img = Img.getImages(Img.SPIELER);
		animations = new BufferedImage[9][8];
		for (int j = 0; j < animations.length; j++)  // hoehe
			for (int i = 0; i < animations[j].length; i++)  // breite
				animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
				// berechnen: breite des bildes dividiert mit anzahl sprites in breite
				//            hoehe genauso
				// bspw fuer /player_sprites werte [9][6] und 64 & 40 nötig
	}
	
	/**
	 * laedt Daten des Levels & speichert es in form eines
	 * 2D Arrays ab 
	 * @param lvlData
	 */
	public void loadLvlData(int[][] lvlData) {
		this.levlData = lvlData;
	}

	/**
	 * setzt Logik auf urspruengliche Ausgangssituation
	 */
	public void resetDirBooleans() {  // zu beginn & bei Störungen alle auf falsch setzen (es gab noch kein input)
		left = false;
		right = false;
		up = false;
		down = false;
		attacking = false;
		inDanger = false;
		jumpStart = false;
	}

	/**
	 * gibt an ob der Spieler gerade Attackiert
	 * @return true, falls schon
	 */
	public boolean isAttacking() {
		return attacking;
	}
	
	/**
	 * setzt ob der Spieler attackiert
	 * @param attacking
	 */
	public void setAttacking(boolean attacking) {   // wenn die linke Maustaste gedrueckt wird!
		this.attacking = attacking;
	}

	/**
	 * gibt an ob der Spieler gerade nach links gehen will
	 * @return true, falls schon
	 */
	public boolean isLeft() {
		return left;
	}

	/**
	 * setzt ob der Spieler sich nach links bewegen soll
	 * @param left
	 */
	public void setLeft(boolean left) {  // a wird gedrueckt
		this.left = left;
	}

	/**
	 * gibt an ob der Spieler gerade nach oben fliegen will
	 * @return true, falls schon
	 */
	public boolean isUp() {
		return up;
	}

	/**
	 * setzt ob der Spieler  gerade nach oben fliegen soll
	 * @param up
	 */
	public void setUp(boolean up) { // w wird gedrueckt
		this.up = up;
	}

	/**
	 * gibt an ob der Spieler gerade nach rechts gehen will
	 * @return true, falls schon
	 */
	public boolean isRight() {
		return right;
	}

	/**
	 * setzt ob der Spieler sich nach rechts bewegen soll
	 * @param right
	 */
	public void setRight(boolean right) {  // d wird gedrueckt
		this.right = right;
	}

	/**
	 * gibt an ob der Spieler gerade sich ducken will
	 * @return true, falls schon
	 */
	public boolean isDown() {
		return down;
	}

	/**
	 * setzt ob der Spieler sich ducke soll
	 * @param down
	 */
	public void setDown(boolean down) {  // s wird gedrueckt
		this.down = down;
	}
	
	/**
	 * gibt an ob der Spieler gerade springen will
	 * @return true, falls schon
	 */
	public boolean isJump() {
		return jumpStart;
	}
	
	/**
	 * setzt ob der Spieler springen soll
	 * @param jump
	 */
	public void setJump(boolean jump) {  // SPACE wird gedrueckt
		this.jumpStart = jump;
	}
	
	/**
	 * gibt an ob der Spieler sich gerade in Gefahr (Dornen) befindet
	 * @return true, falls schon
	 */
	public boolean isInDanger() {
		return inDanger;
	}
	
	/**
	 * setzt ob der Spieler sich in Gefahr befinden soll
	 * @param danger
	 */
	public void setInDanger(boolean danger) {
		this.inDanger = danger;
	}
	
	/**
	 * gibt an ob der Spieler gerade Sprinten will
	 * @return true, falls schon
	 */
	public boolean isSpeed() {
		return speed;
	}
	
	/**
	 * setzt ob der Spieler gerade sprinten soll
	 * @param speed
	 */
	public void setSpeed(boolean speed) {
		this.speed = speed;
	}
	
	/**
	 * setzt die X-Koordinate des Spielers fest. Keine negativen Werte sind moeglich
	 * @param x die X-Koordinate
	 */
	public void setX(float x) {
		if (x <0)
			return;
		this.x = x;
	}
	/**
	 * setzt die Y-Koordinate des Spielers fest. Keine negativen Werte sind moeglich
	 * @param die Y-Koordinate
	 */
	public void setY(float y) {
		if (y <0)
			return;
		this.y = y;
	}
	
	/**
	 * gibt die X-Koordinate des Spielers
	 * @return Y-koordinate
	 */
	public int getX() { 
		return (int) this.x;
	}
	
	/**
	 * gibt die Y-Koordinate des Spielers
	 * @return Y-koordinate
	 */
	public int getY() {
		return (int) this.y;
	}
	
	/**
	 * gibt an, ob sich der Spieler gerade aufm Boden befindet
	 * @return true, falls schon
	 */
	public boolean isOnGround() { 	// gibt an, ob ein Spieler auf dem Boden steht
		return HelpfulMethods.onGroundDetection(hitbox.x, hitbox.y+1, hitbox.width, hitbox.height, levlData);
	}
    
    private void goUp() {
    	if (HelpfulMethods.isNoCollison(hitbox.x, hitbox.y-jumpPower, hitbox.width, hitbox.height, levlData)) {
			this.y = this.y - jumpPower;
			updateHitbox();
    	}
    }	
	  
    /**
     * gibt den Faktor an, mit welchem die Schwerkraft auf den Spieler einwirkt
     * @return der Faktor
     */
    public int getGravityFactor() {
    	return gravityFct;
    }
    
    /**
     * setzt mit welchem Faktor die Schwerkraft auf den Spieler einwirken soll
     * negative Werte werden positiv
     * @param fac der Faktor 
     */
    public void setGrafityFactor(int fac) {
		if (fac <0)
			fac *= (-1);
    	gravityFct = fac;
    }

    /**
     * gibt die Geschwindigkeit an, mit welchem der Spieler sich bewegen kann (im Zustand des Gehens)
     * @return die Geschwindigkeit
     */
    public float getPlayerSpeed() {
    	return playerSpeed;
    }
    
    /**
     * setzt mit welcher Geschwindigkeit der Spieler sich bewegen soll
     * negative Werte werden positiv
     * @param speed die Geschwindigkeit
     */
    public void setPlayerSpeed(float speed) {
		if (speed <0)
			speed *= (-1);
    	playerSpeed = speed;
    }

    /**
     * setzt den Faktor, um welchen der Spieler springen kann
     * negative Werte werden positiv
     * @param pow der Faktor
     */
    public void setJumpPower (int pow) {
		if (pow <0)
			pow *= (-1);
    	jumpPower = pow;
    }
    
    /**
     * gibt den Faktor, um welchen der Spieler springen kann
     * @return der Faktor
     */
    public float getJumpPower() {
    	return jumpPower;
    }

    /**
     * setzt den Faktor, um welchen der Spieler fliegen kann
     * negative Werte werden positiv
     * @param pow der Faktor
     */
    public void setFloatPower(float pow)  {
		if (pow <0)
			pow *= (-1);
    	floatPower = pow;
    }

    /**
     * gibt den Faktor, um welchen der Spieler fliegen kann
     * @return der Faktor
     */
    public float getFloatPower() {
    	return floatPower;
    }
    
    /**
     * setzt den Faktor, welcher die Springdauer ausmacht
     * negative Werte werden positiv
     * @param time der Faktor
     */
    public void setJumpLength(int time) {
		if (time <0)
			time *= (-1);
    	jumpLength = time;
    }
    
    /**
     * setzt den Faktor, welcher die bisherige Springdauer des Spielers ausmacht
     * negative Werte werden positiv
     * @param time die bereits gesprungende Zeit
     */
    public void setJumpTime(int time) {
		if (time <0)
			time *= (-1);
    	jumpTime = time;
    }
     /**
      * gibt die bereits gesprungende Zeit des Spielers zureuck
      * @return die bereits gesprungende Zeit
      */
    public int getJumpTime() {
    	return jumpTime;
    }
    
    /**
     * gibt den Faktor, welcher die Springdauer ausmacht
     * @return der Faktor
     */
    public int getJumpLength() {
    	return jumpLength;
    }
    
    /**
     * gibt den AudioPlayer des Players zuruek
     * @return der AudioPlayer
     */
    public AudioPlayer getAudioPlayer() {
    	return audioPlayer;
    }
    
    /**
     * gibt den Faktor an, um welchen die Geh-Geschwindigkeit beim Sprinten erhoeht
     * @return der Faktor
     */
    public float getSpeedFct() {
    	return speedFct;
    }
}
