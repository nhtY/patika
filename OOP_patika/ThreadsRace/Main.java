import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> arr = new ArrayList<>(1000);
		ArrayList<Integer> even = new ArrayList<>();
		ArrayList<Integer> odd = new ArrayList<>();
		ArrayList<Integer> quarter1 = new ArrayList<>();
		ArrayList<Integer> quarter2 = new ArrayList<>();
		ArrayList<Integer> quarter3 = new ArrayList<>();
		ArrayList<Integer> quarter4 = new ArrayList<>();
		
		for(int i=1; i<=10000; i++) {
			arr.add(i);
		}
		
		for(Integer i:arr) {
			if(i<=2500) {
				quarter1.add(i);
			}else if(2500<i && i<=5000) {
				quarter2.add(i);
			}else if(5000<i && i<=7500) {
				quarter3.add(i);
			}else {
				quarter4.add(i);
			}
		}
		
		System.out.println(quarter1.size() + ", " + quarter2.size() + ", " + quarter3.size() + ", " + quarter4.size());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Integer>[] quarters = (ArrayList<Integer>[]) new ArrayList[]{
			quarter1,
			quarter2,
			quarter3,
			quarter4
	};
		
		Race race = new Race(quarters, even, odd);
		
		Thread t1 = new Thread(race, "T1");
		Thread t2 = new Thread(race, "T2");
		Thread t3 = new Thread(race, "T3");
		Thread t4 = new Thread(race, "T4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n#######################################\n");
		
		System.out.println(even.toString());
		System.out.println("EVEN: " + even.size());
		
		System.out.println("************************");
		
		System.out.println(odd.toString());
		System.out.println("ODD: " + odd.size());
	}

}
