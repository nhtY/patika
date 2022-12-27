package javacase;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MobilePhone extends Product {
	private static int ID = 1;
	private int battery;
	private String color;
	
	// phone list
	private static ArrayList<MobilePhone> phoneList = new ArrayList<>();

	// add some phones initially
	static {
		MobilePhone phone1 = new MobilePhone(getID(), 2500, 10, 25, "Samsung S-5", Brand.getBrand("Samsung"), 8, 64,
				5.5, 4500, "Black");
		MobilePhone phone2 = new MobilePhone(getID(), 3000, 10, 18, "Casper A3", Brand.getBrand("Casper"), 8, 128, 4.8,
				5000, "White");

		phoneList.add(phone1);
		phoneList.add(phone2);
	}

	// constructor method
	public MobilePhone(int id, double price, int discountRate, int stockAmount, String name, Brand brand, int ram,
			int memory, double screenSize, int battery, String color) {

		super(id, price, discountRate, stockAmount, name, brand, ram, memory, screenSize);
		this.color = color;
	}

	
	// Getters and Setters: 
	
	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// when user wants to see mobile phone related operations
	// show him: list all, filter by brand, filter by id, delete, add, go back
	public static boolean phoneOperations() {

		Scanner scanner = new Scanner(System.in);
		int choice = 0;

		while (true) {
			System.out.println("\n---- Mobile Phone ----\n");
			System.out.print("1- List All Mobile Phones" + "\n2- Filter by id" + "\n3- Filter by brand"
					+ "\n4- Delete by ID" + "\n5- Add product" + "\n0- Go back" + "\nEnter your choice: ");
			choice = scanner.nextInt();

			if (choice == 0)
				return true;

			if (choice < 0 || 5 < choice) {
				System.out.print("\nEnter a valid input: ");
				choice = scanner.nextInt();
			} else {

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
							+ "| Price | Discount Rate | Stock Amount | Name | Brand Name | RAM | Memory | Screen Size | Battery Capacity | Color |");
					int id_ = getID();
					scanner.nextLine();

					// get the inputs to create a new mobile phone
					String[] info = scanner.nextLine().split(", ");
					double price = Double.parseDouble(info[0]);
					int discount = Integer.parseInt(info[1]);
					int amount = Integer.parseInt(info[2]);
					String name = info[3];
					Brand brand_ = Brand.getBrand(info[4]);
					int ram = Integer.parseInt(info[5]);
					int memory = Integer.parseInt(info[6]);
					double screen = Double.parseDouble(info[7]);
					int battery_ = Integer.parseInt(info[8]);
					String color = info[9];

					// add new mobile phone to the related product list.

					MobilePhone phone = new MobilePhone(id_, price, discount, amount, name, brand_, ram, memory, screen,
							battery_, color);

					boolean isAddedPhone = MobilePhone.add(phone);

					System.out.println("Is phone added? " + isAddedPhone);
					break;

				default:
					break;
				}
			}
		}
	}

	// list all the mobile phones:
	public static void listProducts() {

		// table header:
		putHorizontalLine(118);
		System.out.format("\n|%2s| %-20s| %-15s| %-15s| %-10s| %-10s| %-8s| %-10s| %-10s|\n", "ID", "Product Name",
				"Brand", "Price", "Amount", "Screen", "RAM", "Memory", "Color");
		putHorizontalLine(118);

		// fill the table
		for (MobilePhone p : MobilePhone.phoneList) {

			System.out.format("\n|%-2d| %-20s| %-15s| %-15s| %-10d| %-10.1f| %-8s| %-10s| %-10s|\n", p.getId(),
					p.getName(), p.getBrand().getName(), String.format("%.1f TL", p.getPrice()), p.getStockAmount(),
					p.getScreenSize(), String.format("%d GB", p.getRAM()), String.format("%d GB", p.getMemory()),
					p.getColor());
			putHorizontalLine(118);
		}
	}

	// fetch the mobile phone with entered id
	private static void filterByID(int id) {
		Optional<MobilePhone> phones = MobilePhone.phoneList.stream().filter(p -> p.getId() == id).findFirst();

		if (phones.isEmpty()) // is there a phone?
			System.out.println("No phone found!");
		else {
			MobilePhone p = phones.get();

			// show the information of the mobile phone in table form
			putHorizontalLine(118);
			System.out.format("\n|%2s| %-20s| %-15s| %-15s| %-10s| %-10s| %-8s| %-10s| %-10s|\n", "ID", "Product Name",
					"Brand", "Price", "Amount", "Screen", "RAM", "Memory", "Color");
			putHorizontalLine(118);

			System.out.format("\n|%-2d| %-20s| %-15s| %-15s| %-10d| %-10.1f| %-8s| %-10s| %-10s|\n", p.getId(),
					p.getName(), p.getBrand().getName(), String.format("%.1f TL", p.getPrice()), p.getStockAmount(),
					p.getScreenSize(), String.format("%d GB", p.getRAM()), String.format("%d GB", p.getMemory()),
					p.getColor());
			putHorizontalLine(118);
		}
	}

	// fetch the mobile phones whose brand names are the entered brand name
	private static void filterByBrand(String brand) {

		putHorizontalLine(118);
		System.out.format("\n|%2s| %-20s| %-15s| %-15s| %-10s| %-10s| %-8s| %-10s| %-10s|\n", "ID", "Product Name",
				"Brand", "Price", "Amount", "Screen", "RAM", "Memory", "Color");
		putHorizontalLine(118);

		MobilePhone.phoneList.stream().filter(p -> p.getBrand().getName().toLowerCase().equals(brand.toLowerCase()))
				.forEach(p -> {
					System.out.format("\n|%-2d| %-20s| %-15s| %-15s| %-10d| %-10.1f| %-8s| %-10s| %-10s|\n", p.getId(),
							p.getName(), p.getBrand().getName(), String.format("%.1f TL", p.getPrice()),
							p.getStockAmount(), p.getScreenSize(), String.format("%d GB", p.getRAM()),
							String.format("%d GB", p.getMemory()), p.getColor());
					putHorizontalLine(118);
				});
		;

	}

	// add a phone
	private static boolean add(MobilePhone phone) {
		return phoneList.add(phone);
	}

	// delete the mobile phone with entered id:
	private static boolean delete(int id) {
		Optional<MobilePhone> phone = MobilePhone.phoneList.stream().filter(p -> p.getId() == id).findFirst();

		if (!phone.isEmpty()) {
			MobilePhone p = phone.get();
			return phoneList.remove(p);
		}
		return false;
	}

	// for building a table view, put horizontal line:
	private static void putHorizontalLine(int len) {
		for (int i = 0; i < len; i++)
			System.out.print("-");
	}

	// while creating a new mobile phone, give it a unique id.
	public static int getID() {
		return ID++;
	}

}
