package animation;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import hsa2.GraphicsConsole;

public class AnimationMain extends Rectangle {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	}

	/***** Global Variables ******/
	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit
// the screen

	private static GraphicsConsole gc;
	// = new GraphicsConsole(GRWIDTH, GRHEIGHT);

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
	private Image backGround; // "bakground.jpg"
	private Image forcefield; // "ForceField.png"
	private Image bullet; // "bullet cartoon.png"
	private Image crosshair; // "crosshair.png"
	private Image reloadButton; // "reload button.png"
	private Image bulletBottom; // "Bullet Bottom.png"
	private Image gunshotFire; // "gunshot fire.png"
	private Image back;
	private static Image backLight;
	private static Image backDark;
	private static Image cursorImg;
	private static Image cursorClicked;

// robot pictures
	private Image robo; // "Thomas face.png"

	private Rectangle CrossHair; // to aim
// with
	private Rectangle ReloadButton = new Rectangle((GRWIDTH / 20) - (GRWIDTH / 50), GRHEIGHT - (GRWIDTH / 10),
			GRHEIGHT / 10, GRHEIGHT / 10); // this button reloads the gun

	private int shotCounter = 0; // crucial for fireRate
	private int bulletsLeft; // number of bullets left in the gun
	private int reload; // provides an angle for the arc around the reload button
	private boolean reloading; // checks if the gun is reloading
	private boolean canShoot; // checks if the player is able to shoot
	private boolean shotFired; // checks if the player has shot the gun
	private int fireCounter; // counter for the fire out of the gun
	private int moveX = 0; // the amount of movement for the player
	private static int forceStrength = 200; // health of the forcefield protecting the player

	private static boolean autoReload = true; // code for purchasing to be added later

	private int size = 50;
	private static int counter = 0;
	private static int gunNum = 0;
// bullet attributes
	ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
	ArrayList<Rectangle> hit = new ArrayList<Rectangle>();
	private int bulletSize = 50;
	private int bulletSpeed = 0;

	private static Rectangle backBTN = new Rectangle(GRWIDTH / 54, (int) (GRHEIGHT / 1.09), GRWIDTH / 8, GRHEIGHT / 14);
	private static Rectangle cursor = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 100, GRHEIGHT / 100);

// gun object for player guns
// damage, reload time, bullet #, price, fire rate, pic, picFlipped

	private static Gun equippedGun; // the gun being held by the player
	private Rectangle player = new Rectangle(0, 0, (int) (GRHEIGHT / 2 * 1.777777777777778), GRHEIGHT / 2);

// THE ENEMIES
	private static boolean defeat = false;

	private ArrayList<Rectangle> enemies = new ArrayList<Rectangle>();
	private static int wave = 1;
	private ArrayList<Rectangle> destroyedEnemies = new ArrayList<Rectangle>();
	private static Image AR15Img; // "AR15 POV.png"
	private static Image AR15Flipped; // "AR15 POV flipped.png"
	private static Image AR15Side; // "AR15 POV flipped.png"

	private static boolean running = true;

	private static boolean newWave = true;
	private final int small = 1;
	private final int big = 2;
	private final int lar = 6;
	private static int robotCounter = 0;
	private static int numRobots = 100;
	private static int totalWaves;

	private static Gun[] guns = new Gun[5];

// delete later
	private static int[] upgrades = { 0, 0, 0, 0 };

// gun pictures
	private static Image pistolImg; // "Pistol POV.png"
	private static Image pistolFlipped; // "Pistol POV flipped.png"
	private static Image pistolSide; // "Pistol POV flipped.png"
	private static Image normalBullet;
	private static Image normalBulletBottom;
	private static boolean win;

	public static void getImg() throws IOException {
		normalBullet = ImageIO.read(new File("bullet cartoon.png"));
		pistolImg = ImageIO.read(new File("Pistol POV.png"));
		pistolFlipped = ImageIO.read(new File("Pistol POV flipped.png"));
		pistolSide = ImageIO.read(new File("Pistol side view.png"));
		normalBulletBottom = ImageIO.read(new File("Bullet Bottom.png"));

		backLight = ImageIO.read(new File("lightBack.png"));
		backDark = ImageIO.read(new File("darkBack.png"));
		cursorImg = ImageIO.read(new File("cursor.png"));
		cursorClicked = ImageIO.read(new File("cursor clicked.png"));

	}

	public AnimationMain(GraphicsConsole x, int totalWaves)
			throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		running = true;
		gc = x;
		getimg();

		// setting up variables
		// more
		wave = 1;
		defeat = false;
		win = false;
		forceStrength = 200;
		this.totalWaves = totalWaves;

		equippedGun = UpgradeMenu.getGun();
		initiate();

		while (wave + 1 <= totalWaves && forceStrength > 0) {

			gc.drawString(String.valueOf(enemies.size()), 250, 250);

			gc.drawString(String.valueOf(wave), 400, 500);

			mechanics();

			enemyMechanics();

			drawGraphics();

			gc.sleep(1);

		}

		gc.sleep(100);

		// lose if forcefield dies, win if robots die
		if (forceStrength <= 0)
			new Mission(gc, false, guns);
		else if (forceStrength > 0)
			new Mission(gc, true, guns);

	}

	private void getimg() throws IOException {
		AR15Img = ImageIO.read(new File("AR15 POV.png"));
		AR15Flipped = ImageIO.read(new File("AR15 POV flipped.png"));
		AR15Side = ImageIO.read(new File("AR15 side view.png"));
	}

	private void initiate() throws IOException {
		guns = UpgradeMenu.getGuns();
		backGround = ImageIO.read(new File("bakground.png"));
		forcefield = ImageIO.read(new File(("ForceField.png")));
		bullet = ImageIO.read(new File(("bullet cartoon.png")));
		crosshair = ImageIO.read(new File("crosshair.png"));
		reloadButton = ImageIO.read(new File("reload button.png"));
		bulletBottom = ImageIO.read(new File("Bullet Bottom.png"));
		gunshotFire = ImageIO.read(new File("gunshot fire.png"));

// robot pictures
		robo = ImageIO.read(new File("tinyRobot stand.png"));

		CrossHair = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 10, GRHEIGHT / 10);

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

	private static int ranNum(int low, int high) {
		return (int) Math.floor(Math.random() * high + low);
	}

	private void mechanics() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
// moving left and right

		if (gc.isKeyDown(69)) {

			if (gunNum + 1 < guns.length) {
				gunNum++;
				equippedGun = guns[gunNum];
			} else
				gunNum = 0;
		}

		if (gc.isKeyDown(37) || gc.isKeyDown(65))
			moveX -= 10;
		if (gc.isKeyDown(39) || gc.isKeyDown(100) || gc.isKeyDown(68))
			moveX += 10;

// limits for player movement
		if (moveX > 0)
			moveX = 0;
		if (moveX < -(GRWIDTH + (GRWIDTH / 11)))
			moveX = -(GRWIDTH + (GRWIDTH / 11));

// shooting the gun
		if (equippedGun.getFireRate() == 0) { // if gun is semi-auto
			if (gc.getMouseClick() > 0 && bulletsLeft > 0) {
				if (equippedGun.getName().equals("shotgun")) {
					Gun.shoot(bullets, CrossHair.x, CrossHair.y, bulletSize, ranNum(1, 5));
				} else
					Gun.shoot(bullets, CrossHair.x, CrossHair.y, bulletSize);

				//gunshot sound
				gunshotEffect = AudioSystem.getAudioInputStream(new File("gunshot sound.wav").getAbsoluteFile());
				gunshotSound = AudioSystem.getClip();
				gunshotSound.open(gunshotEffect);
				gunshotSound.start();
				
				bulletsLeft--;
			}
		} else { // if gun is full-automatic
			if (gc.getMouseButton(0) && bulletsLeft > 0 && shotCounter % equippedGun.getFireRate() == 0) {
				if (equippedGun.getName().equals("shotgun")) {
					Gun.shoot(bullets, CrossHair.x, CrossHair.y, bulletSize, ranNum(1, 5));
				} else
					Gun.shoot(bullets, CrossHair.x, CrossHair.y, bulletSize);

				//gunshot sound
				gunshotEffect = AudioSystem.getAudioInputStream(new File("gunshot sound.wav").getAbsoluteFile());
				gunshotSound = AudioSystem.getClip();
				gunshotSound.open(gunshotEffect);
				gunshotSound.start();
				
				bulletsLeft--;

			}
		}
		shotCounter++;

// gun reloads with the 'R' key OR by hovering over button
		if (!reloading && gc.isKeyDown(82) || (!reloading && CrossHair.intersects(ReloadButton))
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

// higher the number - the easier the game

	public void enemyMechanics() {

		if (newWave) {
			for (int i = 0; i < (2 * (int) (wave * small)); i++)
				enemies.add(new Rectangle(ranNum(1, GRWIDTH), 0, size * small, size * small));

			for (int i = 0; i < wave * big; i++)
				enemies.add(new Rectangle(ranNum(1, GRWIDTH), 0, size * big, size * big));

			for (int i = 0; i < wave * lar; i++)
				enemies.add(new Rectangle(ranNum(1, GRWIDTH), 0, size * lar, size * lar));

			newWave = false;
		}

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

			if (enemies.size() < 2) {
				newWave = true;
				wave++;
			}
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
				gc.drawImage(equippedGun.getBulletBottom(), rect);

// basically nerfing bullet speed by using this if statement
// so bullet only moves every factor of bulletSpeed

				if (bulletSpeed % equippedGun.getBulletSpeed() == 0) {
					rect.y--;
					rect.width--;
					rect.height--;
				}
				if (rect.width <= equippedGun.getBulletD() && rect.height <= equippedGun.getBulletD())
					hit.add(rect);
				for (Rectangle enem : enemies) {

					if (rect.intersects(enem)) {

						if (rect.intersects(enem))
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
// player.x + moveX > GRWIDTH / 3 //previous code for flipping

// reloading process
			if (reloading) {
				gc.setColor(Color.RED);
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
					gc.drawImage(equippedGun.getBulletPic(),
							ReloadButton.width * 8 / 6 + (GRWIDTH / 20) + b * (ReloadButton.width / 3 + 1),
							GRHEIGHT - (GRWIDTH / 10), ReloadButton.width / 3,
							(int) (ReloadButton.width / 3 * (2.68421)));
				}
			} else {
				gc.drawImage(equippedGun.getBulletPic(), ReloadButton.width * 8 / 6 + (GRWIDTH / 20),
						GRHEIGHT - (GRWIDTH / 10), ReloadButton.width / 3, (int) (ReloadButton.width / 3 * (2.68421)));

				gc.setColor(Color.WHITE);
				gc.drawString("x" + bulletsLeft, ReloadButton.width * 8 / 6 + (GRWIDTH / 13),
						GRHEIGHT - (GRWIDTH / 16));
			}

// resseting counter values, so they dont take up too much memory
			if (counter > 100000)
				counter = 0;
			if (robotCounter > 100000)
				counter = 0;
			if (fireCounter > 100000)
				counter = 0;

		}
	}

}
