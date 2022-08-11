import java.lang.Comparable;

public class Book implements Comparable<Book>{
	private String name;
	private int pages;
	
	public Book(String name, int pages) {
		this.name = name;
		this.pages = pages;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public int getPages() {
		return this.pages;
	}
	
	@Override
	public int compareTo(Book o) {
		
		return this.getName().compareTo(o.getName());
	}

}
