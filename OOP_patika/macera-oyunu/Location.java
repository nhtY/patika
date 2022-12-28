import java.util.Scanner;

public abstract class Location {
	private Player player;
	private String locationName;
	
	protected static Scanner scanner = new Scanner(System.in);
	
	public Location(Player player, String locationName) {
		this.player = player;
		this.locationName = locationName;
	}
	

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	abstract boolean onLocation();

}
