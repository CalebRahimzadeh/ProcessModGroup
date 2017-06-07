package models;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Fort {
	private static final int foodPerPerson = 20;
	private static final int BLANKETS_PER_PERSON = 1;
	private static final int WAGON_PARTS_PER_FAMILY = 4;
	private int foodCapacity;
	private int blanketCapacity;
	private int wagonParts;
	private Semaphore fortDoctors;
	
	
	
	public Fort(int foodCap, int blanketCapacity, int wagonParts, int docCnt){
		this.foodCapacity = foodCap;
		this.fortDoctors = new Semaphore(docCnt);
		this.blanketCapacity = blanketCapacity;
		this.wagonParts = wagonParts;
	}
	
	public int getFoodFromDepot(int pplCnt){
		int foodReturned = 0;
		if(pplCnt * foodPerPerson <= foodCapacity){
			foodReturned = pplCnt * foodPerPerson;
			foodCapacity -= foodReturned;
		}
		return foodReturned;
	}
	
	public int getBlanketsFromDeopt(int pplCnt){
		int blanketsReturned = 0;
		if(pplCnt * BLANKETS_PER_PERSON <= blanketCapacity){
			blanketsReturned = pplCnt * BLANKETS_PER_PERSON;
			this.blanketCapacity -= blanketsReturned; 
		}
		return blanketsReturned;
	}
	public int getWagonPartsFromDepot(){
		int partsReturned = 0;
		if(canFixWagon()){
			partsReturned = WAGON_PARTS_PER_FAMILY;
			wagonParts -= partsReturned;
		}
		return partsReturned;
	}
	
	public boolean canFixWagon() {
		return wagonParts >= WAGON_PARTS_PER_FAMILY;
	}

	public static int getFoodperperson() {
		return foodPerPerson;
	}

	public static int getBlanketsPerPerson() {
		return BLANKETS_PER_PERSON;
	}

	public static int getWagonPartsPerFamily() {
		return WAGON_PARTS_PER_FAMILY;
	}

	public int getFoodCapacity() {
		return foodCapacity;
	}

	public int getBlanketCapacity() {
		return blanketCapacity;
	}

	public int getWagonParts() {
		return wagonParts;
	}

	public Semaphore getFortDoctors() {
		return fortDoctors;
	}

	@Override
	public String toString() {
		return "Fort [foodCapacity=" + foodCapacity + ", blanketCapacity=" + blanketCapacity + ", wagonParts="
				+ wagonParts + ", fortDoctors=" + fortDoctors + "]";
	}
	
	
	
}
