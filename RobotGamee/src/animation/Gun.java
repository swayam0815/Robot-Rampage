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
	private String name;
	private int[] Upgrades = new int[4];

	public static void main(String[] args) {
	}

	public Gun(int damage, int reloadTime, int magazineSize, int price, int fireRate, Image pic, Image picFlipped,
			Image picSide, boolean isBought, boolean isEquipped, String name, int[] Upgrades) {
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
		this.name = name;
		this.Upgrades = Upgrades;

	}

	public static void shoot(ArrayList<Rectangle> bullets, int x, int y, int size) {
		bullets.add(new Rectangle(x + 15, y + 15, size, size));

	}

	public static void shoot(ArrayList<Rectangle> bullets, int x, int y, int size, int bullet) {

		for (int i = 0; i < bullet; i++) {
			bullets.add(new Rectangle(x + ranNum(1, 100), y + ranNum(1, 100), size, size));
		}
	}

	public static int ranNum(int low, int high) {
		return (int) Math.floor(Math.random() * high + low);
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @return the reloadTime
	 */
	public int getReloadTime() {
		return reloadTime;
	}

	/**
	 * @param reloadTime the reloadTime to set
	 */
	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}

	/**
	 * @return the magazineSize
	 */
	public int getMagazineSize() {
		return magazineSize;
	}

	/**
	 * @param magazineSize the magazineSize to set
	 */
	public void setMagazineSize(int magazineSize) {
		this.magazineSize = magazineSize;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the fireRate
	 */
	public int getFireRate() {
		return fireRate;
	}

	/**
	 * @param fireRate the fireRate to set
	 */
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	/**
	 * @return the pic
	 */
	public Image getPic() {
		return pic;
	}

	/**
	 * @param pic the pic to set
	 */
	public void setPic(Image pic) {
		this.pic = pic;
	}

	/**
	 * @return the picFlipped
	 */
	public Image getPicFlipped() {
		return picFlipped;
	}

	/**
	 * @param picFlipped the picFlipped to set
	 */
	public void setPicFlipped(Image picFlipped) {
		this.picFlipped = picFlipped;
	}

	/**
	 * @return the picSide
	 */
	public Image getPicSide() {
		return picSide;
	}

	/**
	 * @param picSide the picSide to set
	 */
	public void setPicSide(Image picSide) {
		this.picSide = picSide;
	}

	/**
	 * @return the isBought
	 */
	public boolean isBought() {
		return isBought;
	}

	/**
	 * @param isBought the isBought to set
	 */
	public void setBought(boolean isBought) {
		this.isBought = isBought;
	}

	/**
	 * @return the isEquipped
	 */
	public boolean isEquipped() {
		return isEquipped;
	}

	/**
	 * @param isEquipped the isEquipped to set
	 */
	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the upgrades of the gun
	 */
	public int[] getUpgrades() {
		return Upgrades;
	}

	/**
	 * @param the upgrades done to the gun
	 */
	public void setUpgrades(int[] Upgrades) {
		this.Upgrades = Upgrades;
	}

}
