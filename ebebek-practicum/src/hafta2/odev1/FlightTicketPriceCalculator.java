
/**
* The FlightTicketPriceCalculator program 
* simply calculates ticket prices of a flight 
* according to the age of the passenger, distance, type of the ticket etc.
* Also, it displays the result to the console.
*
* @author  Nihat Yalcin
*/

package hafta2.odev1;

import java.util.Scanner;

public class FlightTicketPriceCalculator {

	public static void main(String[] args) {
		
		// create a Scanner object to take the user input from console
		Scanner scanner = new Scanner(System.in);
		
		// in a loop ask user to enter needed values in appropriate format
		while(true) {
			// take distance
			System.out.print("Mesafeyi km türünden giriniz: ");
			double distance = scanner.nextDouble();
			
			// take age
			System.out.print("Yaşınızı giriniz: ");
			int age = scanner.nextInt();
			
			// take ticket type
			System.out.print("Yolculuk tipini seçiniz (1 => Tek Yön, 2 => Gidiş Dönüş): ");
			int ticketType = scanner.nextInt();
			
			// if input format is wrong take the inputs again
			if(age < 1 || distance < 1 || !(ticketType == 1 || ticketType == 2)) {
				System.out.println("\n\nHatalı Veri Girdiniz !\n***Enter Again***\n");
				continue;
			}
			
			// if input format is correct, print out the result
			System.out.println("\n\nToplam Tutar = " + calculatePrice(distance, age, ticketType) + " TL");
			break;
		}
		
		scanner.close();
		
	}
	
	
	/**
	 * Calculates the price of the ticket by applying discounts if needed. <br>
	 * For age < 12, discount rate is 50% <br>
	 * For 12 <= age <= 24, discount rate is 10% <br>
	 * For age > 65, discount rate is 30%. <br>
	 * 
	 * After applying the age discount, it makes 20% discount
	 * on already discounted price if return ticket is chosen.
	 * 
	 * @param distance indicates the distance of the travel.
	 * @param age is the age of the passenger
	 * @param ticketType is either 1(single ticket) or 2(return ticket)
	 * @return <b>double</b> price
	 */
	static double calculatePrice(double distance, int age, int ticketType) {
		
		double price = distance * 0.10;
		
		double ageDiscountRate = 0;
		
		// check if an age discount needed and determine the discount rate
		if(age < 12) {
			ageDiscountRate = 0.50;
		}else if (12 <= age && age<= 24) {
			ageDiscountRate = 0.10;
		}else if(age > 65) {
			ageDiscountRate = 0.30;
		}
		
		// apply the age discount
		price -= price * ageDiscountRate;
		
		// apply the return ticket discount
		if(ticketType == 2) {

			price -= price * 0.20;
			price *= 2; // go and come back --> two tickets
		}
		
		return price;
	}

}
