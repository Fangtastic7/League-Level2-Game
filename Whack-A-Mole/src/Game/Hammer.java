package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hammer extends GameObject implements MouseMotionListener{
	boolean hammerdown = true;
	Image hammerUpImg;
	Image hammerLeftDownImg;
	Image hammerRightDownImg;

	public Hammer(int x, int y, int width, int height) {
		super(x, y, width, height);
		try {

			hammerLeftDownImg = ImageIO.read(this.getClass().getResourceAsStream("hammerleftdown.png"));
			hammerRightDownImg = ImageIO.read(this.getClass().getResourceAsStream("hammerrightdown.png"));
			hammerUpImg = ImageIO.read(this.getClass().getResourceAsStream("hammer.png"));
		} catch (IOException e) {
		}
	}

	void update() {

	}

	void draw(Graphics g) {
		
		if(hammerdown) {
			g.drawImage(hammerLeftDownImg, x - 50, y -50, width, height, null);
			
		}
		else {
			g.drawImage(hammerUpImg, x, y, width, height, null);
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
	}


}
