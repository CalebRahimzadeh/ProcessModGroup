package controllers;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import events.CompletedComputerEvent;
import models.AssemblyLine;
import resources.Resource;

public class ComputerFactory {
	private final int WORK_WEEK_ITERATIONS_TO_SIM = 100;
	private ExecutorService assemblyPool = Executors.newFixedThreadPool(1);
	
	public ComputerFactory(){
	}
	
	public void startSimulation(){
		AssemblyLine assemblyLine = new AssemblyLine(convertWorkHoursToMinutes());
		assemblyPool.submit(assemblyLine);
		
		
			System.out.println("-----------------------------------------------------------------------------------");
			Iterator<CompletedComputerEvent> finishedEventIterator = Resource.getFinishedEventsIterator();
			while(finishedEventIterator.hasNext()){
				System.out.println(finishedEventIterator.next().toString());
			}
		
		
//		SimulationConfiguration.promptForSimulationType();
//		int input = SimulationConfiguration.readInput();
//		if(input == 1){
//			//new assembly line
//			//submit
//			assemblyPool.submit(new AssemblyLine(convertWorkHoursToMilli()));
//			
//		} else if(input == 2) {
//			//run 100 times. Might not need here just in case
//		} else{
//			System.out.println("Invalid input: " + input);
//		}
		
	}
	
	private int convertWorkHoursToMinutes(){
		final int MINUTES_IN_ONE_HOUR = 60;
		final int WEEKLY_WORK_DAYS_TO_SIM = 5;
		final int DAILY_WORK_HOURS_TO_SIM = 8;
	
		int hoursAWeek = WEEKLY_WORK_DAYS_TO_SIM * DAILY_WORK_HOURS_TO_SIM;
		return hoursAWeek * MINUTES_IN_ONE_HOUR;
	}
	
}
