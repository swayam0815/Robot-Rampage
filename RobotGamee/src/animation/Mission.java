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

public class Mission {

	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen

// gc
	private static GraphicsConsole gc;

	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to aim

	private static Rectangle creditsBTN = new Rectangle(GRWIDTH / 54, (int) (GRHEIGHT / 1.09), GRWIDTH / 8, GRHEIGHT / 14);
	//GRWIDTH / 141 * 57, GRHEIGHT / 78 * 55, GRWIDTH / 141 * 30, GRHEIGHT / 79 * 9

	// sound effects
	private static Clip buttonSound;
	private static AudioInputStream buttonEffect;

// the images that will show on screen
	private static Image cursorImg;
	private static Image cursorClicked;
	private static Image bkg;
	private static Gun[] guns = new Gun[6];
	private Image back;
	private Image backLight;
	private Image backDark;
	private Image roboPartsImg;
	
	private static int moneyEarned;
	private Font font = new Font("Elephant", Font.PLAIN, GRHEIGHT / 10);

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
	}

	public Mission(GraphicsConsole x, boolean win, Gun[] guns, int moneyEarned)
			throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		gc = x;
		Mission.guns = guns;
		Mission.moneyEarned = moneyEarned;
		
		setInitialValues.setMoney(setInitialValues.getMoney() + moneyEarned);
		initialize(win);

	}

	private void initialize(boolean win) throws IOException, LineUnavailableException, UnsupportedAudioFileException {

		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the

		gc.setFont(font);
// images imported
		roboPartsImg = ImageIO.read(new File("Robot parts.png"));
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));

		if (win)
			bkg = ImageIO.read(new File("win.png"));
		else
			bkg = ImageIO.read(new File("lose.png"));

		backLight = ImageIO.read(new File("lightBack.png"));
		backDark = ImageIO.read(new File("darkBack.png"));

		while (true) {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}

	}

	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);

		if (cursor.intersects(creditsBTN)) {
			back = backLight;
			if (gc.getMouseClick() > 0) {
				// button sound
				buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
				buttonSound = AudioSystem.getClip();
				buttonSound.open(buttonEffect);
				buttonSound.start();
				new levels(gc, guns);	
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
			gc.drawImage(back, creditsBTN);

//money earned
			gc.setColor(Color.RED);
			gc.drawString("Earned:", (int)(GRWIDTH / 3.85), (int)(GRHEIGHT / 1.3));
			gc.drawImage(roboPartsImg, (int)(GRWIDTH / 2.026), (int)(GRHEIGHT / 1.444), GRHEIGHT / 9, GRHEIGHT / 9);
			gc.setColor(Color.GREEN);
			gc.drawString(moneyEarned + "", (int)(GRWIDTH / 1.77), (int)(GRHEIGHT / 1.3));

// cursor
			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15,
						cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
		}
	}

}
