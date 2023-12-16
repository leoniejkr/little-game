package test;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entities.Player;
import inputs.KeyboardInputs;
import main.Game;
import main.GamePanel;

/**
 * TestKlasse zu den KeyboardInputs
 * @author leonie & hannah
 *
 */
public class KeyboardInputsTest {

	private KeyboardInputs keyinp;
	private GamePanel panel;
	private Game game;
	private Player player;
	
	KeyEvent w;
	KeyEvent a;
	KeyEvent s;
	KeyEvent d;
	KeyEvent space;
	
	public KeyboardInputsTest() {
		init();
	}
	
	@BeforeEach
	private void init() {
		game = new Game();
		player = game.getPlayer();
		panel = new GamePanel(game);
		keyinp = new KeyboardInputs(panel);
		
		w = new KeyEvent(panel, 0, 0, 0, 0);
		w.setKeyCode(KeyEvent.VK_W);
		
		a = new KeyEvent(panel, 0, 0, 0, 0);
		a.setKeyCode(KeyEvent.VK_A);
		
		s = new KeyEvent(panel, 0, 0, 0, 0);
		s.setKeyCode(KeyEvent.VK_S);
		
		d = new KeyEvent(panel, 0, 0, 0, 0);
		d.setKeyCode(KeyEvent.VK_D);
		
		space = new KeyEvent(panel, 0, 0, 0, 0);
		space.setKeyCode(KeyEvent.VK_SPACE);
	}
	
	
	@Test
	public void testKeyTyped() {
		
	}
	
	@Test
	public void testKeyReleased() {
		keyinp.keyReleased(w);
		assertFalse(player.isUp());

		keyinp.keyReleased(a);
		assertFalse(player.isLeft());

		keyinp.keyReleased(s);
		assertFalse(player.isDown());

		keyinp.keyReleased(d);
		assertFalse(player.isRight());

		keyinp.keyReleased(space);
		assertFalse(player.isJump());
	}
	
	@Test
	public void testKeyPressed() {

		keyinp.keyPressed(w);
		assertTrue(player.isUp());
		
		keyinp.keyPressed(a);
		assertTrue(player.isLeft());

		keyinp.keyPressed(s);
		assertTrue(player.isDown());

		keyinp.keyPressed(d);
		assertTrue(player.isRight());

		keyinp.keyPressed(space);
		assertTrue(player.isJump());
	}
	
	@Test
	public void testKeyPressedAndReleased() {
		keyinp.keyReleased(w);
		assertFalse(player.isUp());
		
		keyinp.keyPressed(w);
		assertTrue(player.isUp());

		keyinp.keyReleased(a);
		assertFalse(player.isLeft());

		keyinp.keyPressed(a);
		assertTrue(player.isLeft());

		keyinp.keyReleased(s);
		assertFalse(player.isDown());

		keyinp.keyReleased(d);
		assertFalse(player.isRight());

		keyinp.keyReleased(space);
		assertFalse(player.isJump());

		keyinp.keyPressed(w);
		assertTrue(player.isUp());

		keyinp.keyPressed(a);
		assertTrue(player.isLeft());
		
		keyinp.keyPressed(s);
		assertTrue(player.isDown());

		keyinp.keyPressed(d);
		assertTrue(player.isRight());

		keyinp.keyReleased(d);
		assertFalse(player.isRight());
		
		keyinp.keyPressed(space);
		assertTrue(player.isJump());
	}
	
}
