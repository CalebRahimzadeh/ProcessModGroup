package models;

import java.util.concurrent.Semaphore;

public class Fort {
	private static final int foodPerPerson = 20;
	private int foodCapacity;
	private int blanketCapacity;
	private int wagonParts;
	private Semaphore fortDoctors;
	
	
	
	public Fort(int foodCap, int docCnt){
		this.foodCapacity = foodCap;
		fortDoctors = new Semaphore(docCnt);
	}
	
	public int getFoodFromDepot(int pplCnt){
		int foodReturned = 0;
		if(pplCnt * foodPerPerson <= foodCapacity){
			foodReturned = pplCnt * foodPerPerson;
			foodCapacity -= foodReturned;
		}
		return foodReturned;
	}
	public int getFoodStorage(){
		return foodCapacity;
	}
}
