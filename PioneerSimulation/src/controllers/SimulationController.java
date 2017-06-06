package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import models.Fort;
import models.PioneerFamily;
import resources.SimulationResources;

public class SimulationController {

	private static final int NUM_OF_FORTS = 10;
	private final double OREGON_DIST_IN_METERS = 3218688; //2000 miles
	private ExecutorService wagonTrain;
	private List<PioneerFamily> pioneerPool;
	
	public SimulationController(int numToExecute){
		wagonTrain = Executors.newFixedThreadPool(numToExecute);
	}
	
	//10 forts
	public void start(int caravanSize){
		this.pioneerPool = new ArrayList<>(caravanSize);
	}
	
	public void generatePioneers() {
		for (int i = 0; i < pioneerPool.size(); i++) {
			pioneerPool.add(new PioneerFamily(i));
		}
	}
	
	public void submitPioneers(){
		for (int i = 0; i < pioneerPool.size(); i++) {
			wagonTrain.submit(pioneerPool.get(i));
		}
	}
	
	public void generateForts() {
//		3 218 688 / 10 = 321 868.8 //per mile
		SimulationResources.addFort(new Fort(0, OREGON_DIST_IN_METERS)); // final fort
		for (int i = 1; i < NUM_OF_FORTS - 1; i++) {
			
		}
	}
}
