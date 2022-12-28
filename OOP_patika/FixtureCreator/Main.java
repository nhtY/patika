import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main{
	
	public static void main(String[] args) {
		
		// create a scanner object to take the user inputs:
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome To Fixture Creator");
		
		// take the number of teams:
		System.out.print("Enter the number of teams in the league: ");
		int numOfTeams = scanner.nextInt();
		
		System.out.println(); // line break.
		
		// To keep the team names, initialize an arrayList:
		ArrayList<String> teams = new ArrayList<>(numOfTeams);
		
		String teamName = "";
		scanner.nextLine();
		for(int i=0; i<numOfTeams; i++) {
			System.out.print((i+1) +"- Enter a team name: ");
			
			teamName = scanner.nextLine();
			teams.add(teamName);
		}
		
        if (numOfTeams%2 != 0) {
            teams.add("PASS");
            numOfTeams +=1;
        }
		
		// close the scanner because there will be no input anymore:
		scanner.close();
		
		// see if arrayList is filled by the inputs:
		for(String s : teams) {
			System.out.println(s);
		}
		

	        
        ArrayList<String> tempTeams = new ArrayList<>();
        
        while (0 < teams.size()) {
            int index = (int)(Math.random()*teams.size());
            tempTeams.add(teams.get(index));
            teams.remove(teams.get(index));
        }
        
        teams = tempTeams;
        
        int totalRound = numOfTeams - 1;
        int numMatchPerRound = numOfTeams / 2;
        
        LinkedHashMap<String, ArrayList<ArrayList<String>>> rounds = new LinkedHashMap<>();
        
        for (int i=0; i<totalRound; i++) {
            String round = String.valueOf(i + 1);
            rounds.put(round, new ArrayList<ArrayList<String>>());
        }
       
        for (int i=0; i<totalRound; i++) {
            ArrayList<String> host = new ArrayList<>();
            ArrayList<String> guest = new ArrayList<>();
            
            for (int j=0; j<numMatchPerRound; j++) {
                host.add(teams.get(j));
                guest.add(teams.get((numOfTeams - 1) - j)); 
            }
            
            String round = String.valueOf(i + 1);
            rounds.get(round).add(host);
            rounds.get(round).add(guest);
            
            ArrayList<String> newTeams = new ArrayList<>();
            
            newTeams.add(teams.get(0));
            newTeams.add(teams.get((numOfTeams - 1)));
            
            for (int k=1; k<teams.size() - 1; k++) {
                newTeams.add(teams.get(k));
            }
            
            teams = newTeams;
        }
        
        System.out.println();
        
        for (int i=0; i<2*totalRound; i++) {
            System.out.println((i + 1) + ". Round");
            for (int j=0; j<numMatchPerRound; j++) {
                if (i < totalRound) {
                    System.out.println(rounds.get(String.valueOf(i + 1)).get(0).get(j) + " vs " +  rounds.get(String.valueOf(i + 1)).get(1).get(j));
                } else {
                    System.out.println(rounds.get(String.valueOf(i + 1 - totalRound)).get(1).get(j) + " vs " +  rounds.get(String.valueOf(i + 1 - totalRound)).get(0).get(j));
                }
            }
            System.out.println();
        }
		
		
		
	}
	
}
