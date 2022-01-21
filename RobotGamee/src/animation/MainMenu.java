package animation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.GraphicsConsole;

public class MainMenu {

	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen

	private static GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);

	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to
																												// aim

	private static Rectangle startBTN = new Rectangle(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 34, GRWIDTH / 141 * 30,
			GRHEIGHT / 79 * 9);
	private static Rectangle quitBTN = new Rectangle(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 46, GRWIDTH / 141 * 30,
			GRHEIGHT / 79 * 9);
	private static Rectangle creditsBTN = new Rectangle(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 58, GRWIDTH / 141 * 30,
			GRHEIGHT / 79 * 9);

	private static Image pistolImg; // "Pistol POV.png"
	private static Image pistolFlipped; // "Pistol POV flipped.png"
	private static Image pistolSide; // "Pistol POV flipped.png"
	private static Gun gun;

	// the images that will show on screen
	private static Image start;
	private static Image quit;
	private static Image credits;
	private static Image cursorImg;
	private static Image cursorClicked;
	private static Image bkg;

	// the prepared images that will be replaced
	private static Image startLight;
	private static Image quitLight;
	private static Image creditsLight;
	private static Image startDark;
	private static Image quitDark;
	private static Image creditsDark;

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		new MainMenu(gc);
	}

	public MainMenu(GraphicsConsole gc) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.gc = gc;
		initialize();
	}

	private void initialize() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the

		// images imported
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));
		bkg = ImageIO.read(new File("blankMAINMENU.png"));
		startDark = ImageIO.read(new File("darkStart.png"));
		quitDark = ImageIO.read(new File("darkQuit.png"));
		creditsDark = ImageIO.read(new File("darkCredits.png"));
		startLight = ImageIO.read(new File("lightStart.png"));
		quitLight = ImageIO.read(new File("lightQuit.png"));
		creditsLight = ImageIO.read(new File("lightCredits.png"));

		pistolImg = ImageIO.read(new File("Pistol POV.png"));
		pistolFlipped = ImageIO.read(new File("Pistol POV flipped.png"));
		pistolSide = ImageIO.read(new File("Pistol side view.png"));

		// pistol
		gun = new Gun(10, 100, 7, 0, 2, pistolImg, pistolFlipped, pistolSide, true, true);

		while (true) {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}

	}

	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);
		
		// buttons light up when hovered over
		if (cursor.intersects(startBTN)) {
			start = startLight;
			if (gc.getMouseClick() > 0) {
				new Start(gc);
			}
		} else
			start = startDark;

		if (cursor.intersects(creditsBTN))
			credits = creditsLight;
		else
			credits = creditsDark;

		if (cursor.intersects(quitBTN)) {
			quit = quitLight;
			if (gc.getMouseClick() > 0)
				gc.close();
		} else
			quit = quitDark;

		gc.getMouseClick();
		
	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();

			// background
			gc.drawImage(bkg, 0, 0, GRWIDTH, GRHEIGHT);

			// start & quit & credits buttons
			gc.drawImage(start, startBTN);
			gc.drawImage(quit, quitBTN);
			gc.drawImage(credits, creditsBTN);

			// cursor
			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15,
						cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
		}
	}

}
