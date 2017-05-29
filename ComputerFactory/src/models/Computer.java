package models;

import events.ComputerEvents;

public class Computer  implements Runnable {
	private final int id;
	private ComputerEvents currentEventType;
	
	public Computer(int id) {
		this.id = id;
		this.currentEventType = ComputerEvents.STARTING;
	}
	
	@Override
	public void run() {
		//aquire mobo
				//process mbo install 
				//switch state
				//repeat with different parts until finished
	}

	public double calculateDuration(){
		double duration = currentEventType.getAvgDuration();
		double actualTime = (-Math.log(1 - Math.random()) * duration);
		return actualTime;
	}
	
	public ComputerEvents getEventType(){
		return currentEventType;
	}


}
