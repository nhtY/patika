/**
 * FindMaxMin program finds the maximum and minimum
 * integer among the entered integers. Then, prints
 * out the max and min.
 */

package hafta3.odev1;

import java.util.Scanner;

public class FindMaxMin {

	public static void main(String[] args) {
		
		// create a Scanner object to take the user inputs from the console
		Scanner scanner = new Scanner(System.in);
		
		// Ask user to enter the number of integers (N)
		System.out.print("Kaç tane sayı gireceksiniz: ");
		
		// take the input
		int N = scanner.nextInt();
		
		// initialize the max and min values:
		int max = Integer.MIN_VALUE; // any value bigger than this updates max.
		int min = Integer.MAX_VALUE; // any value smaller than this updates min.
		
		// keep the current number in a variable:
		int current;
		
		// get the numbers and compare them to initial values
		for(int i=0; i<N; i++) {
			System.out.printf("%d. Sayıyı giriniz: ", i+1);
			
			// take the number
			current = scanner.nextInt();
			
			// compare the current number and determine if max or min
			if(max < current)
				max = current; // update max
			
			if(min > current)
				min = current; // update min
		}
		
		// close the scanner
		scanner.close();
		
		// print out the result
		System.out.println("En büyük sayı: " + max);
		System.out.println("En küçük sayı: " + min);

	}

}
