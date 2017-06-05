package models;

public class Fort {
	private static final int FOOD_PER_PERSON = 15;
	private final int ID;
	private final double FORT_MILE_MARKER;
	private int foodCapacity = 1000;
	
	public Fort(int id, double mileMarker){
		this.ID = id;
		this.FORT_MILE_MARKER = mileMarker;
	}
	
	public int getFoodCapacity() {
		return this.foodCapacity;
	}
	
	public double getMileMarker(){
		return FORT_MILE_MARKER;
	}
	
	public void takeFoodFromFort(int numOfPeople){
		foodCapacity -= (numOfPeople * FOOD_PER_PERSON);
	}
	
}
