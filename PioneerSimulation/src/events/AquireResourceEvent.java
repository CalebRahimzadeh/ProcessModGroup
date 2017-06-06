package events;

import java.util.ArrayList;
import java.util.List;
//
//import csc360.doctor_office.statistics.Statistics;
//import csc360.doctor_office_simulator.models.Patient;
//import csc360.doctor_office_simulator.models.Resource;
//import csc360.doctor_office_simulator.utilities.Clock;
//import csc360.doctor_office_simulator.utilities.Scheduler;
import listeners.ISimEventListener;
import models.PioneerFamily;
import uitilities.Clock;
import uitilities.Scheduler;

public class AquireResourceEvent implements ISimulationEvent{
	private final int time;
	private final Clock clock;
	private final Scheduler scheduler;
	private final PioneerFamily family;
	private List<ISimEventListener> listeners;
	
	public AquireResourceEvent(int time, Clock clock, Scheduler scheduler, PioneerFamily family) {
		this.time = time;
		this.clock = clock;
		this.scheduler = scheduler;
		this.family = family;
		this.listeners = new ArrayList<>();
	}

	@Override
	public void run() {
		performAction();
	}

	@Override
	public void performAction() {
		double MEAN_TIME = 1.0;
		Resource doc = scheduler.getClinic().getDoctor(); //get fort get food
		doc.aquire(patient);
		if(doc.isLockedByConsumer(patient)){
			scheduler.scheduleFutureEvent(new ReleaseResourceEvent(time + 20, scheduler, clock, patient));
			for (ISimEventListener iSimEventListener : listeners) {
				iSimEventListener.onComplete(this);
			}
		}else{
			for (ISimEventListener iSimEventListener : listeners) {
				iSimEventListener.onInComplete(this);
			}
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
		return "Thread " + Thread.currentThread().getId() + " Aquire resource.";
	}

}
