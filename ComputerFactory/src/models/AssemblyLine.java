package models;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import events.CompletedComputerEvent;
import interfaces.IEventHandler;
import interfaces.IFinishedSimListener;
import resources.Clock;
import resources.Resource;

public class AssemblyLine implements Runnable{
	private final int COMPS_TO_PROCESS_AT_A_TIME = 2; //number of comps to be submited //might be employee count???
	private final double RUN_LIMIT;
	private ExecutorService compPool;
	
	IFinishedSimListener listener;
	public AssemblyLine(int limit){
		
		Employee emp = new Employee(5,2,1,2);
		this.RUN_LIMIT = limit;
		this.compPool = Executors.newFixedThreadPool(COMPS_TO_PROCESS_AT_A_TIME);
	}
	
	@Override
	public void run() {
		//Generates a computer until clock limit is hit;
		int idIncrementer = 0;
			while(Clock.getInstance().getCurrentTime() < RUN_LIMIT){
				compPool.submit(new Computer(idIncrementer));
				idIncrementer++;
			}
		//TODO: find out where to call print all finished computers

		
	}

	private void printFinish(){
		System.out.println("-----------------------------------------------------------------------------------");
		Iterator<CompletedComputerEvent> finishedEventIterator = Resource.getFinishedEventsIterator();
		while(finishedEventIterator.hasNext()){
			System.out.println(finishedEventIterator.next().toString());
		}
	}


}
