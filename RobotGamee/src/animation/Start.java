package animation;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.GraphicsConsole;

public class Start {
	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen

	private static GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);

	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to
																												// aim
	private static Image cursorImg;
	private static Image cursorClicked;
	private static Image bkg;

	private static Rectangle levelsBTN = new Rectangle(220, 100, 480, 525);
	private static Rectangle quitBTN = new Rectangle(GRWIDTH / 141 * 57, 100, GRWIDTH / 141 * 30,
			GRHEIGHT / 79 * 9);
	private static Rectangle creditsBTN = new Rectangle(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 58, 480, 525);
	// 556, (316 - 437 - 560), 298, 90

	private static Image levels;
	private static Image Upgrade;
	private static boolean running = true;

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		new Start();
	}

	public Start() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the

		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));
		bkg = ImageIO.read(new File("menu page 2.png"));
		levels = ImageIO.read(new File("darkCampaign.png"));
		Upgrade = ImageIO.read(new File("darkUpgrade.png"));

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
			creditsBTN.x -= 5;
		if (gc.isKeyDown(39) || gc.isKeyDown(100) || gc.isKeyDown(68))
			creditsBTN.x += 5;

		if (gc.isKeyDown(87))
			creditsBTN.y -= 5;
		if (gc.isKeyDown(83))
			creditsBTN.y += 5;

		if (gc.isKeyDown(32))
			System.out.println(levelsBTN.x + " x val. \t" + levelsBTN.y + " y val.");

		if (cursor.intersects(levelsBTN)) {
			levels = ImageIO.read(new File("lightCampagin.png"));
			if (cursor.intersects(levelsBTN) && gc.getMouseButton(0)) {
				new AnimationMain(gc);
			}
		} else
			levels = ImageIO.read(new File("darkCampaign.png"));

		if (cursor.intersects(quitBTN)) {
			Upgrade = ImageIO.read(new File("lightUpgrade.png"));
			if (cursor.intersects(quitBTN) && gc.getMouseButton(0))
				gc.close();
		} else
			Upgrade = ImageIO.read(new File("darkUpgrade.png"));

	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();
			gc.drawImage(bkg, 0, 0, GRWIDTH, GRHEIGHT);
			gc.drawRect(creditsBTN);
			gc.drawImage(levels, 184, 100, 550, 550);
			gc.drawImage(Upgrade, 220, 100, 550, 550);
			// 184, 76
			// gc.drawImage(Upgrade, quitBTN.x, quitBTN.y, quitBTN.width, quitBTN.height);

			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15,
						cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
		}
	}

}
