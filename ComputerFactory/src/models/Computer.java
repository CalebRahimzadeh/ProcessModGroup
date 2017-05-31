package models;

import java.util.LinkedList;
import java.util.Queue;

import events.CompletedComputerEvent;
import events.ComputerEvents;
import resources.Clock;
import resources.Resource;

public class Computer implements Runnable {
	private final int id;
	private final Queue<ComputerEvents> future;
	private final double startConstructTime;

	public Computer(int id) {
		this.id = id;
		this.future = new LinkedList<ComputerEvents>();
		populateFutureList();
		this.startConstructTime = Clock.getCurrentTime();
	}

	private void populateFutureList() {
		ComputerEvents[] events = ComputerEvents.values();
		for (int i = 0; i < events.length; i++) {
			this.future.add(events[i]);
		}
	}

	@Override
	public void run() {
		// might need to loop? idk might break it
		while (future.size() != 0) {
			try {
					ComputerEvents present = future.poll();
					Employee.ChooseEmployeeToAquire(present);
					double duration = calculateDuration(present);
					Clock.incrementClock(duration);
					Employee.ChooseEmployeeToRelease(present);
					addEventData(present, Clock.getCurrentTime());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void addEventData(ComputerEvents present, double clockTime) {
		CompletedComputerEvent completedEvent = new CompletedComputerEvent(this, present, clockTime);
		System.out.println(completedEvent.toString());
		if (present.equals(ComputerEvents.FINISHED)) {
			Resource.addToCompletedEventList(completedEvent);
			Resource.addComputerFinishedEvent(completedEvent);
			System.out.println("Comp: " + id + " build finished");
		}
	}

	private double calculateDuration(ComputerEvents currentEventType) {
		double duration = currentEventType.getAvgDuration();
		double actualTime = (-Math.log(1 - Math.random())) * duration;
		return actualTime;
	}

	public int getID() {
		return this.id;
	}

}
