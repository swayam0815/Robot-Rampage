package animation;

// importing libraries to use within program
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

public class AnimationMain {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	}

	/***** Global Variables ******/
	private static Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen
	// size
	private static int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private static int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit

	// console to draw on
	private static GraphicsConsole gc;

	// sound effects
	private static Clip gunshotSound;
	private static Clip dryFire;
	private static Clip shellFall;
	private static Clip gunCocking;
	private static Clip waterShot;
	private static AudioInputStream gunshotEffect;
	private static AudioInputStream dryFireEffect;
	private static AudioInputStream gunCockingEffect;
	private static AudioInputStream shellFallEffect;
	private static AudioInputStream waterShotEffect;

	// pictures
	private Image backGround; // "bakground.jpg"
	private Image forcefield; // "ForceField.png"
	private Image crosshair; // "crosshair.png"
	private Image reloadButton; // "reload button.png"
	private Image gunshotFire; // "gunshot fire.png"

	// robot pictures
	private Image thomasFace; // "Thomas face"
	private Image tinyRobotImg; // "tinyRobot stand"
	private Image midRobotImg; // "midRobot stand"
	private Image bigRobotImg; // "bigRobot stand"
	private Image bossRobotImg; // "bigRobot stand"

	// hit boxes for buttons
	private Rectangle CrossHair; // to aim
	private Rectangle ReloadButton = new Rectangle((GRWIDTH / 20) - (GRWIDTH / 50), GRHEIGHT - (GRWIDTH / 10),
			GRHEIGHT / 10, GRHEIGHT / 10); // this button reloads the gun

	// variables for game mechanics
	private int shotCounter = 0; // crucial for fireRate
	private int bulletsLeft; // number of bullets left in the gun
	private int reload; // provides an angle for the arc around the reload button
	private boolean reloading; // checks if the gun is reloading
	private int moveX = 0; // the amount of movement for the player
	private static int forceStrength = 200; // health of the forcefield protecting the player
	private int moneyEarned = 0; // total amount of money the player earns during level

	// boolean for powerup
	private static boolean autoReload = false; // code for purchasing to be added later

	// for bullet animation
	private int size = 50;
	private static int counter = 0;

	// bullet attributes
	ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
	ArrayList<Rectangle> hit = new ArrayList<Rectangle>();
	private int bulletSize = 50;
	private int bulletSpeed = 0;

	// gun object for player gun
	// damage, reload time, bullet #, price, fire rate, pic, picFlipped
	private static Gun equippedGun; // the gun being held by the player
	private Rectangle player = new Rectangle(0, 0, (int) (GRHEIGHT / 2 * 1.777777777777778), GRHEIGHT / 2);

	// enemy attributes
	private ArrayList<Robot> enemies = new ArrayList<Robot>();
	private static int wave = 1;
	private ArrayList<Robot> destroyedEnemies = new ArrayList<Robot>();
	private static boolean newWave = true; // when to spawn new wave
	private static int wavesLeft; // how many waves left
	private final int small = 1; // ratio of how many small robots to spawn
	private final int mid = 2; // ratio of how many big robots to spawn
	private final int big = 6; // ratio of how many LARGE robots to spawn
	private static int robotCounter = 0; // counter to keep track of when to spawn robots

	// font for HUD
	private Font HUDfont = new Font("Elephant", Font.PLAIN, GRHEIGHT / 20);

	// class constructor
	// requires graphicconsole to draw on, number of waves in level, array of guns,
	// and levelNum
	public AnimationMain(GraphicsConsole x, int totalWaves, Gun[] guns, int levelNum, boolean bossFight)
			throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		// setting up variables from parameters
		gc = x;
		wavesLeft = totalWaves;
		// setting up variables
		wave = 1;
		forceStrength = 200;

		// giving player a default gun, if they didnt equip anything from upgrade menu
		if (UpgradeMenu.getGun() != null)
			equippedGun = UpgradeMenu.getGun();
		else
			equippedGun = setInitialValues.getGuns()[0];
		// initiating level
		initiate();

		// statement to add boss to level, if boolean passed in from constructor
		if (bossFight)
			//x, y, width, height, damage, health, speed, ATKSpeed, money, pic
			enemies.add(new Robot(ranNum(1, GRWIDTH), 0, size * 10, size * 10, 1, 1000, 5000, 1, 1500, bossRobotImg));

		// MAIN GAME LOOP
		// level runs until all waves in level defeated or player loses due to
		// forcefield strength being too low
		while (wave + 1 <= totalWaves && forceStrength > 0) {

			// method to fetch player I/O
			mechanics();
			// mechanics to move robots
			enemyMechanics();
			// drawing everything
			drawGraphics();
			// making framerates
			gc.sleep(1);

		}

		// slow everything down since level finished
		gc.sleep(100);

		// lose if forcefield dies, win if robots die
		if (forceStrength <= 0) {
			new Mission(gc, false, guns, moneyEarned);
		} else if (forceStrength > 0) {

			// level 5 has something special, so only do stuff in if statements, if level !=
			// 5
			if (levelNum != 5) {
				if (levelNum + 1 <= 5) {
					levels.win(levelNum + 1);
					new Mission(gc, true, guns, moneyEarned);
				} else {
					levels.win(levelNum);
					new Mission(gc, true, guns, moneyEarned);
				}
			} else {
				// cutscene
				// drawing boss face to screen and playing audio
				gc.drawImage(ImageIO.read(new File("bossman.jpg")), 0, 0, GRWIDTH, GRHEIGHT);
				AudioInputStream boss = AudioSystem.getAudioInputStream(new File("EVILBOSS.wav").getAbsoluteFile());
				Clip evilBoss = AudioSystem.getClip();
				evilBoss.open(boss);
				evilBoss.start();
				// once audio finished then go next page
				if (!evilBoss.isActive())
					new Mission(gc, true, guns, moneyEarned);
			}

		}

	}

	private void initiate() throws IOException {
		// screen attributes
		backGround = ImageIO.read(new File("bakground.png"));
		forcefield = ImageIO.read(new File(("ForceField.png")));
		crosshair = ImageIO.read(new File("crosshair.png"));
		reloadButton = ImageIO.read(new File("reload button.png"));
		gunshotFire = ImageIO.read(new File("gunshot fire.png"));

		// robot pictures
		thomasFace = ImageIO.read(new File("Thomas face.png"));
		tinyRobotImg = ImageIO.read(new File("tinyRobot stand.png"));
		midRobotImg = ImageIO.read(new File("midRobot stand.png"));
		bigRobotImg = ImageIO.read(new File("bigRobot stand.png"));
		bossRobotImg = ImageIO.read(new File("bossRobot stand.png"));

		// hitbox for crosshair item
		CrossHair = new Rectangle(GRWIDTH / 2, GRHEIGHT / 2, GRHEIGHT / 10, GRHEIGHT / 10);

		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the mouse
		gc.setFont(HUDfont); // setting font for hud

		// set the value for all variables
		bulletsLeft = equippedGun.getMagazineSize();
		reload = 0;
		reloading = false;

	}

	// GETTER METHOD
	// returns a random number from a range of int low - int high
	private static int ranNum(int low, int high) {
		return (int) Math.floor(Math.random() * high + low);
	}

	// method to get player I/O mechanics
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

		// shooting the gun
		if (equippedGun.getFireRate() == 0) { // if gun is semi-auto
			if (gc.getMouseClick() > 0) {
				if (bulletsLeft > 0) {
					if (equippedGun.getName().equals("shotgun")) {
						Gun.shoot(bullets, CrossHair.x, CrossHair.y, bulletSize, ranNum(1, 5));
					} else
						Gun.shoot(bullets, CrossHair.x, CrossHair.y, bulletSize);

					// gunshot sound
					gunshotEffect = AudioSystem.getAudioInputStream(new File("gunshot sound.wav").getAbsoluteFile());
					gunshotSound = AudioSystem.getClip();
					gunshotSound.open(gunshotEffect);
					gunshotSound.start();

					// shell fall sound
					shellFallEffect = AudioSystem
							.getAudioInputStream(new File("bullet shell fall.wav").getAbsoluteFile());
					shellFall = AudioSystem.getClip();
					shellFall.open(shellFallEffect);
					shellFall.start();

					bulletsLeft--;
				} else {
					// empty gun sound
					dryFireEffect = AudioSystem.getAudioInputStream(new File("gun dry fire.wav").getAbsoluteFile());
					dryFire = AudioSystem.getClip();
					dryFire.open(dryFireEffect);
					dryFire.start();
				}
			}
		} else { // if gun is full-automatic
			if (gc.getMouseButton(0) && shotCounter % equippedGun.getFireRate() == 0) {
				if (bulletsLeft > 0) {
					Gun.shoot(bullets, CrossHair.x, CrossHair.y, bulletSize);
					if (equippedGun.getName().equals("hose")) {
						// gunshot sound
						gunshotEffect = AudioSystem.getAudioInputStream(new File("water shot.wav").getAbsoluteFile());
						gunshotSound = AudioSystem.getClip();
						gunshotSound.open(gunshotEffect);
						gunshotSound.start();
					} else {
						// gunshot sound
						gunshotEffect = AudioSystem
								.getAudioInputStream(new File("gunshot sound.wav").getAbsoluteFile());
						gunshotSound = AudioSystem.getClip();
						gunshotSound.open(gunshotEffect);
						gunshotSound.start();
					}

					bulletsLeft--;
				} else {
// empty gun sound
					dryFireEffect = AudioSystem.getAudioInputStream(new File("gun dry fire.wav").getAbsoluteFile());
					dryFire = AudioSystem.getClip();
					dryFire.open(dryFireEffect);
					dryFire.start();
				}

			}
		}
		shotCounter++;

// gun reloads with the 'R' key OR by hovering over button
		if (!reloading && gc.isKeyDown(82) || (!reloading && CrossHair.intersects(ReloadButton))
				|| (autoReload && bulletsLeft == 0)) {
			bulletsLeft = -1; // this makes the reload sound play ONLY once
			reloading = true;

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

	public void enemyMechanics() {

		if (newWave) {
			bullets.removeAll(bullets);
			// x, y, width, height, damage, health, speed, ATKSpeed, money, pic
			for (int i = 0; i < (2 * (int) (wave * small)); i++)
				enemies.add(new Robot(ranNum(1, GRWIDTH), 0, size * small, size * small, 1, 10, 1, 5, 3, tinyRobotImg));

			for (int i = 0; i < wave * mid; i++)
				enemies.add(new Robot(ranNum(1, GRWIDTH), 0, size * mid, size * mid, 3, 18, 2, 10, 10, midRobotImg));

			for (int i = 0; i < wave * big; i++)
				enemies.add(new Robot(ranNum(1, GRWIDTH), 0, size * big, size * big, 8, 25, 8, 18, 15, bigRobotImg));

//			for (int i = 0; i < (2 * (int) (wave * small)); i++)
//				enemies.add(new Robot(ranNum(1, GRWIDTH), 0, size * small, size * small,
//						1, 10, 1, 5, tinyRobotStand, tinyRobotRight, tinyRobotLeft, tinyRobotHurt));
//
//			for (int i = 0; i < wave * mid; i++)
//				enemies.add(new Robot(ranNum(1, GRWIDTH), 0, size * mid, size * mid,
//						3, 18, 2, 10, midRobotStand, midRobotRight, midRobotLeft, midRobotHurt));
//
//			for (int i = 0; i < wave * big; i++)
//				enemies.add(new Robot(ranNum(1, GRWIDTH), 0, size * big, size * big,
//						8, 25, 8, 18, bigRobotStand, bigRobotRight, bigRobotLeft, bigRobotHurt));

			newWave = false;
		}

		for (Robot rect : enemies) {

			// robot tracks the player
			if (rect.x + rect.width < player.x + moveX)
				rect.x += 2;

			if (rect.x > player.x + moveX + player.width)
				rect.x -= 2;

			// each robot moves at their own speed
			if (counter % rect.getSpeed() == 0) {
				rect.y++;

			}

			// robots size increases to indicate less distance
			if (counter % 5 == 0) {
				rect.width++;
				rect.height++;
			}

			// this stops the robots from getting too big
			if (rect.height > GRHEIGHT / 10 * 9) {
				rect.width = rect.height = GRHEIGHT / 10 * 9;
			}

			// robots deal damage when they get close enough
			if (rect.y >= GRHEIGHT - rect.height - (GRHEIGHT / 7)) {
				rect.y = GRHEIGHT - rect.height - (GRHEIGHT / 7);
				if (counter % rect.getATKSpeed() == 0) {
					forceStrength -= rect.getDamage();
				}
			}

			// sets limits for robots as to not leave the screen
			if (rect.x < 0)
				rect.x = 1;
			if (rect.x + rect.width > GRWIDTH)
				rect.x = GRWIDTH - rect.width;

		}
		counter++;
	}

	private void drawGraphics() {
		synchronized (gc) {
			// when robots are killed, new wave starts
			if (enemies.size() <= 0) {
				newWave = true;
				wave++;
			}
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();

// background
			gc.drawImage(backGround, 0, 0, (int) (GRHEIGHT * 1.777777777778), GRHEIGHT);

// drawing enemies
			for (Robot rect : enemies) {
				gc.drawImage(rect.getPic(), rect);
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
				if (rect.width <= equippedGun.getBulletD() - 5 && rect.height <= equippedGun.getBulletD() - 5)
					hit.add(rect);
				for (Robot enem : enemies) {

					// when hit, enemy loses health the amount of the gun's damage
					if (rect.intersects(enem)) {
						enem.setHealth(enem.getHealth() - equippedGun.getDamage());
					}
					// if enemy health is zero, it dies
					if (enem.getHealth() <= 0) {
						destroyedEnemies.add(enem);
						moneyEarned += enem.getMoney();
					}
					hit.add(rect);

				}
			}
			bulletSpeed++; // incrementing counter for above statement

// removing hit bullets from main bullets list
			bullets.removeAll(hit);
			hit.removeAll(hit);
			enemies.removeAll(destroyedEnemies);
			destroyedEnemies.removeAll(destroyedEnemies);

// Forcefield
			gc.drawImage(forcefield, 0, 0, GRWIDTH, GRHEIGHT);

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

				}
			}
// reload button
			gc.drawImage(reloadButton, ReloadButton.x, ReloadButton.y, ReloadButton.width, ReloadButton.height);

//HUD{
//-forcefield power left
			gc.setStroke(GRWIDTH / 130);
			gc.setColor(Color.RED);
			gc.fillRect(GRWIDTH / 4 * 3, GRHEIGHT / 20 * 18, GRWIDTH / 4, GRHEIGHT / 20);
			gc.setColor(Color.GREEN);
			gc.fillRect(GRWIDTH / 4 * 3, GRHEIGHT / 20 * 18, (int) (forceStrength * 1.44375), GRHEIGHT / 20);
			gc.drawString("ForceField Power", (int) (GRWIDTH * 0.74), (int) (GRHEIGHT * 0.99));
			gc.setColor(Color.BLACK);
			gc.drawRect(GRWIDTH / 4 * 3, GRHEIGHT / 20 * 18, GRWIDTH / 4, GRHEIGHT / 20);

//-robots left in the wave
			gc.setColor(Color.RED);
			gc.drawString(" X " + String.valueOf(enemies.size()), 550, GRHEIGHT / 20 * 19);
			gc.drawImage(thomasFace, 450, GRHEIGHT / 20 * 17, 100, 100);

//-number of waves left}
			gc.drawString(String.valueOf(wavesLeft - wave), GRWIDTH / 100, GRHEIGHT / 4 * 3);
			gc.setColor(Color.GREEN);
			gc.drawString("    Waves Left", GRWIDTH / 100, GRHEIGHT / 4 * 3);

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

// reseting counter values, so they don't take up too much memory
			if (counter > 100000)
				counter = 0;
			if (robotCounter > 100000)
				counter = 0;

		}
	}

}
