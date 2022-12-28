
public class ToolStore extends NormalLoc{

	public ToolStore(Player player) {
		super(player, "Tool Store");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onLocation() {
		boolean showMenu = true;
		while(showMenu) {
			System.out.println("** You are at the Tool Store. **");
			System.out.println("You can buy items here!");
			System.out.println("1- Weapons\n"
					+"2- Armor\n"
					+"3- Exit.\n"
					+ "Enter your choice: ");
			int selectedChoice = Location.scanner.nextInt();
			
			while(selectedChoice <1 || selectedChoice>3) {
				System.out.println("Invalid input. Enter again: ");
				selectedChoice = Location.scanner.nextInt();
			}
			
			switch (selectedChoice) {
			case 1:
				this.printWeapon();
				break;
			case 2:
				this.printArmor();
				break;
			case 3:
				System.out.println("See you again :)");
				showMenu = false;
				break;
			}
		}
		
		
		return true;
	}
	
	public void printWeapon() {
		System.out.println("++++++ Weapons ++++++");
		Weapon[] weapons = Weapon.getWeapons();
		System.out.println("id\tname\tdamage\tprice");
		for(Weapon w:weapons) {
			System.out.println(w.getId() + "-\t" + w.getName() + "\t" + w.getDamage() + "\t"+ w.getPrice());
		}
		System.out.println("0- Exit");
		buyWeapon();
		
	}
	
	private void buyWeapon() {
		System.out.println("Select a weapon: ");
		int selectedWeaponID = Location.scanner.nextInt();
		
		Weapon[] weapons = Weapon.getWeapons();
		while(selectedWeaponID<0 || selectedWeaponID>weapons.length) {
			System.out.println("Invalid input. Enter again: ");
			selectedWeaponID = Location.scanner.nextInt();
		}
		
		if(selectedWeaponID != 0) {
			Weapon selectedWeapon = Weapon.getWeaponByID(selectedWeaponID);
			if(selectedWeapon != null) {
				if(selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
					System.out.println("You do not have enough amount of money!");
				}else {
					// buying a weapon:
					int balance = this.getPlayer().getMoney()  - selectedWeapon.getPrice();
					this.getPlayer().setMoney(balance);
					System.out.println("You bought " + selectedWeapon.getName()
									+ "\nRemaining money you have is: " + balance);
					this.getPlayer().getInventory().setWeapon(selectedWeapon);
					
				}
			}
		}
	}
	
	public void printArmor() {
		System.out.println("++++++ Armor ++++++");
		Armor[] armors = Armor.getArmors();
		System.out.println("id\tname\tblock\tprice");
		
		for(Armor a:armors) {
			System.out.println(a.getId() + "-\t" + a.getName() + "\t" + a.getBlock() + "\t"+ a.getPrice());
		}
		System.out.println("0- Exit");
		this.buyArmor();
	}
	
	private void buyArmor() {
		System.out.println("Select an Armor: ");
		int selectedArmorID = Location.scanner.nextInt();
		
		Armor[] armors = Armor.getArmors();
		while(selectedArmorID<0 || selectedArmorID>armors.length) {
			System.out.println("Invalid input. Enter again: ");
			selectedArmorID = Location.scanner.nextInt();
		}
		
		if(selectedArmorID != 0) {
			Armor selectedArmor = Armor.getArmorByID(selectedArmorID);
			if(selectedArmor != null) {
				if(selectedArmor.getPrice() > this.getPlayer().getMoney()) {
					System.out.println("You do not have enough amount of money!");
				}else {
					// buying a weapon:
					int balance = this.getPlayer().getMoney()  - selectedArmor.getPrice();
					this.getPlayer().setMoney(balance);
					System.out.println("You bought " + selectedArmor.getName()
									+ "\nRemaining money you have is: " + balance);
					this.getPlayer().getInventory().setArmor(selectedArmor);
					
				}
			}
		}
	}
	

}
