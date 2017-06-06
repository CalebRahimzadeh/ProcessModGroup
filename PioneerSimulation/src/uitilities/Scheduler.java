package uitilities;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import events.ISimulationEvent;
//import csc360.doctor_office.statistics.Statistics;
//import csc360.doctor_office_simulator.events.ISimulationEvent;
//import csc360.doctor_office_simulator.models.DoctorOffice;
import listeners.ISimEventListener;
import models.Trail;

public class Scheduler implements ISimEventListener{
	private final List<ISimulationEvent> presentList;
	private final PriorityQueue<ISimulationEvent> futureQueue;
	private Clock clock;
	private Trail trail;
	private boolean isRunning = false;
	private int inCompleteCnt = 0;
	private int totalAttempts = 0;
	
	public Scheduler(Clock clock, Trail trail) {
		this.futureQueue = new PriorityQueue<>(new SimComparator()); 
		this.presentList = new ArrayList<ISimulationEvent>();
		this.clock = clock;
		this.trail = trail;
	}
	
	public void start(){
		isRunning = true;
		while(isRunning) {
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println("Future List: " + futureQueue.peek());//.toString());
			System.out.println("Present List: " + presentList.toString());
			if(presentList.isEmpty()){	
				pullFromFuture();
			}else{
				List<ISimulationEvent> presentListCopy = cloneList(presentList);
				for (ISimulationEvent event : presentListCopy) {
					event.subscribe(this);
					event.run();
					event.unsubscribe(this);
				}
			}
		}
		System.out.println("Present List: " + presentList.toString());
	}
	public void scheduleFutureEvent(ISimulationEvent event) {
		futureQueue.add(event);
	}
	
	public void schedulePresentEvent(ISimulationEvent event) {
		presentList.add(event);
	}
	
	private List<ISimulationEvent> cloneList(List<ISimulationEvent> list) {
		List<ISimulationEvent> tmpList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			tmpList.add(list.get(i));
		}
		return tmpList;
	}

	@Override
	public void onComplete(ISimulationEvent event) {
		totalAttempts++;
		if(totalAttempts == presentList.size()){
			totalAttempts = 0;
			inCompleteCnt = 0;
		}
		presentList.remove(event);
	}
	
	@Override
	public void onInComplete(ISimulationEvent event) {
		inCompleteCnt++;
		totalAttempts++;
		if(inCompleteCnt == presentList.size()) {
			pullFromFuture();
			inCompleteCnt = 0;
			totalAttempts = 0;
		} else if(totalAttempts == presentList.size()){
			totalAttempts = 0;
			inCompleteCnt = 0;
		}
	}
	
	private void pullFromFuture() {
		ISimulationEvent event = futureQueue.remove();
		clock.advanceTime(event.getTime());
		presentList.add(event);
	}
	
	public Trail getTrail() {
		return trail;
	}
	
	public void removeFromList(ISimulationEvent event) {
		presentList.remove(event);
		
	}

	public void stop() {
		isRunning = false;
	}

}
