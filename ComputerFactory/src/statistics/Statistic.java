package statistics;

import java.util.ArrayList;
import java.util.List;

import events.CompletedComputerEvent;

public class Statistic {
	private final int compRevenue = 500;
	private final int totalPartExpense = 250;
	private List<CompletedComputerEvent> finishedComputerEvents;
	
	public Statistic(ArrayList<CompletedComputerEvent> finishedComps){
		this.finishedComputerEvents = new ArrayList<CompletedComputerEvent>();
	}
	
	public int getCompsFinished(){
		return finishedComputerEvents.size();
	}
	
	
}
