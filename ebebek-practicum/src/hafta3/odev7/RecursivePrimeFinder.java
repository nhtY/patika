package hafta3.odev7;

import java.util.Scanner;

public class RecursivePrimeFinder {

	public static void main(String[] args) {
		
		// create a Scanner object to take a number from the console
		Scanner scanner = new Scanner(System.in);
		
		// ask user to enter a number
		System.out.print("Sayı giriniz: ");
		
		// take the input
		int number = scanner.nextInt();
		
		// close the scanner
		scanner.close();
		
		if(isPrime(number, 2)) {
			System.out.println(number + " sayısı ASALDIR !");
		}else {
			System.out.println(number + " sayısı ASAL DEĞİLDİR !");
		}
		
	}
	
	
	/**
	 * Finds out whether given number is a prime or composite
	 * @param n the number which is checked if prime or not
	 * @param i represents numbers from two till n
	 * @return boolean result
	 */
	public static boolean isPrime(int n, int i) {
		if(n <= 2)
			return (n==2)? true : false;
		
		// if any number i (i<n) can divide n, n is not prime
		if(n%i == 0)
			return false;
		
		//called trial division, tests whether n is a multiple of any integer between 2 and square root of n.
		if(i*i > n)
			return true;
		
		return isPrime(n, i+1);
	}

}
