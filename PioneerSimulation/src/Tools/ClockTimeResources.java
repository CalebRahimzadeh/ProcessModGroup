package Tools;

import java.util.Random;

public class ClockTimeResources {
	//3600000 is one hour in milliseconds
	// Random int to decied how long they travel to get to a fort.
	// it is stati right now, we may have to change it or do a reset.
	// Or maybe we would want it to be the same since forts dont change positions
	private static final int MILLIS_IN_HOUR = 3600000;
	private static Random ran = new Random();
	private static int randomDays = ran.nextInt(25) + 1;
	private static long MEAN_TRAVEL_TIME = (randomDays *24) * MILLIS_IN_HOUR;
	private static long MEAN_WAITING_FOR_SUPPLIES_TIME = (8)*MILLIS_IN_HOUR;
	private static long MEAN_GATHER_SUPPLIES_TIME = (4)*MILLIS_IN_HOUR;
	private static long MEAN_LEAVING_TIME = (1)*MILLIS_IN_HOUR;
	private static long MEAN_TREAT_TIME = 2*MILLIS_IN_HOUR;
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
	public static long getMeanTreatTime(){
		return MEAN_TREAT_TIME;
	}
}
