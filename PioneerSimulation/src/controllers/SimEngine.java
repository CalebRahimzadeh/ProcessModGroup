package controllers;

//import csc360.doctor_office.statistics.Statistics;
//import csc360.doctor_office_simulator.models.DoctorOffice;
//import csc360.doctor_office_simulator.utilities.Clock;
//import csc360.doctor_office_simulator.utilities.PatientGenerator;
//import csc360.doctor_office_simulator.utilities.Scheduler;
import listeners.OnClockAdvanceListener;
import models.Trail;
import uitilities.Clock;
import uitilities.PioneerGenerator;
import uitilities.Scheduler;

public class SimEngine implements OnClockAdvanceListener{

	private final Clock clock;
	private final Trail trail;
	private final Scheduler scheduler;
	private boolean forever = false;
	private int duration = 0;
	
	public SimEngine() {
		this.clock = new Clock();
		this.trail = new Trail(1);
		this.scheduler = new Scheduler(clock, trail);
		clock.subscribe(this);
	}
	
	public void simulate(){
		forever = true;
		PioneerGenerator gen = new PioneerGenerator(clock, scheduler);
		scheduler.scheduleFutureEvent(gen.generatePatient());
		scheduler.start();
	}
	public void simulate(int duration){
		this.duration = duration;
		PioneerGenerator gen = new PioneerGenerator(clock, scheduler);
		scheduler.scheduleFutureEvent(gen.generatePatient());
		scheduler.start();
	}
	
	//time limit and forever options
	@Override
	public void onAdvance(int time) {
		//when do i stop
		System.out.println("Clock time after event: " + clock.getTime());
//		Statistics.addTime(clock.getTime());
		if(time > duration && !forever){
			scheduler.stop();
			int average = 0;//Statistics.calcAverage();
			System.out.println("If time is represented in Seconds:");
			System.out.println("----Average----\nSeconds: " + average + "\nMinutes: ~" + average/60);
			System.out.println("If time is represented in Minutes:");
			System.out.println("----Average----\nMinutes: " + average + "\nHours: ~" + (average/60));
		}
	}
	
}
