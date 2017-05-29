package resources;

import java.util.List;
import java.util.ArrayList;

import events.ComputerEvent;
import events.ComputerEvents;

public class Resource {
//	private static final Clock CLOCK = new Clock();
	private static List<ComputerEvents> completedCompEvents = new ArrayList<ComputerEvents>();
	
	public Resource(){
		
	}
	
	public static void addToCompletedEventList(ComputerEvents completedEvent){
		completedCompEvents.add(completedEvent);
	}
	
}
