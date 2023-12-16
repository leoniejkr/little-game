package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;
import main.GamePanel.STATE;
import states.CompletedMessage;
import states.GameOverMessage;
import states.HelpPage;
import states.Menu;
import states.VictoryMessage;

/**
 * Klasse welche die MouseInputs verwaltet
 * @author leonie & hannah
 *
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

	private GamePanel gamePanel;

	/**
	 * Konstruktor
	 * @param gamePanel
	 */
	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if (gamePanel.getState() == GamePanel.STATE.MENU) {
			int hei = Menu.getButtonSizeInfo()[0];
			int len = Menu.getButtonSizeInfo()[1];
			
			int playx = Menu.getPlayButtonInfo()[0];
			int playy = Menu.getPlayButtonInfo()[1];
			
			int helpx = Menu.getHelpButtonInfo()[0];
			int helpy = Menu.getHelpButtonInfo()[1];
			
			int quitx = Menu.getQuitButtonInfo()[0];
			int quity = Menu.getQuitButtonInfo()[1];

			
			
			if (mx >= helpx && mx <= helpx+len && my >= helpy && my <= helpy+hei) {
				Menu.setHelpButtonSelected(true);}
			else
				Menu.setHelpButtonSelected(false);
			
			if (mx >= playx && mx <= playx+len && my >= playy && my <= playy+hei) {
	                Menu.setPlayButtonSelected(true);;}
			else
				Menu.setPlayButtonSelected(false);
		
			if (mx >= quitx && mx <= quitx+len && my >= quity && my <= quity+hei) {
					 Menu.setQuitButtonSelected(true);}
			else
				Menu.setQuitButtonSelected(false);
		}

		if (gamePanel.getState() == GamePanel.STATE.HELP) {
			int hei = HelpPage.getButtonSizeInfo()[0];
			int len = HelpPage.getButtonSizeInfo()[1];
			
			int exitx = HelpPage.getExitButtonInfo()[0];
			int exity = HelpPage.getExitButtonInfo()[1];
			
			int quitx = HelpPage.getQuitButtonInfo()[0];
			int quity = HelpPage.getQuitButtonInfo()[1];

			
			if (mx >= exitx && mx <= exitx+len && my >= exity && my <= exity+hei) {
				HelpPage.setExitButtonSelected(true);;}
			else
				HelpPage.setExitButtonSelected(false);
		
			if (mx >= quitx && mx <= quitx+len && my >= quity && my <= quity+hei) {
				HelpPage.setQuitButtonSelected(true);}
			else
				HelpPage.setQuitButtonSelected(false);
		}
		
		if (gamePanel.getState() == GamePanel.STATE.VICTORY) {
			int hei = VictoryMessage.getButtonSizeInfo()[0];
			int len = VictoryMessage.getButtonSizeInfo()[1];
			
			int exitx = VictoryMessage.getExitButtonInfo()[0];
			int exity = VictoryMessage.getExitButtonInfo()[1];
			
			int quitx = VictoryMessage.getQuitButtonInfo()[0];
			int quity = VictoryMessage.getQuitButtonInfo()[1];

			
			if (mx >= exitx && mx <= exitx+len && my >= exity && my <= exity+hei) {
				VictoryMessage.setExitButtonSelected(true);;}
			else
				VictoryMessage.setExitButtonSelected(false);
		
			if (mx >= quitx && mx <= quitx+len && my >= quity && my <= quity+hei) {
				VictoryMessage.setQuitButtonSelected(true);}
			else
				VictoryMessage.setQuitButtonSelected(false);
		}
		if (gamePanel.getState() == GamePanel.STATE.COMPLETED) {
			int hei = CompletedMessage.getButtonSizeInfo()[0];
			int len = CompletedMessage.getButtonSizeInfo()[1];
			
			int exitx = CompletedMessage.getExitButtonInfo()[0];
			int exity = CompletedMessage.getExitButtonInfo()[1];
			
			int quitx = CompletedMessage.getQuitButtonInfo()[0];
			int quity = CompletedMessage.getQuitButtonInfo()[1];
			
			int resx = CompletedMessage.getResumeButtonInfo()[0];
			int resy = CompletedMessage.getResumeButtonInfo()[1];
			
			
			if (mx >= exitx && mx <= exitx+len && my >= exity && my <= exity+hei) {
				CompletedMessage.setExitButtonSelected(true);;}
			else
				CompletedMessage.setExitButtonSelected(false);
			
			if (mx >= quitx && mx <= quitx+len && my >= quity && my <= quity+hei) {
				CompletedMessage.setQuitButtonSelected(true);}
			else
				CompletedMessage.setQuitButtonSelected(false);
			
			if (mx >= resx && mx <= resx+len && my >= resy && my <= resy+hei) {
				CompletedMessage.setResumeButtonSelected(true);}
			else
				CompletedMessage.setResumeButtonSelected(false);
		}
		
		if (gamePanel.getState() == GamePanel.STATE.GAMEOVER) {
			int hei = GameOverMessage.getButtonSizeInfo()[0];
			int len = GameOverMessage.getButtonSizeInfo()[1];
			
			int exitx = GameOverMessage.getExitButtonInfo()[0];
			int exity = GameOverMessage.getExitButtonInfo()[1];

			
			if (mx >= exitx && mx <= exitx+len && my >= exity && my <= exity+hei) {
				GameOverMessage.setExitButtonSelected(true);;}
			else
				GameOverMessage.setExitButtonSelected(false);
		}

		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gamePanel.getState() == GamePanel.STATE.GAME) {
			if (e.getButton() == MouseEvent.BUTTON1) { // links Klick
				gamePanel.getGame().getPlayer().setAttacking(true);}}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		
		if (gamePanel.getState() == GamePanel.STATE.MENU) {
			int hei = Menu.getButtonSizeInfo()[0];
			int len = Menu.getButtonSizeInfo()[1];
			
			int playx = Menu.getPlayButtonInfo()[0];
			int playy = Menu.getPlayButtonInfo()[1];
			
			int helpx = Menu.getHelpButtonInfo()[0];
			int helpy = Menu.getHelpButtonInfo()[1];
			
			int quitx = Menu.getQuitButtonInfo()[0];
			int quity = Menu.getQuitButtonInfo()[1];

			
			if (mx >= helpx && mx <= helpx+len) {
				if(my >= helpy && my <= helpy+hei) {
	                gamePanel.setState(STATE.HELP);}}

			
			if (mx >= playx && mx <= playx+len) {
				System.out.println("");
				if(my >= playy && my <= playy+hei) {
	                gamePanel.setState(STATE.GAME);}}
		
			if (mx >= quitx && mx <= quitx+len) {
				if(my >= quity && my <= quity+hei) {
					System.exit(1);}}
		}
		
		if (gamePanel.getState() == GamePanel.STATE.HELP) {
			int hei = HelpPage.getButtonSizeInfo()[0];
			int len = HelpPage.getButtonSizeInfo()[1];
			
			int exitx = HelpPage.getExitButtonInfo()[0];
			int exity = HelpPage.getExitButtonInfo()[1];
			
			int quitx = HelpPage.getQuitButtonInfo()[0];
			int quity = HelpPage.getQuitButtonInfo()[1];
			
			if (mx >= exitx && mx <= exitx+len) {
				if(my >= exity && my <= exity+hei) {
	                gamePanel.setState(STATE.MENU);}}
			
			if (mx >= quitx && mx <= quitx+len) {
				if(my >= quity && my <= quity+hei) {
					System.exit(1);}}
		}

		
		if (gamePanel.getState() == GamePanel.STATE.COMPLETED) {
			int hei = CompletedMessage.getButtonSizeInfo()[0];
			int len = CompletedMessage.getButtonSizeInfo()[1];
			
			int exitx = CompletedMessage.getExitButtonInfo()[0];
			int exity = CompletedMessage.getExitButtonInfo()[1];
			
			int quitx = CompletedMessage.getQuitButtonInfo()[0];
			int quity = CompletedMessage.getQuitButtonInfo()[1];
			
			int resx = CompletedMessage.getResumeButtonInfo()[0];
			int resy = CompletedMessage.getResumeButtonInfo()[1];
			
			if (mx >= exitx && mx <= exitx+len) {
				if(my >= exity && my <= exity+hei) {
	                gamePanel.setState(STATE.MENU);
	                gamePanel.getGame().resetAchievement();  // wieder von Level1 anfangen
	              }}
			
			if (mx >= quitx && mx <= quitx+len) {
				if(my >= quity && my <= quity+hei) {
					System.exit(1);}}
			
			if (mx >= resx && mx <= resx+len) {
				if(my >= resy && my <= resy+hei) {
		               gamePanel.setState(STATE.GAME);}}
		}
		
		if (gamePanel.getState() == GamePanel.STATE.VICTORY) {
			int hei = VictoryMessage.getButtonSizeInfo()[0];
			int len = VictoryMessage.getButtonSizeInfo()[1];
			
			int exitx = VictoryMessage.getExitButtonInfo()[0];
			int exity = VictoryMessage.getExitButtonInfo()[1];
			
			int quitx = VictoryMessage.getQuitButtonInfo()[0];
			int quity = VictoryMessage.getQuitButtonInfo()[1];

			 
			if (mx >= exitx && mx <= exitx+len) {
				if(my >= exity && my <= exity+hei) {
	                gamePanel.setState(STATE.MENU);
	                gamePanel.getGame().resetAchievement();  // wieder von Level1 anfangen
	              }}
			
			if (mx >= quitx && mx <= quitx+len) {
				if(my >= quity && my <= quity+hei) {
					System.exit(1);}}
				
		}
		
		if (gamePanel.getState() == GamePanel.STATE.GAMEOVER) {
			int hei = GameOverMessage.getButtonSizeInfo()[0];
			int len = GameOverMessage.getButtonSizeInfo()[1];
			
			int exitx = GameOverMessage.getExitButtonInfo()[0];
			int exity = GameOverMessage.getExitButtonInfo()[1];

			
			if (mx >= exitx && mx <= exitx+len) {
				if(my >= exity && my <= exity+hei) {
	                gamePanel.setState(STATE.MENU);
	                gamePanel.getGame().resetAchievement();  // wieder von Level1 anfangen
	              }}	
		}
		
		if (gamePanel.getState() == GamePanel.STATE.GAME) {
			if (e.getButton() == MouseEvent.BUTTON1) { // links Klick
				gamePanel.getGame().getPlayer().setAttacking(false);}}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (gamePanel.getState() == GamePanel.STATE.GAME) {
			if (e.getButton() == MouseEvent.BUTTON1) { // links Klick
				gamePanel.getGame().getPlayer().setAttacking(false);
}}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
