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
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	Timer timer;
	Timer moletimer;
	 int moledelay = 2000;
	final int MENU_STATE = 0;
	final int SETTINGS_STATE = 1;
	final int INSTRUCTIONS_STATE = 2;
	final int GAME_STATE = 3;
	final int END_STATE = 4;
	int CURRENT_STATE = MENU_STATE;
	double score;
	int printedscore = 0;
	Song rock;
	Song rap;
	Song electro;
	Song whack;
	int counter;
	double attempts = 0;
	double totaltime = 30;
	double rate = 0;
	int countrate;
	Font titlefont;
	Font text;
	Font smalltext;
	Image outsideImg;
	Date starttime;
	Countdown countdown;
	static Image moleImg;
	ObjectManager manager;
	Hammer hammer;
	Hole hole;

	public GamePanel() {
		timer = new Timer(1000 / 60, this);
		rock = new Song("queen.mp3");
		rap = new Song("drake.mp3");
		electro = new Song("electro.mp3");
		whack = new Song("whack.mp3");
		titlefont = new Font("Times New Roman", Font.BOLD, 48);
		smalltext = new Font("Times New Roman", Font.BOLD, 25);
		text = new Font("Arial", Font.BOLD, 25);
		manager = new ObjectManager();
		moletimer = new Timer(moledelay, manager);
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

	void initializeGame() {
		CURRENT_STATE = GAME_STATE;
		score = 0;
		printedscore = 0;
		attempts = 0;
		counter = 30;
		countrate = 120;
	}

	void startGame() {
		timer.start();
		moletimer.start();
		timer.addActionListener(this);
		//System.out.println("start");
		moletimer.addActionListener(manager);
		starttime = new Date();

	}

	void drawMenuState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
		g.setFont(titlefont);
		g.setColor(Color.BLACK);
		g.drawString("Whack A Mole", 80, 200);
		g.setFont(text);

		g.drawString("Click Enter to Play!", 120, 270);
		g.drawString("Settings - Click 's'", 125, 340);
		g.drawString("Instructions - click 'c'", 125, 410);
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
		g.drawString("Click '2' for Rap", 20, 330);
		g.drawString("Click '3' for Electro", 20, 360);
		g.drawString("Click '4' for no music", 20, 390);
		g.setColor(Color.BLUE);
		g.drawString("Mole Speed", 20, 420);
		g.setColor(Color.ORANGE);
		g.drawString("Click '5' for Medium Speed (default) ", 20, 450);
		g.drawString("Click '6' for Fast Speed", 20, 480);
		g.drawString("Click '7' for Faster Speed", 20, 510);
		g.setColor(Color.RED);
		g.drawString("Click 'Esc' to return to Main Menu", 50, 750);

	}

	void drawInstructionsState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
		g.setFont(titlefont);
		g.setColor(Color.BLACK);
		g.drawString("Instructions", 135, 200);
		g.setFont(text);
		g.setColor(Color.blue);
		g.drawString("How to Play:", 20, 270);
		g.setColor(Color.BLACK);
		g.setFont(smalltext);
		g.drawString("1) Smash as many moles within 30 seconds.", 20, 300);
		g.drawString("2) You can adjust the speed and the music", 20, 330);
		g.drawString("by going in to settings.", 20, 360);
		g.drawString("3) Good Luck and Have Fun!", 20, 390);
		g.setColor(Color.RED);
		g.setFont(text);
		g.drawString("Click 'Esc' to return to Main Menu", 50, 750);
	}

	void drawGameState(Graphics g) {
		g.drawImage(outsideImg, 0, 0, 500, 800, null);
		g.setColor(Color.WHITE);

		g.drawRect(180, 3, 300, 30);
		g.setColor(Color.BLUE);
		g.setFont(text);
		g.drawString("Timer: " + counter, 200, 30);
		g.drawString("Score: " + printedscore, 350, 30);
		manager.draw(g);
		hammer.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 800);
		g.setColor(Color.WHITE);
		g.setFont(titlefont);
		g.drawString("Game Over", 110, 200);
		g.setFont(text);
		g.drawString("You bonked " + printedscore + " moles", 115, 350);
		g.drawString("Your rate: " +  String.format("%1.2f",rate) + " moles per second", 60, 425);
		g.drawString("Your accuracy: " + String.format("%1.2f", (score/attempts * 100)) + "%", 120, 500);
		g.drawString("Press Enter to restart", 120, 575); 
		g.drawString("Press Esc to return to Main Menu", 60, 650);
		
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (CURRENT_STATE == GAME_STATE) {

			if (countrate == 0) {
				counter--;
				countrate = 120;
				//System.out.println(counter);
			} else if (counter == 0) {
				molerate();
				CURRENT_STATE = END_STATE;

			} else {
				countrate--;
				
			}
		}
		repaint();

	}

	double molerate( ) {
		Date timeAtEnd = new Date();
		//change
		float time = 0;
		time =  (  ((timeAtEnd.getTime() - starttime.getTime()) / 1000));
		//System.out.println("Time- " + time);
		//System.out.println("Score: " + score);
		rate = ( score / totaltime);
	//	System.out.println("Rate: " + rate);
		
		//JOptionPane.showMessageDialog(null, "Your whack rate is "
				//+ ((timeAtEnd.getTime() - timeAtStart.getTime()) / 1000.00 / molesWhacked) + " moles per second.");
		return rate;
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
	//	System.out.println("keyPressed ");

		if (CURRENT_STATE != GAME_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_S) {
				CURRENT_STATE = SETTINGS_STATE;
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				//System.out.println("enter");
				initializeGame();
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				CURRENT_STATE = MENU_STATE;
			}
			else if(CURRENT_STATE == MENU_STATE) {
				if(e.getKeyCode() == KeyEvent.VK_C) {
					CURRENT_STATE = INSTRUCTIONS_STATE;
				}
			}
			else if(CURRENT_STATE == SETTINGS_STATE) {
				if(e.getKeyCode() == KeyEvent.VK_1) {
					
					rock.stop();
					rap.stop();
					electro.stop();
					rock.play();
					
	
				}
				else if(e.getKeyCode() == KeyEvent.VK_2) {
					rock.stop();
					rap.stop();
					electro.stop();
					rap.play();
					
				}
				else if(e.getKeyCode() == KeyEvent.VK_3) {
					rock.stop();
					rap.stop();
					electro.stop();
					electro.play();
					
							
				}
				else if(e.getKeyCode() == KeyEvent.VK_4) {
					rock.stop();
					rap.stop();
					electro.stop();
					
				}
				else if(e.getKeyCode() == KeyEvent.VK_5) {
					moledelay = 2000;
					moletimer.setDelay(2000);
					//System.out.println(moledelay);
				}
				else if(e.getKeyCode() == KeyEvent.VK_6) {
					moledelay = 1000;
					moletimer.setDelay(1000);
					//System.out.println(moledelay);
				}
				else if(e.getKeyCode() == KeyEvent.VK_7) {
					moledelay = 500;
					moletimer.setDelay(500);
					//System.out.println(moledelay);
				}
			}
		}
		
		// if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		// startGame();

		// }

	//	manager.getNextMole();

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
		if(CURRENT_STATE == GAME_STATE) {
		
			whack.play();
			attempts = attempts + 1;
		}
		hammer.hammerdown = true;
		
	
		int mouseX = e.getX();
		int mouseY = e.getY();
		if (manager.checkCollisions(mouseX, mouseY)) {
			manager.purgeObjects();

			score = score + 1;
			printedscore = printedscore +1;
			

		}
		

	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		hammer.x = e.getX();
		hammer.y = e.getY();
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
