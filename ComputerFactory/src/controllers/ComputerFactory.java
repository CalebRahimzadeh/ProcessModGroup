package controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import events.CompletedComputerEvent;
import models.Computer;
import models.Employee;
import resources.Clock;
import resources.Resource;
import statistics.Statistic;

public class ComputerFactory {
	private final int COMPS_TO_PROCESS_AT_A_TIME = 5; // number of comps to be
	private ExecutorService assemblyLine;
	private final Employee emp;
	
	public ComputerFactory() {
		final int moboEmpNum = 5; 
		final int processorEmpNum = 5;
		final int hddEmpNum = 5; 
		final int ramEmpNum = 5;
		
		this.emp = new Employee(moboEmpNum, processorEmpNum, hddEmpNum, ramEmpNum);
		this.assemblyLine = Executors.newFixedThreadPool(COMPS_TO_PROCESS_AT_A_TIME);
	}

	public void startSimulation() {
		generateComps();
		printFinish();
		//statistic stuff
		Statistic stat = new Statistic(emp);
		double revenue = stat.calculateTotalRevenue();
		Resource.addRevenueToList(revenue);
		NumberFormat formatter = new DecimalFormat("#0.00");
		System.out.println("Instance Total Revenue: $" + formatter.format(revenue));
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
