
public class SafeHouse extends NormalLoc{

	public SafeHouse(Player player) {
		super(player, "Safe House");
	}
	
	@Override
	public boolean onLocation() {
		System.out.println("You are at the Safe House.");
		this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
		System.out.println("Your health is full now!");
		
		boolean isFinished = true;
		for(String str:this.getPlayer().getInventory().getAwards()) {
			if(str==null) {
				isFinished = false;
			}
		}
		
		if(isFinished) {
			System.out.println("You FINISHED the game.\n"
					+ "Enter 0 to exit.");
		}
		
		return true;
	}

}
