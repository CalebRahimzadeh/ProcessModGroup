package models;

import events.ComputerEvents;

public class Computer  implements Runnable {
	private final int id;
	private ComputerEvents compEventType;
	
	public Computer(int id, ComputerEvents compEventType) {
		this.id = id;
		this.compEventType = compEventType;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public double calculateDuration(){
		double duration = compEventType.getAvgDuration();
		double actualTime = (-Math.log(1 - Math.random()) * duration);
		return actualTime;
	}
	
	public ComputerEvents getEventType(){
		return compEventType;
	}


}
