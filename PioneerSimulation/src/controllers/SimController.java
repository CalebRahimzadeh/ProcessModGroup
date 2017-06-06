package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Fort;
import models.Pioneer;

public class SimController {
	private List<Pioneer> pioneers;// = new ArrayList<>();
	private Fort fort;
	public SimController(int numOfPioneers){
		pioneers = new ArrayList<>();
		this.fort = new Fort(10000);
		populatePioneers(numOfPioneers);
	}
	
	public void populatePioneers(int numOfPioneers){
		for (int i = 0; i < numOfPioneers; i++) {
			pioneers.add(new Pioneer(i, fort));
		}
	}
	
	public void start(){
		for (Pioneer pioneer : pioneers) {
			new Thread(pioneer).start();
			System.out.println("pioneer food: " + pioneer.getFoodSupply());

			synchronized(fort){
				try {
					fort.wait(1000); // have  bool in pioneer

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("pioneer food: " + pioneer.getFoodSupply());
		}
	}
}
