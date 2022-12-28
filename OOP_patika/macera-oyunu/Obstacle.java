
public class Obstacle {
	private int id;
	private int damage;
	private int health;
	private String name;
	private int award;
	private int originalHelath;
	
	public Obstacle(int id, String name, int damage, int originalHelath, int health, int award) {
		this.name = name;
		this.id = id;
		this.damage = damage;
		this.originalHelath = originalHelath;
		this.health = health;
		this.award = award;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAward() {
		return award;
	}

	public void setAward(int award) {
		this.award = award;
	}

	public int getOriginalHelath() {
		return originalHelath;
	}

	public void setOriginalHelath(int originalHelath) {
		this.originalHelath = originalHelath;
	}
	
	
}
