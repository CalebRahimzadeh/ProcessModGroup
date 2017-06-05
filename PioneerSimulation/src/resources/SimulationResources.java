package resources;

import java.util.ArrayList;
import java.util.List;

import models.Fort;

public class SimulationResources {
	private static List<Fort> forts = new ArrayList<Fort>();
	
	public SimulationResources(){
		this.forts = new ArrayList<Fort>();
	}
	
	public static void addFort(Fort fort) {
		forts.add(fort);
	}
}
