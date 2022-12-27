/**
 * The FrequencyOfElements program finds out 
 * the frequency of each element in an array.
 */

package hafta4.odev3;

public class FrequencyOfElements {

	public static void main(String[] args) {
		
		// initialize the array
		Integer[] array = {10, 20, 20, 10, 10, 20, 5, 20};
		
		// declare variables that will be used
		int currentElement, frequency;
		
		// iterate through the array
		for(int i = 0; i < array.length; i++) {
			
			// if current element is null, continue. No need to check it.
			if(array[i] == null)
				continue;
			
			// initialize the currentElement and frequency
			currentElement = array[i];
			frequency = 1;
			
			// iterate through the remaining part of the array
			for(int j = i+1; j < array.length ; j++) {
				
				// if encounters a null element, continue
				if(array[j] == null)
					continue;
				
				// if value equals to the current element increase the frequency and set that element null
				if(array[j] == currentElement) {
					
					frequency ++;
					
					array[j] = null; // set this element value null so as not to check it again
				}
			}
			// print out the frequency
			System.out.printf("%d sayısı %d kere tekrar edildi.\n", currentElement, frequency);
			
		}

	}

}
