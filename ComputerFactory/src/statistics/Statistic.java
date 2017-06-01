package statistics;

import models.Employee;
import resources.Resource;

public class Statistic {
	private static final double COMP_REVENUE = 500;
	private static final double TOTAL_PART_EXPENSE = 250;
	private static final double HOURLY_WAGE = 15;
	private final double totalNumOfEmps;

	public Statistic(Employee empNum) {
		this.totalNumOfEmps = empNum.getTotalNumOfEmps();
	}

	public double calculateTotalRevenue() {
		double totalRev = (COMP_REVENUE - TOTAL_PART_EXPENSE) * Resource.getNumOfComputersCompleted();
		totalRev = totalRev - ((getHoursInWorkWeek() * HOURLY_WAGE) * totalNumOfEmps);
		return totalRev;
	}

	public int getHoursInWorkWeek() {
		final int WEEKLY_WORK_DAYS_TO_SIM = 5;
		final int DAILY_WORK_HOURS_TO_SIM = 8;
		return WEEKLY_WORK_DAYS_TO_SIM * DAILY_WORK_HOURS_TO_SIM;
	}

}
