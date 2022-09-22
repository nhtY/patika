import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

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
	

	public static boolean notebookOperations() {
		
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		while(true) {
			System.out.println("\n---- Notebook ----\n");
			System.out.print("1- List All Notebooks"
					+ "\n2- Filter by id"
					+ "\n3- Filter by brand"
					+ "\n4- Delete by ID"
					+ "\n5- Add product"
					+ "\n0- Go back"
					+ "\nEnter your choice: ");
			choice = scanner.nextInt();
			
			if(choice == 0) return true;
			
			if(choice<0 || 5<choice) {
				System.out.print("\nEnter a valid input: ");
				choice = scanner.nextInt();
			}else {
				
				switch (choice) {
				case 1:
					listProducts();
					break;
				case 2:
					System.out.print("Enter the id: ");
					int id = scanner.nextInt();
					filterByID(id);
					break;
				case 3:
					System.out.println("Enter a brand to search: ");
					scanner.nextLine();
					String brand = scanner.nextLine();
					filterByBrand(brand);
					break;
				case 4:
					System.out.println("Enter an id to delete: ");
					int idDelete = scanner.nextInt();
					System.out.println("is deleted? " + delete(idDelete));
					break;
				case 5:
					System.out.println("Enter the followings separated by a comma (, )\n"
							+ "| Price | Discount Rate | Stock Amount | Name | Brand Name | RAM | Memory | Screen Size |");
					int id_ = getID(); 
					scanner.nextLine();
					String[] info = scanner.nextLine().split(", ");
					double price = Double.parseDouble(info[0]);
					int discount = Integer.parseInt(info[1]);
					int amount = Integer.parseInt(info[2]);
					String name = info[3];
					Brand brand_ = Brand.getBrand(info[4]);
					int ram = Integer.parseInt(info[5]);
					int memory = Integer.parseInt(info[6]);
					double screen = Double.parseDouble(info[7]);
					
					boolean isAdded = Product.addProduct("Notebook", 
							new Notebook(id_, price, discount, amount, name, brand_, ram, memory, screen));
					System.out.println("Is notebook added? " + isAdded);
					break;
				default:
					break;
				}
			}
		}
	}
	
	public static void listProducts() {
		ArrayList<Product> notebooks = Product.getProducts("Notebook");
		
		putHorizontalLine(94);
		System.out.format("\n|%2s| %-20s| %-15s| %-15s| %-10s| %-10s| %-8s|\n", "ID", "Product Name", "Brand", "Price", "Amount", "Screen", "RAM");
		putHorizontalLine(94);
		
		for(Product p : notebooks) {
			System.out.format("\n|%-2d| %-20s| %-15s| %-15s| %-10d| %-10.1f| %-8s|\n", 
					p.getId(), 
					p.getName(), 
					p.getBrand().getName(),
					String.format("%.1f TL", p.getPrice()), 
					p.getStockAmount(), 
					p.getScreenSize(), 
					String.format("%d GB", p.getRAM())
					);
			putHorizontalLine(94);
		}
	}
	
	private static void filterByID(int id) {
		Optional<Product> notebooks = Product.getProducts("Notebook").stream()
				.filter(p-> p.getId() == id).findFirst();
		
		
		if(notebooks.isEmpty()) System.out.println("No notebook found!");
		else {
			Notebook p = (Notebook) notebooks.get();
			
			putHorizontalLine(106);
			System.out.format("\n|%2s| %-20s| %-15s| %-15s| %-10s| %-10s| %-8s| %-10s|\n", "ID", "Product Name", "Brand", "Price", "Amount", "Screen", "RAM", "Memory");
			putHorizontalLine(106);
			
			System.out.format("\n|%-2d| %-20s| %-15s| %-15s| %-10d| %-10.1f| %-8s| %-10s|\n", 
					p.getId(), 
					p.getName(), 
					p.getBrand().getName(),
					String.format("%.1f TL", p.getPrice()), 
					p.getStockAmount(), 
					p.getScreenSize(), 
					String.format("%d GB", p.getRAM()),
					String.format("%d GB", p.getMemory())
					);
			putHorizontalLine(106);
		}
	}
	
	private static void filterByBrand(String brand) {
		
		putHorizontalLine(106);
		System.out.format("\n|%2s| %-20s| %-15s| %-15s| %-10s| %-10s| %-8s| %-10s|\n", "ID", "Product Name", "Brand", "Price", "Amount", "Screen", "RAM", "Memory");
		putHorizontalLine(106);
		
		Product.getProducts("Notebook").stream()
				.filter(p-> p.getBrand().getName().toLowerCase().equals(brand.toLowerCase()))
				.forEach(p -> {
					System.out.format("\n|%-2d| %-20s| %-15s| %-15s| %-10d| %-10.1f| %-8s| %-10s|\n", 
							p.getId(), 
							p.getName(), 
							p.getBrand().getName(),
							String.format("%.1f TL", p.getPrice()), 
							p.getStockAmount(), 
							p.getScreenSize(), 
							String.format("%d GB", p.getRAM()),
							String.format("%d GB", p.getMemory())
							);
					putHorizontalLine(106);
				});;
		
		
	}
	
	private static boolean delete(int id) {
		ArrayList<Product> notebooks = Product.getProducts("Notebook");
		Optional<Product> nb = notebooks.stream()
		.filter(n-> n.getId() == id)
		.findFirst();
		
		if(! nb.isEmpty()) {
			Product n = nb.get();
			return notebooks.remove(n);
		}
		return false;
	}
	
	private static void putHorizontalLine(int len) {
		for(int i=0; i<len; i++) System.out.print("-");
	}
	

}
