
/**
* The BodyMassIndexCalculator program implements an application that
* simply calculates body mass index with respect to given formula.
* Also, it displays the result to the console.
*
* @author  Nihat Yalcin
*/


package hafta1.odev1;

import java.util.Scanner;

public class BodyMassIndexCalculator {

	/**
	 * This method calculates BMI using the following formula:
	 * weight / (height * height)
	 * and returns a double result.
	 * @param height
	 * @param weight
	 * @return double result
	 */
	public static double calculateBMI(double height, double weight) {
		return weight / (height * height);
	}
	
	public static void main(String[] args) {
		
		// Create a Scanner object to take user inputs
		Scanner scanner = new Scanner(System.in);
		
		// take the height input in meter
		System.out.printf("Lütfen boyunuzu metre cinsinden giriniz: ");
		double height = scanner.nextDouble();
		
		// take the weight input
		System.out.printf("Lütfen kilonuzu giriniz: ");
		double weight = scanner.nextDouble();
		
		// close the scanner
		scanner.close();
		
		// Let's use the calculateBMI method:
		System.out.printf("Vücut Kitle İndeksiniz: %f\n", calculateBMI(height, weight));

	}
	
	

}
