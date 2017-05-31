package resources;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import events.CompletedComputerEvent;
import events.ComputerEvents;

public class Resource {
	
	private static int NUM_OF_COMPUTERS = 0;
	private static List<CompletedComputerEvent> completedCompEvents = new ArrayList<CompletedComputerEvent>();
	private static List<CompletedComputerEvent> finishedComputerEvents = new ArrayList<CompletedComputerEvent>();
	private static boolean isNotFinished = false;
	
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
	
}
