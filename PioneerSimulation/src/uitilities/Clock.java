package uitilities;

import java.util.ArrayList;
import java.util.List;

import listeners.OnClockAdvanceListener;

public class Clock {
	private List<OnClockAdvanceListener> listeners;
	
	private static final long MEAN_TIME = 20;
	private int time = 0;

	
	public Clock() {
		this.time = 0;
		listeners = new ArrayList<>();
	}
	
	public void advanceTime(int timeToAdvance){
		if(timeToAdvance >= time) {
			time = timeToAdvance;
		}
		
		for (OnClockAdvanceListener onClockAdvanceListener : listeners) {
			onClockAdvanceListener.onAdvance(time);
		}
		
	}
	
	public int getTime() {
		return time;
	}
	
	public void subscribe(OnClockAdvanceListener listener){
		listeners.add(listener);
	}
	public void unsubscribe(OnClockAdvanceListener listener){
		listeners.remove(listener);
	}
}
