import java.util.TreeSet;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {
		
		Book[] books = {
				new Book("LOTR", "J. R. R. Tolkien", "July 29, 1954", 500),
				new Book("Harry Potter", "J. K. Rowling", "June 26 1997", 400),
				new Book("Calýkuþu", "Reþat Nuri Güntekin", "1922", 600),
				new Book("Tarih", "Anonim", "2001",120),
				new Book("Seksen Günde Devri Alem", "MyAuthor", "1935", 250)
		};
		
		TreeSet<Book> bookSet = new TreeSet<>();
		
		for(Book b:books) {
			bookSet.add(b);
		}
		
		for(Book b:bookSet) {
			System.out.println(b.getName() + ", " + b.getPages());
		}
		System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");
		
		TreeSet<Book> bookSet2 = new TreeSet<>(new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				// TODO Auto-generated method stub
				return o1.getPages() - o2.getPages();
			}

			
		});
		
		bookSet2.addAll(bookSet);
		
		for(Book b:bookSet2) {
			System.out.println(b.getName() + ", " + b.getPages());
		}
		
	}

}
