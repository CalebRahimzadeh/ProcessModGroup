package controllers;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import events.CompletedComputerEvent;
import models.AssemblyLine;
import models.Computer;
import models.Employee;
import resources.Clock;
import resources.Resource;
import statistics.Statistic;
import userinterface.SimulationConfiguration;

public class ComputerFactory {
	private final int WORK_WEEK_ITERATIONS_TO_SIM = 100;
	private final int COMPS_TO_PROCESS_AT_A_TIME = 5; // number of comps to be
	private final int numGenerated = 0;													// submited //might be
	private static final int NUM_OF_WEEKS_TO_SIM = 100;												// employee count???
	private ExecutorService assemblyLine;

	public ComputerFactory() {
		Employee emp = new Employee(5, 2, 1, 2);
		assemblyLine = Executors.newFixedThreadPool(COMPS_TO_PROCESS_AT_A_TIME);
	}

	public void startSimulation() {
		generateComps();
		printFinish();
		//statistic stuff
	}
	
	public double getNumFinished(){
		double numFinished = Resource.getNumOfComputersCompleted();
		Clock.resetClock();
		return numFinished;
	}
	
	
	private  void generateComps(){
		double limit = convertWorkHoursToMinutes();
		int idIncrementer = 0;
		while(Clock.getCurrentTime() < limit){
			assemblyLine.submit(new Computer(idIncrementer));
			idIncrementer++;
			try {
				final int VAL_TO_SLEEP = 25;
				Thread.sleep(VAL_TO_SLEEP);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private double convertWorkHoursToMinutes() {
		final int MINUTES_IN_ONE_HOUR = 60;
		final int WEEKLY_WORK_DAYS_TO_SIM = 5;
		final int DAILY_WORK_HOURS_TO_SIM = 8;

		int hoursAWeek = WEEKLY_WORK_DAYS_TO_SIM * DAILY_WORK_HOURS_TO_SIM;
		return hoursAWeek * MINUTES_IN_ONE_HOUR;
	}
	
	private void printFinish(){
		System.out.println("---------------------------------------Finished Computers--------------------------------------------");
		Iterator<CompletedComputerEvent> finishedEventIterator = Resource.getFinishedEventsIterator();
		while(finishedEventIterator.hasNext()){
			System.out.println(finishedEventIterator.next().toString());
		}
	}

}
