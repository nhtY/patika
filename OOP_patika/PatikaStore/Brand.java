import java.util.ArrayList;
import java.util.Optional;

public class Brand implements Comparable<Brand>{
	private static int ID = 1;
	private int id;
	private String name;
	private static ArrayList<Brand> brandList = new ArrayList<>();
	
	static {
		brandList.add(new Brand(ID++, "Samsung"));
		brandList.add(new Brand(ID++, "Lenovo"));
		brandList.add(new Brand(ID++, "Apple"));
		brandList.add(new Brand(ID++, "Huawei"));
		brandList.add(new Brand(ID++, "Casper"));
		brandList.add(new Brand(ID++, "Asus"));
		brandList.add(new Brand(ID++, "HP"));
		brandList.add(new Brand(ID++, "Xiaomi"));
		brandList.add(new Brand(ID++, "Monster"));
	}
	
	public Brand(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public static boolean addBrand(String name) {
		return brandList.add(new Brand(ID++, name));
	}
	
	public static void listBrands(){
		// to be sorted, it is needed to be comparable for the object
		// That is why, the class implements Comparable
		System.out.println("Our Brands");
		System.out.println("-------------------");
		brandList.stream()
		.sorted().
		forEach(b -> System.out.println("- " + b.getName()));
	}
	
	// if a brand with given name exists, return that brand.
	// otherwise, create a new brand with the given name.
	public static Brand getBrand(String name) {
		Optional<Brand> element =  brandList.stream()
		.filter(b -> b.getName().equals(name))
		.findFirst();
		
		if(element.isEmpty()) {
			Brand.addBrand(name);
			return Brand.brandList.get(brandList.size() -1);
		}else {
			return element.get();
		}
	}

	// to sort the brands by their name override compareTo method of Comparable interface
	@Override
	public int compareTo(Brand o) {
		
		return this.getName().compareTo(o.getName());
	}
	

}
