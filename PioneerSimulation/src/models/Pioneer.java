package models;

public class Pioneer implements Runnable{
	private final int id;
	private Fort fort;
	private int foodSupply;
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
