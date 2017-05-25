package models;

import java.util.concurrent.Semaphore;

public class Employee {
	private static Semaphore moboEmployees;
	private static Semaphore processorEmployees;
	private static Semaphore hddEmployees;
	private static Semaphore ramEmployees;
	
	public Employee(int moboEmpCnt, int processorEmpCnt, int hddEmpCnt, int ramEmpCnt) {
		moboEmployees = new Semaphore(moboEmpCnt);
		processorEmployees = new Semaphore(processorEmpCnt);
		hddEmployees = new Semaphore(hddEmpCnt);
		ramEmployees = new Semaphore(ramEmpCnt);
	}
}
