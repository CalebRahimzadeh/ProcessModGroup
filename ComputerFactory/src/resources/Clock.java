package resources;

public class Clock {
	private double clockTime;
	public Clock(){
		this.clockTime = 0.0;
	}
	
	public synchronized void incrementClock(double valToIncrement){
		this.clockTime += valToIncrement;
	}
}
