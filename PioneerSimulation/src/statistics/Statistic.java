package statistics;

import java.util.ArrayList;
import java.util.List;

import Events.PioneerEvent;
import models.Pioneer;

public class Statistic {
	private List<PioneerEvent> traveledEvents = new ArrayList<>();
	public Statistic(Pioneer p){
		traveledEvents = new ArrayList<>();
	}
}
