package controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComputerFactory {
	private final int COMP_AMT_TO_PROCESS = 2;
	private final int compRevenue = 500;
	private final int totalPartExpense = 250;
	private ExecutorService compPool;
	private final int WORK_WEEK_ITERATIONS_TO_SIM = 100;
	
//	private final static int WEEKLY_WORK_DAYS_TO_SIM = 5;
//	private final static int DAILY_WORK_HOURS_TO_SIM = 8;
	
	public ComputerFactory(){
		 this.compPool = Executors.newFixedThreadPool(COMP_AMT_TO_PROCESS);
		 //new comp
		 //for the time is not finished
		 
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
