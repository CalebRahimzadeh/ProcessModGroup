package userinterface;

import java.util.Scanner;

public class SimulationConfiguration {

	public static void promptForSimulationType(){
		//might not need run 100x
		System.out.println("\nWork Week: 5 days, 8 hours a day\n1. Run work-week\n2. Run work-week 100 times");
	}
	
	public static int readInput(){
		int readVal = -1;
		try(Scanner scan = new Scanner(System.in)){
			readVal = Integer.parseInt(scan.nextLine());
		}
		
		return readVal;
	}
}
