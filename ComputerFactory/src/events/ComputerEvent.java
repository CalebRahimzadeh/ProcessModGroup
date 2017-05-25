package events;

public class ComputerEvent {
	private final int computerId;
	private ComputerEvents compEventType;
	
	public ComputerEvent(int id, ComputerEvents eventType){
		this.computerId = id;
		this.compEventType = eventType;
	}
	
	public double calculateDuration(){
		double duration = compEventType.getAvgDuration();
		double actualTime = (-Math.log(1 - Math.random()) * duration);
		return actualTime;
	}
	
	
}
