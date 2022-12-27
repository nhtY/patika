/**
 * The RecursiveExponenetial calculates k th power of a number.
 * k is positive integer
 */

package hafta3.odev6;

import java.util.Scanner;

public class RecursiveExponenetial {

	public static void main(String[] args) {
		
		// create a Scanner object to take a number from the console
		Scanner scanner = new Scanner(System.in);
		
		// ask user to enter a base number
		System.out.print("Taban değeri giriniz: ");
		
		// take the input
		int base = scanner.nextInt();
		

		// ask user to enter a exponent number
		System.out.print("Üs değeri giriniz: ");
		
		// take the input
		int exponent = scanner.nextInt();
		
		// close the scanner
		scanner.close();
		
		// calculate 'base' to the power of 'exponent' and print the result
		System.out.println("Sonuç : " + power(base, exponent));
		

	}
	
	/**
	 * Recursively calculates the power of given integer number.
	 * 
	 * @param base the number whose power will be calculated
	 * @param power the exponent value
	 * @return int result
	 */
	public static int power(int base, int power) {
		
		// base case of recursion:
		if(power == 0)
			return 1;
		
		// recursive part:
		return base * power(base, power-1);
	}

}
