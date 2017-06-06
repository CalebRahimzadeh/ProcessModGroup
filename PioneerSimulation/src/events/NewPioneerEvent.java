package events;

import java.util.ArrayList;
import java.util.List;

//import csc360.doctor_office_simulator.models.Patient;
//import csc360.doctor_office_simulator.utilities.Clock;
//import csc360.doctor_office_simulator.utilities.PatientGenerator;
//import csc360.doctor_office_simulator.utilities.Scheduler;
import listeners.ISimEventListener;
import models.PioneerFamily;
import uitilities.Clock;
import uitilities.PioneerGenerator;
import uitilities.Scheduler;

public class NewPioneerEvent implements ISimulationEvent{

	private Clock clock;
	private int time;
	private Scheduler scheduler;
	private List<ISimEventListener> listeners;
	private static int pioneerID = 0;
	
	public NewPioneerEvent(int time, Scheduler scheduler, Clock clock) {
		this.time = time;
		this.scheduler = scheduler;
		this.clock = clock;
		this.listeners = new ArrayList<>();
		
	}
	@Override
	public void run() {
		performAction();
	}

	@Override
	public void performAction() {
		PioneerGenerator gen = new PioneerGenerator(clock, scheduler);
		scheduler.scheduleFutureEvent(gen.generatePatient());
		AquireResourceEvent aquireEvent = new AquireResourceEvent(time, clock, scheduler, new PioneerFamily(pioneerID++, 5));
		scheduler.schedulePresentEvent(aquireEvent);
		for (ISimEventListener iSimEventListener : listeners) {
			iSimEventListener.onComplete(this);
		}
	}

	@Override
	public void subscribe(ISimEventListener listener) {
		// TODO Auto-generated method stub
		listeners.add(listener);
		
	}

	@Override
	public void unsubscribe(ISimEventListener listener) {
		// TODO Auto-generated method stub
		listeners.remove(listener);
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return this.time;
	}
	
	@Override
	public String toString(){
		return "Thread " + Thread.currentThread().getId() + " New Patient.";
	}

}
