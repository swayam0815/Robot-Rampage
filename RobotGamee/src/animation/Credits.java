package animation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.GraphicsConsole;

public class Credits {

	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen

// gc
	private static GraphicsConsole gc;

	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to aim
	private static Rectangle backBTN = new Rectangle(GRWIDTH / 54, (int) (GRHEIGHT / 1.09), GRWIDTH / 8, GRHEIGHT / 14);


// the images that will show on screen
	private static Image cursorImg;
	private static Image cursorClicked;
	private static Image bkg;
	private static Image back;

// the prepared images that will be replaced
	private Image backLight;
	private Image backDark;

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
	}

	public Credits(GraphicsConsole gc) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.gc = gc;
		running = true;
		initialize();
	}

	private void initialize() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the

// images imported
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));
		bkg = ImageIO.read(new File("credits.png"));
		backLight = ImageIO.read(new File("lightBack.png"));
		backDark = ImageIO.read(new File("darkBack.png"));

		while (running) {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}

	}

	private static boolean running;

	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);

// buttons light up when hovered over
		if (cursor.intersects(backBTN)) {
			back = backLight;
			if (gc.getMouseClick() > 0) {
				running = false;
				new MainMenu(gc);
			}
		} else
			back = backDark;

		gc.getMouseClick();

	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();

// background
			gc.drawImage(bkg, 0, 0, GRWIDTH, GRHEIGHT);

// back button
			gc.drawImage(back, backBTN);

// cursor
			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15,
						cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
		}
	}

}
