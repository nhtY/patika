


public class Main {

	public static void main(String[] args) {
		
		System.out.println("*+*+*+*+ GENERIC LIST TEST *+*+*+*+\n");
		
		System.out.println("-------------------------------------------\n"
				+ "add(), size(), and expanding capacity\n"
				+ "-------------------------------------------");
		test1();
		
		System.out.println("\n-------------------------------------------\n"
				+ "get(), remove(), set() and toString()\n"
				+ "-------------------------------------------");
		test2();
       
		System.out.println("\n-------------------------------------------\n"
				+ "indexOf(), lastIndexOf(), isEmpty(), toArray()"
				+ ", clear(), subList() and contains()\n"
				+ "-------------------------------------------");
		test3();
		
	}
	
	
	private static void test1() {
		
		MyList<Integer> liste = new MyList<>();
        System.out.println("Dizideki Eleman Sayýsý : " + liste.size());
        System.out.println("Dizinin Kapasitesi : " + liste.getCapacity());
        liste.add(10);
        liste.add(20);
        liste.add(30);
        liste.add(40);
        System.out.println("Dizideki Eleman Sayýsý : " + liste.size());
        System.out.println("Dizinin Kapasitesi : " + liste.getCapacity());
        liste.add(50);
        liste.add(60);
        liste.add(70);
        liste.add(80);
        liste.add(90);
        liste.add(100);
        liste.add(110);
        System.out.println("Dizideki Eleman Sayýsý : " + liste.size());
        System.out.println("Dizinin Kapasitesi : " + liste.getCapacity());
	}
	
	private static void test2() {
        MyList<Integer> liste = new MyList<>();
        liste.add(10);
        liste.add(20);
        liste.add(30);
        System.out.println("2. indisteki deðer : " + liste.get(2));
        liste.remove(2);
        liste.add(40);
        liste.set(0, 100);
        System.out.println("2. indisteki deðer : " + liste.get(2));
        System.out.println(liste.toString());
	}
	
	private static void test3() {
		MyList<Integer> liste = new MyList<>();
        System.out.println("Liste Durumu : " + (liste.isEmpty() ? "Boþ" : "Dolu"));
        liste.add(10);
        liste.add(20);
        liste.add(30);
        liste.add(40);
        liste.add(20);
        liste.add(50);
        liste.add(60);
        liste.add(70);

        System.out.println("Liste Durumu : " + (liste.isEmpty() ? "Boþ" : "Dolu"));

        // Bulduðu ilk indeksi verir
        System.out.println("Indeks : " + liste.indexOf(20));

        // Bulamazsa -1 döndürür
        System.out.println("Indeks :" + liste.indexOf(100));

        // Bulduðu son indeksi verir
        System.out.println("Indeks : " + liste.lastIndexOf(20));

        // Listeyi Object[] dizisi olarak geri verir.
        Object[] dizi = liste.toArray();
        System.out.println("Object dizisinin ilk elemaný :" + dizi[0]);

        // Liste veri türünde alt bir liste oluþturdu
        MyList<Integer> altListem = liste.subList(0, 3);
        System.out.println(altListem.toString());

        // Deðerim listedeki olup olmadýðýný sorguladý
        System.out.println("Listemde 20 deðeri : " + liste.contains(20));
        System.out.println("Listemde 120 deðeri : " + liste.contains(120));

        // Listeyi tamamen boþaltýr ve varsayýlan boyutuna çevirir
        liste.clear();
        System.out.println(liste.toString());
	}

}
