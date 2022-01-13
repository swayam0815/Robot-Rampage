package animation;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.GraphicsConsole;

public class AnimationMain extends Rectangle {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	//	new AnimationMain();
	}

	/***** Global Variables ******/
	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
																					// size
	private static int GRHEIGHT; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH; // this sets the size of the grid to fit the screen
																	// size
	private static GraphicsConsole gc;

	// sound effects
	private static Clip gunshotSound;
	private static Clip dryFire;
	private static Clip shellFall;
	private static Clip gunCocking;
	private static AudioInputStream gunshotEffect;
	private static AudioInputStream dryFireEffect;
	private static AudioInputStream gunCockingEffect;
	private static AudioInputStream shellFallEffect;

	// pictures
	private Image backGround = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("bakground.jpg"));
	private Image forcefield = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("ForceField.png"));
	private Image bullet = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("bullet cartoon.png"));
	private Image crosshair = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("crosshair.png"));
	private Image reloadButton = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("reload button.png"));
	private Image bulletBottom = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Bullet Bottom.png"));
	private Image gunshotFire = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("gunshot fire.png"));

	// robot pictures
	private Image robo = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Thomas face.png"));

	// gun pictures
	private static Image pistolImg = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Pistol POV.png"));
	private static Image pistolFlipped = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Pistol POV flipped.png"));
	private static Image AR15Img = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("AR15 POV.png"));
	private static Image AR15Flipped = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("AR15 POV flipped.png"));
	private static Image sniperImg = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Sniper POV.png"));
	private static Image sniperFlipped = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Sniper POV flipped.png"));
	private static Image grenadeLauncherImg = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Grenade Launcher POV.png"));
	private static Image grenadeLauncherFlipped = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Grenade Launcher POV flipped.png"));
	private static Image hoseImg = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Water Hose POV.png"));
	private static Image hoseFlipped = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("Water Hose POV flipped.png"));

	private Rectangle CrossHair = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 10, GRHEIGHT / 10); // this is the
																											// crosshair
																											// to aim
																											// with
	private Rectangle ReloadButton = new Rectangle((GRWIDTH / 20) - (GRWIDTH / 50), GRHEIGHT - (GRWIDTH / 10),
			GRHEIGHT / 10, GRHEIGHT / 10); // this button reloads the gun

	private int bulletsLeft; // number of bullets left in the gun
	private int reload; // provides an angle for the arc around the reload button
	private boolean reloading; // checks if the gun is reloading
	private boolean canShoot; // checks if the player is able to shoot
	private boolean shotFired; // checks if the player has shot the gun
	private int fireCounter; // counter for the fire out of the gun
	private int moveX = 0; // the amount of movement for the player
	private static int forceStrength = 200; // health of the forcefield protecting the player

	private static boolean autoReload = true; // code for purchasing to be added later

	// enemy/robot variables
	private int x = 0;
	private int y = 0;
	private int size = 50;
	private static int counter = 0;

	// bullet attributes
	ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
	ArrayList<Rectangle> hit = new ArrayList<Rectangle>();
	private int bulletSize = 50;
	private int bulletSpeed = 0;

	// gun object for player guns
	// damage, reload time, bullet #, price, fire rate, pic, picFlipped
	private static Gun pistol = new Gun(10, 100, 7, 0, 2, pistolImg, pistolFlipped);
	private static Gun AR15 = new Gun(6, 200, 30, 1500, 1, AR15Img, AR15Flipped);
	private static Gun sniper = new Gun(30, 250, 10, 4000, 5, sniperImg, sniperFlipped);
//	private static Gun minigun = new Gun(3, 8, 400, 8500, 5);
	private static Gun grenadeLauncher = new Gun(50, 170, 5, 12000, 5, grenadeLauncherImg, grenadeLauncherFlipped);
	private static Gun hose = new Gun(2, 500, 1000, 20000, 5, hoseImg, hoseFlipped);

	private static Gun equippedGun = hose; // the gun being held by the player
	private Rectangle player = new Rectangle(0, 0, (int) (GRHEIGHT / 2 * 1.777777777777778), GRHEIGHT / 2);

	// THE ENEMIES
	private Rectangle enemy = new Rectangle(x, y, size, size);
	private static boolean defeat = false;

	private ArrayList<Rectangle> enemies = new ArrayList<Rectangle>();
	private static int wave = 0;
	private ArrayList<Rectangle> destroyedEnemies = new ArrayList<Rectangle>();
	private static boolean newWave = true;

	public AnimationMain(GraphicsConsole x, int GRWIDTH, int GRHEIGHT) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		gc = x;
		this.GRWIDTH = GRWIDTH;
		this.GRHEIGHT = GRHEIGHT;
		
		
		initiate();

		while (gc.getKeyCode() != 'Q') {

			mechanics();

			if (!defeat)
				enemyMechanics();

			drawGraphics();

			gc.sleep(1);
		}
	}

	private void initiate() {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the mouse
		gc.setFont(new Font("Georgia", Font.PLAIN, 50)); //

		// set the value for all variables
		bulletsLeft = equippedGun.getMagazineSize();
		reload = 0;
		reloading = false;
		canShoot = true;
		shotFired = false;
		fireCounter = 10;

	}

	private void mechanics() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// moving left and right
		if (gc.isKeyDown(37) || gc.isKeyDown(65))
			moveX -= 10;
		if (gc.isKeyDown(39) || gc.isKeyDown(100) || gc.isKeyDown(68))
			moveX += 10;

		// limits for player movement
		if (moveX > 0)
			moveX = 0;
		if (moveX < -(GRWIDTH + (GRWIDTH / 11)))
			moveX = -(GRWIDTH + (GRWIDTH / 11));
		
		if ((gc.getMouseClick() > 0 || gc.isKeyDown(32)) && bulletsLeft > 0) {
			bullets.add(new Rectangle(CrossHair.x + 15, CrossHair.y + 15, bulletSize, bulletSize));
			bulletsLeft--;
		}

		// gun reloads with the 'R' key OR by hovering over button
		if (gc.isKeyDown(82) || (!reloading && CrossHair.intersects(ReloadButton))
				|| (autoReload && bulletsLeft == 0)) {
			bulletsLeft = -1; // this makes the reload sound play ONLY once
			reloading = true;
			canShoot = false;

			// reload sound
			gunCockingEffect = AudioSystem.getAudioInputStream(new File("gun cocking.wav").getAbsoluteFile());
			gunCocking = AudioSystem.getClip();
			gunCocking.open(gunCockingEffect);
			gunCocking.start();
		}



		// the crosshair moves to the position of mouse
		CrossHair.x = gc.getMouseX() - (CrossHair.width / 2);
		CrossHair.y = gc.getMouseY() - (CrossHair.height / 2);

		// sets the coordinates for the moving hands
		player.x = GRWIDTH - (GRHEIGHT / 2) + (CrossHair.x / 5) + 10;
		player.y = GRHEIGHT - (GRHEIGHT / 2) + (CrossHair.y / 5) + 10;

	}

	/*
	 * Method Name: ranNum Author: Swayam Sachdeva Creation Date: November 29, 2021
	 * Modified Date: December 01 2021 Description: method to generate a random for
	 * a specific range Parameters: int highestNum and int highestNum Return Value:
	 * array Throws/Exceptions: NONE
	 */
	public static int ranNum(int max, int min) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	public void enemyMechanics() {

		if (counter % 10 == 0)
			enemies.add(new Rectangle(ranNum(1, GRWIDTH), 0, size, size));

		for (Rectangle rect : enemies) {

			if (rect.x + rect.width < player.x + moveX)
				rect.x += 2;

			if (rect.x > player.x + moveX + player.width)
				rect.x -= 2;

			if (counter % 2 == 0) {
				rect.y++;

			}

			if (counter % 5 == 0) {
				rect.width++;
				rect.height++;
			}
			// rect.height = rect.width = rect.y + size;
			// this is for robots to increase size when moving forward

			counter++;

			if (rect.y >= GRHEIGHT - (size + size / 2)) {
				rect.y = GRHEIGHT - (size + size / 2);
				forceStrength--;
			}

			if (rect.x < 0)
				rect.x = 1;
			if (rect.x + rect.width > GRWIDTH)
				rect.x = GRWIDTH - rect.width;
		}

	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();

			// background
			gc.drawImage(backGround, 0, 0, (int) (GRHEIGHT * 1.777777777778), GRHEIGHT);

			// drawing enemies
			for (Rectangle rect : enemies) {
				gc.drawImage(robo, rect);
			}

			// animating projectiles/bullets
			for (Rectangle rect : bullets) {
				gc.drawImage(bulletBottom, rect);

				// basically nerfing bullet speed by using this if statement
				// so bullet only moves every factor of FireRate

				if (bulletSpeed % equippedGun.getFireRate() == 0) {
					rect.y--;
					rect.width--;
					rect.height--;
				}
				if (rect.width <= 10 && rect.height <= 1)
					hit.add(rect);
				for (Rectangle enem : enemies) {

					// something for splash damage stuff
					/*
					 * if ((rect.x <= enem.x + (size / 5) || rect.x >= enem.x) && (rect.y <= enem.y
					 * + (size / 5) || rect.y >= enem.y)
					 * 
					 * ) { destroyedEnemies.add(enem); hit.add(rect); }
					 */

					if (rect.intersects(enem)) {

					if (rect.intersects(enem))
//(rect.x <= enem.x + (size / 5) || rect.x >= enem.x) && (rect.y <= enem.y + (size / 5) || rect.y >= enem.y)
						destroyedEnemies.add(enem);
						hit.add(rect);
					}

				}
			}
			bulletSpeed++; // incrementing counter for above statement

			// removing hit bullets from main bullets list
			bullets.removeAll(hit);
			enemies.removeAll(destroyedEnemies);

			// Forcefield
			gc.drawImage(forcefield, 0, 0, GRWIDTH, GRHEIGHT);

			// fire out of the gun
			if (shotFired || fireCounter < 3) {
				if (player.x + moveX > GRWIDTH / 3)
					gc.drawImage(gunshotFire, (int) (player.x - (GRHEIGHT / 5) * 1.85) + moveX, player.y - GRHEIGHT / 5,
							GRWIDTH / 10 * 6, GRHEIGHT / 10 * 7);
				else
					gc.drawImage(gunshotFire, (int) (player.x - (GRHEIGHT / 5) * 0.5) + moveX, player.y - GRHEIGHT / 5,
							GRWIDTH / 10 * 6, GRHEIGHT / 10 * 7);

				fireCounter++;
			}

			// crosshair
			gc.drawImage(crosshair, CrossHair.x, CrossHair.y, CrossHair.width, CrossHair.height);

			// pistol in hand
			if (CrossHair.x < player.x + moveX + (player.width / 2))
				gc.drawImage(equippedGun.getPic(), player.x + moveX, player.y, player.width, player.height);
			else
				gc.drawImage(equippedGun.getPicFlipped(), player.x + moveX, player.y, player.width, player.height);
//			player.x + moveX > GRWIDTH / 3	//previous code for flipping

			// reloading process
			if (reloading) {
				gc.setColor(Color.DARK_GRAY);
				gc.fillArc(ReloadButton.x - (ReloadButton.width / 6), ReloadButton.y - (ReloadButton.width / 6),
						ReloadButton.width * 8 / 6, ReloadButton.width * 8 / 6, 0, reload);
				reload += (int) (GRHEIGHT / equippedGun.getReloadTime());
				// the arc takes a full turn
				if (reload > 360) {
					reload = 0;
					bulletsLeft = equippedGun.getMagazineSize();
					reloading = false;
					canShoot = true;

				}
			}
			// reload button
			gc.drawImage(reloadButton, ReloadButton.x, ReloadButton.y, ReloadButton.width, ReloadButton.height);

			gc.drawString(String.valueOf(forceStrength), 500, 500);

			// bullets
			if (bulletsLeft < 13) {
				for (int b = 0; b < bulletsLeft; b++) {
					gc.drawImage(bullet, ReloadButton.width * 8 / 6 + (GRWIDTH / 20) + b * (ReloadButton.width / 3 + 1),
							GRHEIGHT - (GRWIDTH / 10), ReloadButton.width / 3,
							(int) (ReloadButton.width / 3 * (2.68421)));
				}
			} else {
				gc.drawImage(bullet, ReloadButton.width * 8 / 6 + (GRWIDTH / 20), GRHEIGHT - (GRWIDTH / 10),
						ReloadButton.width / 3, (int) (ReloadButton.width / 3 * (2.68421)));

				gc.setColor(Color.WHITE);
				gc.drawString("x" + bulletsLeft, ReloadButton.width * 8 / 6 + (GRWIDTH / 13),
						GRHEIGHT - (GRWIDTH / 16));
			}

			if (wave + 1 < 5)
				wave++;

		}
	}

}

