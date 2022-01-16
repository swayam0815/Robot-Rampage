package animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.Image;
import java.awt.Rectangle;

import hsa2.GraphicsConsole;

public class UpgradeMenu {

	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
	}

	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen

	private static GraphicsConsole gc;
	//= new GraphicsConsole(GRWIDTH, GRHEIGHT);

	// rectangles
	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100);
	private static Rectangle pgDown = new Rectangle((int) (GRWIDTH / 1.29), (int) (GRHEIGHT / 1.31), GRWIDTH / 11,
			GRWIDTH / 11);
	private static Rectangle pgUp = new Rectangle((int) (GRWIDTH / 1.29), GRHEIGHT / 13, GRWIDTH / 11, GRWIDTH / 11);
	private static Rectangle equippedBTN = new Rectangle(GRWIDTH / 5, (int) (GRWIDTH / 2.6), (int) (GRWIDTH / 4.125),
			GRHEIGHT / 7);
	private static Rectangle buyBTN = new Rectangle(GRWIDTH / 5, (int) (GRWIDTH / 2.6), (int) (GRWIDTH / 4.125),
			GRHEIGHT / 7);
	private static Rectangle equipBTN = new Rectangle(GRWIDTH / 5, (int) (GRWIDTH / 2.6), 0, 0);
	private static Rectangle backBTN = new Rectangle(GRWIDTH / 54, (int) (GRHEIGHT / 1.09), GRWIDTH / 8, GRHEIGHT / 14);

	// pictures that will show on screen
	private Image background;
	private Image buyImg;
	private Image equipImg;
	private Image equippedImg;
	private Image pgUpImg;
	private Image pgDownImg;
	private Image back;
	private Image cursorImg;
	private Image cursorClicked;
	private Image locked;

	// pictures that will replace them
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

	// gun pictures
	private static Image pistolImg; // "Pistol POV.png"
	private static Image pistolFlipped; // "Pistol POV flipped.png"
	private static Image pistolSide; // "Pistol POV flipped.png"
	private static Image AR15Img; // "AR15 POV.png"
	private static Image AR15Flipped; // "AR15 POV flipped.png"
	private static Image AR15Side; // "AR15 POV flipped.png"
	private static Image sniperImg; // "Sniper POV.png"
	private static Image sniperFlipped; // "Sniper POV flipped.png"
	private static Image sniperSide; // "Sniper POV flipped.png"
	private static Image minigunImg; // "Sniper POV.png"
	private static Image minigunFlipped; // "Sniper POV flipped.png"
	private static Image minigunSide; // "Sniper POV flipped.png"
	private static Image grenadeLauncherImg; // "Grenade Launcher POV.png"
	private static Image grenadeLauncherFlipped;// "Grenade Launcher POV flipped.png"
	private static Image grenadeLauncherSide;// "Grenade Launcher POV flipped.png"
	private static Image hoseImg; // "Water Hose POV.png"
	private static Image hoseFlipped; // "Water Hose POV flipped.png"
	private static Image hoseSide; // "Water Hose POV flipped.png"

	private static Gun[] guns = new Gun[5];

	private Image currentGun;

	// variables
	int gunSize = (int) (GRHEIGHT / 3.61111111111111);
	int gunNum = 0; // represents the gun currently being shown

	public UpgradeMenu(GraphicsConsole gc) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.gc = gc;
		init();
	}
	
	public void init() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		Image loading = ImageIO.read(new File("loading.png"));
		gc.setBackgroundColor(loading);

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

		// pictures
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
		locked = ImageIO.read(new File("Locked Gun.png"));
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));

		// gun pictures
		// gun pictures
		pistolImg = ImageIO.read(new File("Pistol POV.png"));
		pistolFlipped = ImageIO.read(new File("Pistol POV flipped.png"));
		pistolSide = ImageIO.read(new File("Pistol side view.png"));
		AR15Img = ImageIO.read(new File("AR15 POV.png"));
		AR15Flipped = ImageIO.read(new File("AR15 POV flipped.png"));
		AR15Side = ImageIO.read(new File("AR15 side view.png"));
		sniperImg = ImageIO.read(new File("Sniper POV.png"));
		sniperFlipped = ImageIO.read(new File("Sniper POV flipped.png"));
		sniperSide = ImageIO.read(new File("Sniper side view.png"));
		grenadeLauncherImg = ImageIO.read(new File("Grenade Launcher POV.png"));
		grenadeLauncherFlipped = ImageIO.read(new File("Grenade Launcher POV flipped.png"));
		grenadeLauncherSide = ImageIO.read(new File("GrenadeLauncher side view.png"));
		hoseImg = ImageIO.read(new File("Water Hose POV.png"));
		hoseFlipped = ImageIO.read(new File("Water Hose POV flipped.png"));
		hoseSide = ImageIO.read(new File("WaterHose side view.png"));

//				minigunSide = ImageIO.read(new File("Minigun side view.png"));

		// gun objects
		// damage, reload time, bullet #, price, fire rate, pic, picFlipped
		// pistol
		guns[0] = new Gun(10, 100, 7, 0, 2, pistolImg, pistolFlipped, pistolSide, true, true);
		// ar15
		guns[1] = new Gun(6, 200, 30, 1500, 1, AR15Img, AR15Flipped, AR15Side, false, false);
		// sniper
		guns[2] = new Gun(30, 250, 10, 4000, 5, sniperImg, sniperFlipped, sniperSide, false, false);
		// minigun
		// guns[] = new Gun(3, 8, 400, 8500, 5, minigunImg, minigunFlipped, minigunSide,
		// false, false);
		// shotgun/grenade launcher
		guns[3] = new Gun(50, 170, 5, 12000, 5, grenadeLauncherImg, grenadeLauncherFlipped, grenadeLauncherSide, false,
				false);
		guns[4] = new Gun(2, 500, 1000, 20000, 5, hoseImg, hoseFlipped, hoseSide, false, false);

	}

	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);

		// buttons light up when hovered over
		if (cursor.intersects(buyBTN)) {
			buyImg = buyLight;
//					if (gc.getMouseButton(0)) {
//						new AnimationMain(gc);
//					}
		} else
			buyImg = buyDark;

		if (cursor.intersects(equipBTN))
			equipImg = equipLight;
		else
			equipImg = equipDark;

		if (cursor.intersects(pgDown)) {
			pgDownImg = pgDownLight;
			if (gc.getMouseClick() > 0)
				if (gunNum - 1 >= 0)
					gunNum--;
				else
					gunNum = guns.length - 1;
		} else
			pgDownImg = pgDownDark;

		if (cursor.intersects(pgUp)) {
			pgUpImg = pgUpLight;
			if (gc.getMouseClick() > 0)
				if (gunNum + 1 < guns.length) {
					gunNum++;
				} else
					gunNum = 0;
		} else
			pgUpImg = pgUpDark;

		if (cursor.intersects(backBTN)) {
			back = backLight;
			if (cursor.intersects(backBTN) && gc.getMouseClick() > 0)
				new Start(gc);
		} else
			back = backDark;

		gc.getMouseClick(); // this fixes the glitch for scrolling

		switch (gunNum) {

		case 0:
			currentGun = guns[0].getPicSide();
			break;

		case 1:
			currentGun = guns[1].getPicSide();
			break;

		case 2:
			currentGun = guns[2].getPicSide();
			break;

		case 3:
			currentGun = guns[3].getPicSide();
			break;

		case 4:
			currentGun = guns[4].getPicSide();
			break;

		case 5:
			currentGun = guns[5].getPicSide();
			break;
		}

	}

	public void drawGraphics() throws IOException {
		synchronized (gc) {
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();

			// background
			gc.drawImage(background, 0, 0, GRWIDTH, GRHEIGHT);

			// the gun
			gc.drawImage(currentGun, (int) (GRWIDTH / 5.5), (int) (GRHEIGHT / 3.170731707317073),
					(int) (gunSize * 1.777777777778), gunSize);

			// buy/equip/equipped button
			gc.drawImage(equippedImg, equippedBTN);
			gc.drawImage(buyImg, buyBTN);
			gc.drawImage(equipImg, equipBTN);

			// lock on guns
			gc.drawImage(locked, (int) (GRWIDTH / 5.5), (int) (GRHEIGHT / 3.170731707317073),
					(int) (gunSize * 1.777777777778), gunSize);

			// pg up & pg down
			gc.drawImage(pgUpImg, pgUp);
			gc.drawImage(pgDownImg, pgDown);

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
