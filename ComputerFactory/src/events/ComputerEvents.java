package events;

public enum ComputerEvents {
	STARTING(0),
	FINISHED_MOTHERBOARD(10),
	FINISHED_PROCESSOR(2),
	FINISHED_HDD(1),
	FINISHED_RAM(0.75),
	FINISHED(0);
	
	private double avgDuration;
	
	ComputerEvents(double avgDuration){
		this.avgDuration = avgDuration;
	}
	
	public double getAvgDuration() {
		return avgDuration;
	}
}
