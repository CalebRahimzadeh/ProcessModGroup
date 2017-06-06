package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Trail {
	private List<Fort> forts;
	private final double OREGON_DIST_IN_METERS = 3218688;
	private final int distancePerFort = 321868;
	private static int ID = 0;
	
	public Trail(int numOfForts){
		int tmpDist = (int)OREGON_DIST_IN_METERS;
		forts = new ArrayList<>(numOfForts);
		for (int i = 0; i < numOfForts; i++) {
			
			forts.add(new Fort(ID++, distancePerFort));
		}
	}
	
	public Resource getFort(int index){
		return forts.get(index);
	}
	
	
}
