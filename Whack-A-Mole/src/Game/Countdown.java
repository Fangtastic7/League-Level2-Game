package Game;

import java.awt.Graphics;
import java.util.Timer;

public class Countdown {
	Timer time;
	int limit = 30;
	public Countdown() {
		time = new Timer();
		
	}
	void draw(Graphics g) {
		g.drawString("Timer " + time, 100, 50);
	}
}
