package ProjekatUDG;


import java.util.ArrayList;

public class Game {
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<String> eventLog = new ArrayList<>();

	public Game(Player player) {
		this.player = player;
	}

	public boolean checkCollision(Player p, Enemy e) {
		return p.intersects(e);
	}

	public void decreaseHealth(Player p, Enemy e) {
		int staraHealth = p.getHealth();
		int steta = e.getDamage();
		if (e instanceof BossEnemy) {
			steta = steta * 2;
		}
		int novaHealth = staraHealth - steta;
		if (novaHealth < 0) {
			novaHealth = 0;
		}
		p.setHealth(novaHealth);
		eventLog.add("HIT: Player by " + e.getType() + " for " + steta
				+ " -> HP " + staraHealth + " -> " + novaHealth);
	}

	public void addEnemy(Enemy e) {
		enemies.add(e);
		eventLog.add("ADD: " + e);
	}

	public ArrayList<Enemy> findByType(String query) {
		ArrayList<Enemy> rezultat = new ArrayList<>();
		String trazeno = query.trim().toLowerCase();
		for (Enemy e : enemies) {
			if (e.getType().toLowerCase().contains(trazeno)) {
				rezultat.add(e);
			}
		}
		return rezultat;
	}

	public ArrayList<Enemy> collidingWithPlayer() {
		ArrayList<Enemy> rezultat = new ArrayList<>();
		for (Enemy e : enemies) {
			if (checkCollision(player, e)) {
				rezultat.add(e);
			}
		}
		return rezultat;
	}

	public void resolveCollisions() {
		for (Enemy e : collidingWithPlayer()) {
			decreaseHealth(player, e);
		}
	}

	
	public static Enemy parseEnemy(String tekst) {
		String[] dijelovi = tekst.split(";");
		String type = dijelovi[0].trim();
		String[] pozicija = dijelovi[1].trim().split(",");
		String[] dimenzije = dijelovi[2].trim().split("x");
		int x = Integer.parseInt(pozicija[0].trim());
		int y = Integer.parseInt(pozicija[1].trim());
		int width = Integer.parseInt(dimenzije[0].trim());
		int height = Integer.parseInt(dimenzije[1].trim());
		int damage = Integer.parseInt(dijelovi[3].trim());

		
		int health = 60;
		if (dijelovi.length >= 5 && dijelovi[4].trim().equalsIgnoreCase("boss")) {
			return new BossEnemy(type, x, y, width, height, damage, health);
		}
		return new MeleeEnemy(type, x, y, width, height, damage, health);
	}

	public static void main(String[] args) {
		Player igrac = new Player("petar petrović", 10, 5, 32, 32, 85);
		Game igra = new Game(igrac);

		
		igra.addEnemy(new MeleeEnemy("Orc", 200, 200, 16, 16, 35, 60));
		igra.addEnemy(parseEnemy("Goblin King;12,5;16x16;20;boss"));

		System.out.println("Svi neprijatelji:");
		for (Enemy e : igra.enemies) {
			System.out.println(e);
		}

		System.out.println("\nNeprijatelji koji sadrže \"gob\":");
		for (Enemy e : igra.findByType("gob")) {
			System.out.println(e);
		}

		System.out.println("\nPrije sudara: " + igrac);
		igra.resolveCollisions();
		System.out.println("Nakon sudara: " + igrac);

		System.out.println("\nEvent log:");
		for (String dogadjaj : igra.eventLog) {
			System.out.println(dogadjaj);
		}
	}

}