package animation;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.Color;
import java.awt.Dimension;

import hsa2.GraphicsConsole;

public class AnimationMain extends Rectangle {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new AnimationMain();
	}

	/***** Global Variables ******/
	private Dimension GRsize = Toolkit.getDefaultToolkit().getScreenSize(); // creates a variable to get screen size
	private int GRHEIGHT = (int) GRsize.getHeight() - 70; // (int)GRsize.getHeight() - 70
	private int GRWIDTH = (int) (GRHEIGHT * 1.777777777778); // this sets the size of the grid to fit the screen size
	private GraphicsConsole gc = new GraphicsConsole(GRWIDTH, GRHEIGHT);

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
	private Image bullet = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("bullet cartoon.png"));
	private Image pistol = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("pov pistol.png"));
	private Image pistolFlipped = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("pov pistol flipped.png"));
	private Image crosshair = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("crosshair.png"));
	private Image dartboardImg = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("dartboard.png"));
	private Image reloadButton = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("reload button.png"));
	private Image bulletholeImg = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("bullethole.png"));
	private Image gunshotFire = Toolkit.getDefaultToolkit()
			.getImage(gc.getClass().getClassLoader().getResource("gunshot fire.png"));

	private Image robo = Toolkit.getDefaultToolkit().getImage(gc.getClass().getClassLoader().getResource("enemy.png"));

	private Target CrossHair = new Target(); // this is the crosshair to aim with
	private Target dartboard = new Target(); // the target to shoot at
	private Target ReloadButton = new Target(); // this button reloads the gun
	private Target[] bulletholes = new Target[20]; // all the bullet holes are stored here

	private int bulletsLeft; // number of bullets left in the gun
	private int reload; // provides an angle for the arc around the reload button
	private boolean reloading; // checks if the gun is reloading
	private boolean canShoot; // checks if the player is able to shoot
	private boolean shotFired; // checks if the player has shot the gun
//	private boolean kickback;
	private int numOfShots; // counts the number of shots fired
	private int fireCounter; // counter for the fire out of the gun
	private int score; // score of the player

	private int pistolX;
	private int pistolY;
	private int targetXSpeed = 1;
	private int targetYSpeed = 1;

	private int moveX = 0;

	// enemy/robot variables
	private int x = 0;
	private int y = 0;
	private int size = 50;
	private int counter = 0;

	// bullet attributes
	ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
	ArrayList<Rectangle> hit = new ArrayList<Rectangle>();
	private int bulletSize = 50;
	private int bulletSpeed = 0;

//	private int temp = 0;

	private Rectangle enemy = new Rectangle(x, y, size, size);
	private static boolean defeat = false;

	private AnimationMain() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		initiate();

		while (gc.getKeyCode() != 'Q') {

			mechanics();
			
			if (!defeat)
				enemyMechanics();

//			move_Pistol();
			moveTarget(dartboard);

			drawGraphics();

			gc.sleep(1);
		}
	}

	private void initiate() {
		gc.enableMouseMotion();
		gc.enableMouse(); // enables motion and click for the mouse

		// sets the initial coordinates of the corsshair
		CrossHair.x = GRWIDTH / 2;
		CrossHair.y = GRHEIGHT / 2;
		CrossHair.width = CrossHair.height = GRHEIGHT / 10;

		// sets the coordinates of the reload button
		ReloadButton.x = (GRWIDTH / 20) - (GRWIDTH / 50);
		ReloadButton.y = GRHEIGHT - (GRWIDTH / 10);
		ReloadButton.width = ReloadButton.height = GRHEIGHT / 10;

		// set the coordinates of the target
		dartboard.width = dartboard.height = GRHEIGHT / 4;
		dartboard.x = GRWIDTH / 4; // 31 * GRWIDTH / 45 - dartboard.width //this is the limit
		dartboard.y = GRHEIGHT / 5; // 63 * GRHEIGHT / 90 - dartboard.height //this is the limit

		// set the value for all variables
		bulletsLeft = 9;
		reload = 0;
		reloading = false;
		canShoot = true;
		shotFired = false;
//		kickback = false;
		numOfShots = 0;
		fireCounter = 10;
		score = 0;

		// bullet holes
		for (int i = 0; i < bulletholes.length; i++) {
			bulletholes[i] = new Target();
		}

	}

	private void mechanics() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// moving left and right
		if (gc.isKeyDown(37) || gc.isKeyDown(65))
			moveX -= 10;
		if (gc.isKeyDown(39) || gc.isKeyDown(100) || gc.isKeyDown(68))
			moveX += 10;

		if (gc.isKeyDown(32) || gc.getMouseClick() > 0) {
			bullets.add(new Rectangle(CrossHair.x + 15, CrossHair.y + 15, bulletSize, bulletSize));
		}

		// gun reloads with the 'R' key OR by hovering over button
		if (gc.isKeyDown(82) || (!reloading && CrossHair.intersects(ReloadButton))) {
			reloading = true;
			canShoot = false;

			// reload sound
			gunCockingEffect = AudioSystem.getAudioInputStream(new File("gun cocking.wav").getAbsoluteFile());
			gunCocking = AudioSystem.getClip();
			gunCocking.open(gunCockingEffect);
			gunCocking.start();
		}

		// player shoots the gun
		if (gc.getMouseClick() > 0 && canShoot) {
			if (bulletsLeft > 0) {
				shotFired = true;
				bulletsLeft--;
				numOfShots++;
//				kickback = true;
				fireCounter = 0;

				// gunshot sound
				gunshotEffect = AudioSystem.getAudioInputStream(new File("gunshot sound.wav").getAbsoluteFile());
				gunshotSound = AudioSystem.getClip(); // saving the audio into clip object so program can use
				gunshotSound.open(gunshotEffect);
				gunshotSound.start();

				// shell fall sound
				shellFallEffect = AudioSystem.getAudioInputStream(new File("bullet shell fall.wav").getAbsoluteFile());
				shellFall = AudioSystem.getClip();
				shellFall.open(shellFallEffect);
				shellFall.start();

			} else {
				// empty gun sound
				dryFireEffect = AudioSystem.getAudioInputStream(new File("gun dry fire.wav").getAbsoluteFile());
				dryFire = AudioSystem.getClip();
				dryFire.open(dryFireEffect);
				dryFire.start();
			}

		}

		// the crosshair moves to the position of mouse
		CrossHair.x = gc.getMouseX() - (CrossHair.width / 2);
		CrossHair.y = gc.getMouseY() - (CrossHair.height / 2);

		// sets the coordinates of each bullet hole when a shot is fired
		if (shotFired) {
			bulletholes[numOfShots - 1].width = bulletholes[numOfShots - 1].height = GRHEIGHT / 50;
			bulletholes[numOfShots - 1].x = gc.getMouseX();
			bulletholes[numOfShots - 1].y = gc.getMouseY();
			shotFired = false;
		} // resets the coordinates for older bullet holes
		if (numOfShots > bulletholes.length - 1)
			numOfShots = 0;

		// sets the coordinates for the moving hands
		pistolX = GRWIDTH - (GRHEIGHT / 2) + (CrossHair.x / 5) + 10;
		pistolY = GRHEIGHT - (GRHEIGHT / 2) + (CrossHair.y / 5) + 10;

	}

	public void enemyMechanics() {

		if (x < pistolX + moveX + (pistolX / 8))
			x += 2;

		if (x > pistolX + moveX + (pistolX / 8))
			x -= 2;

		if (counter % 1 == 0)
			y++;

		size = y / 2 + 50;
		counter++;

		if (y >= GRHEIGHT - (size + size / 2))
			y = GRHEIGHT - (size + size / 2);

		if (x < 0)
			x = 1;
	}

//	private void move_Pistol() {	//does not work ---- fix later
//		
//		if (kickback) {
//			canShoot = false;
//
//			temp += 9;
//			if(temp < pistolX + 10) {
//				pistolX += 10;
//			}
//		}
//		else {
//			canShoot = true;//check
//			
//		}
//		
//		temp = pistolX;
//	}

	private void moveTarget(Target target) {
		target.x += targetXSpeed;
		target.y += targetYSpeed;

		// from the top
		if (target.y < 0) {
			target.y = 1;
			targetYSpeed *= -1;
		}
		// from the bottom
		if (target.y > 63 * GRHEIGHT / 90 - dartboard.height) {
			target.y = (63 * GRHEIGHT / 90 - dartboard.height) - 1;
			targetYSpeed *= -1;
		}
		// from the left
		if (target.x < 0) {
			target.x = 1;
			targetXSpeed *= -1;
		}
		// from the right
		if (target.x > 31 * GRWIDTH / 45 - dartboard.width) {
			target.x = (31 * GRWIDTH / 45 - dartboard.width) - 1;
			targetXSpeed *= -1;
		}
	}

	private void drawGraphics() {
		synchronized (gc) {
			gc.setBackgroundColor(Color.BLACK);
			gc.clear();

			// background
			gc.drawImage(backGround, 0, 0, (int) (GRHEIGHT * 1.777777777778), GRHEIGHT);

			// target
			gc.drawImage(dartboardImg, dartboard.x, dartboard.y, dartboard.width, dartboard.height);

			// player tracking enemy code thing
			gc.drawImage(robo, x, y, size, size);

			// bullet hole
			for (int j = 0; j < bulletholes.length; j++)
				gc.drawImage(bulletholeImg, bulletholes[j].x - 10, bulletholes[j].y - 10, bulletholes[j].width,
						bulletholes[j].height);

			
			// animating projectiles/bullets
			for (Rectangle rect : bullets) {
				gc.setColor(Color.GREEN);
				gc.fillRect(rect);
				
				
				// basically nerfing bullet speed by using this if statement
				// so bullet only moves every factor of 5
				if (bulletSpeed % 5 == 0) {
					rect.y--;
					rect.width--;
					rect.height--;
				}
				if(rect.intersects(enemy))
					defeat = true;
			}
			bulletSpeed++; // incrementing counter for above statement

			// removing hit bullets from main bullets list
			bullets.removeAll(hit);

			// fire out of the gun
			if (shotFired || fireCounter < 3) {
				if (pistolX + moveX > GRWIDTH / 3)
					gc.drawImage(gunshotFire, (int) (pistolX - (GRHEIGHT / 5) * 1.85) + moveX, pistolY - GRHEIGHT / 5,
							1024, 623);
				else
					gc.drawImage(gunshotFire, (int) (pistolX - (GRHEIGHT / 5) * 0.5) + moveX, pistolY - GRHEIGHT / 5,
							1024, 623);

				fireCounter++;
			}

			// crosshair
			gc.drawImage(crosshair, CrossHair.x, CrossHair.y, CrossHair.width, CrossHair.height);

			// pistol in hand
			if (pistolX + moveX > GRWIDTH / 3)
				gc.drawImage(pistol, pistolX + moveX, pistolY, GRHEIGHT / 2, GRHEIGHT / 2);
			else
				gc.drawImage(pistolFlipped, pistolX + moveX, pistolY, GRHEIGHT / 2, GRHEIGHT / 2);

			// reloading process
			if (reloading) {
				gc.setColor(Color.DARK_GRAY);
				gc.fillArc(ReloadButton.x - (ReloadButton.width / 6), ReloadButton.y - (ReloadButton.width / 6),
						ReloadButton.width * 8 / 6, ReloadButton.width * 8 / 6, 0, reload);
				reload += (int) (GRHEIGHT / 200);
				// the arc takes a full turn
				if (reload > 360) {
					reload = 0;
					bulletsLeft = 9;
					reloading = false;
					canShoot = true;

				}
			}
			// reload button
			gc.drawImage(reloadButton, ReloadButton.x, ReloadButton.y, ReloadButton.width, ReloadButton.height);

			// bullets
			for (int b = 0; b < bulletsLeft; b++) {
				gc.drawImage(bullet, ReloadButton.width * 8 / 6 + (GRWIDTH / 20) + b * (ReloadButton.width / 3 + 1),
						GRHEIGHT - (GRWIDTH / 10), ReloadButton.width / 3, (int) (ReloadButton.width / 3 * (2.68421)));
			}

		}
	}

}
