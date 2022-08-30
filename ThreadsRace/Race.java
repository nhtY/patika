import java.util.ArrayList;

public class Race implements Runnable{
	ArrayList<Integer> quarter;
	ArrayList<Integer> even;
	ArrayList<Integer> odd;
	
	public Race(ArrayList<Integer> quarter, ArrayList<Integer> even, ArrayList<Integer> odd) {
		this.quarter = quarter;
		this.odd = odd;
		this.even = even;
	}
	
	@Override
	public void run() {
		
		for(Integer i:quarter) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		
		System.out.println(even.size() + "+++++" + odd.size());
		
	}

}
