package hafta3.odev5;

import java.util.Scanner;

public class FibonacciSeries {
	
	public static void main(String[] args) {
		
		// declare the variables
		int N, num1, num2, temp;
		
		// create a scanner object to take user inputs
		Scanner scanner = new Scanner(System.in);
		
		// take the number indicating the number of first Nth elements 
		System.out.print("How many terms do you want to print out: ");
		N = scanner.nextInt();
		
		// close the scanner
		scanner.close();
		
		temp = 0; // temp will be used to swap the values
		
		// initialize the first two terms
		num1 = 0;
		num2 = 1;
		
		// iterate N times
		for(int i = 0; i<N; i++) {
			
			// if N is 1, print first term. No swap needed.
			if(N==1) {
				System.out.print(num1);
				break;
			}
			
			// print out the term
			System.out.print(num1 + " ");
			
			// change the values of num1 and num2
			temp = num2;
			
			// prepare next terms
			num2 = num1 + num2;
			
			num1 = temp;
			
		}
		
	}
}


/*

0 1 2 3 4 5 6 7  8  9 
0 1 1 2 3 5 8 13 21 34

*/

