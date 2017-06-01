package resources;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import events.CompletedComputerEvent;
import events.ComputerEvents;
import statistics.Statistic;

public class Resource {
	
	private static List<CompletedComputerEvent> completedCompEvents = new ArrayList<CompletedComputerEvent>();
	private static List<CompletedComputerEvent> finishedComputerEvents = new ArrayList<CompletedComputerEvent>();
	private static boolean isNotFinished = false;
	private static List<Double> simsRevenueStats = new ArrayList<>();
	public Resource() {
		
	}
	
	public static void addToCompletedEventList(CompletedComputerEvent completedEvent){
		completedCompEvents.add(completedEvent);
	}
	
	public static int getNumOfComputersCompleted(){
		return finishedComputerEvents.size();
	}
	
	public static void addComputerFinishedEvent(CompletedComputerEvent finishedEvent){
		finishedComputerEvents.add(finishedEvent);
	}
	
	public static Iterator<CompletedComputerEvent> getFinishedEventsIterator(){
		return finishedComputerEvents.iterator();
	}
	
	public static boolean isNotFinished(){
		return isNotFinished;
	}
	
	public static void setIsFinished(boolean setIsFinished){
		isNotFinished = setIsFinished;
	}

	public static void clearFinishedList(){
		finishedComputerEvents.clear();
	}
	
	public static void addRevenueToList(double revenue){
		simsRevenueStats.add(revenue);
	}
	
	public static Iterator<Double> getTotalRevenueIterator(){
		return simsRevenueStats.iterator();
	}
}
