/**
 * The MatrixTranspose program find the transpose of a given matrix.
 */

package hafta4.odev4;

public class MatrixTranspose {

	public static void main(String[] args) {
		
		// initialize the matrix
		int[][] matrix1 = {
					{2, 3, 4},
					{5, 6, 4}
				};
		
		// print the original matrix
		printOriginalMatrix(matrix1);
		
		// call the function:
		transpose(matrix1);
		
		System.out.println(); // for readability
		
		// initialize the matrix
		int[][] matrix2 = {
				{1, 2, 3},
				{4 ,5 ,6}
			};
		
		// print the original matrix
		printOriginalMatrix(matrix2);
		
		// call the function:
		transpose(matrix2);
		
		
		
	}
	
	/**
	 * calculates the transpose of the given matrix
	 * @param array the matrix whose transpose is calculated
	 */
	public static void transpose(int[][] array) {
		
		System.out.println("Transpose : ");
		
		// for each column
		for(int col=0; col < array[0].length; col++) {
			
			// for each entity in that column
			for(int row=0; row < array.length; row++) {
				System.out.printf("%d\t", array[row][col]);
			}
			// next line
			System.out.println();
		}
		
	}
	
	/**
	 * prints the given matrix
	 * @param matrix which will be printed on the console
	 */
	public static void printOriginalMatrix(int[][] matrix) {
		System.out.println("Matrix : ");
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[i].length; j++) {
				System.out.printf("%d\t", matrix[i][j]);
			}
			System.out.println();
		}
	}

}
