
public class Weapon {
	private String name;
	private int id;
	private int damage;
	private int price;
	
	public Weapon(String name, int id, int damage, int price) {
		this.name = name;
		this.id = id;
		this.damage = damage;
		this.price =  price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public static Weapon[] getWeapons() {
		Weapon[] weapons = {
				new Weapon("Gun", 1, 2, 5),
				new Weapon("Sword", 2, 3, 35),
				new Weapon("Rifle", 3, 7, 45)
		};
		
		return weapons;
	}
	
	public static Weapon getWeaponByID(int id) {
		for(Weapon w:Weapon.getWeapons()) {
			if(id == w.getId()) {
				return w;
			}
		}
		return null;
	}
}
