package models;

import java.util.concurrent.Semaphore;

public class Pioneer implements Runnable{
	private final int id;
	private Fort fort;
	private int foodSupply;
	private int blanketSupply;
	private boolean isWagonDamage = false;
	
	private int pplCnt;
	
	public Pioneer(int id, Fort fort){
		this.id = id;
		this.fort = fort;
		this.foodSupply = 0;
		this.pplCnt = 5;
	}

	@Override
	public void run() {
		foodSupply += fort.getFoodFromDepot(pplCnt);
		//get blankets
		//try to aquire doctor if sick
		//fix wagon
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(fort){
			//make sure it s waiting first
			fort.notifyAll();
		}
	}
	
	public int getFoodSupply() {
		return foodSupply;
	}
}