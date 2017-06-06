package events;

import java.util.ArrayList;
import java.util.List;

import listeners.ISimEventListener;
import models.Consumer;
import models.Resource;
import uitilities.Clock;
import uitilities.Scheduler;

public class ReleaseResourceEvent implements ISimulationEvent {

	private int time;
	private Scheduler scheduler;
	private Clock clock;
	private Consumer consumer;
	private List<ISimEventListener> listeners;
	
	public ReleaseResourceEvent(int time, Scheduler scheduler, Clock clock, Consumer consumer) {
		this.time = time;
		this.scheduler = scheduler;
		this.clock = clock;
		this.consumer = consumer;
		this.listeners = new ArrayList<>();
	}
	
	@Override
	public void run() {
		performAction();
	}

	@Override
	public void performAction() {
		Resource resource = scheduler.getTrail().getFort(0);
		resource.release(consumer);
		for (ISimEventListener iSimEventListener : listeners) {
			iSimEventListener.onComplete(this);
		}
	}

	@Override
	public void subscribe(ISimEventListener listener) {
		listeners.add(listener);
		
	}

	@Override
	public void unsubscribe(ISimEventListener listener) {
		listeners.remove(listener);
	}

	@Override
	public int getTime() {
		return time;
	}
	
	@Override
	public String toString(){
		return "Thread " + Thread.currentThread().getId() + " Done and leave.";
	}

}
