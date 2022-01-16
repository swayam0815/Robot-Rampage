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
	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100);
	private static Rectangle pgDown = new Rectangle((int)(GRWIDTH / 1.29), (int)(GRHEIGHT / 1.31), GRWIDTH / 11, GRWIDTH / 11);
	private static Rectangle pgUp = new Rectangle((int)(GRWIDTH / 1.29), GRHEIGHT / 13, GRWIDTH / 11, GRWIDTH / 11);
	private static Rectangle buyBTN = new Rectangle(GRWIDTH / 5, (int)(GRWIDTH / 2.6), (int)(GRWIDTH / 4.125), GRHEIGHT / 7);
	private static Rectangle equipBTN = new Rectangle(GRWIDTH / 5, (int)(GRWIDTH / 2.6), 0 ,0);
	private static Rectangle equippedBTN = new Rectangle(GRWIDTH / 5, (int)(GRWIDTH / 2.6), 0, 0);
	private static Rectangle backBTN = new Rectangle(GRWIDTH / 54, (int)(GRHEIGHT / 1.09), GRWIDTH / 8, GRHEIGHT / 14);

	//pictures that will show on screen
	private Image background;
	private Image buyImg;
	private Image equipImg;
	private Image equippedImg;
	private Image pgUpImg;
	private Image pgDownImg;
	private Image back;
	private static Image cursorImg;
	private static Image cursorClicked;
	
	//pictures that will replace them
	private Image buyLight;
	private Image buyDark;
	private Image equipLight;
	private Image equipDark;
	private Image equippedLight;
	private Image equippedDark;
	private Image pgUpLight;
	private Image pgUpDark;
	private Image pgDownLight;
	private Image pgDownDark;
	private Image backLight;
	private Image backDark;
	
	
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
		background = ImageIO.read(new File("UpgradesMenu.jpg"));
		buyLight = ImageIO.read(new File("lightBuy.png"));
		buyDark = ImageIO.read(new File("buy button.png"));
		equipLight = ImageIO.read(new File("lightEquip.png"));
		equipDark = ImageIO.read(new File("darkEquip.png"));
		equippedImg = ImageIO.read(new File("equipped.png"));
		pgUpLight = ImageIO.read(new File("upLight.png"));
		pgUpDark = ImageIO.read(new File("upArrow.png"));
		pgDownLight = ImageIO.read(new File("lightDown.png"));
		pgDownDark = ImageIO.read(new File("downArrow.png"));
		backLight = ImageIO.read(new File("lightBack.png"));
		backDark = ImageIO.read(new File("darkBack.png"));
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

		//buttons light up when hovered over
				if (cursor.intersects(buyBTN)) {
					buyImg = buyLight;
//					if (cursor.intersects(buyBTN) && gc.getMouseButton(0)) {
//						new AnimationMain(gc);
//					}
				} else
					buyImg = buyDark;

				if (cursor.intersects(equipBTN))
					equipImg = equipLight;
				else
					equipImg = equipDark;

				if (cursor.intersects(pgDown))
					pgDownImg = pgDownLight;
				//gunNum--;
				else
					pgDownImg = pgDownDark;
				
				if (cursor.intersects(pgUp))
					pgUpImg = pgUpLight;
				//gunNum++;
				//currentGun = pistolImg (gunNum == 1), AR15Img (gunNum == 2), ...
				else
					pgUpImg = pgUpDark;
				
				if (cursor.intersects(backBTN))
					back = backLight;
				else
					back = backDark;

	}
	
	public void drawGraphics() throws IOException {
		synchronized (gc) {
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();
			
			//background
			gc.drawImage(background, 0, 0, GRWIDTH, GRHEIGHT);
			
			//the gun
//			gc.drawImage(currentGun, GRWIDTH / 7, GRHEIGHT / 10, 
//					(int)(gunSize * 1.777777777778), gunSize);
			
			//buy/equip/equipped button
			gc.drawImage(buyImg, buyBTN);
			gc.drawImage(equipImg, equipBTN);
			gc.drawImage(equippedImg, equippedBTN);
			
			//pg up & pg down
			gc.drawImage(pgUpImg, pgUp);
			gc.drawImage(pgDownImg, pgDown);
			
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
