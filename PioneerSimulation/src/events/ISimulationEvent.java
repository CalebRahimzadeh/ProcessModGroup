package events;

import listeners.ISimEventListener;

public interface ISimulationEvent extends Runnable {
	public void performAction();
	public int getTime();
	public void subscribe(ISimEventListener listener);
	public void unsubscribe(ISimEventListener listener);
}
