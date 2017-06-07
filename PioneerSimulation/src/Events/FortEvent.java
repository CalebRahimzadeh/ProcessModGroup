package Events;

import Tools.ClockTimeResources;
import models.Fort;
import resources.Resource;

public class FortEvent {
	private PioneerEvent eventType;
	private int pioneerId;
	private long duration;
	private long clockTimestamp;
	private Fort fort;
	public FortEvent(int pioneerId, PioneerEvent type, Fort fort){
		this.setPioneerId(pioneerId);
		this.setEventType(type);
		this.fort = fort;
	}
	
	//returns next event to go to
	public void calculateTime(){
		long meanTime = -1;
		switch(eventType){
		case TREATED_PARTY:
			meanTime = ClockTimeResources.getMeanTreatTime();
			break;
		case DEPARTED:
			meanTime = ClockTimeResources.getMeanLeavingTime();
			break;
		case ENTEREDFORT:
			meanTime = ClockTimeResources.getMeanWaitingForSuppliesTime();
			break;
		case GOTSUPPLIES:
			meanTime = ClockTimeResources.getGatherSuppliesTime();
			break;
		case TRAVELED:
			meanTime = ClockTimeResources.getMeanTravelTime();
			break;
		case DEATH:
			meanTime = 1000;
		default:
			break;
		
		}
		long valToIncrement =  Math.round(Math.log((1 - Math.random()) * meanTime));
		ClockTimeResources.incrementClock(valToIncrement);
		this.clockTimestamp = ClockTimeResources.getSimulationClock();
	}
	public PioneerEvent getEventType() {
		return eventType;
	}
	public void setEventType(PioneerEvent eventType) {
		this.eventType = eventType;
	}
	public int getPioneerId() {
		return pioneerId;
	}
	public void setPioneerId(int pioneerId) {
		this.pioneerId = pioneerId;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public long getClockTimeStamp(){
		return clockTimestamp;
	}

	public Fort getFort(){
		return fort;
	}
	@Override
	public String toString() {
		return "FortEvent [eventType=" + eventType + ", pioneerId=" + pioneerId + ", duration=" + duration
				+ ", clockTimestamp=" + clockTimestamp + "/hour(s)]";
	}
	
		

}
