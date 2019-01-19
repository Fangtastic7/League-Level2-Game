package Game;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager {

	Hole[] holes = new Hole[6];
	Hammer hammer;
	Timer timer;
	GamePanel score;
	int mole;
	Random random = new Random();

	public ObjectManager() {
		mole = random.nextInt(6);
		holes[0] = new Hole(188, 400, 145, 90);
		holes[1] = new Hole(340, 450, 145, 90);
		holes[2] = new Hole(325, 555, 145, 90);
		holes[3] = new Hole(188, 620, 145, 90);
		holes[4] = new Hole(44, 550, 145, 90);
		holes[5] = new Hole(25, 445, 145, 90);

	}

	void draw(Graphics g) {
		holes[mole].draw(g);
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

}
