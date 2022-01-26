package animation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.*;

public class levels {

	public static void main(String[] args)
			throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
	}

	private static AnimationMain[] level = new AnimationMain[5];
	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	private static boolean[] levelss = new boolean[5];
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit

	private static GraphicsConsole gc;
// = new GraphicsConsole(GRWIDTH, GRHEIGHT);

// rectangles
	private Rectangle lvl1 = new Rectangle((int) (GRWIDTH / 5.923), (int) (GRHEIGHT / 3.421), (int) (GRWIDTH / 9.625),
			(int) (GRWIDTH / 9.625));
	private Rectangle lvl2 = new Rectangle((int) (GRWIDTH / 1.383), (int) (GRHEIGHT / 3.421), (int) (GRWIDTH / 9.625),
			(int) (GRWIDTH / 9.625));
	private Rectangle lvl3 = new Rectangle((int) (GRWIDTH / 5.923), (int) (GRHEIGHT / 1.7567), (int) (GRWIDTH / 9.625),
			(int) (GRWIDTH / 9.625));
	private Rectangle lvl4 = new Rectangle((int) (GRWIDTH / 1.383), (int) (GRHEIGHT / 1.7567), (int) (GRWIDTH / 9.625),
			(int) (GRWIDTH / 9.625));
	private Rectangle lvlBoss = new Rectangle((int) (GRWIDTH / 2.51), (int) (GRHEIGHT / 3.421), (int) (GRWIDTH / 4.957),
			(int) (GRHEIGHT / 2.407));

	private static Rectangle backBTN = new Rectangle(GRWIDTH / 54, (int) (GRHEIGHT / 1.09), GRWIDTH / 8, GRHEIGHT / 14);

// images
	private static Image lvl;
	private static Image cursorImg;
	private static Image cursorClicked;
	private static Image lockImg;
	private static Image back;
	private static Image backLight;
	private static Image backDark;

// number images
	private static Image oneImg;
	private static Image twoImg;
	private static Image threeImg;
	private static Image fourImg;
	private static Image bossImg;
	private static Image oneDark;
	private static Image oneLight;
	private static Image twoDark;
	private static Image twoLight;
	private static Image threeDark;
	private static Image threeLight;
	private static Image fourDark;
	private static Image fourLight;
	private static Image bossLight;

	private static Gun[] guns = new Gun[6];
	private static boolean running;
	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to

	public levels(GraphicsConsole gc, Gun[] guns)
			throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.gc = gc;
		levelss[0] = true;

		running = true;
		this.guns = guns;
		init();
		while (running) {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}
	}

	public static void win(int index) {
		levelss[index] = true;
	}

	private static void init() throws IOException {
		gc.enableMouse();
		gc.enableMouseMotion();

		lvl = ImageIO.read(new File("levelMenu.png"));
		lockImg = ImageIO.read(new File("lock.png"));
		backLight = ImageIO.read(new File("lightBack.png"));
		backDark = ImageIO.read(new File("darkBack.png"));
		oneDark = ImageIO.read(new File("1 dark.png"));
		twoDark = ImageIO.read(new File("2 dark.png"));
		threeDark = ImageIO.read(new File("3 dark.png"));
		fourDark = ImageIO.read(new File("4 dark.png"));
		oneLight = ImageIO.read(new File("1 light.png"));
		twoLight = ImageIO.read(new File("2 light.png"));
		threeLight = ImageIO.read(new File("3 light.png"));
		fourLight = ImageIO.read(new File("4 light.png"));
		bossLight = ImageIO.read(new File("bossLight.png"));

		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));

	}

	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
// set coordinates for cursor
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);

// buttons
		if (cursor.intersects(backBTN)) {
			back = backLight;
			if (gc.getMouseClick() > 0) {
				running = false;
				new Start(gc, guns);
			}
		} else
			back = backDark;

		if (cursor.intersects(lvl1)) {
			oneImg = oneLight;
			if (gc.getMouseClick() > 0) {
				System.out.println("before");

				new AnimationMain(gc, 5, guns, 0);
				System.out.println("after");
			}
		} else
			oneImg = oneDark;

		if (cursor.intersects(lvl2)) {
			twoImg = twoLight;
			if (gc.getMouseClick() > 0) {
				if (levelss[1])
					new AnimationMain(gc, 10, guns, 1);
			}
		} else
			twoImg = twoDark;

		if (cursor.intersects(lvl3)) {
			threeImg = threeLight;
			if (gc.getMouseClick() > 0) {
				if (levelss[2])

					new AnimationMain(gc, 15, guns, 2);
			}
		} else
			threeImg = threeDark;

		if (cursor.intersects(lvl4)) {
			fourImg = fourLight;
			if (gc.getMouseClick() > 0) {
				if (levelss[3])

					new AnimationMain(gc, 20, guns, 4);
			}
		} else
			fourImg = fourDark;

		if (cursor.intersects(lvlBoss)) {
			bossImg = bossLight;
			if (gc.getMouseClick() > 0) {
// play boss level
			}
		} else
			bossImg = null;

// this fixes a bug with buttons
		gc.getMouseClick();

	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();

// background
			gc.drawImage(lvl, 0, 0, GRWIDTH, GRHEIGHT);

// locks on levels
			gc.drawImage(lockImg, lvl2);
			gc.drawImage(lockImg, lvl3);
			gc.drawImage(lockImg, lvl4);

// numbers on levels
			gc.drawImage(oneImg, lvl1);
			gc.drawImage(twoImg, lvl2);
			gc.drawImage(threeImg, lvl3);
			gc.drawImage(fourImg, lvl4);
			gc.drawImage(bossImg, lvlBoss);

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
