package animation;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ShotGun extends Gun {

	public ShotGun(int damage, int reloadTime, int magazineSize, int price, int fireRate, Image pic, Image picFlipped,
			Image picSide, boolean isBought, boolean isEquipped) {
		super(damage, reloadTime, magazineSize, price, fireRate, pic, picFlipped, picSide, isBought, isEquipped);
	}

	public static void shoot(ShotGun shotgun, ArrayList<Rectangle> bullets, int x, int y, int size) {
		for (int i = 0; i < ranNum(1, 5); i++) {
			bullets.add(new Rectangle(x + ranNum(1, 100), y + ranNum(1, 100), size, size));
		}
	}

	public static int ranNum(int low, int high) {
		return (int) Math.floor(Math.random() * high + low);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
