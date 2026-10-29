package ProjekatUDG;

public class Enemy extends GameObject{
	private String type;
	private int damage;
	private int health;

	public Enemy(String type, int x, int y, int width, int height, int damage, int health) {
		super(x, y, width, height);
		setType(type);
		setDamage(damage);
		setHealth(health);
	}

	public String getType() {
		return type;
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public void setType(String type) {
		if (type == null || type.trim().isEmpty()) {
			System.out.println("Tip neprijatelja ne smije biti prazan");
			return;
		}
		this.type = type.trim();
	}

	public void setDamage(int damage) {
		if (damage >= 0 && damage <= 100) {
			this.damage = damage;
		} else {
			System.out.println("Damage mora biti između 0 i 100");
		}
	}

	public void setHealth(int health) {
		if (health >= 0 && health <= 100) {
			this.health = health;
		} else {
			System.out.println("Health mora biti između 0 i 100");
		}
	}

	@Override
	public String toString() {
		return "Enemy[" + type + "] @ (" + getX() + "," + getY() + ") "
				+ getWidth() + "x" + getHeight() + " DMG=" + damage + " HP=" + health;
	}
}
	