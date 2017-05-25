package events;

public class ComputerEvent {
	private final int COMPUTER_ID;
	private ComputerEvents compEventType;
	
	public ComputerEvent(int id, ComputerEvents eventType){
		this.COMPUTER_ID = id;
		this.compEventType = eventType;
	}
	
	public double calculateDuration(){
		double duration = compEventType.getAvgDuration();
		double actualTime = (-Math.log(1 - Math.random()) * duration);
		return actualTime;
	}
	
	
}
