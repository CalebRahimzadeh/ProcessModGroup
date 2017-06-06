package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import events.CompletedEvent;
import events.PioneerStates;
import interfaces.ITransportMethods;
import models.transport.HandCart;
import models.transport.Horse;
import models.transport.Oxen;
import resources.Clock;
import resources.SimulationResources;

public class PioneerFamily implements Runnable {

	private final double OREGON_DIST_IN_METERS = 3218688;
	private final int ID;
	private int NUM_OF_PEOPLE;
	private final int originalFamCnt;
	private final ITransportMethods transportMethod;
	private final static Clock clock = new Clock();
	private Queue<PioneerStates> future;
	private PioneerStates present;

	public PioneerFamily(int id, int famNum) {
		this.ID = id;
		this.NUM_OF_PEOPLE = famNum;
		this.originalFamCnt = famNum;
		this.transportMethod = genTypeOfTransport();
		this.future = new LinkedList<>();
		populateFuture();
		this.present = future.poll();
		initFoodAndCapacity();
	}

	private void populateFuture() {
		PioneerStates states[] = PioneerStates.values();
		for (int i = 0; i < states.length - 2; i++) {
			if (!states[i].equals(PioneerStates.DEATH)) {
				this.future.add(states[i]);
				System.out.println("BLARG: " + states[i]);
			}
		}
	}

	private void initFoodAndCapacity() {
		int initalFoodAmt = 150;
		transportMethod.setFood(initalFoodAmt);
		transportMethod.setCapacity((transportMethod.getCapacity() - initalFoodAmt));
	}

	private ITransportMethods genTypeOfTransport() {
		Random rand = new Random();
		ITransportMethods transportMethod;
		int whichOneToChoose = rand.nextInt(3);
		if (whichOneToChoose == 1) {
			transportMethod = new HandCart();
		} else if (whichOneToChoose == 2) {
			transportMethod = new Horse();
		} else {
			transportMethod = new Oxen();
		}
		return transportMethod;
	}

	private boolean isLooping = true;
	private boolean oregonReached = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isLooping) {
			synchronized (clock) {
				if (clock.getTime() >= OREGON_DIST_IN_METERS) {
					present = PioneerStates.ARRIVED;
					oregonReached = true;
				}
				advanceTime(present);
				NumberFormat formatter = new DecimalFormat("#0.00");
				System.out.println(this.ID + " " + present.name() + " time: " + formatter.format(clock.getTime()));
				SimulationResources.addCompletedEvent(
						new CompletedEvent(this.ID, this.present, this.originalFamCnt, NUM_OF_PEOPLE, clock.getTime(),
								this.transportMethod.getHealth(), this.transportMethod.getFoodAmt(), transportMethod));
//				consumeFood();
//				checkHealth();
				switch (present) {
				case STARTING_JOURNEY:
					present = PioneerStates.TRAVEL;
					break;
				case TRAVEL:
					Fort fort = SimulationResources.getFortBasedOffClock(clock.getTime());
					if (fort != null) {
						present = PioneerStates.ARRIVED;
							boolean didSupply = fort.getSuppliesFromFort(this);
					} else {
						present = PioneerStates.STOP;
					}
					break;
				case STOP:
					present = PioneerStates.CONTINUE;
					break;
				case CONTINUE:
					present = PioneerStates.TRAVEL;
					break;
				case ARRIVED:
					if (oregonReached) {
						present = PioneerStates.ARRIVED;
						SimulationResources.addFinishedEvents(new CompletedEvent(this.ID, this.present,
								this.originalFamCnt, NUM_OF_PEOPLE, clock.getTime(), this.transportMethod.getHealth(),
								this.transportMethod.getFoodAmt(), transportMethod));
						isLooping = false;
						Thread.currentThread().interrupt();
					} else {
						present = PioneerStates.STOP;
					}
					break;
				case DEATH:
					// stop
					isLooping = false;
					System.out.println("DED");
					break;
				}
			
			}

		}
		System.out.println("wooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
	}

//	private void processTravelLoop() {
//		consumeFood();
//		checkHealth();
//		switch (present) {
//		case STARTING_JOURNEY:
//			present = PioneerStates.TRAVEL;
//			break;
//		case TRAVEL:
//			Fort fort = SimulationResources.getFortBasedOffClock(clock.getTime());
//			if (fort != null) {
//				present = PioneerStates.ARRIVED;
//			} else {
//				present = PioneerStates.STOP;
//			}
//			break;
//		case STOP:
//			present = PioneerStates.CONTINUE;
//			break;
//		case CONTINUE:
//			present = PioneerStates.TRAVEL;
//			break;
//		case ARRIVED:
//			if (clock.getTime() >= OREGON_DIST_IN_METERS) {
//				present = PioneerStates.ARRIVED;
//				SimulationResources.addFinishedEvents(
//						new CompletedEvent(this.ID, this.present, this.originalFamCnt, NUM_OF_PEOPLE, clock.getTime(),
//								this.transportMethod.getHealth(), this.transportMethod.getFoodAmt(), transportMethod));
//				isLooping = false;
//			} else {
//				present = PioneerStates.STOP;
//			}
//			break;
//		case DEATH:
//			// stop
//			System.out.println("DED");
//			break;
//		}
//		advanceTime(present);
//		SimulationResources.addCompletedEvent(
//				new CompletedEvent(this.ID, this.present, this.originalFamCnt, NUM_OF_PEOPLE, clock.getTime(),
//						this.transportMethod.getHealth(), this.transportMethod.getFoodAmt(), transportMethod));
//
//		present = PioneerStates.TRAVEL;
//		advanceTime(present);
//		System.out.println(ID + " " + present.name());
//		SimulationResources.addCompletedEvent(
//				new CompletedEvent(this.ID, this.present, this.originalFamCnt, NUM_OF_PEOPLE, clock.getTime(),
//						this.transportMethod.getHealth(), this.transportMethod.getFoodAmt(), transportMethod));
//		consumeFood();
//		checkHealth();
//		System.out.println("WUT");
//		Fort fort = SimulationResources.getFortBasedOffClock(clock.getTime());
//		if (fort != null) {
//			present = PioneerStates.ARRIVED;
//			advanceTime(present);
//			System.out.println(ID + " " + present.name());
//			SimulationResources.addCompletedEvent(
//					new CompletedEvent(this.ID, this.present, this.originalFamCnt, NUM_OF_PEOPLE, clock.getTime(),
//							this.transportMethod.getHealth(), this.transportMethod.getFoodAmt(), transportMethod));
//			consumeFood();
//			checkHealth();
//			try {
//				boolean didSupply = fort.getSuppliesFromFort(this);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		} else {
//			present = PioneerStates.STOP;
//
//			advanceTime(present);
//			System.out.println(ID + " " + present.name());
//
//			SimulationResources.addCompletedEvent(
//					new CompletedEvent(this.ID, this.present, this.originalFamCnt, NUM_OF_PEOPLE, clock.getTime(),
//							this.transportMethod.getHealth(), this.transportMethod.getFoodAmt(), transportMethod));
//		}
//		present = PioneerStates.CONTINUE;
//		advanceTime(present);
//		SimulationResources.addCompletedEvent(
//				new CompletedEvent(this.ID, this.present, this.originalFamCnt, NUM_OF_PEOPLE, clock.getTime(),
//						this.transportMethod.getHealth(), this.transportMethod.getFoodAmt(), transportMethod));
//	}

	private void advanceTime(PioneerStates event) {
		double duration = calculateDuration(event);
		clock.advanceTime(duration);
	}

	public ITransportMethods getTransport() {
		return this.transportMethod;
	}

	public int getFamilyCnt() {
		return this.NUM_OF_PEOPLE;
	}

	private void consumeFood() {
		int FoodAmt = transportMethod.getFoodAmt();
		int newFoodAmt = (FoodAmt - (NUM_OF_PEOPLE * 10));
		double newCapacity = (transportMethod.getCapacity() - newFoodAmt);
		transportMethod.setFood(newFoodAmt);
		transportMethod.setCapacity(newCapacity);
	}

	private double calculateDuration(PioneerStates currentEventType) {
		double duration = currentEventType.getTime();
		double actualTime = (-Math.log(1 - Math.random())) * duration;
		return actualTime* transportMethod.getSpeed();// * transportMethod.getSpeed();
	}

	private void checkHealth() {
		if (transportMethod.getHealth() == 0) {
			double time;
			synchronized (clock) {
				time = clock.getTime();
			}
			this.present = PioneerStates.DEATH;
			advanceTime(present);
			SimulationResources.addDeadPioneerEvent(
					new CompletedEvent(this.ID, this.present, this.originalFamCnt, NUM_OF_PEOPLE, time,
							this.transportMethod.getHealth(), this.transportMethod.getFoodAmt(), transportMethod));
			// Thread.currentThread().interrupt();
			isLooping = false;
		} else if (transportMethod.getHealth() % 25 == 0) {
			NUM_OF_PEOPLE--;
		}

	}

	public int getID() {
		return this.ID;
	}
}
