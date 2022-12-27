package javacase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// the main program we use for the operations:
public class PatikaStore {
	private Scanner scanner = new Scanner(System.in);
	
	// the product categories we have: notebook, mobile phone etc.. we can add more
	private static ArrayList<String> productCategories = new ArrayList<>();
	
	// initialize the categories
	static {
		productCategories.add("Notebook");
		productCategories.add("Mobile phone");
	}
	
	// list the operations on console
	public void listMainOperations() {
		// order of the choices:
		int choiceIndex = 0;
		
		// selected choice:
		int choice = 0;

		// keep the names of the existing category names:
		HashMap<Integer, String> categories = new HashMap<>();
		
		while(true) {
			System.out.println("\n<-- Patika | Product Management Panel -->");
			choiceIndex = 0;
			
			// print choices for categories, and fill the variable 'categories'
			for(String key : productCategories) {
				choiceIndex += 1;
				System.out.println(choiceIndex + "- " + key + " Operations");
				categories.put(choiceIndex, key);
			}
			
			// print choices:
			choiceIndex += 1;
			
			System.out.print(choiceIndex + "- " + "List the Brands\n"
					+ "0- Exit\n"
					+ "Enter your choice: ");

			// get user input
			choice = scanner.nextInt();
			
			if(choice<0 || choiceIndex<choice) {
				System.out.println("!! Enter a valid input !!");
				continue;
			}
			
			if(choice == 0) {
				System.exit(0); // end the program
			}else if(0<choice && choice<choiceIndex) {
				String category = categories.get(choice);
				
				switch (category) {
				case "Notebook":
					Notebook.notebookOperations();
					break;
				case "Mobile phone":
					System.out.println();
					MobilePhone.phoneOperations();
					break;

				default:
					break;
				}
			}else {
				Brand.listBrands();
			}
		}
	}

}