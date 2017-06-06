package controllers;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import events.CompletedEvent;
import models.Fort;
import models.PioneerFamily;
import resources.SimulationResources;

public class SimulationController {

	private static final int NUM_OF_FORTS = 10;
	private int caravanSize;
	private final double OREGON_DIST_IN_METERS = 3218688; //2000 miles
	private ExecutorService wagonTrain;
	private List<PioneerFamily> pioneerPool;
	
	public SimulationController(int numToExecute){
		wagonTrain = Executors.newFixedThreadPool(numToExecute);
	}
	
	//10 forts
	public void start(int caravanSize){
		this.caravanSize = caravanSize;
		this.pioneerPool = new ArrayList<>(caravanSize);
		generatePioneers();
		generateForts();
		submitPioneers();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		printFinishedInfo();
		synchronized (pioneerPool) {
			try {
				pioneerPool.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("BOOP");
//		printDeadPioneerInfo();
	}
	
	public void printDeadPioneerInfo(){
		System.out.println("----------------------------------------DEATH INFO----------------------------------------------");
		int avgTime = 0;
		int avgFoodLeft = 0;
		int avgHealth = 0;
		int avgFamilyCnt = 0;
		int elementCount = 0;
		
		for (Iterator<CompletedEvent> iterator = SimulationResources.getDeadPioneersIterator(); iterator.hasNext();) {
			CompletedEvent event = iterator.next();
			System.out.println(event.toString());
			avgTime += event.getClockTime();
			avgFoodLeft += event.getFoodAmt();
			avgHealth += event.getHealth();
			avgFamilyCnt  += event.getFamilyCnt();
			elementCount++;
			System.out.println(elementCount);
		}
		
		System.out.println("Average Distance Walked When Dead: " + avgTime/elementCount);
		System.out.println("Average Food When Dead: " + avgFoodLeft/elementCount);
		System.out.println("Average Health When Dead: " + avgHealth/elementCount);
		System.out.println("Average Family Size When Dead: " + avgFamilyCnt/elementCount);
	}
	
	public void printFinishedInfo(){
		System.out.println("----------------------------------------FINISHED(MADE IT TO OREGON) INFO----------------------------------------------");
		int avgTime = 0;
		int avgFoodLeft = 0;
		int avgHealth = 0;
		int avgFamilyCnt = 0;
		int elementCount = 0;
		
		for (Iterator<CompletedEvent> iterator =  SimulationResources.getFinishedEventsIterator(); iterator.hasNext();) {
			CompletedEvent event = iterator.next();
			System.out.println(event.toString());
			avgTime += event.getClockTime();
			avgFoodLeft += event.getFoodAmt();
			avgHealth += event.getHealth();
			avgFamilyCnt  += event.getFamilyCnt();
			elementCount++;
		}
		
		System.out.println("Average Distance Walked When Oregon is Reached: " + avgTime/elementCount);
		System.out.println("Average Food When Oregon is Reached: " + avgFoodLeft/elementCount);
		System.out.println("Average Health When Oregon is Reached: " + avgHealth/elementCount);
		System.out.println("Average Family Size When Oregon is Reached: " + avgFamilyCnt/elementCount);
	}
	
	public void printStarveInfo(){
		System.out.println("----------------------------------------STARVE INFO----------------------------------------------");
		int avgTime = 0;
		int avgFoodLeft = 0;
		int avgHealth = 0;
		int avgFamilyCnt = 0;
		int elementCount = 0;
		
		for (Iterator<CompletedEvent> iterator = SimulationResources.getStarveEventsIterator(); iterator.hasNext();) {
			CompletedEvent event = iterator.next();
			System.out.println(event.toString());
			avgTime += event.getClockTime();
			avgFoodLeft += event.getFoodAmt();
			avgHealth += event.getHealth();
			avgFamilyCnt  += event.getFamilyCnt();
			elementCount++;
		}
		
		System.out.println("Average Distance in Meters starting to Starve: " + avgTime/elementCount);
		System.out.println("Average Food While Starving: " + avgFoodLeft/elementCount);
		System.out.println("Average Health While Starving: " + avgHealth/elementCount);
		System.out.println("Average Family Size While Starving: " + avgFamilyCnt/elementCount);
	}
	
	public void generatePioneers() {
		int maxMembers = 7;
		int minMembers = 4;
		for (int i = 0; i < caravanSize; i++) {
			pioneerPool.add(new PioneerFamily(i,  nextIntBetweenRange(minMembers,maxMembers)));
		}
	}
	
	public void submitPioneers(){
		for (int i = 0; i < pioneerPool.size(); i++) {
			wagonTrain.submit(pioneerPool.get(i));
		}
	}
	
	public int nextIntBetweenRange(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public void generateForts() {
//		3 218 688 / 10 = 321 868.8 //per mile
		final double MILES_PER_FORT = 321868.8;
		final double MIN_MILES_PER_FORT = 100000;
		double overallDist = OREGON_DIST_IN_METERS;
		SimulationResources.addFort(new Fort(0, 50 ,overallDist)); // final fort
		for (int i = NUM_OF_FORTS; i >= 0; i--) {
			int wagonCapac = nextIntBetweenRange(50, 100);
			Fort fort = new Fort(i, wagonCapac, overallDist);
			SimulationResources.addFort(fort);
			overallDist -= nextIntBetweenRange((int)MIN_MILES_PER_FORT, (int)MILES_PER_FORT);
		}
	}
}
