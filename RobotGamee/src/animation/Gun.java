package animation;

import java.util.ArrayList;
import java.awt.*;

public class Gun {

	private int damage;
	private int reloadTime;
	private int magazineSize;
	private int price;
	private int fireRate;
	private Image pic;
	private Image picFlipped;
	private Image picSide;
	private boolean isBought;
	private boolean isEquipped;

	public static void main(String[] args) {
	}

	public Gun(int damage, int reloadTime, int magazineSize, int price, int fireRate, Image pic, Image picFlipped,
			Image picSide, boolean isBought, boolean isEquipped) {
		this.damage = damage;
		this.reloadTime = reloadTime;
		this.magazineSize = magazineSize;
		this.price = price;
		this.fireRate = fireRate;
		this.pic = pic;
		this.picFlipped = picFlipped;
		this.picSide = picSide;
		this.isBought = isBought;
		this.isEquipped = isEquipped;

	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}

	public int getMagazineSize() {
		return magazineSize;
	}

	public void setMagazineSize(int magazineSize) {
		this.magazineSize = magazineSize;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getFireRate() {
		return fireRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	public Image getPic() {
		return pic;
	}

	public Image getPicFlipped() {
		return picFlipped;
	}

	public Image getPicSide() {
		return picSide;
	}

	public boolean getBought() {
		return isBought;
	}

	public void setBought(boolean isBought) {
		this.isBought = isBought;
	}

	public boolean getEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}

	public static void shoot(Gun gun, ArrayList<Rectangle> bullets, int x, int y, int size) {
		bullets.add(new Rectangle(x + 15, y + 15, size, size));

	}

	public static void j(int x) {
		System.out.println(x);
	}

}
