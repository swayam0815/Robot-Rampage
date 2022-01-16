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

	//images that will show on screen
	private static Image cursorImg;
	private static Image cursorClicked;
	private static Image bkg;
	private static Image levels;
	private static Image Upgrade;
	
	//images that will replace them
	private static Image levelsDark;
	private static Image UpgradeDark;
	private static Image levelsLight;
	private static Image UpgradeLight;

	private static Rectangle levelsBTN = new Rectangle(GRWIDTH / 7, GRWIDTH / 11,
			(int)(GRHEIGHT / 1.6), (int)(GRHEIGHT / 1.585365853658537));
	private static Rectangle upgradeBTN = new Rectangle((int)(GRWIDTH / 2), GRWIDTH / 11,
			(int)(GRHEIGHT / 1.6), (int)(GRHEIGHT / 1.585365853658537));

	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		new Start();
	}


	public Start() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the

		//images imported
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));
		bkg = ImageIO.read(new File("menu page 2.png"));
		levelsDark = ImageIO.read(new File("darkCampaign.png"));
		UpgradeDark = ImageIO.read(new File("darkUpgrade.png"));
		levelsLight = ImageIO.read(new File("lightCampagin.png"));
		UpgradeLight = ImageIO.read(new File("lightUpgrade.png"));

		while (true) {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}
	}

	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);

		//buttons light up when hovered over
		if (cursor.intersects(levelsBTN)) {
			levels = levelsLight;
			if (gc.getMouseButton(0)) {
				new AnimationMain(gc);
			}
		} else
			levels = levelsDark;

		if (cursor.intersects(upgradeBTN)) {
			Upgrade = UpgradeLight;
			if (gc.getMouseButton(0))
				gc.close();
		} else
			Upgrade = UpgradeDark;

	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();
			
			//background
			gc.drawImage(bkg, 0, 0, GRWIDTH, GRHEIGHT);
			
			//campaign & upgrade button
			gc.drawImage(levels, levelsBTN);
			gc.drawImage(Upgrade, upgradeBTN);
			
			//back button
			
			//cursor
			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
		}
	}

}