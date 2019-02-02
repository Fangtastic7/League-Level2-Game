package Game;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class WhackAMole {
	static final int width = 500;
	static final int height = 800;

	JFrame frame;
	GamePanel panel;
	JLabel timer;

	public static void main(String[] args) {
		new WhackAMole().createUI();
	}

	public WhackAMole() {

		frame = new JFrame("Whack A Mole");
		panel = new GamePanel();

	}

	private void createUI() {
		// TODO Auto-generated method stub
		frame.setVisible(true);
		frame.add(panel);
		frame.setTitle("WhackAMole");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		frame.addKeyListener(panel);
		frame.addMouseListener(panel);
		panel.startGame();
	}
}
