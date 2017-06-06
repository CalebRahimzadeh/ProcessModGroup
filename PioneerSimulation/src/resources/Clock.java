package resources;

import java.util.ArrayList;
import java.util.List;

import listeners.OnClockAdvanceListener;

public class Clock {
	private List<OnClockAdvanceListener> listeners;
	
	private static double time = 0;
	
	public Clock() {
		this.time = 0;
		listeners = new ArrayList<>();
	}
	
	public synchronized void advanceTime(double timeToAdvance){
		if(timeToAdvance >= time) {
			time = timeToAdvance;
		}
	}
	
	public synchronized double getTime() {
		return time;
	}
}