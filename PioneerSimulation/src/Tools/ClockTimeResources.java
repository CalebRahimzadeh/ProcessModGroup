package Tools;

import java.util.Random;

public class ClockTimeResources {
	//3600000 is one hour in milliseconds
	// Random int to decied how long they travel to get to a fort.
	// it is stati right now, we may have to change it or do a reset.
	// Or maybe we would want it to be the same since forts dont change positions
	private static Random ran = new Random();
	private static int randomDays = ran.nextInt(25) + 1;
	private static long MEAN_TRAVEL_TIME = (randomDays *24) * 3600000;
	private static long MEAN_WAITING_FOR_SUPPLIES_TIME = (8)*3600000;
	private static long MEAN_GATHER_SUPPLIES_TIME = (4)*3600000;
	private static long MEAN_LEAVING_TIME = (1)*3600000;
	
	private static long simulationClock;
	
	private static Object clockLock = new Object();
	
	public static void initClock(long startTime){
		if(simulationClock != 0){
			throw new RuntimeException("It is Broken currently");
		}
		synchronized(clockLock){
			simulationClock = startTime;
		}
	}
	public static void incrementClock(long milliseconds){
		synchronized(clockLock){
			simulationClock +=milliseconds;
		}
	}
	public static long getSimulationClock(){
		return simulationClock;
	}
	
	public static long getMeanTravelTime(){
		return MEAN_TRAVEL_TIME;
	}
	public static long getMeanWaitingForSuppliesTime(){
		return MEAN_WAITING_FOR_SUPPLIES_TIME;
	}
	public static long getGatherSuppliesTime(){
		return MEAN_GATHER_SUPPLIES_TIME;
	}
	public static long getMeanLeavingTime(){
		return MEAN_LEAVING_TIME;
	}
}
