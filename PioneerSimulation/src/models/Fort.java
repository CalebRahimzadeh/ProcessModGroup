package models;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import events.CompletedEvent;
import resources.SimulationResources;

public class Fort extends Resource{
	private static final int FOOD_PER_PERSON = 15;
	private static final int TIMEOUT = 6000;
	private static final int DAMAGE = 15;
	private final int ID;
	private final double FORT_MILE_MARKER;
	private final int WAGON_CAPACTIY;
	private int foodCapacity = 1000;
	private Semaphore wagonSpots;
//	private Semaphore foodDispensers;
	
	public Fort(int id, int mileMarker){
		this.ID = id;
		this.FORT_MILE_MARKER = mileMarker;
		this.WAGON_CAPACTIY = 50;
		this.wagonSpots = new Semaphore(50);
	}
	public synchronized int getFoodCapacity() {
		return this.foodCapacity;
	}
	
	public synchronized double getMileMarker(){
		return FORT_MILE_MARKER;
	}

	
	public synchronized int takeFoodFromFort(int numOfPeople){
		int numToStock = (numOfPeople * FOOD_PER_PERSON);
		if(numToStock > foodCapacity){
			numToStock = foodCapacity;
		} else {	
			foodCapacity -= numToStock;
		}
		return numToStock;
	}
	
	public synchronized int getWagonCapacity(){
		return WAGON_CAPACTIY;
	}
	
	public synchronized boolean getSuppliesFromFort(PioneerFamily fam){
		final int numToAquire = 1;
		boolean didGetSupplied = false;
			try {
				if(wagonSpots.tryAcquire(numToAquire, TIMEOUT, TimeUnit.MILLISECONDS)){
					int currFoodCnt = fam.getTransport().getFoodAmt();
					currFoodCnt += takeFoodFromFort(fam.getFamilyCnt());
					fam.getTransport().setFood(currFoodCnt);
					didGetSupplied = true;
					wagonSpots.release();
				} else {
					int currHealth = fam.getTransport().getHealth();
					currHealth -= DAMAGE;
					fam.getTransport().setHealth(currHealth);
					//starve event
					
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return didGetSupplied;
	}
}
