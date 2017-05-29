package models;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import events.CompletedComputerEvent;
import resources.Clock;
import resources.Resource;

public class AssemblyLine implements Runnable{
	private final int compRevenue = 500;
	private final int totalPartExpense = 250;
	private final int COMPS_TO_PROCESS_AT_A_TIME = 2; //number of comps to be submited //might be employee count???
	private final long RUN_LIMIT;
	private ExecutorService compPool;
	
	public AssemblyLine(long limit){
		this.RUN_LIMIT = limit;
		 this.compPool = Executors.newFixedThreadPool(COMPS_TO_PROCESS_AT_A_TIME);
		 
	}
	
	@Override
	public void run() {
		//Generates a computer until clock limit is hit;
		//maybe put a sleep
		int idIncrementer = 0;
		while(Clock.getInstance().getCurrentTime() < RUN_LIMIT){
			compPool.submit(new Computer(idIncrementer));
			idIncrementer++;
		}
		System.out.println("-------------------------------------------------------------------");
		Iterator<CompletedComputerEvent> finishedEventIterator = Resource.getFinishedEventsIterator();
		while(finishedEventIterator.hasNext()){
			System.out.println(finishedEventIterator.next().toString());
		}
	}
	

}
