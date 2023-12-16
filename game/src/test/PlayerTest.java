package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entities.Player;
import main.Game;
import main.GamePanel;
import utilz.AudioPlayer;
import utilz.Constants;
import utilz.Img;

/**
 * TestKlase zur Player-Klasse
 * @author leonie & hannah
 *
 */
public class PlayerTest {

	private Player player;
	private AudioPlayer audioPlayer;

	private final String TEST = "/test/PlayerTest.png";

	public PlayerTest() {
		init();
	}
	
	@BeforeEach
	public void init() {
		player = new Player(200,200,20,20);
		player.loadLvlData(Img.getLevelData(TEST));
		audioPlayer = player.getAudioPlayer();
	}

	@Test 
	public void testActionBoolean() {
		assertFalse(player.isLeft());
		assertFalse(player.isRight());
		assertFalse(player.isAttacking());
		assertFalse(player.isDown());
		assertFalse(player.isInDanger());
		assertFalse(player.isJump());
		assertFalse(player.isUp());

		player.setLeft(true);
		assertTrue(player.isLeft());
		player.setLeft(false);
		assertFalse(player.isLeft());

		player.setRight(true);
		assertTrue(player.isRight());
		player.setRight(false);
		assertFalse(player.isRight());

		player.setAttacking(true);
		assertTrue(player.isAttacking());
		player.setAttacking(false);
		assertFalse(player.isAttacking());

		player.setDown(true);
		assertTrue(player.isDown());
		player.setDown(false);
		assertFalse(player.isDown());

		player.setInDanger(true);
		assertTrue(player.isInDanger());
		player.setInDanger(false);
		assertFalse(player.isInDanger());

		player.setJump(true);
		assertTrue(player.isJump());
		player.setJump(false);
		assertFalse(player.isJump());

		player.setUp(true);
		assertTrue(player.isUp());
		player.setUp(false);
		assertFalse(player.isUp());
	}

	@Test
	public void testPlayerAnimation() {
		assertEquals(Constants.PlayerConstants.STEHEN, player.getAnimation());

		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.GEHEN, player.getAnimation());
		
		player.setSpeed(true);
		player.update();
		assertEquals(Constants.PlayerConstants.LAUFEN, player.getAnimation());

		player.setRight(true);
		player.update();
		assertEquals(Constants.PlayerConstants.STEHEN, player.getAnimation()); // wenn beides bewegt sich die person nicht --> stehen

		player.setLeft(false);
		player.update();
		assertEquals(Constants.PlayerConstants.LAUFEN, player.getAnimation());

		player.setJump(true);
		player.update();
		assertEquals(Constants.PlayerConstants.LAUFEN, player.getAnimation());
		
		setPlayerToGround();
		player.setJump(true);
		player.update();
		assertEquals(Constants.PlayerConstants.SPRINGEN, player.getAnimation());


		player.setUp(true);
		player.update();
		assertEquals(Constants.PlayerConstants.FLIEGEN, player.getAnimation());

		player.setDown(true);
		player.update();
		assertEquals(Constants.PlayerConstants.DUCKEN, player.getAnimation());

		player.setAttacking(true);
		player.update();
		assertEquals(Constants.PlayerConstants.ATTACKE, player.getAnimation());

		player.setInDanger(true);
		player.update();
		assertEquals(Constants.PlayerConstants.STERBEN, player.getAnimation());
		player.update();
		assertEquals(Constants.PlayerConstants.ATTACKE, player.getAnimation()); 
		// Sterbe Animation wird nach jedem Update neu abgefragt --> falls nicht explixit nochmal Danger erhoben wird, wird animation beendet

		player.setInDanger(true);
		player.setAttacking(true);
		player.setDown(true);
		player.setUp(true);
		player.setJump(true);
		player.setRight(true);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.STERBEN, player.getAnimation());

		player.setInDanger(false);
		player.setAttacking(true);
		player.setDown(true);
		player.setUp(true);
		player.setJump(true);
		player.setRight(true);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.ATTACKE, player.getAnimation());

		player.setInDanger(false);
		player.setAttacking(false);
		player.setDown(true);
		player.setUp(true);
		player.setJump(true);
		player.setRight(true);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.DUCKEN, player.getAnimation());

		player.setInDanger(false);
		player.setAttacking(false);
		player.setDown(false);
		player.setUp(true);
		player.setJump(true);
		player.setRight(true);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.SPRINGEN, player.getAnimation());

		player.setInDanger(false);
		player.setAttacking(false);
		player.setDown(false);
		player.setUp(false);
		player.setJump(true);
		player.setRight(true);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.SPRINGEN, player.getAnimation());

		player.setInDanger(false);
		player.setAttacking(false);
		player.setDown(false);
		player.setUp(false);
		player.setJump(false);
		player.setRight(true);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.SPRINGEN, player.getAnimation());

		player.setInDanger(false);
		player.setAttacking(false);
		player.setDown(false);
		player.setUp(false);
		player.setJump(false);
		player.setRight(false);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.SPRINGEN, player.getAnimation());
		
		
		for (int i = 0; i < player.getJumpLength() ; i++ )
			player.update();
		
		player.setInDanger(false);
		player.setAttacking(false);
		player.setDown(false);
		player.setUp(false);
		player.setJump(false);
		player.setRight(true);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.STEHEN, player.getAnimation());

		player.setInDanger(false);
		player.setAttacking(false);
		player.setDown(false);
		player.setUp(false);
		player.setJump(false);
		player.setRight(false);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.LAUFEN, player.getAnimation());
		
		player.setInDanger(false);
		player.setAttacking(false);
		player.setDown(false);
		player.setUp(false);
		player.setJump(false);
		player.setRight(false);
		player.setSpeed(false);
		player.setLeft(true);
		player.update();
		assertEquals(Constants.PlayerConstants.GEHEN, player.getAnimation());

		player.setInDanger(false);
		player.setAttacking(false);
		player.setDown(false);
		player.setUp(false);
		player.setJump(false);
		player.setRight(false);
		player.setLeft(false);
		player.update();
		assertEquals(Constants.PlayerConstants.STEHEN, player.getAnimation());

		//Rangliste Sterben >> Attacke >> DUCKEN >> SPRINGEN/FLIEGEN >> LAUFEN (L/R) >> GEHEN >> STEHEN

	}

	@Test
	public void testHorizontalMovement() {
		player.loadLvlData(Img.getLevelData(TEST));

		int x = 200;
		int y = 200;

		int factor = 5;
		player.setGrafityFactor(factor);

		// Test links-Bewegung
		int speed = 3;
		player.setPlayerSpeed(speed);
		player.setLeft(true);
		resetPlayerPos(x,y);
		player.update();
		assertTrue(player.getX() == x - speed);
		player.setLeft(false);
		
		player.setSpeed(true);  // geschwindigkeit verdoppeln
		player.setLeft(true);
		resetPlayerPos(x,y);
		player.update();
		assertTrue(player.getX() == (int)( x - speed*(player.getSpeedFct())));
		player.setLeft(false);
		player.setSpeed(false);

		// Test rechts-Bewegung
		int speed2 = 6;
		player.setPlayerSpeed(speed2);
		player.setRight(true);
		resetPlayerPos(x,y);
		player.update();
		assertTrue(player.getX() == x + speed2);
		player.setRight(false);
		
		player.setSpeed(true);  // geschwindigkeit verdoppeln
		player.setRight(true);
		resetPlayerPos(x,y);
		player.update();
		assertTrue(player.getX() == (int)( x + speed2*(player.getSpeedFct())));
		player.setRight(false);
		player.setSpeed(false);

	}

		@Test
	public void TestDownMovement() {
		player.loadLvlData(Img.getLevelData(TEST));

		int x = 200;
		int y = 200;

		// Test Schwerkraft
		int factor = 5;
		player.setGrafityFactor(factor);
		resetPlayerPos(x,y);
		player.update();
		assertTrue(player.getY() == y + factor);
		assertTrue(player.getX() == x);

		// Test Ducken
		int speed3 = 2;
		player.setPlayerSpeed(speed3);
		player.setDown(true);
		resetPlayerPos(x,y);
		player.update();
		assertTrue(player.getY() == y + speed3 + factor);
	}
		
	@Test
	public void TestConstraints() {
		player.loadLvlData(Img.getLevelData(TEST));

		// Test Randbereiche (WAND)
		player.setGrafityFactor(0);
		setPlayerToCeiling();
		int lasty = player.getY();
		player.update();
		assertTrue(player.getY() == lasty); 
		
		// Test Randbereiche (BODEN)
		setPlayerToGround();
		lasty = player.getY();
		player.update();
		assertEquals(player.getY(), lasty);

		// Test Randbereiche (LINKS)
		setPlayerToLeftWall();
		int lastx =player.getX();
		player.update();
		assertEquals(player.getX(), lastx);

		// Test Randbereiche (RECHTS)
		setPlayerToRightWall();
		lastx =player.getX();
		player.update();
		assertEquals(player.getX(), lastx);

	}

	@Test
	public void TestUpMovement() {
		player.loadLvlData(Img.getLevelData(TEST));

		int x = 300;
		int y = 300;

		int factor = 3;
		
		// Test nach Oben-Bewegen (Fliegen) (geht immer)
		player.setGrafityFactor(factor);
		player.setFloatPower(8);
		player.setUp(true);
		resetPlayerPos(x,y);
		player.update();
		assertTrue(player.getY() == y -8);
		player.setUp(false);
		
		// Test nach Oben-Bewegen (Springen) (geht nur wnn auf boden steht)
		int pow = 7;
		player.setJumpPower(pow);
		resetPlayerPos(x, y);
		player.setJump(true);
		player.update();
		assertTrue(player.getY() == y + factor);

		setPlayerToGround();
		player.setJumpPower(pow);
		player.setGrafityFactor(factor);
		int groundy = player.getY();
		player.setJump(true);
		player.update();
		assertTrue(player.getY() == groundy -pow);

		player.update();
		assertTrue(player.getY() == groundy - 2*pow + factor);
		player.update();
		assertTrue(player.getY() == groundy - 3*pow + 2*factor);
		player.setJump(false);

		//Test nach Oben-Bewegen (Springen + Fliegen)
		int pow2 = 8;
		player.setFloatPower(pow2);
		player.setUp(true);
		resetPlayerPos(x,y);
		player.update();
		assertTrue(player.getY() == y -pow2 -pow);

	}

	private void resetPlayerPos(int x, int y) {
		player.setX(x);
		player.setY(y);
	}

	private void setPlayerToGround() {
		player.setUp(false);
		player.setJump(false);
		player.setGrafityFactor(1);
		player.setDown(false);
		player.setLeft(false);
		player.setRight(false);
		int lastY = player.getY();
		player.update();
		while (lastY < player.getY()) {
			lastY = player.getY();
			player.update();
		}
	}

	private void setPlayerToLeftWall() {
		player.setPlayerSpeed(1);
		player.setUp(false);
		player.setJump(false);
		player.setLeft(true);
		player.setRight(false);
		player.setDown(false);
		int lastX = player.getX();
		player.update();
		while (lastX != player.getX()) {
			lastX = player.getX();
			player.update();
		}
	}

	private void setPlayerToRightWall() {
		player.setPlayerSpeed(1);
		player.setUp(false);
		player.setJump(false);
		player.setLeft(false);
		player.setRight(true);
		player.setDown(false);
		int lastX = player.getX();
		player.update();
		while (lastX != player.getX()) {
			lastX = player.getX();
			player.update();
		}
	} 

	private void setPlayerToCeiling() {
		int lastY = player.getY();
		player.setPlayerSpeed(1);
		player.setUp(true);
		player.setJump(false);
		player.setDown(false);
		player.setLeft(false);
		player.setRight(false);
		player.update();
		while (lastY > player.getY()) {
			lastY = player.getY();
			player.update();
		}
	}

	@Test
	public void testPlayerSetter() {
		player.setGrafityFactor(5);   // Achtung: Speed nicht groesser als SpielFeld!!!
		assertEquals(5, player.getGravityFactor());
		player.setGrafityFactor(9);
		assertEquals(9, player.getGravityFactor());
		
		player.setPlayerSpeed(8);
		assertTrue(8 == player.getPlayerSpeed());
		player.setPlayerSpeed(-2);
		assertTrue(2 == player.getPlayerSpeed());
		
		player.setJumpPower(9);
		assertTrue(9 == player.getJumpPower());
		player.setJumpPower(-2);
		assertTrue(2 == player.getJumpPower());
		
		player.setFloatPower(9);
		assertTrue(9 == player.getFloatPower());
		player.setFloatPower(90);
		assertTrue(90 == player.getFloatPower());
		
		
		player.setJumpLength(9);
		assertTrue(9 == player.getJumpLength());
		player.setJumpLength(-20);
		assertTrue(20 == player.getJumpLength());
		
		player.setJumpTime(9);
		assertTrue(9 == player.getJumpTime());
		player.setJumpTime(-3);
		assertTrue(3 == player.getJumpTime());
		
		player.setX(40);
		assertTrue(40 == player.getX());
		player.setX(-40);
		assertTrue(40 == player.getX());

		player.setY(40);
		assertTrue(40 == player.getY());
		player.setY(-50);
		assertTrue(40 == player.getY());
	}
	
	@Test
	public void testSoundEffects() {
		player.setInDanger(true);
		while(!audioPlayer.isEffectPlaying(AudioPlayer.DYING))
			player.update();
		
		assertTrue(audioPlayer.isEffectPlaying(AudioPlayer.DYING));
		player.setInDanger(false);


		player.setAttacking(true);
		while(!audioPlayer.isEffectPlaying(AudioPlayer.ATTACK))
			player.update();
				
		assertTrue(audioPlayer.isEffectPlaying(AudioPlayer.ATTACK));
		player.setAttacking(false);
		
		setPlayerToGround();
		player.setJump(true);
		while(!audioPlayer.isEffectPlaying(AudioPlayer.JUMP))
			player.update();		

		assertTrue(audioPlayer.isEffectPlaying(AudioPlayer.JUMP));

	}


}
