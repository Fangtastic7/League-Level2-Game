package Game;

import java.awt.Graphics;
import java.util.Timer;

public class Countdown extends GamePanel {
	Timer time;
	Timer counter;
	public Countdown() {
		time = new Timer();
		counter = new Timer();
	}
	void draw(Graphics g) {
		g.drawString("Timer " + counter, 100, 50);
	}
}
