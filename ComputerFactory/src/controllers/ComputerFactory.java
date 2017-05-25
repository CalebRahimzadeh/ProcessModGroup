package controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComputerFactory {
	private final int COMP_AMT_TO_PROCESS = 2;
	private ExecutorService compPool;
	
	public ComputerFactory(){
		 this.compPool = Executors.newFixedThreadPool(COMP_AMT_TO_PROCESS);
		 
	}
	
	private void generateComputers(){
		
	}
}
