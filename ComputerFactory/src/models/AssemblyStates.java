package models;

public enum AssemblyStates {
	WAITING(0),
	INSTALL_MOTHERBOARD(10),
	INSTALL_PROCESSOR(2),
	INSTALL_HDD(1),
	INSTALL_RAM(0.75),
	FINISHED(0);
	
	private double avgDuration;
	
	AssemblyStates(double avgDuration){
		this.avgDuration = avgDuration;
	}
	
	public double getAvgDuration() {
		return avgDuration;
	}
	
	
}
