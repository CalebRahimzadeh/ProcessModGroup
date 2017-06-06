package events;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import interfaces.ITransportMethods;
import models.PioneerFamily;

public class CompletedEvent {
	
	private final int pioneerID;
	private final PioneerStates state;
	private final int familyCnt;
	private final double clockTime;
	private final int health;
	private final int originalFamCnt;
	private final int foodAmt;
	private final ITransportMethods transport;
	
	public CompletedEvent(int pioneerID,  PioneerStates state, int originalFamCnt, int familyCnt, double clockTime, int health, int foodAmt, ITransportMethods transport){
		this.pioneerID = pioneerID;
		this.state = state;
		this.familyCnt = familyCnt;
		this.clockTime =  clockTime;
		this.health = health;
		this.originalFamCnt = originalFamCnt;
		this.foodAmt = foodAmt;
		this.transport = transport;
	}
	public int getFoodAmt() {
		return foodAmt;
	}
	public int getPioneerID() {
		return pioneerID;
	}

	public PioneerStates getState() {
		return state;
	}

	public int getFamilyCnt() {
		return familyCnt;
	}

	public double getClockTime() {
		return clockTime;
	}

	public int getHealth() {
		return health;
	}

	@Override
	public String toString(){
		NumberFormat formatter = new DecimalFormat("#0.00");
		return "ID: " + pioneerID + " State: " + state.name() + " Original Family Count: " + originalFamCnt + "New Family Count: " + familyCnt + " ClockTime: " + formatter.format(clockTime) + " Health: " + health + " FoodAMT: " + foodAmt + "\nTransport Method: " + transport;
	}
}
