/**
 * UpsideDownTree program draws an upside-down tree shape
 * using asterisk characters
 * 
 * @author Nihat Yalcin
 */

package hafta3.odev3;

import java.util.Scanner;

public class UpsideDownTree {

	public static void main(String[] args) {
		
		// create a Scanner object to take user input from the console
		Scanner scanner = new Scanner(System.in);
		
		// ask user to enter the number of lines
		System.out.print("Enter the number of lines: ");
		
		// take the number of lines
		int lines = scanner.nextInt();
		
		// close the scanner
		scanner.close();
		
		// iterate number of lines times
		for(int i=0; i < lines; i++) {
			
			// put blank characters before the asterisks
			for(int j=0; j<i; j++) {
				System.out.print(" ");
			}
			
			// put asterisk characters
			for(int k=i; k < lines*2 - 1 - i; k++) {	
				System.out.print("*");	
			}
			
			// go to the next line
			System.out.println();
		}
	}

}
