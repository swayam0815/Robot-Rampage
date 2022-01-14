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

	private static Rectangle playstartBTN = new Rectangle(GRWIDTH / 2, GRHEIGHT / 4, 500, 500);
	private static Color playColor = Color.RED;
	private static Rectangle CrossHair = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 10, GRHEIGHT / 10); // to
																													// aim
	private static Image crosshair;
	private static Image bkg;

	private static Rectangle startBTN = new Rectangle(556, 316, 298, 90);
	private static Rectangle quitBTN = new Rectangle(556, 437, 298, 90);

	private static Rectangle creditsBTN = new Rectangle(556, 560, 298, 90);

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

		crosshair = ImageIO.read(new File("crosshair.png"));
		bkg = ImageIO.read(new File("ROBOT.png"));
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
		CrossHair.x = gc.getMouseX() - (CrossHair.width / 2);
		CrossHair.y = gc.getMouseY() - (CrossHair.height / 2);

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

		if (CrossHair.intersects(startBTN))
			start = ImageIO.read(new File("lightStart.png"));
		else if (CrossHair.intersects(startBTN) && gc.getMouseClick() > 0) {
			System.out.println("working");
			running = false;}
		else
			start = ImageIO.read(new File("darkStart.png"));

		if (CrossHair.intersects(quitBTN))
			quit = ImageIO.read(new File("lightQuit.png"));
		else
			quit = ImageIO.read(new File("darkQuit.png"));

		if (CrossHair.intersects(creditsBTN))
			credits = ImageIO.read(new File("lightCredits.png"));
		else
			credits = ImageIO.read(new File("darkCredits.png"));

	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();
			gc.drawImage(bkg, 0, 0, GRWIDTH, GRHEIGHT);
			gc.setColor(Color.WHITE);
			gc.drawImage(start, 556, 316, 298, 90);
			gc.drawImage(quit, 556, 437, 298, 90);
			gc.drawImage(credits, 556, 560, 298, 90);
			gc.drawImage(crosshair, CrossHair.x, CrossHair.y, CrossHair.width, CrossHair.height);
		}
	}

}
