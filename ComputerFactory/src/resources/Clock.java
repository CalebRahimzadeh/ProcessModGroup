package resources;

public final class Clock {
	private static double clockTime = 0.0;
	private static volatile Clock instance;
	
	
//	public static Clock getInstance(){
//		if(instance == null){
//			synchronized(Clock.class){
//				if (instance == null) {
//                    instance = new Clock();
//                }
//			}
//		}
//		return instance;
//	}
	
	public static synchronized void incrementClock(double valToIncrement){
		clockTime += valToIncrement;
	}
	
	public synchronized static double getCurrentTime(){
		return clockTime;
	}
	
	public static void resetClock(){
		clockTime = 0.0;
	}
	
}
