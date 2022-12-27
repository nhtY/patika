/**
 * This program finds the prime numbers within the interval [1, 100]
 * 
 */

package hafta3.odev4;

public class PrimeNumberFinder {

	public static void main(String[] args) {
		
		
		// create a flag indicating if the number is prime
		boolean isPrime;
		
		// in a for loop iterate from 2 to 100
		for(int i=2; i<100; i++) {
			
			// in each iteration change the value of the flag
			isPrime = true;
			
			for(int j=2; j <= i/2; j++) {
				
				// if a number in the range of [2, n/2] can divide i, i is not prime. 
				if(i%j == 0) {
					isPrime = false;
					break;
				}
			}
			
			// depending on the value of the flag print result
			if(isPrime)
				System.out.print(i + " ");
			
		}
		
	}

}
