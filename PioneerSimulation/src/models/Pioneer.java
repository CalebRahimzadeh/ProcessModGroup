package models;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Events.FortEvent;
import Events.PioneerEvent;
import States.PioneerStates;
import resources.Resource;

public class Pioneer implements Runnable{
	private final static int TIMEOUT_MILLI = 1500;
	private final int id;
	private Fort fort;
	private int foodSupply;
	private int blanketSupply;
	private boolean isWagonDamage = false;
	private boolean didRecieveFood = false;
	private boolean didRecieveBlankets = false;
	private boolean didRecieveHealthcare = false;
	private FortEvent stateProcessor;
	private PioneerEvent currentState;
	private int pplCnt;
	
	public Pioneer(int id, Fort fort){
		this.id = id;
		this.fort = fort;
		this.foodSupply = 0;
		this.pplCnt = 5;
		this.currentState = PioneerEvent.TRAVELED;
		
	}
	
	private boolean isWagonDamagedRandomly(){
		Random random = new Random();
		isWagonDamage = random.nextBoolean();
		return isWagonDamage;
	}

	@Override
	public void run() {
		
		processEvent(currentState);
		this.currentState = PioneerEvent.ENTEREDFORT;
		processEvent(currentState);
		recieveFood();
		recieveBlankets();
		getParts();
		if(didRecieveFood || didRecieveBlankets || isWagonDamage){
			this.currentState = PioneerEvent.GOTSUPPLIES;
			processEvent(currentState);
		}
		
		getMedicalCare();
		
		if(didRecieveHealthcare) {
			this.currentState = PioneerEvent.TREATED_PARTY;
			
		} else {
			this.currentState = PioneerEvent.DEATH;
			System.out.println("DEATH OCCURED");
		}
		processEvent(currentState);
		
		synchronized(fort){
			fort.notifyAll();
		}
	}
	private void processEvent(PioneerEvent event){
		stateProcessor = new FortEvent(id, event, this.fort);
		stateProcessor.calculateTime();
		Resource.addEventToItsCorrespondingList(stateProcessor);
	}
	
	private void getMedicalCare(){
		try {
			if(fort.getFortDoctors().tryAcquire(pplCnt, TIMEOUT_MILLI, TimeUnit.MILLISECONDS)){
				didRecieveHealthcare = true;	
			} 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fort.getFortDoctors().release();
	}
	private void getParts(){
		if(isWagonDamagedRandomly()){
			if(fort.canFixWagon()){
				fort.getWagonPartsFromDepot();
				this.isWagonDamage = false;
			}
		}
	}
	private void recieveBlankets(){
		int blanketsRecieved = fort.getBlanketsFromDeopt(pplCnt);
		if(blanketsRecieved != 0){
			blanketSupply += blanketsRecieved;
			didRecieveBlankets = true;	
		}
	}
	private void recieveFood(){
		int foodRecieved = fort.getFoodFromDepot(pplCnt);
		if(foodRecieved != 0) {
			foodSupply += foodRecieved;			
			didRecieveFood = true;
		} 
	}
	public int getFoodSupply() {
		return foodSupply;
	}

	public Fort getFort() {
		return fort;
	}

	public void setFort(Fort fort) {
		this.fort = fort;
	}

	public int getBlanketSupply() {
		return blanketSupply;
	}

	public void setBlanketSupply(int blanketSupply) {
		this.blanketSupply = blanketSupply;
	}

	public boolean isWagonDamage() {
		return isWagonDamage;
	}

	public void setWagonDamage(boolean isWagonDamage) {
		this.isWagonDamage = isWagonDamage;
	}

	public boolean isDidRecieveFood() {
		return didRecieveFood;
	}

	public void setDidRecieveFood(boolean didRecieveFood) {
		this.didRecieveFood = didRecieveFood;
	}

	public boolean isDidRecieveBlankets() {
		return didRecieveBlankets;
	}

	public void setDidRecieveBlankets(boolean didRecieveBlankets) {
		this.didRecieveBlankets = didRecieveBlankets;
	}

	public boolean isDidRecieveHealthcare() {
		return didRecieveHealthcare;
	}

	public void setDidRecieveHealthcare(boolean didRecieveHealthcare) {
		this.didRecieveHealthcare = didRecieveHealthcare;
	}

	public int getPplCnt() {
		return pplCnt;
	}

	public void setPplCnt(int pplCnt) {
		this.pplCnt = pplCnt;
	}

	public int getId() {
		return id;
	}

	public void setFoodSupply(int foodSupply) {
		this.foodSupply = foodSupply;
	}

	@Override
	public String toString() {
		return "Pioneer [id=" + id + ", fort=" + fort + ", foodSupply=" + foodSupply + ", blanketSupply="
				+ blanketSupply + ", isWagonDamage=" + isWagonDamage + ", didRecieveFood=" + didRecieveFood
				+ ", didRecieveBlankets=" + didRecieveBlankets + ", didRecieveHealthcare=" + didRecieveHealthcare
				+ ", stateProcessor=" + stateProcessor + ", currentState=" + currentState + ", pplCnt=" + pplCnt + "]";
	}
	
	
}
