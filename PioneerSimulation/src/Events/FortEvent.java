package Events;

import Tools.ClockTimeResources;

public class FortEvent {
	private PioneerEvent eventType;
	private int pioneerId;
	private long duration;
	
	public FortEvent(int pioneerId, PioneerEvent type){
		this.setPioneerId(pioneerId);
		this.setEventType(type);
	}
	public long calculateDuration(){
		long meanTime = -1;
		switch(eventType){
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
		default:
			break;
		
		}
		return Math.round(Math.log((1 - Math.random()) * meanTime));
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
	
		

}
