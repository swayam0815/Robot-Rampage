package animation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.*;

public class levels {
	
	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		new setInitialValues(gc, GRWIDTH, GRHEIGHT);
		new levels(gc);
	}
	private static AnimationMain[] level = new AnimationMain[5];
	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen

	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit

	private static GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);

	//rectangles
	private Rectangle lvl1 = new Rectangle((int)(GRWIDTH / 5.923), (int)(GRHEIGHT / 3.421), (int)(GRWIDTH / 9.625), (int)(GRWIDTH / 9.625));
	private Rectangle lvl2 = new Rectangle(835, (int)(GRHEIGHT / 3.421), (int)(GRWIDTH / 9.625), (int)(GRWIDTH / 9.625));
	private Rectangle lvl3 = new Rectangle((int)(GRWIDTH / 5.923), 370, (int)(GRWIDTH / 9.625), (int)(GRWIDTH / 9.625));
	private Rectangle lvl4 = new Rectangle(835, 370, (int)(GRWIDTH / 9.625), (int)(GRWIDTH / 9.625));
	private Rectangle lvlBoss = new Rectangle(460, (int)(GRHEIGHT / 3.421), 233, 270);
	
	//images
	private static Image lvl;
	private static Image cursorImg;
	private static Image cursorClicked;
	private static Image bossImg;
	private static Image bossLight;
	private static Image bossDark;
	
	
	// the screen

//	private static GraphicsConsole gc;
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
		gc.enableMouse();
		gc.enableMouseMotion();
		
		lvl = ImageIO.read(new File("levelMenu.png"));
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));

	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.clear();

			// background
			gc.drawImage(lvl, 0, 0, GRWIDTH, GRHEIGHT);

			gc.setColor(Color.GREEN);
			gc.drawRect(lvl1);
			gc.drawRect(lvl2);
			gc.drawRect(lvl3);
			gc.drawRect(lvl4);
			gc.drawRect(lvlBoss);
			
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
