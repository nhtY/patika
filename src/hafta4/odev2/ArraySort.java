/**
 * The ArraySort program sorts the elements of the user defined array.
 */

package hafta4.odev2;

import java.util.Scanner;

public class ArraySort {

	public static void main(String[] args) {
		
		// create a Scanner object to take a number from the console
		Scanner scanner = new Scanner(System.in);
		
		// ask user to enter a number
		System.out.print("Dizinin boyutu n : ");
		
		// take the input
		int size = scanner.nextInt();
		
		// initialize the array
		int[] array = new int[size];
		
		System.out.println("Dizinin elemanlarını giriniz :");
		for(int i=0; i<size; i++) {
			
			// take the element
			System.out.printf("%d. Elemanı : ", i+1);
			
			// fill the array
			array[i] = scanner.nextInt();
			
		}
		
		// close the scanner
		scanner.close();
		
		// sort the array
		sort(array);
		
		// print the result:
		System.out.print("Sıralama : ");
		for(int i : array) {
			System.out.print(i + " ");
		}

	}
	
	/**
	 * Sorts the array using insertion sort algorithm
	 * @param array the array which will be sorted
	 */
	public static void sort(int[] array) {
		
		// iterate through the array
		for(int i=1; i<array.length; i++) {
			
			// hold the current element in hand
			int key = array[i];
			
			// starting from the previous element compare elements till the current one
			int j = i - 1;
			
			// compare
			while(j > -1 && key < array[j]) {
				// shift smaller elements to the left
				array[j+1] = array[j];
				
				// go left
				j--;
			}
			// current element's new position:
			array[j+1] = key;
		}
		
	}

}
