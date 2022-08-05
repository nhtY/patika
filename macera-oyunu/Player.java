
public class Player {
	public Inventory inventory;
	private String name;
	private int damage;
	private int health;
	private int originalHealth;
	private int money;
	private String charName;
	
	public Player(String name) {
		this.name = name;
		this.inventory = new Inventory();
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalDamage() {
		return damage + this.getInventory().getWeapon().getDamage();
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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getCharName() {
		return charName;
	}

	public void setCharName(String charName) {
		this.charName = charName;
	}

	public void selectChar(int charNumber) {
		GameCharacter[] characters = {
				new GameCharacter("Samurai", 10, 100, 15), // 5, 18, 15
				new GameCharacter("Archery", 7, 21, 20),
				new GameCharacter("Knight", 8, 24, 5)
		};		
		switch (charNumber) {
		case 1:
			initCharacter(characters[0]);
			break;
		case 2:
			initCharacter(characters[1]);
			break;		
		case 3:
			initCharacter(characters[2]);
			break;
			
		default:
			System.out.println("Character could not created!");
			break;
		}
	}
	
	private void initCharacter(GameCharacter character) {
		this.charName = character.getCharName();
		this.damage = character.getDamage();
		this.health = character.getHealth();
		this.setOriginalHealth(character.getHealth());
		this.money = character.getMoney();
	}
	
	public Location selectLocation(int locationNum) {
		
		switch (locationNum) {
		case 1:
			return new SafeHouse(this);
		case 2:
			return new ToolStore(this);
		case 3:
			return new Cave(this);
		case 4:
			return new Forest(this);
		case 5:
			return new River(this);
		case 6:
			return new Mine(this);
		default:
			return new SafeHouse(this);
		}
	}
	
	public void printPlayerInfo() {
		System.out.println("Weapon: " + this.getInventory().getWeapon().getName()
						+ ", Armor: " + this.getInventory().getArmor().getName()
						+ ", Armor Block: " + this.getInventory().getArmor().getBlock()
						+ ", Damage: " + this.getTotalDamage()
						+ ", Health: " + this.getHealth()
						+ ", Money: " + this.getMoney());
	}
	
	public Weapon getWeapon() {
		return this.getInventory().getWeapon();
	}

	public int getOriginalHealth() {
		return originalHealth;
	}

	public void setOriginalHealth(int originalHealth) {
		this.originalHealth = originalHealth;
	}
	
}
