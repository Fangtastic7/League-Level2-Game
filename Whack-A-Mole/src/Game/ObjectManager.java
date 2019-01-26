package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener{

	Hole[] holes = new Hole[6];
	Hammer hammer;
	Timer timer;
	GamePanel score;
	int mole;
	int samemole;
	int sign;
	Random random = new Random();

	public ObjectManager() {
		mole = random.nextInt(6);
		sign = random.nextInt(2);
		holes[0] = new Hole(188, 400, 145, 90);
		holes[1] = new Hole(340, 450, 145, 90);
		holes[2] = new Hole(325, 555, 145, 90);
		holes[3] = new Hole(188, 620, 145, 90);
		holes[4] = new Hole(44, 550, 145, 90);
		holes[5] = new Hole(25, 445, 145, 90);

	}

	void draw(Graphics g) {
		/*
		if(samemole == mole) {
			if(mole==5) {
				mole = mole - random.nextInt((4-1)+1);
			}
			else if(mole==4) {
				if(sign==0) {
					
					mole = mole+1;
				}
				else if(sign==1) {
					mole = mole - random.nextInt(mole-1);
				}
			}
		
			else if(mole==3) {
				if(sign==0) {
					
					mole = mole + random.nextInt((2-1)+1);
				}
				else if(sign==1) {
					mole = mole - random.nextInt((2-1)+1);
				}
			}
			else if(mole==2) {
				if(sign==0) {
					
					mole = mole + random.nextInt((3-1)+1);
				}
				else if(sign==1) {
					mole = mole - 1;
				}
			}
			///
			else if(mole==1) {
				mole = mole +random.nextInt((4-1)+1);
				
			}
			
		}
		*/
		holes[mole].draw(g);
		//mole = samemole;
		
		
		
	}

	void getNextMole() {
		mole = random.nextInt(6);

	}

	void purgeObjects() {

	}

	void checkCollisions() {

	}

	int getscore(int score) {

		return score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("moletimer");
		getNextMole();
		
	}

}
