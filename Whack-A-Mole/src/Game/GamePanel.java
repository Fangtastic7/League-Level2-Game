package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	Timer timer;
	Timer moletimer;
	private int moledelay;
	final int MENU_STATE = 0;
	final int SETTINGS_STATE = 1;
	final int INSTRUCTIONS_STATE = 2;
	final int GAME_STATE = 3;
	final int END_STATE = 4;
	int CURRENT_STATE = MENU_STATE;
	int score;
	int delay;
	int counter;
	int countrate;
	Font titlefont;
	Font text;
	Image outsideImg;
	Countdown countdown;
	static Image moleImg;
	ObjectManager manager;
	Hammer hammer;
	Hole hole;

	public GamePanel() {
		timer = new Timer(1000 / 60, this);
		moledelay = 2000;
		titlefont = new Font("Times New Roman", Font.BOLD, 48);
		text = new Font("Arial", Font.BOLD, 25);
		manager = new ObjectManager();
		
		hammer = new Hammer(250, 400, 200, 150);

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
	void setMoleDelay(int delay) {
		moledelay = delay;
		
		System.out.println("delay " + moledelay);
	}

	void initializeGame() {
		CURRENT_STATE = GAME_STATE;
		score = 0;
		counter = 30;
		countrate = 120;
		timer.start();		

	}

	void startGame() {

		moletimer = new Timer(moledelay, manager);
		moletimer.start();

	}

	void drawMenuState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
		g.setFont(titlefont);
		g.setColor(Color.BLACK);
		g.drawString("Whack A Mole", 80, 200);
		g.setFont(text);

		g.drawString("Click Enter to Play!", 120, 270);
		g.drawString("Settings - Click 's'", 125, 340);
		g.drawString("Instructions - click 'c'", 135, 410);
	}

	void drawSettingsState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
		g.setFont(titlefont);
		g.setColor(Color.BLACK);
		g.drawString("Settings", 160, 200);
		g.setFont(text);
		g.setColor(Color.BLUE);
		g.drawString("Music", 20, 270);
		g.setColor(Color.BLACK);
		g.drawString("Click '1' for Rock", 20, 300);
		g.drawString("Click '2' for Jazz", 20, 330);
		g.drawString("Click '2' for Electro", 20, 360);
		g.setColor(Color.BLUE);
		g.drawString("Mole Speed", 20, 390);
		g.setColor(Color.ORANGE);
		g.drawString("Click '4' for Medium Speed (default) ", 20, 420);
		g.drawString("Click '5' for Fast Speed", 20, 450);
		g.drawString("Click '6' for Faster Speed", 20, 480);
		g.setColor(Color.RED);
		g.drawString("Click 'Esc' to return to Main Menu", 50, 750);

	}

	void drawInstructionsState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
	}

	void drawGameState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
		g.setColor(Color.WHITE);

		g.drawRect(180, 3, 300, 30);
		g.setColor(Color.BLUE);
		g.setFont(text);
		g.drawString("Timer: " + counter, 200, 30);
		g.drawString("Score: " + score, 350, 30);
		manager.draw(g);
		hammer.draw(g);
		System.out.println("repaint");
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 800);
		g.setColor(Color.WHITE);
		g.setFont(titlefont);
		g.drawString("Game Over", 110, 200);
		g.setFont(text);
		g.drawString("You bonked " + score + " moles", 115, 350);
		g.drawString("Your rate: " + score/30 + " moles per second", 80, 425);
		g.drawString("Press Enter to restart", 110, 500);
		g.drawString("Click 'Esc' to return to Main Menu", 50, 750);

	}

	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (CURRENT_STATE == GAME_STATE) {

			if (countrate == 0) {
				counter--;
				countrate = 120;
				System.out.println(counter);
			} else if (counter == 0) {
				CURRENT_STATE = END_STATE;

			} else {
				countrate--;

			}
		}
		repaint();
		
	}

	private void endGame(Date timeAtStart, int molesWhacked) {
		Date timeAtEnd = new Date();
		JOptionPane.showMessageDialog(null, "Your whack rate is "
				+ ((timeAtEnd.getTime() - timeAtStart.getTime()) / 1000.00 / molesWhacked) + " moles per second.");
	}

	public void paintComponent(Graphics g) {
		// object.draw(g);
		if (CURRENT_STATE == MENU_STATE) {

			drawMenuState(g);

		} else if (CURRENT_STATE == GAME_STATE) {

			drawGameState(g);
			
		} else if (CURRENT_STATE == INSTRUCTIONS_STATE) {

			drawInstructionsState(g);

		} else if (CURRENT_STATE == SETTINGS_STATE) {

			drawSettingsState(g);

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
		System.out.println(CURRENT_STATE);
		if (CURRENT_STATE == MENU_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				System.out.println("enter");
				startGame();
				initializeGame();
				
			} else if (e.getKeyCode() == KeyEvent.VK_S) {

				CURRENT_STATE = SETTINGS_STATE;

			}
		} else if (CURRENT_STATE != GAME_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

				CURRENT_STATE = MENU_STATE;

			}
		}

		else if (CURRENT_STATE == SETTINGS_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_1) {

			} else if (e.getKeyCode() == KeyEvent.VK_2) {

			} else if (e.getKeyCode() == KeyEvent.VK_3) {

			} else if (e.getKeyCode() == KeyEvent.VK_4) {
				
				setMoleDelay(2000);
				
			} else if (e.getKeyCode() == KeyEvent.VK_5) {
				
				setMoleDelay(1000);
				
			} else if (e.getKeyCode() == KeyEvent.VK_6) {
				
				setMoleDelay(500);
				
			}

		}

		// if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		// startGame();
	
		// }

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("mouse_Clicked ");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("mouse_event ");
		hammer.hammerdown = true;
		// hammer.hammerdown = true;
		int mouseX = e.getX();
		int mouseY = e.getY();
		if (manager.checkCollisions(mouseX, mouseY)) {
			manager.purgeObjects();
			//manager.getNextMole();
			score = score + 1;

		}
		// System.out.println(mouseX + ", " + mouseY);

		System.out.println("score = " + score);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("mouseMoved!");
		hammer.x = e.getX();
		hammer.y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		hammer.hammerdown = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("mouseMoving ");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
