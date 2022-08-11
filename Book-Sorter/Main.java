import java.util.TreeSet;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {
		
		Book[] books = {
				new Book("LOTR", 500),
				new Book("Harry Potter", 400),
				new Book("Calýkuþu", 600),
				new Book("Tarih", 120),
				new Book("Seksen Günde Devri Alem", 250)
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
