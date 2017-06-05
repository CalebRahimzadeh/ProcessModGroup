package models.transport;

import interfaces.ITransportMethods;

public class Horse implements ITransportMethods{
	
	private int health = 100;
	private int foodAmt = 0;
	private double capacity = 500;
	private final int SPEED = 224500;
	public Horse(){
		
	}
	
	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return SPEED;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public double getCapacity() {
		// TODO Auto-generated method stub
		return capacity;
	}

	@Override
	public int getFoodAmt() {
		// TODO Auto-generated method stub
		return foodAmt;
	}

	@Override
	public void setFood(int newFoodAmt) {
		// TODO Auto-generated method stub
		this.foodAmt = newFoodAmt;
	}

	@Override
	public void setCapacity(double newCapacity) {
		// TODO Auto-generated method stub
		this.capacity = newCapacity;
	}

	@Override
	public int setHealth(int newHealth) {
		// TODO Auto-generated method stub
		return 0;
	}

}
