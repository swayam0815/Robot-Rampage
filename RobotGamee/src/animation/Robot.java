package animation;

import java.awt.Image;
import java.awt.Rectangle;

public class Robot extends Rectangle {

	private int damage;
	private int health;
	private int speed;
	private int ATKSpeed;
	private int money;
	private Image pic;

	public static void main(String[] args) {
	}

	public Robot(int x, int y, int width, int height, int damage, int health, int speed, int ATKSpeed, int money,
			Image pic) {
		super(x, y, width, height);
		this.damage = damage;
		this.health = health;
		this.speed = speed;
		this.ATKSpeed = ATKSpeed;
		this.money = money;
		this.pic = pic;
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
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the aTKSpeed
	 */
	public int getATKSpeed() {
		return ATKSpeed;
	}

	/**
	 * @param aTKSpeed the aTKSpeed to set
	 */
	public void setATKSpeed(int aTKSpeed) {
		ATKSpeed = aTKSpeed;
	}

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
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

}
