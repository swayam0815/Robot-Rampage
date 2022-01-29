// Author: Swayam and Aryan
// Creation Date: December 2022
// description: display credits for game
// Last Modified: January 26 2022
package animation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.GraphicsConsole;

public class Credits {

	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	// size of screen
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen

	// gc to draw on
	private static GraphicsConsole gc;
	// hitboxed for buttons
	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to
																												// aim
	private static Rectangle backBTN = new Rectangle(GRWIDTH / 54, (int) (GRHEIGHT / 1.09), GRWIDTH / 8, GRHEIGHT / 14);

	// sound effects
	private static Clip buttonSound;
	private static AudioInputStream buttonEffect;

	// the images that will show on screen
	private static Image cursorImg;
	private static Image cursorClicked;
	private static Image bkg;
	private static Image back;

	// the prepared images that will be replaced
	private Image backLight;
	private Image backDark;

	public Credits(GraphicsConsole gc) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		// taking in parameter values and storing them
		Credits.gc = gc;
		running = true; // starting program of this class
		initialize();
	}
	
	// method to start the class program
	private void initialize() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the

		// images imported
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));
		bkg = ImageIO.read(new File("credits.png"));
		backLight = ImageIO.read(new File("lightBack.png"));
		backDark = ImageIO.read(new File("darkBack.png"));

		// keep running this class, while condition is met
		while (running) {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}

	}
	
	// boolean to keep track of whether class running or not
	private static boolean running;
	
	// method to get player I/O and act accordingly
	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);

		// buttons light up when hovered over
		if (cursor.intersects(backBTN)) {
			back = backLight;
			if (gc.getMouseClick() > 0) {
				// button sound
				buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
				buttonSound = AudioSystem.getClip();
				buttonSound.open(buttonEffect);
				buttonSound.start();
				// player exited class, so terminate condition for loop
				running = false;
				// go to the page (class) player wants
				new MainMenu(gc);
			}
		} else
			back = backDark;

		gc.getMouseClick();

	}

	// drawing everything for class from this method
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
