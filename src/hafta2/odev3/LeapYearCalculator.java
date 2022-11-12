/**
 * LeapYearCalculator program calculates 
 * whether the entered year is a leap year.
 * 
 * @author Nihat Yalcin
 * 
 */

package hafta2.odev3;

import java.util.Scanner;

public class LeapYearCalculator {

	public static void main(String[] args) {
		
		// create a Scanner object to take user inputs from console
		Scanner scanner = new Scanner(System.in);
		
		// ask user to enter a year
		System.out.print("Yıl Giriniz: ");

		// take the input
		int year = scanner.nextInt();
		
		// close the scanner
		scanner.close();
		
		// determine if the year is a leap year	
		
		if(year % 4 != 0) { // year must be a multiple of 4

			System.out.println(year + " bir artık yıldır değildir !");
			
		}else if (year %100 == 0 && year % 400 != 0){ // if divided by 100, it must be a multiple of 400
			
			System.out.println(year + " bir artık yıldır değildir !");
			
		}else {
			System.out.println(year + " bir artık yıldır !");
		}
		
		
	}

}
