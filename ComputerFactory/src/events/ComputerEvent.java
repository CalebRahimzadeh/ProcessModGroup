package events;

import models.Computer;

public class ComputerEvent {
	private final int COMPUTER_ID;
	private final ComputerEvents compEventType;
	private final Computer computer;
	private final double clockTime;
	
	public ComputerEvent(int id, Computer comp, ComputerEvents eventType, double clockTime){
		this.COMPUTER_ID = id;
		this.compEventType = eventType;
		this.computer = comp;
		this.clockTime = clockTime;
	}

	public int getCOMPUTER_ID() {
		return COMPUTER_ID;
	}

	public ComputerEvents getCompEventType() {
		return compEventType;
	}

	public Computer getComputer() {
		return computer;
	}

	public double getClockTime() {
		return clockTime;
	}
	
	
}
