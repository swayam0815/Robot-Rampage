package animation;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hsa2.*;

public class setInitialValues {
	private Image normalBullet;
	private Image shotgunBullet;
	private Image waterDrop;
	private Image normalBulletBottom;
	private Image shotgunBulletBottom;
	private Image waterDropBottom;
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

	// gun objects
	private static Gun[] guns = new Gun[6];

	public setInitialValues(GraphicsConsole gc, int GRWIDTH, int GRHEIGHT) throws IOException {
		Image loading = ImageIO.read(new File("loading.png"));
		
		gc.setBackgroundColor(loading, GRWIDTH, GRHEIGHT);

		normalBullet = ImageIO.read(new File("bullet cartoon.png"));
		shotgunBullet = ImageIO.read(new File("shotgun bullet.png"));
		waterDrop = ImageIO.read(new File("water drop cartoon.png"));
		normalBulletBottom = ImageIO.read(new File("Bullet Bottom.png"));
		shotgunBulletBottom = ImageIO.read(new File("Shotgun Bullet Bottom.png"));
		waterDropBottom = ImageIO.read(new File("water drop bullet.png"));

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
		minigunImg = ImageIO.read(new File("Minigun POV.png"));
		minigunFlipped = ImageIO.read(new File("Minigun POV flipped.png"));
		minigunSide = ImageIO.read(new File("Minigun side view.png"));
		grenadeLauncherImg = ImageIO.read(new File("Grenade Launcher POV.png"));
		grenadeLauncherFlipped = ImageIO.read(new File("Grenade Launcher POV flipped.png"));
		grenadeLauncherSide = ImageIO.read(new File("GrenadeLauncher side view.png"));
		hoseImg = ImageIO.read(new File("Water Hose POV.png"));
		hoseFlipped = ImageIO.read(new File("Water Hose POV flipped.png"));
		hoseSide = ImageIO.read(new File("WaterHose side view.png"));
		// gun objects
		// damage, reload time, bullet #, price, fire rate, pic, picFlipped
		// pistol
		guns[0] = new Gun(10, 100, 7, 0, 2, pistolImg, pistolFlipped, pistolSide, normalBullet, normalBulletBottom,
				true, true, "pistol", 10);
		// AR15
		guns[1] = new Gun(6, 200, 30, 1500, 1, AR15Img, AR15Flipped, AR15Side, normalBullet, normalBulletBottom, false,
				false, "AR15", 10);
		// sniper
		guns[2] = new Gun(30, 250, 10, 4000, 5, sniperImg, sniperFlipped, sniperSide, normalBullet, normalBulletBottom,
				false, false, "sniper", 10);
		// minigun
		guns[3] = new Gun(3, 8, 400, 8500, 5, minigunImg, minigunFlipped, minigunSide, normalBullet, normalBulletBottom,
				false, false, "minigun", 10);
		// shotgun/grenade launcher
		guns[4] = new Gun(50, 170, 5, 12000, 5, grenadeLauncherImg, grenadeLauncherFlipped, grenadeLauncherSide,
				shotgunBullet, shotgunBulletBottom, false, false, "shotgun", 10);
		// water hose
		guns[5] = new Gun(2, 500, 1000, 20000, 5, hoseImg, hoseFlipped, hoseSide, waterDrop, waterDropBottom, false,
				false, "hose", 10);

	}

	public static Gun[] getGuns() {
		return guns;
	}

}
