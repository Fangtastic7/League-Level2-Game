package Game;

import java.awt.Graphics;

public class ObjectManager {
	Holes hole1;
	Holes hole2;
	Holes hole3;
	Holes hole4;
	Holes hole5;
	Holes hole6;
	Hammer hammer;
public ObjectManager(Holes hole1, Holes hole2, Holes hole3, Holes hole4, Holes hole5, Holes hole6, Hammer hammer) {
this.hole1 = hole1;
this.hole2 = hole2;
this.hole3 = hole3;
this.hole4 = hole4;
this.hole5 = hole5;
this.hole6 = hole6;
this.hammer = hammer;
	
}
void draw(Graphics g) {
	hole1.draw(g);
	hole2.draw(g);
	hole3.draw(g);
	hole4.draw(g);
	hole5.draw(g);
	hole6.draw(g);
}
int getscore(int score) {
	
	return score;
}
}
