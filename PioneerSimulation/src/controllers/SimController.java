package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Events.FortEvent;
import Events.PioneerEvent;
import models.Fort;
import models.Pioneer;
import resources.Resource;

public class SimController {
	private List<Pioneer> pioneers;// = new ArrayList<>();
	private Fort fort;
	private static final int STARVATION_TIMEOUT = 2000;
	private static final int FORT_FOOD_CAP = 10000;
	private static final int FORT_BLANKET_CAP = 500;
	private static final int FORT_DOC_CNT = 1;
	private static final int FORT_WAGON_PART_CAP = 200;
	private static final int NUM_OF_PIONEERS = 25;

	public SimController() {
		pioneers = new ArrayList<>();
		this.fort = new Fort(FORT_FOOD_CAP, FORT_BLANKET_CAP, FORT_WAGON_PART_CAP, FORT_DOC_CNT);
		populatePioneers(NUM_OF_PIONEERS);
	}

	public void populatePioneers(int numOfPioneers) {
		for (int i = 0; i < numOfPioneers; i++) {
			pioneers.add(new Pioneer(i, fort));
		}
	}

	public void start() {
		for (Pioneer pioneer : pioneers) {
			new Thread(pioneer).start();
			synchronized (fort) {
				try {
					fort.wait(STARVATION_TIMEOUT);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(pioneer.getId() + "::" + pioneer.toString());
		}
		System.out.println("-----------------------------PIONEER INFO---------------------------------\n");
		for (Pioneer pioneer : pioneers) {
			System.out.println(pioneer.toString());
		}
		System.out.println("--------------------------------CURRENT FORT INFO--------------------------------------\n"
				+ fort.toString());
		printSuppliedEvents();
		printTravelledEvents();
		printTreatedEvents();
		printDeathEvents();
		
		try {
			Thread.currentThread().sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("====================================STATISTICS=========================================");
		printFortStats();
		printSupplyStats();
		printHealthRelatedStats();
	}

	private void printSuppliedEvents() {
		System.out.println("------------------------------SUPPLIED EVENTS-------------------------------\n");
		for (Iterator iterator = Resource.getSuppliedEvents(); iterator.hasNext();) {
			FortEvent fortEvent = (FortEvent) iterator.next();
			System.out.println(fortEvent.toString());
		}
	}

	private void printTravelledEvents() {
		System.out.println("------------------------------TRAVELLED EVENTS-------------------------------\n");
		for (Iterator iterator = Resource.getTraveledEvents(); iterator.hasNext();) {
			FortEvent fortEvent = (FortEvent) iterator.next();
			System.out.println(fortEvent.toString());
		}
	}

	private void printTreatedEvents() {
		System.out.println("------------------------------TREATED EVENTS-------------------------------\n");
		for (Iterator iterator = Resource.getTreatedEvents(); iterator.hasNext();) {
			FortEvent fortEvent = (FortEvent) iterator.next();
			System.out.println(fortEvent.toString());
		}
	}

	private void printDeathEvents() {
		System.out.println("------------------------------DEATH EVENTS-------------------------------\n");
		
		for (Iterator iterator = Resource.getDeathEvents(); iterator.hasNext();) {
			FortEvent fortEvent = (FortEvent) iterator.next();
			System.out.println(fortEvent.toString());
		}
	}

	private void printFortStats() {
		System.out.println("------------------------------Fort Resource Stats-------------------------------\n");
		long foodAvg = 0;
		long blanketAvg = 0;
		long partAvg = 0;
		int count = 0;
		for (Iterator iterator = Resource.getAllEvents(); iterator.hasNext();) {
			FortEvent fortEvent = (FortEvent) iterator.next();
			foodAvg += fortEvent.getFort().getFoodCapacity();
			blanketAvg += fortEvent.getFort().getBlanketCapacity();
			partAvg += fortEvent.getFort().getWagonParts();
			System.out.println(fortEvent.toString());
			count++;
		}
		System.out.println("\t___Resources Averages:____ ");
		System.out.println("Average food capacity left: " + foodAvg / count + " Original Val: " + FORT_FOOD_CAP);
		System.out.println("Average blanket capacity left: " + blanketAvg / count + " Original Val: " + FORT_BLANKET_CAP);
		System.out.println("Average wagon part capacity left: " + partAvg / count + " Original Val: " +  FORT_WAGON_PART_CAP);
	}
	
	public void printSupplyStats(){
		int avg = 0;
		int cnt = 0;
		
		for (Iterator iterator = Resource.getSuppliedEvents(); iterator.hasNext();) {
			FortEvent fortEvent = (FortEvent) iterator.next();
			avg += fortEvent.getClockTimeStamp();
			cnt++;
		}
		System.out.println("Average time spent supplying pioneers: " + avg/cnt + " hour(s).");
	}
	public void printHealthRelatedStats(){
		int treatAvg = 0;
		int treatCnt= 0;
		int deathAvg = 0;
		int deathCnt= 0;
		int totalCnt = 0;
		for (Iterator iterator = Resource.getDeathEvents(); iterator.hasNext();) {
			FortEvent fortEvent = (FortEvent) iterator.next();
			deathCnt++;
			totalCnt++;
		}
		for (Iterator iterator = Resource.getTreatedEvents(); iterator.hasNext();) {
			FortEvent fortEvent = (FortEvent) iterator.next();
			treatCnt++;
			totalCnt++;
		}
		System.out.println("\t___Health STATS___\n");
		System.out.println("Total Amount visited: " + totalCnt);
		System.out.println("Amount Treated: " + treatCnt);
		System.out.println("Amount Died: " + deathCnt);
		System.out.println("Average Treatment: " + totalCnt/treatCnt );
		System.out.println("Average Death: " + deathCnt/treatCnt );
	}
}
