import java.lang.Comparable;

public class Book implements Comparable<Book>{
	private String name;
	private String author;
	private String publishDate;
	private int pages;
	
	public Book(String name, String author, String publishDate, int pages) {
		this.name = name;
		this.author = author;
		this.publishDate = publishDate;
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
	
	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int compareTo(Book o) {
		
		return this.getName().compareTo(o.getName());
	}

}
