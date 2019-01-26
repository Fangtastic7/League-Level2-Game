package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	Timer moletimer;
	int moledelay = 2000;
	final int MENU_STATE = 0;
	final int SETTINGS_STATE = 1;
	final int CREDITS_STATE = 2;
	final int INSTRUCTIONS_STATE = 3;
	final int GAME_STATE = 4;
	final int END_STATE = 5;
	int CURRENT_STATE = GAME_STATE;
	int score = 0;
	Font titlefont;
	Font text;
	Image outsideImg;
	static Image moleImg;
	ObjectManager manager;
	Hammer hammer;
	

	public GamePanel() {
		timer = new Timer(1000 / 60, this);
		
		titlefont = new Font("Times New Roman", Font.BOLD, 48);
		text = new Font("Arial", Font.BOLD, 25);

		manager = new ObjectManager();
		moletimer = new Timer(moledelay, manager);
		try {

			outsideImg = ImageIO.read(this.getClass().getResourceAsStream("outside.png"));

			moleImg = ImageIO.read(this.getClass().getResourceAsStream("mole.png"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}
	// public void paintComponent(Graphics g){
	// g.fillRect(10, 10, 100, 100);

	// }
	void startGame() {
		timer.start();
		moletimer.start();
		timer.addActionListener(this);
		System.out.println("start");
		moletimer.addActionListener(manager);
	}

	void drawMenuState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
		g.setFont(titlefont);
		g.setColor(Color.BLACK);
		g.drawString("Whack A Mole", 80, 200);
		g.setFont(text);

		g.drawString("Click Enter to Play!", 120, 270);
		g.drawString("Settings - Click 's'", 125, 340);
		g.drawString("Credits - click 'c'", 135, 410);
	}

	void drawSettingsState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
		g.setFont(titlefont);
		g.setColor(Color.BLACK);
		g.drawString("Settings", 80, 200);
		g.setFont(text);
		g.setColor(Color.BLUE);
		g.drawString("Music", 20, 270);
	}

	void drawCreditsState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
	}

	void drawInstructionsState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
	}

	void drawGameState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
		manager.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		repaint();
	}

	public void paintComponent(Graphics g) {
		// object.draw(g);
		if (CURRENT_STATE == MENU_STATE) {

			drawMenuState(g);

		} else if (CURRENT_STATE == GAME_STATE) {

			drawGameState(g);

		} else if (CURRENT_STATE == INSTRUCTIONS_STATE) {

			drawInstructionsState(g);

		} else if (CURRENT_STATE == CREDITS_STATE) {

			drawCreditsState(g);

		} else if (CURRENT_STATE == END_STATE) {

			drawEndState(g);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Hole[] keyPressed = (Hole[]) e.getSource();
		if (e.getKeyCode() == KeyEvent.VK_S) {
			CURRENT_STATE = SETTINGS_STATE;

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("enter");
			CURRENT_STATE = GAME_STATE;
		} else if (e.getKeyCode() == KeyEvent.VK_C) {
			CURRENT_STATE = CREDITS_STATE;

		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			CURRENT_STATE = MENU_STATE;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			startGame();

		}
		//if (manager.holes == keyPressed) {
			//score = score + 1;
			//System.out.println(score);
			//manager.getNextMole();

		//}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
