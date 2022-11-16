package javacase;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Product {
	
	private int id;
	private double price;
	private int discountRate;
	private int stockAmount;
	private String name;
	private Brand brand;
	private int RAM;
	private int memory;
	private double screenSize;
	private String category;
	
	// There will be products in advance when program started
	public static HashMap<String, ArrayList<Product>> productCategories = new HashMap<>();
	
	static {
		productCategories.put("Notebook", new ArrayList<Product>());
		productCategories.put("Mobile phone", new ArrayList<Product>());
		
		Product samsungPhone = new MobilePhone(MobilePhone.getID(), 12000.0, 10, 15, "Galaxy", Brand.getBrand("ASD"), 3, 64, 5.1, 4000, "Black");
		Product.addProduct("Mobile phone", samsungPhone);
		
		Product samsungPhone2 = new MobilePhone(MobilePhone.getID(), 1200.0, 10, 15, "Galaxy-2", Brand.getBrand("Samsung"), 3, 64, 5.1, 4500, "White");
		Product.addProduct("Mobile phone", samsungPhone2);
		
		Product HP = new Notebook(Notebook.getID(), 1200.0, 10, 15, "HP notebook", Brand.getBrand("HP"), 3, 64, 5.1);
		Product.addProduct("Notebook", HP);
	}
	
	
	public Product(int id, double price, int discountRate, int stockAmount, String name, Brand brand, int rAM,
			int memory, double screenSize) {
		
		this.id = id;
		this.price = price;
		this.discountRate = discountRate;
		this.stockAmount = stockAmount;
		this.name = name;
		this.brand = brand;
		RAM = rAM;
		this.memory = memory;
		this.screenSize = screenSize;
		//this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	public int getStockAmount() {
		return stockAmount;
	}
	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public int getRAM() {
		return RAM;
	}
	public void setRAM(int rAM) {
		RAM = rAM;
	}
	public int getMemory() {
		return memory;
	}
	public void setMemory(int memory) {
		this.memory = memory;
	}
	public double getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	// add a new product by its category name
	public static boolean addProduct(String category, Product product) {
		ArrayList<Product> products = Product.getProducts(category);
		if(products == null) {
			return false;
		}
		
		return products.add(product);
	}
	
	// if needed, create a new category
	public static boolean addCategory(String category) {
		return productCategories.put(category, new ArrayList<Product>()) == null;
	}
	
	// get all the products by category name
	public static ArrayList<Product> getProducts(String category){
		return productCategories.get(category);
	}
	
	
}
