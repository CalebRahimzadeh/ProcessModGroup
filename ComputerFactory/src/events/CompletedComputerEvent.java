package events;

import models.Computer;

public class CompletedComputerEvent {
	private final ComputerEvents compEventType;
	private final Computer computer;
	private final double clockTime;
	
	public CompletedComputerEvent(Computer comp, ComputerEvents eventType, double clockTime){
		this.compEventType = eventType;
		this.computer = comp;
		this.clockTime = clockTime;
	}

	public int getCOMPUTER_ID() {
		return computer.getID();
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

	@Override
	public String toString() {
		return "CompletedComputerEvent [compEventType=" + compEventType.name() + ", computer=" + getCOMPUTER_ID() + ", clockTime="
				+ clockTime + ", getCOMPUTER_ID()=" + getCOMPUTER_ID() + "]";
	}
	
	
	
}
