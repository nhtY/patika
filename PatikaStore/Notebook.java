
public class Notebook extends Product{
	private static int ID = 0;

	public Notebook(int id, double price, int discountRate, int stockAmount, String name, Brand brand,
			int rAM, int memory, double screenSize) {
		super(id, price, discountRate, stockAmount, name, brand, rAM, memory, screenSize);
		this.setCategory("Notebook");
	}
	
	public static int getID() {
		return ID ++;
	}

}
