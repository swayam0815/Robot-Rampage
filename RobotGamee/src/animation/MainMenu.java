package animation;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
	private static Image cursorImg;
	private static Image bkg;
	
	private static Rectangle startBTN = new Rectangle	(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 32, GRWIDTH / 141 * 30, GRHEIGHT / 79 * 9);
	private static Rectangle quitBTN = new Rectangle	(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 44, GRWIDTH / 141 * 30, GRHEIGHT / 79 * 9);
	private static Rectangle creditsBTN = new Rectangle	(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 56, GRWIDTH / 141 * 30, GRHEIGHT / 79 * 9);
	//556, (316 - 437 - 560), 298, 90
	
	private static Image start;
	private static Image quit;
	private static Image credits;
	private static boolean running = true;

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		new MainMenu();

	}

	public MainMenu() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the

		cursorImg = ImageIO.read(new File("crosshair.png"));
		bkg = ImageIO.read(new File("blankMAINMENU.png"));
		start = ImageIO.read(new File("darkStart.png"));
		quit = ImageIO.read(new File("darkQuit.png"));
		credits = ImageIO.read(new File("darkCredits.png"));

		while (running) {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}
		new AnimationMain(gc);
	}

	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);

		if (gc.isKeyDown(37) || gc.isKeyDown(65))
			startBTN.x -= 1;
		if (gc.isKeyDown(39) || gc.isKeyDown(100) || gc.isKeyDown(68))
			startBTN.x += 1;

		if (gc.isKeyDown(87))
			startBTN.y -= 1;
		if (gc.isKeyDown(83))
			startBTN.y += 1;

		if (gc.isKeyDown(32))
			System.out.println(startBTN.x + " x val. \t" + startBTN.y + " y val.");

		if (cursor.intersects(startBTN)) {
			start = ImageIO.read(new File("lightStart.png"));
			if (cursor.intersects(startBTN) && gc.getMouseClick() > 0) {
				new AnimationMain(gc);
			}
		} else
			start = ImageIO.read(new File("darkStart.png"));

		if (cursor.intersects(quitBTN)) {
			quit = ImageIO.read(new File("lightQuit.png"));
			if (cursor.intersects(quitBTN) && gc.getMouseClick() > 0)
				gc.close();
		} else
			quit = ImageIO.read(new File("darkQuit.png"));

		if (cursor.intersects(creditsBTN))
			credits = ImageIO.read(new File("lightCredits.png"));
		else
			credits = ImageIO.read(new File("darkCredits.png"));

	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();
			gc.drawImage(bkg, 0, 0, GRWIDTH, GRHEIGHT);

//			gc.drawRect(new Rectangle(GRWIDTH / 2, GRHEIGHT / 4 + 100, 500, 109));
			gc.drawImage(start, startBTN.x, startBTN.y, startBTN.width, startBTN.height);
			gc.drawImage(quit, quitBTN.x, quitBTN.y, quitBTN.width, quitBTN.height);
			gc.drawImage(credits, creditsBTN.x, creditsBTN.y, creditsBTN.width, creditsBTN.height);
			gc.drawImage(cursorImg, cursor.x, cursor.y, cursor.width * 10, cursor.height * 10);

		}
	}

}
