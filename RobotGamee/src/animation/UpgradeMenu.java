package animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.Rectangle;

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
	
	//rectangles
	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100); // to
	private static Rectangle pgDown;
	private static Rectangle pgUp;
	private static Rectangle buyBTN;
	
	//pictures
	private Image background;
	private Image buyImg;
	private Image equipImg;
	private Image equippedImg;
	private Image pgUpImg;
	private Image pgDownImg;
	private static Image cursorImg;
	private static Image cursorClicked;
	
	//gun pictures
	private Image pistolImg;
	private Image AR15Img;
	private Image sniperImg;
	private Image minigunImg;
	private Image grenadeLauncherImg;
	private Image hoseImg;
	
	private Image currentGun;
	
	//variables
	int gunSize;
	
	public UpgradeMenu() throws IOException {
		setValues();
		
		while (gc.getKeyCode() != 'Q') {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}
	}
	
	public void setValues() throws IOException {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the mouse
		
		//pictures
		background = ImageIO.read(new File("Upgrade menu.jpg"));
		buyImg = ImageIO.read(new File("Buy Button.png"));
		equipImg = ImageIO.read(new File("Equip Button.png"));
		equippedImg = ImageIO.read(new File("Equipped Button.png"));
		pgUpImg = ImageIO.read(new File("pg up arrow.png"));
		pgDownImg = ImageIO.read(new File("pg down arrow.png"));
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));

		//gun pictures
		pistolImg = ImageIO.read(new File("Pistol side view.png"));
		AR15Img = ImageIO.read(new File("AR15 side view.png"));
		sniperImg = ImageIO.read(new File("Sniper side view.png"));
		minigunImg = ImageIO.read(new File("Minigun side view.png"));
		grenadeLauncherImg = ImageIO.read(new File("GrenadeLauncher side view.png"));
		hoseImg = ImageIO.read(new File("WaterHose side view.png"));
		
		currentGun = pistolImg;
		gunSize = 350;
		
	}
	
	private void mechanics() {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);

	}
	
	public void drawGraphics() throws IOException {
		synchronized (gc) {
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();
			
			//background
			gc.drawImage(background, 0, 0, GRWIDTH, GRHEIGHT);
			
			//the gun
			gc.drawImage(currentGun, GRWIDTH / 7, GRHEIGHT / 10, 
					(int)(gunSize * 1.777777777778), gunSize);
			
			//buy/equip/equipped button
			gc.drawImage(buyImg, 100, 500, GRWIDTH / 5, GRHEIGHT / 5);
			
			//pg up & pg down
			gc.drawImage(pgUpImg, 0 + cursor.width, 0 + cursor.width, GRHEIGHT / 8, GRHEIGHT / 8);
			gc.drawImage(pgDownImg, 0 + cursor.width, GRHEIGHT - GRHEIGHT / 8 - cursor.width, GRHEIGHT / 8, GRHEIGHT / 8);
			
			//cursor
			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);

			
		}

	}
	
}
