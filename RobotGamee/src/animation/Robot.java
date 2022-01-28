package animation;

import java.awt.Image;
import java.awt.Rectangle;

public class Robot extends Rectangle{
	 
	private int damage;
	private int health;
	private int speed;
	private int ATKSpeed;
	private int money;
	private Image pic;
	
	public static void main(String[] args) {
	}
	
	public Robot(int x, int y, int width, int height, int damage, int health, int speed, int ATKSpeed, int money, Image pic) {
		super(x, y, width, height);
		this.damage = damage;
		this.health = health;
		this.speed = speed;
		this.ATKSpeed = ATKSpeed;
		this.money = money;
		this.pic = pic;
	}

//	public Robot(int x, int y, int width, int height, int damage, int health, int speed, int ATKSpeed, Image standImg, Image rightImg, Image leftImg, Image hurtImage) {
//		super(x, y, width, height);
//		this.damage = damage;
//		this.health = health;
//		this.speed = speed;
//		this.ATKSpeed = ATKSpeed;
//		this.standImg = standImg;
//		this.rightImg = rightImg;
//		this.leftImg = leftImg;
//		this.hurtImg = hurtImg;
//	}

	public int getDamage() {
		return damage;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getATKSpeed() {
		return ATKSpeed;
	}

	public int getMoney() {
		return money;
	}
	
	public Image getPic() {
		return pic;
	}
	
}
