package controllers;

import resources.Resource;
import userinterface.SimulationConfiguration;

public class Main {

	public static void main(String[] args) {
		double sum = 0;
		double totalAvg = 0;
		final int NUM_TO_ITERATE = 100;
		SimulationConfiguration.promptForSimulationType();
		int input = SimulationConfiguration.readInput();

		if (input == 1) {
			ComputerFactory compFact = new ComputerFactory();
			compFact.startSimulation();

		} else if (input == 2) {
			for (int i = 0; i < NUM_TO_ITERATE; i++) {
				ComputerFactory compFact = new ComputerFactory();
				compFact.startSimulation();
				sum += compFact.getNumFinished();
				Resource.clearFinishedList();
			}
			totalAvg = sum / NUM_TO_ITERATE;
			System.out.println("Average for 100 iterations: " + totalAvg);
		} else {
			System.out.println("Invalid input: " + input);
		}

	}
	// Answers
	// 1. in 5 days and 8 hours on average we created 172~184 computers
	// 2  Finished Computer Average for 100 iterations: 175.95

}
