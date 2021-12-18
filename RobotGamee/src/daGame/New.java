package daGame;

import java.awt.*;

import javax.crypto.spec.GCMParameterSpec;

import hsa2.GraphicsConsole;

public class New {

	/***** Global Variables ******/
	private Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen size
	private int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen size
	private GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);

	private int x = 0;
	private int y = 0;
	private int size = 50;
	private int counter = 0;
	static Rectangle rect = null;

	public static void main(String[] args) {
		new New();
	}

	private New() {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the mouse

		while (gc.getKeyCode() != 'Q') {

			mechanics();

			drawGraphics();

			gc.sleep(1);
		}
	}

	public void mechanics() {
		if (gc.isKeyDown(32)) {
			counter = 0;
			rect = new Rectangle(x + size / 4, y + size / 4, 500, 500);
		}

		if (rect != null) {
			if (counter % 5 == 0) {
				rect.y--;
				rect.width--;
				rect.height--;
				if (rect.y <= 0)
					rect = null;
			}
			counter++;

		}

		if (gc.isKeyDown(65) || gc.isKeyDown(97)) {
			x -= 3;
		}
		if (gc.isKeyDown(68) || gc.isKeyDown(100)) {
			x += 3;
		}
		if (gc.isKeyDown(87) || gc.isKeyDown(119)) {
			y -= 3;
		}
		if (gc.isKeyDown(83) || gc.isKeyDown(115)) {
			y += 3;
		}

		size = y / 2;

	}

	public void drawGraphics() {
		synchronized (gc) {
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();
			gc.setColor(Color.GREEN);
			gc.drawRect(x, y, size, size);
			if (rect != null)
				gc.drawRect(rect);

		}

	}
}
