package events;

public enum PioneerStates {
	STARTING_JOURNEY(1),
	TRAVEL(1),
	STOP(0.5),
	CONTINUE(0.1),
	ARRIVED(0.0);
	
	private double time;
	
	PioneerStates(double time){
		this.time = time;
	}
	
	public double getTime(){
		return time;
	}
}
