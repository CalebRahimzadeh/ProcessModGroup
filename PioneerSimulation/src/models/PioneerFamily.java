package models;

import java.util.Random;

import events.PioneerStates;
import interfaces.ITransportMethods;
import models.transport.HandCart;
import models.transport.Horse;
import models.transport.Oxen;

public class PioneerFamily implements Runnable{

	private final int ID;
	private final int NUM_OF_PEOPLE;
	private final ITransportMethods transportMethod;
	
	public PioneerFamily(int id){
		this.ID = id;
		this.NUM_OF_PEOPLE = genFamilyMembers();
		this.transportMethod = genTypeOfTransport();
		initFoodAndCapacity();
	}
	
	private void initFoodAndCapacity(){
		int initalFoodAmt = 150;
		transportMethod.setFood(initalFoodAmt);
		transportMethod.setCapacity((transportMethod.getCapacity() - initalFoodAmt));
	}
	
	private int genFamilyMembers() {
		Random rand = new Random();
		return rand.nextInt(6);
	}
	
	private ITransportMethods genTypeOfTransport() {
		Random rand = new Random();
		ITransportMethods transportMethod;
		int whichOneToChoose = rand.nextInt(3);
		if(whichOneToChoose == 1){
			transportMethod = new HandCart();
		} else if(whichOneToChoose == 2){
			transportMethod = new Horse();
		} else {
			transportMethod = new Oxen();
		}
		return transportMethod;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
	private void consumeFood(){
		int FoodAmt = transportMethod.getFoodAmt();
		int newFoodAmt = (FoodAmt - (NUM_OF_PEOPLE * 10));
		double newCapacity = (transportMethod.getCapacity() - newFoodAmt);
		transportMethod.setFood(newFoodAmt);
		transportMethod.setCapacity(newCapacity);
	}
	
	private double calculateDuration(PioneerStates currentEventType) {
		double duration = currentEventType.getTime();
		double actualTime = (-Math.log(1 - Math.random())) * duration;
		return actualTime;
	}
	
	private void restockFoodFromFort(){
		
	}
}

