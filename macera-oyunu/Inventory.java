
public class Inventory {
	
	private Weapon weapon;
	private Armor armor;
	private String[] awards;
	
	public Inventory() {
		this.weapon = new Weapon("Punch", 0, 0, 0);
		this.armor = new Armor(0, "Rag", 0, 0);
		this.awards = new String[3];
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public String[] getAwards() {
		return awards;
	}

	public void setAwards(String[] awards) {
		this.awards = awards;
	}
	
	public boolean isInventoryFull() {
		
		for(String str:this.getAwards()) {
			if(str==null) {
				return false;
			}
		}
		return true;
	}
	
	
}
