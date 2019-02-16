package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Hole extends GameObject {

	public Hole(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update() {

	}

	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.drawImage(GamePanel.moleImg, x, y, width, height, null);

	}

	boolean checkcollision(int mouseX, int mouseY) {
		if (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height) {

			return true;
		}
		return false;
	}

}
