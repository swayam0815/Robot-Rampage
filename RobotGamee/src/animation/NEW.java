package animation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.GraphicsConsole;

public class NEW {

	/***** Global Variables ******/
	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
																					// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen
																	// size
	private static GraphicsConsole gc;
	private static Image image;
	
	private int x = 0;
	private int y = 0;
	private int size = 50;
	private int counter = 0;

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
	// new AnimationMain(gc, GRHEIGHT, GRWIDTH); 
		}

	public NEW(GraphicsConsole gc) throws IOException {

		this.gc = gc;
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the mouse

		File sourceimage = new File("bullet cartoon.png");
		image = ImageIO.read(sourceimage);
		
		while (gc.getKeyCode() != 'Q') {

//			mechanics();

			drawGraphics();

			gc.sleep(1);
		}
	}

	public void mechanics() {
		if (x <= gc.getMouseX())
			x += 2;

		if (x >= gc.getMouseX() - size)
			x -= 2;

		if (counter % 1 == 0)
			y++;

		size = y / 2 + 50;
		counter++;

		if (y >= GRHEIGHT - 500)
			y = GRHEIGHT - 500;

		if (x < 0)
			x = 0;
	}

	public void drawGraphics() {
		synchronized (gc) {
			gc.setBackgroundColor(Color.BLACK);
			gc.setColor(Color.RED);
			
			gc.drawRect(x,x,x,x);
			gc.sleep(10);
			x++;
			gc.drawImage(image, GRWIDTH / 2, GRHEIGHT / 2);
		}

	}
}