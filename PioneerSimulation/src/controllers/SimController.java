package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Fort;
import models.Pioneer;

public class SimController {
	private List<Pioneer> pioneers;// = new ArrayList<>();
	private Fort fort;
	private static final int STARVATION_TIMEOUT = 1000;
	private static final int FORT_FOOD_CAP = 10000;
	private static final int FORT_BLANKET_CAP = 500;
	private static final int FORT_DOC_CNT = 1;
	private static final int FORT_WAGON_PART_CAP = 200;
	
	
	public SimController(int numOfPioneers){
		pioneers = new ArrayList<>();
		this.fort = new Fort(FORT_FOOD_CAP, FORT_BLANKET_CAP, FORT_WAGON_PART_CAP, FORT_DOC_CNT);
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
			System.out.println();
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
		
		for (Pioneer pioneer : pioneers) {
			System.out.println(pioneer.toString());
		}
	}
}
