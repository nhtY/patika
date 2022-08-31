import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Book> books = new ArrayList<>(10);
		
		// prepare values for book object

		String name, author, publishDate, value;
		
		Random random = new Random();
		
		for(int i=0; i<10; i++) {
			int pageNo = 15;
			
			value = String.valueOf(i+1);
			name = "Book-" + value;
			author = "Author-" + value;
			publishDate = "01-" + value + "-2000";
			pageNo = pageNo*(random.nextInt(10)+1);
			
			// generate a book and add it to the list
			books.add(new Book(name, pageNo, author, publishDate));
		}
		
		// print books:
		books.
		stream()
		.forEach(book -> System.out.println(book.getName() + " : " + book.getAuthor()));
		
		System.out.println("#####################");
		
		// keep name and author values in a hashMap:
		HashMap<String, String> nameAndAuthor = new HashMap<>();
		
		books.
		stream()
		.forEach(b-> nameAndAuthor.put(b.getName(), b.getAuthor()));
		
		// print key and values kept in the hashMap
		nameAndAuthor.forEach((key, val) -> System.out.println(key + "=>" + val));
		
		System.out.println("#####################");
		
		// filter the books and keep them in a separate ArrayList:
		ArrayList<Book> filteredBooks = new ArrayList<>();
		
		books.
		stream()
		.filter(book-> book.getPageNo()>100)
		.forEach(b-> filteredBooks.add(b));
		
		// print filtered books:
		filteredBooks.
		stream()
		.forEach(b->System.out.println(b.getName() + ", " +
										b.getAuthor()+ ", " +
										b.getPublicationDate()+ ", " +
										b.getPageNo()));
		
	}

}
