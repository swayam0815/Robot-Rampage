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
import java.awt.Font;
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
	private static Rectangle magazineBTN = new Rectangle(0, 0, 40, 40);
	private static Rectangle damageBTN = new Rectangle(0, 0, 40, 40);
	private static Rectangle fireRateBTN = new Rectangle(0, 0, 40, 40);
	private static Rectangle reloadTimeBTN = new Rectangle(0, 0, 40, 40);
	
	// pictures that will show on screen
	private Image background;
	private Image buyImg;
	private Image equipImg;
	private Image equippedImg;
	private Image pgUpImg;
	private Image pgDownImg;
	private Image back;
	private Image magazineImg;
	private Image cursorImg;
	private Image cursorClicked;
	private Image locked;
	private Image roboPartsImg;
	private Image damageImg;
	private Image magazineSizeImg;
	private Image reloadSpeedImg;
	private Image fireRateImg;
	
	// pictures that will replace them
	private Image buyLight;
	private Image buyDark;
	private Image equipLight;
	private Image equipDark;
	private Image pgUpLight;
	private Image pgUpDark;
	private Image pgDownLight;
	private Image pgDownDark;
	private Image backLight;
	private Image backDark;
	private Image magazineLight;
	private Image magazineDark;
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
	private static Gun currentGun;		//the gun that will be shown on screen
	private static Gun equippedGun;		//the gun that is equipped by the player
	// variables
	int gunSize = (int)(GRHEIGHT / 3.61111111111111);	//the size of the gun picture
	int gunNum = 0;		// represents the gun currently being shown
	int money = 30000;	//amount of money the player has
	
	Font moneyLeftFont = new Font("Serif", Font.PLAIN, GRWIDTH / 20);	//font for money at the top
	Font attributesFont = new Font("Serif", Font.BOLD, GRWIDTH / 40);	//font for money at the top
	public UpgradeMenu(GraphicsConsole gc) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.gc = gc;
		init();
	}
	
	public void init() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		Image loading = ImageIO.read(new File("loading.png"));
		gc.setBackgroundColor(loading, GRWIDTH, GRHEIGHT);
		gc.setFont(moneyLeftFont);
		setValues();
		while (gc.getKeyCode() != 'Q') {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}
	}
	
	public static Gun getGun() {
		return equippedGun;
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
		magazineLight = ImageIO.read(new File("light add button.png"));
		magazineDark = ImageIO.read(new File("dark add button.png"));
		locked = ImageIO.read(new File("Locked Gun.png"));
		roboPartsImg = ImageIO.read(new File("Robot parts.png"));
		damageImg = ImageIO.read(new File("damage.png"));
		magazineSizeImg = ImageIO.read(new File("magazine size.png"));
		reloadSpeedImg = ImageIO.read(new File("reload speed.png"));
		fireRateImg = ImageIO.read(new File("fire rate.png"));
		
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));
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
		// guns[3] = new Gun(3, 8, 400, 8500, 5, minigunImg, minigunFlipped, minigunSide,
		// false, false);
		// shotgun/grenade launcher
		guns[3] = new Gun(50, 170, 5, 12000, 5, grenadeLauncherImg, grenadeLauncherFlipped, grenadeLauncherSide, false,
				false);
		guns[4] = new Gun(2, 500, 1000, 20000, 5, hoseImg, hoseFlipped, hoseSide, false, false);
		
		equippedGun = guns[0];
	}
	
	private void showButton(Rectangle button) {
		button.width = (int) (GRWIDTH / 4.125);
		button.height = GRHEIGHT / 7;
		button.x = GRWIDTH / 5;
		button.y = (int) (GRWIDTH / 2.6);
	}
	
	private void hideButton(Rectangle button) {
		button.width = button.height = 0;
		button.x = button.y = -GRHEIGHT;
	}
	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);
		//represents the gun currently selected
		currentGun = guns[gunNum];
		
		// buttons light up when hovered over
		if (cursor.intersects(buyBTN)) {
			buyImg = buyLight;
			if (gc.getMouseClick() > 0 && money > currentGun.getPrice()) {
				money -= currentGun.getPrice();
				currentGun.setBought(true);
			}
		}
		else
			buyImg = buyDark;
		if (cursor.intersects(equipBTN)) {
			equipImg = equipLight;
			if (gc.getMouseClick() > 0) {
				for (int i = 0; i < guns.length; i++) {
					guns[i].setEquipped(false);
				}
				currentGun.setEquipped(true);
				equippedGun = currentGun;
				}
		}
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
			if (gc.getMouseClick() > 0)
				new Start(gc);
		} else
			back = backDark;
		
		
		//add buttons
		if (cursor.intersects(magazineBTN)) {
			magazineImg = magazineLight;
//			if (gc.getMouseClick() > 0)
				
		} else
			magazineImg = magazineDark;
		gc.getMouseClick(); // this fixes the glitch for scrolling
		//choosing which button to show for equip & ...
		if (!currentGun.getBought()) {
			showButton(buyBTN);
			hideButton(equipBTN);
		}
		else {
			if (currentGun.getEquipped()) {
				hideButton(buyBTN);
				hideButton(equipBTN);
			}
			else {
				hideButton(buyBTN);
				showButton(equipBTN);
			}
		}
		
		
	
	}
	public void drawGraphics() throws IOException {
		synchronized (gc) {
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();
			// background
			gc.drawImage(background, 0, 0, GRWIDTH, GRHEIGHT);
			// the gun
			gc.drawImage(currentGun.getPicSide(), (int) (GRWIDTH / 5.5), (int) (GRHEIGHT / 3.170731707317073),
					(int) (gunSize * 1.777777777778), gunSize);
			// buy/equip/equipped button
			gc.drawImage(equippedImg, equippedBTN);
			gc.drawImage(buyImg, buyBTN);
			gc.drawImage(equipImg, equipBTN);
			// lock on guns
			if (!currentGun.getBought())
			gc.drawImage(locked, (int) (GRWIDTH / 5.5), (int) (GRHEIGHT / 3.170731707317073),
					(int) (gunSize * 1.777777777778), gunSize);
			// pg up & pg down
			gc.drawImage(pgUpImg, pgUp);
			gc.drawImage(pgDownImg, pgDown);
			// back button
			gc.drawImage(back, backBTN);
			//money left for player
			gc.drawImage(roboPartsImg, GRWIDTH / 20, 0, GRWIDTH / 14, GRWIDTH / 14);
			gc.setColor(Color.RED);
			gc.drawString("" + money, GRWIDTH / 8, GRHEIGHT / 10);
			
			//add buttons (magazine, damage, ...)
			gc.drawImage(magazineImg, magazineBTN);
			
			//names for attributes
			gc.drawImage(damageImg, GRWIDTH / 2, 175, GRWIDTH / 10, GRWIDTH / 9);
			gc.drawImage(magazineSizeImg, GRWIDTH / 2, 225, GRWIDTH / 10, GRWIDTH / 9);
			gc.drawImage(reloadSpeedImg, GRWIDTH / 2, 275, GRWIDTH / 10, GRWIDTH / 9);
			gc.drawImage(fireRateImg, GRWIDTH / 2, GRHEIGHT / 2, GRWIDTH / 10, GRWIDTH / 9);
			
			//rectangles for attributes
			gc.setStroke(GRHEIGHT / 100);
			for (int i = 0; i < 5; i++) {
				gc.setColor(Color.RED);
				gc.fillRect(700 + (i * 50), 300, 50, 20);
				gc.fillRect(700 + (i * 50), GRHEIGHT / 2, 50, 20);
				gc.setColor(Color.BLACK);
				gc.drawRect(700 + (i * 50), 300, 50, 20);
				gc.drawRect(700 + (i * 50), GRHEIGHT / 2, 50, 20);
			}

			// cursor
			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15,
						cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
		}
	}
}