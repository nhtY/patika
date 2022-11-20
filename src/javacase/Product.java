package javacase;

// A parent class for Products

public abstract class Product {
	
	// fields
	private int id;
	private double price;
	private int discountRate;
	private int stockAmount;
	private String name;
	private Brand brand;
	private int RAM;
	private int memory;
	private double screenSize;
	
	
	// constructor method
	public Product(int id, double price, int discountRate, int stockAmount, String name, Brand brand, int ram,
			int memory, double screenSize) {
		
		this.id = id;
		this.price = price;
		this.discountRate = discountRate;
		this.stockAmount = stockAmount;
		this.name = name;
		this.brand = brand;
		RAM = ram;
		this.memory = memory;
		this.screenSize = screenSize;
	}
	
	// Getters and Setters:
	
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
	
	
}
