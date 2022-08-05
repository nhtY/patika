
public abstract class NormalLoc extends Location{

	public NormalLoc(Player player, String locationName) {
		super(player, locationName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	boolean onLocation() {
		return true;
	}
}
