
public class Book {
	private String name;
	private int pageNo;
	private String author;
	private String publicationDate;
	
	public Book(String name, int pageNo, String author, String publicationDate) {
		this.name = name;
		this.author = author;
		this.pageNo = pageNo;
		this.publicationDate = publicationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	
}
