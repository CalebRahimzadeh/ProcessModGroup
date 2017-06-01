package events;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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
		NumberFormat formatter = new DecimalFormat("#0.00");
		return "computerID: " + getCOMPUTER_ID() + " [compEventType: " + compEventType.name()  + ", clockTime= "
				+ formatter.format(clockTime) + "]";
	}
	
	
	
}
