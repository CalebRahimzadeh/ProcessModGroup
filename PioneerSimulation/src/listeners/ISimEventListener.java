package listeners;

import events.ISimulationEvent;

public interface ISimEventListener {
	
	public void onComplete(ISimulationEvent event);
	public void onInComplete(ISimulationEvent event);
	
}
