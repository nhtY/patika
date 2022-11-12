package hafta3.odev8;

import java.util.Scanner;

public class RecursiveNumberPattern {

	public static void main(String[] args) {
		
		// create a Scanner object to take a number from the console
		Scanner scanner = new Scanner(System.in);
		
		// ask user to enter a number
		System.out.print("N sayısı : ");
		
		// take the input
		int number = scanner.nextInt();
		
		// close the scanner
		scanner.close();
		
		// result:
		recursivePattern(number, number, 1);
		

	}
	
	/**
	 * prints the desired pattern n, n-5, n-10 .... 0 ... n-5, n
	 * @param number start and end points of the pattern
	 * @param temp the value after subtracting from or adding 5 to the current term
	 * @param half first half(1) or second half(2) of the pattern
	 */
	public static void recursivePattern(int number, int temp, int half) {
		
		// print the current term of the pattren
		System.out.print(temp + " ");
		
		// if current term is positive call method for the first half by decrementing temp value by 5
		if(temp > 0 && half == 1) {
			recursivePattern(number, temp - 5, 1);
			return;
		}
		
		// when reaching the number on the second half, stop
		if(temp == number)
			return;
		
		// add 5, the second half of the pattern
		recursivePattern(number, temp + 5, 2);
		
	}

}
