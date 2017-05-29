package models;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import events.ComputerEvents;

public class Employee {
	private static Semaphore moboEmployees;
	private static Semaphore processorEmployees;
	private static Semaphore hddEmployees;
	private static Semaphore ramEmployees;
	private static final int HOURLY_WAGE = 15;
	
	public Employee(int moboEmpCnt, int processorEmpCnt, int hddEmpCnt, int ramEmpCnt) {
		moboEmployees = new Semaphore(moboEmpCnt);
		processorEmployees = new Semaphore(processorEmpCnt);
		hddEmployees = new Semaphore(hddEmpCnt);
		ramEmployees = new Semaphore(ramEmpCnt);
	}
	
	public static void ChooseEmployeeToAquire(ComputerEvents event) throws InterruptedException{
		switch(event){
		case STARTING:
			break;
		case FINISHED_MOTHERBOARD:
			aquireMotherboardEmployee();
			break;
		case FINISHED_PROCESSOR:
			aquireProcessorEmployee();
			break;
		case FINISHED_HDD:
			aquireHDDEmployee();
			break;
		case FINISHED_RAM:
			aquireRAMEmployee();
			break;
		case FINISHED:
			break;
		}
	}
	
	public static void ChooseEmployeeToRelease(ComputerEvents event){
		switch(event){
		case STARTING:
			break;
		case FINISHED_MOTHERBOARD:
			releaseMotherboardEmployee();
			break;
		case FINISHED_PROCESSOR:
			releaseProcessorEmployee();
			break;
		case FINISHED_HDD:
			releaseHDDEmployee();
			break;
		case FINISHED_RAM:
			releaseRAMEmployee();
			break;
		case FINISHED:
			break;
		}
	}
	
	public static void aquireMotherboardEmployee() throws InterruptedException{
		moboEmployees.acquire();
	}
	
	public static void releaseMotherboardEmployee(){
		moboEmployees.release();
	}
	
	public static void aquireProcessorEmployee() throws InterruptedException{
		processorEmployees.acquire();
	}
	
	public static void releaseProcessorEmployee(){
		processorEmployees.release();
	}
	
	public static void aquireHDDEmployee() throws InterruptedException{
		hddEmployees.acquire();
	}
	
	public static void releaseHDDEmployee(){
		hddEmployees.release();
	}
	
	public static void aquireRAMEmployee() throws InterruptedException{
		ramEmployees.acquire();
	}
	
	public static void releaseRAMEmployee(){
		ramEmployees.release();
	}
}
