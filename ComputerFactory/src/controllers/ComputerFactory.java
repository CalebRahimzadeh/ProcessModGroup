package controllers;

import java.time.Clock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import models.AssemblyLine;
import userinterface.SimulationConfiguration;

public class ComputerFactory {
	private final int COMP_AMT_TO_PROCESS = 2;
	private final int compRevenue = 500;
	private final int totalPartExpense = 250;
	private final int WORK_WEEK_ITERATIONS_TO_SIM = 100;
	ExecutorService assemblyPool = Executors.newFixedThreadPool(1);
//	private final static int WEEKLY_WORK_DAYS_TO_SIM = 5;
//	private final static int DAILY_WORK_HOURS_TO_SIM = 8;
//  ExecutorService compPool = Executors.newFixedThreadPool(COMP_AMT_TO_PROCESS);
	
	public ComputerFactory(){
	}
	
	private void startSimulation(){
		final int NORMAL_SIM = 1;
		final int NORMAL_SIM_100_TIMES = 2;
		
		SimulationConfiguration.promptForSimulationType();
		int input = SimulationConfiguration.readInput();
		if(input == 1){
			//new assembly line
			//submit
			assemblyPool.submit(new AssemblyLine(convertWorkHoursToMilli()));
			
		} else if(input == 2) {
			//run 100 times. Might not need here just in case
		} else{
			System.out.println("Invalid input: " + input);
		}
	}
	
	
	private void generateComputers(){
		
	}
	
	private long convertWorkHoursToMilli(){
		final long MILLI_IN_ONE_HOUR = 3600000;
		final int WEEKLY_WORK_DAYS_TO_SIM = 5;
		final int DAILY_WORK_HOURS_TO_SIM = 8;
	
		int hoursAWeek = WEEKLY_WORK_DAYS_TO_SIM * DAILY_WORK_HOURS_TO_SIM;
		return hoursAWeek * MILLI_IN_ONE_HOUR;
	}
	
}
