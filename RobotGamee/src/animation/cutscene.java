package animation;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hsa2.GraphicsConsole;

public class cutscene {
	private static Image backGround; // "bakground.jpg"
	private static Image close; // "bakground.jpg"
	private static Image open; // "bakground.jpg"
	private static Image image;

	private static void getImg() throws IOException {
		backGround = ImageIO.read(new File("bakground.png"));
		open = ImageIO.read(new File("open.png"));
		close = ImageIO.read(new File("close.png"));

	}

	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit
	// the screen

	private static GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);

	public cutscene() throws IOException {
		getImg();
		while (true) {

			mechanics();
			drawGraphics();

			gc.sleep(1);

		}

	}

	public static void mechanics() {
		if (gc.getKeyCode() == 32) {
			image = open;
		} else
			image = close;

	}

	public static void drawGraphics() {
		synchronized (gc) {
			gc.drawImage(backGround, 0, 0, GRWIDTH, GRHEIGHT);
			gc.drawImage(image, ((int) (GRWIDTH / 3.5)), GRHEIGHT / 8, 700, 700);
		}

	}

	public static void main(String[] args) throws IOException {
		new cutscene();

	}

}
