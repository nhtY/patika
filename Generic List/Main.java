


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
        System.out.println("Dizideki Eleman Say�s� : " + liste.size());
        System.out.println("Dizinin Kapasitesi : " + liste.getCapacity());
        liste.add(10);
        liste.add(20);
        liste.add(30);
        liste.add(40);
        System.out.println("Dizideki Eleman Say�s� : " + liste.size());
        System.out.println("Dizinin Kapasitesi : " + liste.getCapacity());
        liste.add(50);
        liste.add(60);
        liste.add(70);
        liste.add(80);
        liste.add(90);
        liste.add(100);
        liste.add(110);
        System.out.println("Dizideki Eleman Say�s� : " + liste.size());
        System.out.println("Dizinin Kapasitesi : " + liste.getCapacity());
	}
	
	private static void test2() {
        MyList<Integer> liste = new MyList<>();
        liste.add(10);
        liste.add(20);
        liste.add(30);
        System.out.println("2. indisteki de�er : " + liste.get(2));
        liste.remove(2);
        liste.add(40);
        liste.set(0, 100);
        System.out.println("2. indisteki de�er : " + liste.get(2));
        System.out.println(liste.toString());
	}
	
	private static void test3() {
		MyList<Integer> liste = new MyList<>();
        System.out.println("Liste Durumu : " + (liste.isEmpty() ? "Bo�" : "Dolu"));
        liste.add(10);
        liste.add(20);
        liste.add(30);
        liste.add(40);
        liste.add(20);
        liste.add(50);
        liste.add(60);
        liste.add(70);

        System.out.println("Liste Durumu : " + (liste.isEmpty() ? "Bo�" : "Dolu"));

        // Buldu�u ilk indeksi verir
        System.out.println("Indeks : " + liste.indexOf(20));

        // Bulamazsa -1 d�nd�r�r
        System.out.println("Indeks :" + liste.indexOf(100));

        // Buldu�u son indeksi verir
        System.out.println("Indeks : " + liste.lastIndexOf(20));

        // Listeyi Object[] dizisi olarak geri verir.
        Object[] dizi = liste.toArray();
        System.out.println("Object dizisinin ilk eleman� :" + dizi[0]);

        // Liste veri t�r�nde alt bir liste olu�turdu
        MyList<Integer> altListem = liste.subList(0, 3);
        System.out.println(altListem.toString());

        // De�erim listedeki olup olmad���n� sorgulad�
        System.out.println("Listemde 20 de�eri : " + liste.contains(20));
        System.out.println("Listemde 120 de�eri : " + liste.contains(120));

        // Listeyi tamamen bo�alt�r ve varsay�lan boyutuna �evirir
        liste.clear();
        System.out.println(liste.toString());
	}

}
