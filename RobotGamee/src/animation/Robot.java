package animation;

import java.awt.Image;

public class Robot{
	 
	private int damage;
	private int health;
	private int speed;
	private int ATKSpeed;
	private Image standImg;
	private Image rightImg;
	private Image leftImg;
	
	public static void main(String[] args) {
	}
	
	public Robot(int x, int y, int width, int height, int damage, int health, int speed, int ATKSpeed, Image standImg, Image rightImg, Image leftImg) {
		this.damage = damage;
		this.health = health;
		this.speed = speed;
		this.ATKSpeed = ATKSpeed;
		this.standImg = standImg;
		this.rightImg = rightImg;
		this.leftImg = leftImg;
	}

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

	public Image getStandImg() {
		return standImg;
	}

	public Image getRightImg() {
		return rightImg;
	}
	
	public Image getLeftImg() {
		return leftImg;
	}
}
