package ProjekatUDG;


	public class Player extends GameObject {
		private String name;
		private int health;

		public Player(String name, int x, int y, int width, int height, int health) {
			super(x, y, width, height);
			setName(name);
			setHealth(health);
		}

		public String getName() {
			return name;
		}

		public int getHealth() {
			return health;
		}

		public void setName(String name) {
			if (name == null || name.trim().isEmpty()) {
				System.out.println("Im igrača ne sme biti prazno");
				return;
			}

			String[] rijeci = name.trim().split("\\s+");
			StringBuilder novoIme = new StringBuilder();
			for (int i = 0; i < rijeci.length; i++) {
				String r = rijeci[i].toLowerCase();
				novoIme.append(r.substring(0, 1).toUpperCase()).append(r.substring(1));
				if (i < rijeci.length - 1) {
					novoIme.append(" ");
				}
			}
			this.name = novoIme.toString();
		}

		public void setHealth(int health) {
			if (health >= 0 && health <= 100) {
				this.health = health;
			} else {
				System.out.println("Health mora bit između 0 i 100");
			}
		}

		@Override
		public String toString() {
			return "Player[" + name + "] @ (" + getX() + "," + getY() + ") "
					+ getWidth() + "x" + getHeight() + " HP=" + health;
		}
	}

