package animation;

public class Gun {
	
	private int damage;
	private int reloadTime;
	private int magazineSize;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Gun(int damage, int reloadTime, int magazineSize) {
		this.damage = damage;
		this.reloadTime = reloadTime;
		this.magazineSize = magazineSize;
		
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

}
