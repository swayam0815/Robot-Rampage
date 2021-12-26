package daGame;

// class to combine bunch of features

import java.awt.*;
import java.util.ArrayList;

import hsa2.GraphicsConsole;

public class enemy {

	/***** Global Variables ******/
	/***** Global Variables ******/
	private Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen size
	private int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen size
	private GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);
	ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
	ArrayList<Rectangle> hit = new ArrayList<Rectangle>();
	private static Color ene = Color.RED;

	// enemy/robot variables
	private int x = 0;
	private int y = 0;
	private int size = 50;
	private int counter = 0;
	
	private int playerX = 50;
	
	public static void main(String[] args) {
		new enemy();
	}

	private enemy() {
		
	}

	public void enemymechanics() {
		if (x <= playerX)
			x += 2;

		if (x >= playerX - size)
			x -= 2;

		if (counter % 1 == 0)
			y++;

		size = y / 2 + 50;
		counter++;

		if (y >= GRHEIGHT - (size + size / 2))
			y = GRHEIGHT - (size + size / 2);

		if (x < 0)
			x = 0;
	}



}