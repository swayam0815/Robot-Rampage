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

	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to aim
	
	private static Rectangle startBTN = new Rectangle	(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 34, GRWIDTH / 141 * 30, GRHEIGHT / 79 * 9);
	private static Rectangle quitBTN = new Rectangle	(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 46, GRWIDTH / 141 * 30, GRHEIGHT / 79 * 9);
	private static Rectangle creditsBTN = new Rectangle	(GRWIDTH / 141 * 57, GRHEIGHT / 79 * 58, GRWIDTH / 141 * 30, GRHEIGHT / 79 * 9);
	//556, (316 - 437 - 560), 298, 90
	private static Rectangle backBTN = new Rectangle(GRWIDTH / 54, (int)(GRHEIGHT / 1.09), GRWIDTH / 8, GRHEIGHT / 14);

	
	//the images that will show on screen
	private static Image start;
	private static Image quit;
	private static Image credits;
	private static Image back;
	private static Image cursorImg;
	private static Image cursorClicked;
	private static Image bkg;
	
	//the prepared images that will be replaced
	private static Image startLight;
	private static Image quitLight;
	private static Image creditsLight;
	private static Image startDark;
	private static Image quitDark;
	private static Image creditsDark;
	private static Image backLight;
	private static Image backDark;

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		new AnimationMain(gc);

	}

	public MainMenu() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the

		//images imported
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));
		bkg = ImageIO.read(new File("blankMAINMENU.png"));
		startDark = ImageIO.read(new File("darkStart.png"));
		quitDark = ImageIO.read(new File("darkQuit.png"));
		creditsDark = ImageIO.read(new File("darkCredits.png"));
		startLight = ImageIO.read(new File("lightStart.png"));
		quitLight = ImageIO.read(new File("lightQuit.png"));
		creditsLight = ImageIO.read(new File("lightCredits.png"));
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

//		if (gc.isKeyDown(37) || gc.isKeyDown(65))
//			startBTN.x -= 1;
//		if (gc.isKeyDown(39) || gc.isKeyDown(100) || gc.isKeyDown(68))
//			startBTN.x += 1;
//
//		if (gc.isKeyDown(87))
//			startBTN.y -= 1;
//		if (gc.isKeyDown(83))
//			startBTN.y += 1;
//
//		if (gc.isKeyDown(32))
//			System.out.println(startBTN.x + " x val. \t" + startBTN.y + " y val.");
		
		//buttons light up when hovered over
		if (cursor.intersects(startBTN)) {
			start = startLight;
			if (gc.getMouseButton(0)) {
				new AnimationMain(gc);
			}
		} else
			start = startDark;

		if (cursor.intersects(creditsBTN))
			credits = creditsLight;
		else
			credits = creditsDark;

		if (cursor.intersects(quitBTN)) {
			quit = quitLight;
			if (gc.getMouseButton(0))
				gc.close();
		} else
			quit = quitDark;
		
		if (cursor.intersects(backBTN))
			back = backLight;
		else
			back = backDark;
		
	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();
			
			//background
			gc.drawImage(bkg, 0, 0, GRWIDTH, GRHEIGHT);
			
			//start & quit & credits buttons
			gc.drawImage(start, startBTN);
			gc.drawImage(quit, quitBTN);
			gc.drawImage(credits, creditsBTN);
			
			//back button
			gc.drawImage(back, backBTN);

			//cursor
			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
		}
	}

}
