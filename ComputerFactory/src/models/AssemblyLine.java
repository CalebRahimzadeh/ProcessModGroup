package models;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import events.ComputerEvent;
import resources.Clock;

public class AssemblyLine implements Runnable{
	private final int compRevenue = 500;
	private final int totalPartExpense = 250;
	private final int COMPS_TO_PROCESS_AT_A_TIME = 2; //number of comps to be submited //might be employee count???
	private final long RUN_LIMIT;
	private ExecutorService compPool;
	private Clock clock;
	
	public AssemblyLine(long limit){
		this.clock = Clock.getInstance();
		this.RUN_LIMIT = limit;
		 this.compPool = Executors.newFixedThreadPool(COMPS_TO_PROCESS_AT_A_TIME);
		 
	}
	
	@Override
	public void run() {
		//Generates a computer until clock limit is hit;
		int idIncrementer = 0;
		while(Clock.getCurrentTime() < RUN_LIMIT){
			compPool.submit(new Computer(idIncrementer));
			idIncrementer++;
		}
		//new comp
		//submit comp
		
	}
	

}
