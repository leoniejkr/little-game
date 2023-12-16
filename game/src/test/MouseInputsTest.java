package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.MouseEvent;

import org.junit.Rule;
import org.junit.Test;
//import org.junit.contrib.java.lang.system.ExpectedSystemExit;


import entities.Player;
import inputs.MouseInputs;
import main.Game;
import main.GamePanel;
import states.CompletedMessage;
import states.GameOverMessage;
import states.HelpPage;
import states.Menu;
import states.VictoryMessage;

/**
 * Testklasse zu den MouseInputs.
 * @author leonie & hannah
 *
 */
public class MouseInputsTest {

	private MouseInputs menuMoIn;
	private MouseInputs helpMoIn;
	private MouseInputs vicMoIn;
	private MouseInputs comMoIn;
	private MouseInputs govMoIn;
	private MouseInputs gMoIn;
	
	private GamePanel menuPanel;
	private MouseEvent playButMen;
	private MouseEvent helpButMen;
	private MouseEvent quitButMen;
	private MouseEvent nothingMen;
	
	private GamePanel helpPanel;
	private MouseEvent exitButHel;
	private MouseEvent quitButHel;
	private MouseEvent nothingHel;
	
	private GamePanel vicPanel;
	private MouseEvent exitButVic;
	private MouseEvent quitButVic;
	private MouseEvent nothingVic;
	
	private GamePanel comPanel;
	private MouseEvent exitButCom;
	private MouseEvent quitButCom;
	private MouseEvent resButCom;
	private MouseEvent nothingCom;
	
	private GamePanel govPanel;
	private MouseEvent exitButGov;
	private MouseEvent nothingGov;
	
	private GamePanel gPanel;
	private MouseEvent leftKlickG;
	private MouseEvent rightKlickG;
	private MouseEvent leftKlickH;
	
	private Game game;
	
	private Player player;
	
//	public final ExpectedSystemExit exit = ExpectedSystemExit.none();


	
	public MouseInputsTest() {
		init();
	}
	
//	@BeforeEach
	private void init() {
		game = new Game();
		player = game.getPlayer();
		
		initMenu();
		initHelp();
		initVic();
		initCom();
		initGov();
		initKlicks();
		
	}

	private void initKlicks() {
		gPanel = new GamePanel(game);
		gPanel.setState(GamePanel.STATE.GAME);
		gMoIn = new MouseInputs(gPanel);	
		
		leftKlickG = new MouseEvent(gPanel, 0 , 0, 0, 0, 0, 0, false, MouseEvent.BUTTON1);
		rightKlickG = new MouseEvent(gPanel, 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON2);
		
		leftKlickH = new MouseEvent(helpPanel, 0 , 0, 0, 0, 0, 0, false, MouseEvent.BUTTON1);
	}

	private void initGov() {
		govPanel = new GamePanel(game);
		govPanel.setState(GamePanel.STATE.GAMEOVER);
		govMoIn = new MouseInputs(govPanel);	
		
		int exitx = GameOverMessage.getExitButtonInfo()[0];
		int exity = GameOverMessage.getExitButtonInfo()[1];
		
		int x = 0;
		int y = 0;
		
		exitButGov = new MouseEvent(comPanel, 0, 0, 0, exitx, exity, 0, false);
		nothingGov = new MouseEvent(comPanel, 0, 0, 0, x, y, 0, false);
	}

	private void initCom() {
		comPanel = new GamePanel(game);
		comPanel.setState(GamePanel.STATE.COMPLETED);
		comMoIn = new MouseInputs(comPanel);	
		
		int exitx = CompletedMessage.getExitButtonInfo()[0];
		int exity = CompletedMessage.getExitButtonInfo()[1];
		
		int quitx = CompletedMessage.getQuitButtonInfo()[0];
		int quity = CompletedMessage.getQuitButtonInfo()[1];
		
		int resx = CompletedMessage.getResumeButtonInfo()[0];
		int resy = CompletedMessage.getResumeButtonInfo()[1];
		
		int x = 0;
		int y = 0;
		
		exitButCom = new MouseEvent(comPanel, 0, 0, 0, exitx, exity, 0, false);
		quitButCom = new MouseEvent(comPanel, 0, 0, 0, quitx, quity, 0, false);
		resButCom = new MouseEvent(comPanel, 0, 0, 0, resx, resy, 0, false);
		nothingCom  = new MouseEvent(comPanel, 0, 0, 0, x, y, 0, false);
		
	}

	private void initVic() {	
		vicPanel = new GamePanel(game);
		vicPanel.setState(GamePanel.STATE.VICTORY);
		vicMoIn = new MouseInputs(vicPanel);	
		
		int exitx = VictoryMessage.getExitButtonInfo()[0];
		int exity = VictoryMessage.getExitButtonInfo()[1];
		
		int quitx = VictoryMessage.getQuitButtonInfo()[0];
		int quity = VictoryMessage.getQuitButtonInfo()[1];

		int x = 0;
		int y = 0;
		
		exitButVic = new MouseEvent(vicPanel, 0, 0, 0, exitx, exity, 0, false);
		quitButVic = new MouseEvent(vicPanel, 0, 0, 0, quitx, quity, 0, false);
		nothingVic  = new MouseEvent(vicPanel, 0, 0, 0, x, y, 0, false);
		
	}

	private void initHelp() {
		helpPanel = new GamePanel(game);
		helpPanel.setState(GamePanel.STATE.HELP);
		helpMoIn = new MouseInputs(helpPanel);
		
		int exitx = HelpPage.getExitButtonInfo()[0];
		int exity = HelpPage.getExitButtonInfo()[1];
		
		int quitx = HelpPage.getQuitButtonInfo()[0];
		int quity = HelpPage.getQuitButtonInfo()[1];

		int x = 0;
		int y = 0;
		
		exitButHel = new MouseEvent(helpPanel, 0, 0, 0, exitx, exity, 0, false);
		quitButHel = new MouseEvent(helpPanel, 0, 0, 0, quitx, quity, 0, false);
		nothingHel  = new MouseEvent(helpPanel, 0, 0, 0, x, y, 0, false);
	}

	private void initMenu() {
		menuPanel = new GamePanel(game);
		menuPanel.setState(GamePanel.STATE.MENU);
		menuMoIn = new MouseInputs(menuPanel);	
		
		int playx = Menu.getPlayButtonInfo()[0];
		int playy = Menu.getPlayButtonInfo()[1];
		
		int helpx = Menu.getHelpButtonInfo()[0];
		int helpy = Menu.getHelpButtonInfo()[1];
		
		int quitx = Menu.getQuitButtonInfo()[0];
		int quity = Menu.getQuitButtonInfo()[1];
		
		int x = 0;
		int y = 0;
		
		nothingMen  = new MouseEvent(menuPanel, 0, 0, 0, x, y, 0, false);
		playButMen = new MouseEvent(menuPanel, 0, 0, 0, playx, playy, 0, false);
		helpButMen = new MouseEvent(menuPanel, 0, 0, 0, helpx, helpy, 0, false);
		quitButMen = new MouseEvent(menuPanel, 0, 0, 0, quitx, quity, 0, false);
	}

	@Test
	public void testMouseMoved() {
		//Menu
		menuMoIn.mouseMoved(helpButMen);
		assertTrue(Menu.isHelpButtonSelected());
		assertFalse(Menu.isPlayButtonSelected());
		assertFalse(Menu.isQuitButtonSelected());
		
		menuMoIn.mouseMoved(quitButMen);
		assertFalse(Menu.isHelpButtonSelected());
		assertFalse(Menu.isPlayButtonSelected());
		assertTrue(Menu.isQuitButtonSelected());
		
		menuMoIn.mouseMoved(playButMen);
		assertFalse(Menu.isHelpButtonSelected());
		assertTrue(Menu.isPlayButtonSelected());
		assertFalse(Menu.isQuitButtonSelected());
		
		menuMoIn.mouseMoved(nothingMen);
		assertFalse(Menu.isHelpButtonSelected());
		assertFalse(Menu.isPlayButtonSelected());
		assertFalse(Menu.isQuitButtonSelected());
		
		
		//HelpPage
		helpMoIn.mouseMoved(nothingHel);
		assertFalse(HelpPage.isExitButtonSelected());
		assertFalse(HelpPage.isQuitButtonSelected());
		
		helpMoIn.mouseMoved(exitButHel);
		assertTrue(HelpPage.isExitButtonSelected());
		assertFalse(HelpPage.isQuitButtonSelected());
		
		helpMoIn.mouseMoved(quitButHel);
		assertFalse(HelpPage.isExitButtonSelected());
		assertTrue(HelpPage.isQuitButtonSelected());
		
		
		// VictoryMessage
		vicMoIn.mouseMoved(nothingVic);
		assertFalse(VictoryMessage.isExitButtonSelected());
		assertFalse(VictoryMessage.isQuitButtonSelected());
		
		vicMoIn.mouseMoved(exitButVic);
		assertTrue(VictoryMessage.isExitButtonSelected());
		assertFalse(VictoryMessage.isQuitButtonSelected());
		
		vicMoIn.mouseMoved(quitButVic);
		assertFalse(VictoryMessage.isExitButtonSelected());
		assertTrue(VictoryMessage.isQuitButtonSelected());
		
		
		// CompletedMessage
		comMoIn.mouseMoved(nothingCom);
		assertFalse(CompletedMessage.isExitButtonSelected());
		assertFalse(CompletedMessage.isQuitButtonSelected());
		assertFalse(CompletedMessage.isResumeButtonSelected());

		comMoIn.mouseMoved(exitButCom);
		assertTrue(CompletedMessage.isExitButtonSelected());
		assertFalse(CompletedMessage.isQuitButtonSelected());
		assertFalse(CompletedMessage.isResumeButtonSelected());
		
		comMoIn.mouseMoved(quitButCom);
		assertFalse(CompletedMessage.isExitButtonSelected());
		assertTrue(CompletedMessage.isQuitButtonSelected());	
		assertFalse(CompletedMessage.isResumeButtonSelected());
		
		comMoIn.mouseMoved(resButCom);
		assertFalse(CompletedMessage.isExitButtonSelected());
		assertFalse(CompletedMessage.isQuitButtonSelected());
		assertTrue(CompletedMessage.isResumeButtonSelected());
		
		// GameOverMessage
		govMoIn.mouseMoved(nothingGov);
		assertFalse(GameOverMessage.isExitButtonSelected());
		
		govMoIn.mouseMoved(exitButGov);
		assertTrue(GameOverMessage.isExitButtonSelected());
		
		
		//Test: events auf anderen Panels bewirken nichts
		comMoIn.mouseMoved(nothingCom);
		govMoIn.mouseMoved(exitButCom);
		assertFalse(CompletedMessage.isExitButtonSelected());
		vicMoIn.mouseMoved(exitButCom);
		assertFalse(CompletedMessage.isExitButtonSelected());
		
		helpMoIn.mouseMoved(nothingCom);
		govMoIn.mouseMoved(quitButHel);
		assertFalse(HelpPage.isQuitButtonSelected());
		vicMoIn.mouseMoved(exitButHel);
		assertFalse(HelpPage.isExitButtonSelected());
		
	}
	
	@Test
	public void testMouseClicked() {
		assertFalse(player.isAttacking());
		
		helpMoIn.mouseClicked(leftKlickH);
		assertFalse(player.isAttacking());
		
		gMoIn.mouseClicked(rightKlickG);
		assertFalse(player.isAttacking());
		
		gMoIn.mouseReleased(leftKlickG);
		assertFalse(player.isAttacking());
	}
	
	@Test
	public void testMouseReleased() {
		// Menu
		menuMoIn.mousePressed(helpButMen);
		assertEquals(menuPanel.getState(), GamePanel.STATE.HELP);
		resetMenu(menuPanel);
		menuMoIn.mousePressed(playButMen);
		assertEquals(menuPanel.getState(), GamePanel.STATE.GAME);
		resetMenu(menuPanel);

		// HelpPage
		helpMoIn.mousePressed(exitButHel);
		assertEquals(menuPanel.getState(), GamePanel.STATE.MENU);
		resetHelpPage(helpPanel);

		// CompletedMessage
		comMoIn.mousePressed(exitButCom);
		assertEquals(comPanel.getState(), GamePanel.STATE.MENU);
		resetCompMess(comPanel);
		comMoIn.mousePressed(resButCom);
		assertEquals(comPanel.getState(), GamePanel.STATE.GAME);
		resetCompMess(comPanel);

		// VictoryMessage
		vicMoIn.mousePressed(exitButVic);
		assertEquals(vicPanel.getState(), GamePanel.STATE.MENU);
		resetCompMess(vicPanel);

		// GameOverMessage
		govMoIn.mousePressed(exitButGov);
		assertEquals(govPanel.getState(), GamePanel.STATE.MENU);
		resetGovMess(vicPanel);
		
		// Game
		gMoIn.mouseReleased(leftKlickG);
		assertFalse(player.isAttacking());
	}
	
	private void resetMenu(GamePanel panel)  {
		panel.setState(GamePanel.STATE.MENU);
	}
	
	private void resetHelpPage(GamePanel panel)  {
		panel.setState(GamePanel.STATE.HELP);
	}
	
	private void resetCompMess(GamePanel panel)  {
		panel.setState(GamePanel.STATE.COMPLETED);
	}
	
	private void resetGovMess(GamePanel panel)  {
		panel.setState(GamePanel.STATE.GAMEOVER);
	}

	
}
