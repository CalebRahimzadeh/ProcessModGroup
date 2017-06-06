package uitilities;

import java.util.Comparator;

import events.ISimulationEvent;


public class SimComparator implements Comparator<ISimulationEvent> {

	@Override
	public int compare(ISimulationEvent event1, ISimulationEvent event2) {
		int compareVal = 0;
		int event1Time = event1.getTime();
		int event2Time = event2.getTime();
		if(event1Time < event2Time){
			compareVal = -1;
		} else if(event1Time > event2Time) {
			compareVal = 1;
		}
		return compareVal;
	}

}
