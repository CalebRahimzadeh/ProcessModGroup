package controllers;

import resources.Resource;
import userinterface.SimulationConfiguration;

public class Main {

	public static void main(String[] args) {
		SimController cont = new SimController();
		cont.start();
	}
	/** QUESTIONS
	 * 1. If you model a work-week worth of effort (5 days time 8 hours a day) how many computers are produced?
	 * 2. If you model the work-week 100 times on average how many computers are produced?
	 * 3. If you were the plant manager and could hire 3 more workers who would perform at the same rates as above, where would you assign them?
	 * 	  Guesstimate how many computers, on average (over 100 runs), your production will increase by. Modify your simulation to include the additional workers and run it again.  How close were you?
	 * 4. Assume your revenue from each computer is $500.  Assume that the parts cost $250 total.  Your workers all get paid the same amount ($15/hour).
	 *   What was your profit/loss before you hired the additional three workers?  What is is after hiring them?  Did it increase or decrease?
	 * 5. Now try re-allocating all of the workers (including the 3 new hires) however you think best (except they can only do one job - no switching back and forth).
	 *   Run the simulation again.  What happened to your profit/loss this time?
	 */
	
	// ANSWERS
	// 1. in 5 days and 8 hours on average we created 172~184 computers
	// 2. Finished Computer Average for 100 iterations: 175.95
	// 3. Workers relocated: moboEmpNum = 5, processorEmpNum = 3, hddEmpNum = 3, ramEmpNum = 2;
	// Guesstimation: ~200  Actual: 173.66 comps produced
	// 4. Total Revenue: $3,561,500.00
	// 5. Workers relocated:  moboEmpNum = 6, processorEmpNum = 3, hddEmpNum = 2, ramEmpNum = 2;
	//    Guesstimation: ~200  Actual: 174.78 comps produced
	//    Total Revenue: $3589500.00

	
	
}
