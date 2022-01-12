package animation;

import java.awt.*;

import hsa2.GraphicsConsole;

public class NEW {

	/***** Global Variables ******/
	private Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen size
	private int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen size
	private GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);

	private Image dartboardImg = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("dartboard.png"));

	private int x = 0;
	private int y = 0;
	private int size = 50;
	private int counter = 0;

	public static void main(String[] args) {
		new NEW();
	}

	private NEW() {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the mouse

		while (gc.getKeyCode() != 'Q') {

			mechanics();

			drawGraphics();

			gc.sleep(1);
		}
	}

	public void mechanics() {
		if (x <= gc.getMouseX())
			x += 2;

		if (x >= gc.getMouseX() - size)
			x -= 2;

		if (counter % 1 == 0)
			y++;

		size = y / 2 + 50;
		counter++;

		if (y >= GRHEIGHT - 500)
			y = GRHEIGHT - 500;

		if (x < 0)
			x = 0;
	}

	public void drawGraphics() {
		synchronized (gc) {
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();

			gc.drawImage(dartboardImg, x, y, size, size);
		}

	}
}