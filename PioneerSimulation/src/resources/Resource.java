package resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Events.FortEvent;
import Events.PioneerEvent;
import Tools.ClockTimeResources;

public class Resource {
	private static List<FortEvent> allEvents  = new ArrayList<>();
	private static List<FortEvent> traveledEvents = new ArrayList<>();
	private static List<FortEvent> suppliedEvents = new ArrayList<>();
	private static List<FortEvent> enteredFortEvents = new ArrayList<>();
	private static List<FortEvent> departedEvents = new ArrayList<>();
	private static List<FortEvent> deathEvents = new ArrayList<>();
	private static List<FortEvent> treatedEvents = new ArrayList<>();
	
	public synchronized static void addEventToItsCorrespondingList(FortEvent event){
		switch(event.getEventType()){
		case TREATED_PARTY:
			treatedEvents.add(event);
			break;
		case DEPARTED:
			departedEvents.add(event);
			break;
		case ENTEREDFORT:
			enteredFortEvents.add(event);
			break;
		case GOTSUPPLIES:
			suppliedEvents.add(event);
			break;
		case TRAVELED:
			traveledEvents.add(event);
			break;
		case DEATH:
			deathEvents.add(event);
			break;
		default:
			break;
		}
		allEvents.add(event);
	}
	public static Iterator<FortEvent> getTraveledEvents() {
		return traveledEvents.iterator();
	}
	public static Iterator<FortEvent> getSuppliedEvents() {
		return suppliedEvents.iterator();
	}
	public static Iterator<FortEvent> getEnteredFortEvents() {
		return enteredFortEvents.iterator();
	}
	public static Iterator<FortEvent> getDepartedEvents() {
		return departedEvents.iterator();
	}
	public static Iterator<FortEvent> getDeathEvents() {
		return deathEvents.iterator();
	}
	public static Iterator<FortEvent> getTreatedEvents() {
		return treatedEvents.iterator();
	}
	public static Iterator<FortEvent> getAllEvents() {
		return allEvents.iterator();
	}
	
}
