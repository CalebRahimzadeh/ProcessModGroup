package resources;

public final class Clock {
	private static double clockTime;
	private static volatile Clock instance;
	private Clock(){
		this.clockTime = 0.0;
	}
	
	public static Clock getInstance(){
		if(instance == null){
			synchronized(Clock.class){
				if (instance == null) {
                    instance = new Clock();
                }
			}
		}
		return instance;
	}
	
	public synchronized void incrementClock(double valToIncrement){
		clockTime += valToIncrement;
	}
	
	public double getCurrentTime(){
		return clockTime;
	}
}
