package controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;

import resources.Resource;
import userinterface.SimulationConfiguration;

public class SimController {
	
	public void start(){
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
			displayTotalRevenue();
		} else {
			System.out.println("Invalid input: " + input);
		}

	}
	
	private void displayTotalRevenue(){
		double totalRevFromIterations = 0.0;
		for (Iterator<Double> iterator = Resource.getTotalRevenueIterator(); iterator.hasNext();) {
			totalRevFromIterations += iterator.next().doubleValue();
		}
		NumberFormat formatter = new DecimalFormat("#0.00");
		System.out.println("Total Revenue: $" + formatter.format(totalRevFromIterations));
	}
	

}
