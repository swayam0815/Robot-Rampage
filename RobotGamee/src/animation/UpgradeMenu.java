// Author: Swayam and Aryan
// Creation Date: December 2022
// description: upgrading page of gui
// Last Modified: January 26 2022

package animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
// = new GraphicsConsole(GRWIDTH, GRHEIGHT);
// rectangles
	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100);
	private static Rectangle pgDown = new Rectangle((int) (GRWIDTH / 1.29), (int) (GRHEIGHT / 1.31), GRWIDTH / 11,
			GRWIDTH / 11);
	private static Rectangle pgUp = new Rectangle((int) (GRWIDTH / 1.29), GRHEIGHT / 13, GRWIDTH / 11, GRWIDTH / 11);
	private static Rectangle equippedBTN = new Rectangle(GRWIDTH / 5, (int) (GRWIDTH / 2.6), (int) (GRWIDTH / 4.125),
			GRHEIGHT / 7);
	private static Rectangle buyBTN = new Rectangle(GRWIDTH / 5, (int) (GRWIDTH / 2.6), (int) (GRWIDTH / 4.125), // button
																													// for
																													// buying
																													// guns
			GRHEIGHT / 7);
	private static Rectangle equipBTN = new Rectangle(GRWIDTH / 5, (int) (GRWIDTH / 2.6), 0, 0); // button for equipping
	private static Rectangle backBTN = new Rectangle(GRWIDTH / 54, (int) (GRHEIGHT / 1.09), GRWIDTH / 8, GRHEIGHT / 14);// button
																														// for
																														// going
																														// back

	private static Rectangle damageBTN = new Rectangle((int) (GRWIDTH / 1.335), (int) (GRHEIGHT / 2.45), // button that
																											// increases
																											// damage of
																											// gun
			(int) (GRHEIGHT / 16.25), (int) (GRHEIGHT / 16.25));
	private static Rectangle magazineBTN = new Rectangle((int) (GRWIDTH / 1.335), (int) (GRHEIGHT / 2.06), // button
																											// that
																											// increases
																											// magazine
																											// size
			(int) (GRHEIGHT / 16.25), (int) (GRHEIGHT / 16.25));
	private static Rectangle reloadTimeBTN = new Rectangle((int) (GRWIDTH / 1.335), (int) (GRHEIGHT / 1.78), // button
																												// that
																												// decreases
																												// reload
																												// time
			(int) (GRHEIGHT / 16.25), (int) (GRHEIGHT / 16.25));
	private static Rectangle fireRateBTN = new Rectangle((int) (GRWIDTH / 1.335), (int) (GRHEIGHT / 1.53), // button
																											// that
																											// increases
																											// fire rate
			(int) (GRHEIGHT / 16.25), (int) (GRHEIGHT / 16.25));

	// sound effects
	private static Clip buttonSound;
	private static AudioInputStream buttonEffect;

// pictures that will show on screen
	private Image background;
	private Image buyImg;
	private Image equipImg;
	private Image equippedImg;
	private Image pgUpImg;
	private Image pgDownImg;
	private Image back;
	private Image damageImg;
	private Image magazineImg;
	private Image reloadTimeImg;
	private Image fireRateImg;
	private Image cursorImg;
	private Image cursorClicked;
	private Image locked;
	private Image roboPartsImg;
	private Image damageNameImg;
	private Image magazineNameImg;
	private Image reloadTimeNameImg;
	private Image fireRateNameImg;

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
	private Image addLight;
	private Image addDark;

	private static Gun currentGun; // the gun that will be shown on screen
	private static Gun equippedGun; // the gun that is equipped by the player
// variables
	private int gunSize = (int) (GRHEIGHT / 3.61111111111111); // the size of the gun picture
	private int gunNum = 0; // represents the gun currently being shown

// le guns
	private static Gun[] guns;

//	the font for screen
	Font moneyLeftFont = new Font("Elephant", Font.ITALIC, GRWIDTH / 40); // font for money at the top
	private static boolean running;

	public UpgradeMenu(GraphicsConsole gc, Gun[] guns)
			throws IOException, LineUnavailableException, UnsupportedAudioFileException {
//		setting the initial values
		UpgradeMenu.gc = gc;
		running = true;
		gunNum = 0;
		UpgradeMenu.guns = guns;
		currentGun = guns[0];

		init();
	}

	/**
	 * sets the initial values and starts the main loop
	 */
	public void init() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		setValues();
		gc.setFont(moneyLeftFont);

		while (running) {
			mechanics();
			drawGraphics();
			gc.sleep(1);
		}
	}

	/**
	 * @return the gun that's equipped
	 */
	public static Gun getGun() {
		return equippedGun;
	}

	/**
	 * sets the initial values for pics, variables, ...
	 */
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
		addLight = ImageIO.read(new File("light add button.png"));
		addDark = ImageIO.read(new File("dark add button.png"));
		locked = ImageIO.read(new File("Locked Gun.png"));
		roboPartsImg = ImageIO.read(new File("Robot parts.png"));
		damageNameImg = ImageIO.read(new File("damage.png"));
		magazineNameImg = ImageIO.read(new File("magazine size.png"));
		reloadTimeNameImg = ImageIO.read(new File("reload speed.png"));
		fireRateNameImg = ImageIO.read(new File("fire rate.png"));

		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));

	}

	/**
	 * @return an array of guns
	 */
	public static Gun[] getGuns() {
		return guns;
	}

	/**
	 * @param the button you need to show
	 */
	private void showButton(Rectangle button) {
		button.width = (int) (GRWIDTH / 4.125);
		button.height = GRHEIGHT / 7;
		button.x = GRWIDTH / 5;
		button.y = (int) (GRWIDTH / 2.6);
	}

	/**
	 * @param the button you need to hide
	 */
	private void hideButton(Rectangle button) {
		button.width = button.height = 0;
		button.x = button.y = -GRHEIGHT;
	}

	/**
	 * the physics of the game
	 */
	private void mechanics() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		cursor.x = gc.getMouseX() - (cursor.width / 2);
		cursor.y = gc.getMouseY() - (cursor.height / 2);
// represents the gun currently selected
		currentGun = guns[gunNum];

// buttons light up when hovered over
		if (cursor.intersects(buyBTN)) { // button for buying
			buyImg = buyLight;
			if (gc.getMouseClick() > 0 && setInitialValues.getMoney() >= currentGun.getPrice()) {
				// button sound
				buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
				buttonSound = AudioSystem.getClip();
				buttonSound.open(buttonEffect);
				buttonSound.start();
				setInitialValues.setMoney(setInitialValues.getMoney() - currentGun.getPrice());
				currentGun.setBought(true);
			}
		} else
			buyImg = buyDark;
		if (cursor.intersects(equipBTN)) { // button for equipping
			equipImg = equipLight;
			if (gc.getMouseClick() > 0) {
				// button sound
				buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
				buttonSound = AudioSystem.getClip();
				buttonSound.open(buttonEffect);
				buttonSound.start();
				for (int i = 0; i < guns.length; i++) {
					guns[i].setEquipped(false);
				}
				currentGun.setEquipped(true);
				equippedGun = currentGun;
			}
		} else
			equipImg = equipDark;
		if (cursor.intersects(pgDown)) { // button for moving down a gun
			pgDownImg = pgDownLight;
			if (gc.getMouseClick() > 0) {
				// button sound
				buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
				buttonSound = AudioSystem.getClip();
				buttonSound.open(buttonEffect);
				buttonSound.start();
				if (gunNum - 1 >= 0)
					gunNum--;
				else
					gunNum = guns.length - 1;
			}
		} else
			pgDownImg = pgDownDark;
		if (cursor.intersects(pgUp)) { // button for moving up a gun
			pgUpImg = pgUpLight;
			if (gc.getMouseClick() > 0) {
				// button sound
				buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
				buttonSound = AudioSystem.getClip();
				buttonSound.open(buttonEffect);
				buttonSound.start();
				if (gunNum + 1 < guns.length) {
					gunNum++;
				} else
					gunNum = 0;
			}
		} else
			pgUpImg = pgUpDark;
		if (cursor.intersects(backBTN)) { // button for going to the previous screen
			back = backLight;
			if (gc.getMouseClick() > 0) {
				// button sound
				buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
				buttonSound = AudioSystem.getClip();
				buttonSound.open(buttonEffect);
				buttonSound.start();
				running = false;
				new Start(gc, guns);
			}
		} else
			back = backDark;

// add buttons (works only if gun has been bought)
		if (currentGun.isBought()) {
			if (cursor.intersects(damageBTN)) { // button for upgrading damage
				damageImg = addLight;
				if (gc.getMouseClick() > 0) {
					if (setInitialValues.getMoney() >= currentGun.getUpgradePrice()) {
						// button sound
						buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
						buttonSound = AudioSystem.getClip();
						buttonSound.open(buttonEffect);
						buttonSound.start();
						currentGun.setDamage(currentGun.getDamage() + 1);
						setInitialValues.setMoney(setInitialValues.getMoney() - currentGun.getUpgradePrice());
						currentGun.setUpgradePrice(currentGun.getUpgradePrice() + 100);
					}
				}
			} else
				damageImg = addDark;

			if (cursor.intersects(magazineBTN)) { // button for upgrading magazine size
				magazineImg = addLight;
				if (gc.getMouseClick() > 0) {
					if (setInitialValues.getMoney() >= currentGun.getUpgradePrice()) {
						// button sound
						buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
						buttonSound = AudioSystem.getClip();
						buttonSound.open(buttonEffect);
						buttonSound.start();
						currentGun.setMagazineSize(currentGun.getMagazineSize() + 1);
						setInitialValues.setMoney(setInitialValues.getMoney() - currentGun.getUpgradePrice());
						currentGun.setUpgradePrice(currentGun.getUpgradePrice() + 100);
					}
				}
			} else
				magazineImg = addDark;

			if (cursor.intersects(reloadTimeBTN) && currentGun.getReloadTime() > 20) { // button for upgrading reload
																						// time
				reloadTimeImg = addLight;
				if (gc.getMouseClick() > 0) {
					if (setInitialValues.getMoney() >= currentGun.getUpgradePrice()) {
						// button sound
						buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
						buttonSound = AudioSystem.getClip();
						buttonSound.open(buttonEffect);
						buttonSound.start();
						currentGun.setReloadTime(currentGun.getReloadTime() - 5);
						setInitialValues.setMoney(setInitialValues.getMoney() - currentGun.getUpgradePrice());
						currentGun.setUpgradePrice(currentGun.getUpgradePrice() + 100);
					}
				}
			} else
				reloadTimeImg = addDark;

			if (cursor.intersects(fireRateBTN) && currentGun.getFireRate() > 1) { // button for upgrading fire rate
				fireRateImg = addLight;
				if (gc.getMouseClick() > 0) {
					if (setInitialValues.getMoney() >= currentGun.getUpgradePrice()) {
						// button sound
						buttonEffect = AudioSystem.getAudioInputStream(new File("Button Sound.wav").getAbsoluteFile());
						buttonSound = AudioSystem.getClip();
						buttonSound.open(buttonEffect);
						buttonSound.start();
						currentGun.setFireRate(currentGun.getFireRate() - 1);
						setInitialValues.setMoney(setInitialValues.getMoney() - currentGun.getUpgradePrice());
						currentGun.setUpgradePrice(currentGun.getUpgradePrice() + 100);
					}
				}
			} else
				fireRateImg = addDark;

		}

		gc.getMouseClick(); // this fixes the glitch for scrolling

// choosing which button to show for equip & ...
		if (!currentGun.isBought()) {
			showButton(buyBTN);
			hideButton(equipBTN);
		} else {
			if (currentGun.isEquipped()) {
				hideButton(buyBTN);
				hideButton(equipBTN);
			} else {
				hideButton(buyBTN);
				showButton(equipBTN);
			}
		}

	}

	/**
	 * draws everything on screen
	 */
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
			if (!currentGun.isBought())
				gc.drawImage(locked, (int) (GRWIDTH / 5.5), (int) (GRHEIGHT / 3.170731707317073),
						(int) (gunSize * 1.777777777778), gunSize);

// pg up & pg down
			gc.drawImage(pgUpImg, pgUp);
			gc.drawImage(pgDownImg, pgDown);

// back button
			gc.drawImage(back, backBTN);

// money left for player
			gc.drawImage(roboPartsImg, GRWIDTH / 20, GRHEIGHT / 50, GRWIDTH / 16, GRWIDTH / 16);
			gc.setColor(new Color(0, 200, 20));
			gc.drawString("" + setInitialValues.getMoney(), GRWIDTH / 8, GRHEIGHT / 11);

//price of the gun
			if (!currentGun.isBought()) {
				gc.drawImage(roboPartsImg, (int) (GRHEIGHT / 1.25), (int) (GRHEIGHT / 1.44444), GRHEIGHT / 10,
						GRHEIGHT / 10);
				gc.drawString(currentGun.getPrice() + "", (int) (GRWIDTH / 1.925), (int) (GRHEIGHT / 1.3));
			}

// add buttons (magazine, damage, ...)
			gc.drawImage(damageImg, damageBTN);
			gc.drawImage(magazineImg, magazineBTN);
			gc.drawImage(reloadTimeImg, reloadTimeBTN);
			gc.drawImage(fireRateImg, fireRateBTN);

			if (currentGun.isBought()) {
// robo parts before prices
				gc.drawImage(roboPartsImg, (int) (GRWIDTH / 1.27), (int) (GRHEIGHT / 2.452), (int) (GRHEIGHT / 16.25),
						(int) (GRHEIGHT / 16.25));
				gc.drawImage(roboPartsImg, (int) (GRWIDTH / 1.27), (int) (GRHEIGHT / 2.063), (int) (GRHEIGHT / 16.25),
						(int) (GRHEIGHT / 16.25));
				gc.drawImage(roboPartsImg, (int) (GRWIDTH / 1.27), (int) (GRHEIGHT / 1.78), (int) (GRHEIGHT / 16.25),
						(int) (GRHEIGHT / 16.25));
				gc.drawImage(roboPartsImg, (int) (GRWIDTH / 1.27), (int) (GRWIDTH / 2.783), (int) (GRHEIGHT / 16.25),
						(int) (GRHEIGHT / 16.25));

// upgrade prices
				gc.drawString(currentGun.getUpgradePrice() + "", (int) (GRWIDTH / 1.21), (int) (GRWIDTH / 3.915));
				gc.drawString(currentGun.getUpgradePrice() + "", (int) (GRWIDTH / 1.21), (int) (GRHEIGHT / 1.88));
				gc.drawString(currentGun.getUpgradePrice() + "", (int) (GRWIDTH / 1.21), (int) (GRWIDTH / 2.924));
				gc.drawString(currentGun.getUpgradePrice() + "", (int) (GRWIDTH / 1.21), (int) (GRHEIGHT / 1.46));
			}

// names for attributes
			gc.drawImage(damageNameImg, GRWIDTH / 2, (int) (GRWIDTH / 6.6), GRWIDTH / 10, GRWIDTH / 9);
			gc.drawImage(magazineNameImg, GRWIDTH / 2, (int) (GRWIDTH / 5.13333), GRWIDTH / 10, GRWIDTH / 9);
			gc.drawImage(reloadTimeNameImg, GRWIDTH / 2, (int) (GRWIDTH / 4.2), GRWIDTH / 10, GRWIDTH / 9);
			gc.drawImage(fireRateNameImg, GRWIDTH / 2, GRHEIGHT / 2, GRWIDTH / 10, GRWIDTH / 9);

// attributes of each gun
			gc.setColor(Color.RED);
			gc.drawString(currentGun.getDamage() + "", (int) (GRWIDTH / 1.55), (int) (GRWIDTH / 3.915));
			gc.drawString(currentGun.getMagazineSize() + "", (int) (GRWIDTH / 1.55), (int) (GRHEIGHT / 1.88));
			if (currentGun.getReloadTime() > 20)
				gc.drawString(currentGun.getReloadTime() + "", (int) (GRWIDTH / 1.55), (int) (GRWIDTH / 2.924));
			else
				gc.drawString(currentGun.getReloadTime() + " (Max)", (int) (GRWIDTH / 1.65), (int) (GRWIDTH / 2.924));
			if (currentGun.getFireRate() == 0)
				gc.drawString("Semi-Auto", (int) (GRWIDTH / 1.65), (int) (GRHEIGHT / 1.46));
			else if (currentGun.getFireRate() == 1)
				gc.drawString(10 / currentGun.getFireRate() + " (Max)", (int) (GRWIDTH / 1.65),
						(int) (GRHEIGHT / 1.46));
			else
				gc.drawString(10 / currentGun.getFireRate() + "", (int) (GRWIDTH / 1.55), (int) (GRHEIGHT / 1.46));

// cursor
			if (gc.getMouseButton(0))
				gc.drawImage(cursorClicked, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15,
						cursor.height * 15);
			else
				gc.drawImage(cursorImg, cursor.x, cursor.y - cursor.width * 2, cursor.width * 15, cursor.height * 15);
		}
	}
}
