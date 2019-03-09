package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener {

	Hole[] holes = new Hole[6];
	Hammer hammer;
	Timer timer;
	GamePanel score;
	int mole;
	int samemole;
	int sign;

	int x;
	int y;
	Random random = new Random();

	public ObjectManager() {
		mole = random.nextInt(6);
		
		sign = random.nextInt(2);
		holes[0] = new Hole(x = 188, y = 400, 145, 90);
		holes[1] = new Hole(x = 340, y = 450, 145, 90);
		holes[2] = new Hole(x = 325, y = 555, 145, 90);
		holes[3] = new Hole(x = 188, y = 620, 145, 90);
		holes[4] = new Hole(x = 44, y = 550, 145, 90);
		holes[5] = new Hole(x = 25, y = 445, 145, 90);

	}

	void draw(Graphics g) {

		if(mole>-1) {
		holes[mole].draw(g);
		}
		
		
		// System.out.println(mole + " is printed");
		// mole = samemole;

	}

	void getNextMole() {
		mole = random.nextInt(6);
		/*
		 * if(samemole == mole) { if(mole==5) { mole = mole - random.nextInt((4-1)+1); }
		 * else if(mole==4) { if(sign==0) {
		 * 
		 * mole = mole+1; } else if(sign==1) { mole = mole - random.nextInt(mole-1); } }
		 * 
		 * else if(mole==3) { if(sign==0) {
		 * 
		 * mole = mole + random.nextInt((2-1)+1); } else if(sign==1) { mole = mole -
		 * random.nextInt((2-1)+1); } } else if(mole==2) { if(sign==0) {
		 * 
		 * mole = mole + random.nextInt((3-1)+1); } else if(sign==1) { mole = mole - 1;
		 * } } /// else if(mole==1) { mole = mole +random.nextInt((4-1)+1);
		 * 
		 * }
		 * 
		 * }
		 */
		//System.out.println("mole location: " + mole);
	}

	void purgeObjects() {
		mole =-1; 
	}

	boolean checkCollisions(int mouseX, int mouseY) {
		for (int i = 0; i < holes.length; i++) {
			if (holes[i].checkcollision(mouseX, mouseY)) {
				if (i == mole) {
					System.out.println("mole smashed");
					return true;

				}

			}

		}
		return false;
	}

	int getscore(int score) {

		return score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("moletimer");
		getNextMole();
		//System.out.println("Next Mole!");
	}

}
