package controllers;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimulationController cont = new SimulationController(2);
		int caravanSize = 2;
		cont.start(caravanSize);
	}

}
