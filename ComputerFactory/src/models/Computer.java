package models;

import java.util.LinkedList;
import java.util.Queue;

import events.CompletedComputerEvent;
import events.ComputerEvents;
import resources.Clock;
import resources.Resource;

public class Computer  implements Runnable {
	private final int id;
	private final Queue<ComputerEvents> future; 
	
	public Computer(int id) {
		this.id = id;
		this.future = new LinkedList<ComputerEvents>();
		populateFutureList();	
	}
	
	private void populateFutureList(){
		ComputerEvents[] events = ComputerEvents.values();
		for (int i = 0; i < events.length; i++) {
			this.future.add(events[i]);
		}
	}
	
	@Override
	public void run() {
		//might need to loop? idk might break it
		while(future.size() != 0){
			ComputerEvents present = future.poll();
			try {
				Employee.ChooseEmployeeToAquire(present);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			double duration = calculateDuration(present);
			Clock clock = Clock.getInstance();
			clock.incrementClock(duration);
			Employee.ChooseEmployeeToRelease(present);
			
			addEventData(present, clock.getCurrentTime());
				
		}
	}
	private void addEventData(ComputerEvents present, double clockTime){
		CompletedComputerEvent completedEvent = new CompletedComputerEvent(this, present, clockTime);
		if(present.equals(ComputerEvents.FINISHED)){
			Resource.addToCompletedEventList(completedEvent);			
		}
	}
	

	private double calculateDuration(ComputerEvents currentEventType){
		double duration = currentEventType.getAvgDuration();
		double actualTime = (-Math.log(1 - Math.random()) * duration);
		return actualTime;
	}
	
	public int getID(){
		return this.id;
	}

}
