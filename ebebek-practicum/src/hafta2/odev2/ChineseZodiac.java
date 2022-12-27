/**
 * The ChineseZodiac program determines the Chinese Zodiac
 * based on the birth year.
 */

package hafta2.odev2;

import java.util.Scanner;

public class ChineseZodiac {

	public static void main(String[] args) {
		
		// Create Scanner object to take inputs from the console
		Scanner scanner = new Scanner(System.in);
		
		// ask user to enter his/her birth year
		System.out.print("Doğum Yılınızı Giriniz: ");
		
		// take the input
		int birthYear = scanner.nextInt();
		
		// close the scanner
		scanner.close();
		
		// find remainder when dividing the birth year by 12
		int remainder = birthYear % 12;
		
		// determine the Chinese Zodiac:
		String zodiac = "";
		switch (remainder) {
			case 0: 
				zodiac = "Maymun";
				break;
			case 1: 
				zodiac = "Horoz";
				break;
			case 2: 
				zodiac = "Köpek";
				break;
			case 3: 
				zodiac = "Domuz";
				break;
			case 4: 
				zodiac = "Fare";
				break;
			case 5: 
				zodiac = "Öküz";
				break;
			case 6: 
				zodiac = "Kaplan";
				break;
			case 7: 
				zodiac = "Tavşan";
				break;
			case 8: 
				zodiac = "Ejderha";
				break;
			case 9: 
				zodiac = "Yılan";
				break;
			case 10: 
				zodiac = "At";
				break;
			case 11: 
				zodiac = "Koyun";
				break;
			default:
				System.out.println("Could  not calculate!");
		}
		
		// print out the result
		System.out.println("Çin Zodyağı Burcunuz: " + zodiac);
		
	}

}
