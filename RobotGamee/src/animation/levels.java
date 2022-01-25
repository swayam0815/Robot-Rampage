package animation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.*;

public class levels {

	private static AnimationMain[] level = new AnimationMain[5];
	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen

	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit
	private static Image lvl;
	private static Image cursorImg;
	private static Image cursorClicked;

	// the screen

	private static GraphicsConsole gc;
	// = new GraphicsConsole(GRWIDTH, GRHEIGHT);
	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to

	public levels(GraphicsConsole gc) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.gc = gc;
		init();
		while (true) {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}
	}

	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);

		gc.getMouseClick();

	}

	private static void init() throws IOException {
		lvl = ImageIO.read(new File("levelBlank.png"));
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));

	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();

			// background
			gc.drawImage(lvl, 0, 0, GRWIDTH, GRHEIGHT);

			// cursor
			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15,
						cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);

		}

	}

	public static void i() {
		// level 1
//		level[0] = new AnimationMain(5);
		// level 2
//		level[1] = new AnimationMain(10);
		// level 3
//		level[2] = new AnimationMain(15);
		// level 4
//		level[3] = new AnimationMain(20);
		// level 5
//		level[4] = new AnimationMain(5);

	}

}
