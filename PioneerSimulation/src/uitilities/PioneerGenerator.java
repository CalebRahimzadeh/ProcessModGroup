package uitilities;

import java.util.Random;

import events.NewPioneerEvent;

public class PioneerGenerator {
	private Clock clock;
	private Scheduler scheduler;
	
	private static final double MEAN_TIME = 20; //weeks
	public PioneerGenerator(Clock clock, Scheduler scheduler){
		this.clock = clock;
		this.scheduler = scheduler;
	}
	
	public NewPioneerEvent generatePatient(){
		int eventTime = (int)generateTime();
		eventTime += clock.getTime();
		NewPioneerEvent event = new NewPioneerEvent((int)eventTime, scheduler, clock);
		return event;
	}
	
	private double generateTime(){
		double generateTime = 0;
		Random rand = new Random();
		generateTime = (-Math.log(1 - rand.nextDouble() -.1) * MEAN_TIME);
		return generateTime;
		
	}
}
