
/**
* The GreenGroceryCashRegister program
* simply calculates the amount of money that has to be taken from the
* customer in return for what is sold in a grocery store.
*
* @author  Nihat Yalcin
*/

package odev2;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class GreenGroceryCashRegister {
	

	public static void main(String[] args) {
		
		// initialize a LinkedHashMap to keep Product-Price pairs
		LinkedHashMap<String, Double> products = new LinkedHashMap<>();
		
		// put the pairs into the linked hash map
		products.put("Armut", 2.14);
		products.put("Elma", 3.67);
		products.put("Domates", 1.11);
		products.put("Muz", 0.95);
		products.put("Patlıcan", 5.00);
		
		// create a Scanner object to take user input
		Scanner scanner = new Scanner(System.in);
		
		// to keep the user input indicating kilogram of product:
		double amountOfProduct;
		
		// price of the product
		double price;
		
		// to keep the amount of money to take from customer:
		double amountOfMoney = 0;
		
		// iterate through each pair and ask user how much s/he wants to buy
		for(String key : products.keySet()) {
			System.out.printf("%s Kaç Kilo? :", key);
			amountOfProduct = scanner.nextDouble();
			
			price = products.get(key);
			amountOfMoney += amountOfProduct * price; 
			
		}
		
		// close the scanner
		scanner.close();
		
		// print out the total amount of money
		System.out.printf("Toplam Tutar: %.2f TL\n", amountOfMoney);

	}

}
