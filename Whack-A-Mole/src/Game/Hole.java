package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.w3c.dom.css.Rect;

public class Hole extends GameObject{

	
public Hole(int x, int y, int width, int height) {
	super(x, y, width, height);
	
}
void update(){
	
}
void draw(Graphics g) {
g.setColor(Color.GRAY);
g.drawImage(GamePanel.moleImg, x, y, width, height, null);
	
}

}
