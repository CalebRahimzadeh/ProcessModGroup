package resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import events.CompletedEvent;
import events.PioneerStates;
import models.Fort;
import models.PioneerFamily;

public class SimulationResources {
	private static List<Fort> forts = new ArrayList<Fort>();
	private static List<CompletedEvent> completedEvents = new ArrayList<>();
	private static List<CompletedEvent> deadPioneers = new ArrayList<>();
	private static List<CompletedEvent> starveEvents = new ArrayList<>();
	private static List<CompletedEvent> finishedSimEvents = new ArrayList<>();
	public SimulationResources(){
		this.forts = new ArrayList<Fort>();
	}
	
	public static void addFort(Fort fort) {
		forts.add(fort);
	}
	
	public synchronized static void addDeadPioneerEvent(CompletedEvent compEvent){
		deadPioneers.add(compEvent);
	}
	
	public synchronized static void addCompletedEvent(CompletedEvent compEvent){
		completedEvents.add(compEvent);
	}
	
	public synchronized static void addStarveEvents(CompletedEvent compEvent){
		starveEvents.add(compEvent);
	}

	public synchronized static void addFinishedEvents(CompletedEvent compEvent){
		finishedSimEvents.add(compEvent);
	}
	
	public synchronized static Iterator<CompletedEvent> getFinishedEventsIterator(){
		return finishedSimEvents.iterator();
	}
	
	public synchronized static Iterator<CompletedEvent> getDeadPioneersIterator(){
		return deadPioneers.iterator();
	}
	
	public synchronized static Iterator<CompletedEvent> getStarveEventsIterator(){
		return starveEvents.iterator();
	}
	
	public synchronized static Iterator<CompletedEvent> getCompletedEventsIterator(){
		return completedEvents.iterator();
	}
	
	public synchronized static Fort getFortBasedOffClock(double time){
		Fort fort = null;
		for (int i = 0; i < forts.size(); i++) {
			if(forts.get(i).getMileMarker() == time){
				fort = forts.get(i);
				System.out.println(fort.toString());
			}
		}
		return fort;
	}
}
