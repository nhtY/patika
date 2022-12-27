
/**
 * The PerfectNumberFinder finds whether a number is a perfect number or not.
 * 
 * @author Nihat Yalcin
 */

package hafta3.odev2;

import java.util.Scanner;

public class PerfectNumberFinder {

	public static void main(String[] args) {
		
		// create a Scanner object to take a number from the console
		Scanner scanner = new Scanner(System.in);
		
		// ask user to enter a number
		System.out.print("Bir sayı giriniz: ");
		
		// take the input
		int number = scanner.nextInt();
		
		// close the scanner
		scanner.close();
		
		// keep the sum of the values in a variable:
		int sum = 0;
		
		// iterate from 1 to the number/2. (no integer can divide number after number/2) 
		// If current value divides 'number', add it to 'sum'
		for(int i=1; i<= number/2; i++) {
			
			if(number % i == 0)
				sum += i;
			
		}
		
		// if sum equals to the number, the number is perfect
		if(sum == number)
			System.out.println(number + " Mükemmel sayıdır");
		else
			System.out.println(number + " Mükemmel sayı değildir.");
		
	
	}

}
