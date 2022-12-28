import java.util.ArrayList;

public class Race implements Runnable{
	ArrayList<Integer>[] quarters;
	ArrayList<Integer> even;
	ArrayList<Integer> odd;
	
	public Race(ArrayList<Integer>[] quarters, ArrayList<Integer> even, ArrayList<Integer> odd) {
		this.quarters = quarters;
		this.odd = odd;
		this.even = even;
	}
	
	@Override
	public void run() {
		
		String name = Thread.currentThread().getName();
		
		switch (name) {
		case "T1":
			filter(quarters[0]);
			break;
		case "T2":
			filter(quarters[1]);
			break;
		case "T3":
			filter(quarters[2]);
			break;
		case "T4":
			filter(quarters[3]);
			break;
		default:
			break;
		}
		
	}
	
	private void filter(ArrayList<Integer> quarter) {
		for(Integer i:quarter) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			add(i);
		}
	}
	
	private synchronized void add(int i) {
		StringBuilder builder = new StringBuilder();
		builder.append(Thread.currentThread().getName() + " : ");
		if(i%2 == 0) {
			even.add(i);
			builder.append(i + " --> EVEN");
			System.out.println(builder.toString());
		}else {
			odd.add(i);
			builder.append(i + " --> ODD");
			System.out.println(builder.toString());
		}
	}

}
