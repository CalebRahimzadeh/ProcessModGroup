package resources;

import java.util.List;
import java.util.ArrayList;

import events.CompletedComputerEvent;
import events.ComputerEvents;

public class Resource {
	
	private static int NUM_OF_COMPUTERS = 0;
	private static List<CompletedComputerEvent> completedCompEvents = new ArrayList<CompletedComputerEvent>();
	
	
	public static void addToCompletedEventList(CompletedComputerEvent completedEvent){
		completedCompEvents.add(completedEvent);
	}
	
	public static void incrementComputersCompleteCount(){
		NUM_OF_COMPUTERS += 1;
	}
	
	public static int getNumOfComputersCompleted(){
		return NUM_OF_COMPUTERS;
	}
	
}
