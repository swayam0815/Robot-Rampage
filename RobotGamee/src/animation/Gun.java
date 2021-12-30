package animation;

import java.awt.Image;

public class Gun {
	 
	private int damage;
	private int reloadTime;
	private int magazineSize;
	private int price;
	private int fireRate;
	private Image pic;
	private Image picFlipped;
	
	public static void main(String[] args) {
	}
	
	public Gun(int damage, int reloadTime, int magazineSize, int price, int fireRate, Image pic, Image picFlipped) {
		this.damage = damage;
		this.reloadTime = reloadTime;
		this.magazineSize = magazineSize;
		this.price = price;
		this.fireRate = fireRate;
		this.pic = pic;
		this.picFlipped = picFlipped;
		
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
}
