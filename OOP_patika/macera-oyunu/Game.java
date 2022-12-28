import java.util.Scanner;

public class Game {
	private Scanner scanner = new Scanner(System.in);
	
	private Player player;
	private Location location = null;
	
	public void start() {
		System.out.println("Welcome to the Adventure Game");
		
		System.out.println("Please enter a player name: ");
		String name = scanner.nextLine();
		
		this.player = new Player(name);
		System.out.println("Welcome " + player.getName() + ". In this world, everything is real");
		
		System.out.println("\n***********************");
		int selectedChar = this.askForCharacter();
		this.player.selectChar(selectedChar);
		System.out.println(this.player.getCharName() + " is selected!\n"
							+ "Damage: " + this.player.getDamage()
							+ "\nHealth: " + this.player.getHealth()
							+ "\nMoney: " + this.player.getMoney());
		
		
		
		
		while(true) {
			System.out.println("\n***********************");
			this.player.printPlayerInfo();
			int selectedLocation = this.askForLocation();
			
			if(selectedLocation == 0) {
				System.out.println("You ended the game.");
				break;
			}
			
			this.location = player.selectLocation(selectedLocation);
			this.location.setPlayer(player);
			
			if(!location.onLocation()) {
				System.out.println("GAME OVER");
				break;
			}
		}
		
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	private int askForCharacter() {
		
		System.out.println("Please choose your character:\n"
				+ "1- Samurai \t damage: 5 \t health: 18 \t money: 15\n"
				+ "2- Archery \t damage: 7 \t health: 21 \t money: 20\n"
				+ "3- Knight \t damage: 8 \t health: 24 \t money: 5\n"
				+ "enter the number here: ");
		int selected = scanner.nextInt();
		
		if(selected < 1 || selected>3) {
			return 1; // default samurai
		}
		return selected;
	}
	
	private int askForLocation() {
		System.out.println("Locations:\n"
				+ "1- Safe House \t Here is a safe place for you. You can boost your health.\n"
				+ "2- Tool Store \t Here is a store to buy weapons and armors.\n"
				+ "3- Go to the Cave \t Be careful, some Zombies may appear!\n"
				+ "4- Go to the Forest \t Be careful some Vampires may appear!\n"
				+ "5- Go to the River \t Be careful some Bears may appear!\n"
				+ "6- Go to the Mine \t There are award waiting you...\n"
				+ "0- Finish the game.\n"
				+ "Enter the location you want to go: ");
		
		return scanner.nextInt();
			
	}
	
}
