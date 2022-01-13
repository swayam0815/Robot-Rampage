package animation;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Image;

import hsa2.GraphicsConsole;

public class UpgradeMenu {
	
	public static void main (String[] args) throws IOException {
		new UpgradeMenu();
	}
	
	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen
	
	private static GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);
	
	//pictures
	private Image background;
	private Image buyImg;
	private Image equipImg;
	private Image equippedImg;
	private Image pgUpImg;
	private Image pgDownImg;
	
	public UpgradeMenu() throws IOException {
		setValues();
		
		while (true) {
			drawGraphics();
			gc.sleep(1);
		}
	}
	
	public void setValues() throws IOException {
		background = ImageIO.read(new File("Upgrade menu.jpg"));
		buyImg = ImageIO.read(new File("Buy Button.png"));
//		equipImg
//		equippedImg
//		pgUpImg
//		pgDownImg
	}
	
	
	public void drawGraphics() throws IOException {
		gc.drawImage(background, 0, 0, GRWIDTH, GRHEIGHT);
	}

	
	
}
