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
	private int playerY = 50;
	private int bulletSize = 50;
	private int counter1 = 0;

	public static void main(String[] args) {
		new enemy();
	}

	private enemy() {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the mouse

		while (gc.getKeyCode() != 'Q') {

			mechanics();
			enemymechanics();
			drawGraphics();

			gc.sleep(1);
		}
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

	public void mechanics() {
		if (gc.isKeyDown(32)) {
			bullets.add(new Rectangle(playerX, playerY, bulletSize, bulletSize));
		}

		// movement controls
		if (gc.isKeyDown(65) || gc.isKeyDown(97)) {
			playerX -= 3;
		}
		if (gc.isKeyDown(68) || gc.isKeyDown(100)) {
			playerX += 3;
		}
		if (gc.isKeyDown(87) || gc.isKeyDown(119)) {
			playerY -= 3;
		}
		if (gc.isKeyDown(83) || gc.isKeyDown(115)) {
			playerY += 3;
		}

		// animating size
		bulletSize = playerY / 4;
	}

	public void drawGraphics() {
		synchronized (gc) {

			// graphics stuff
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();
			gc.setColor(ene);
			gc.drawRect(x, y, size, size);
			gc.setColor(Color.BLUE);
			gc.drawRect(playerX, playerY, bulletSize, bulletSize);

			for (Rectangle rect : bullets) {
				gc.setColor(Color.GREEN);
				gc.drawRect(rect);
				rect.y -= 1;
			}
			// removing hit bullets from main bullets list
			bullets.removeAll(hit);
		
		}

	}
}