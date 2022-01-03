package animation;

import java.awt.Image;

public class Robot {
	 
	private int damage;
	private int health;
	private int speed;
	private Image standImg;
	private Image rightImg;
	private Image leftImg;
	
	public static void main(String[] args) {
	}
	
	public Robot(int damage, int health, int speed, Image standImg, Image rightImg, Image leftImg) {
		this.damage = damage;
		this.health = health;
		this.speed = speed;
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
