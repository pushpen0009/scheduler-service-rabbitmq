package io.egrow.frontend.scheduler_service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.egrow.frontend.scheduler_service.controller.TaskHandler;
import io.egrow.frontend.scheduler_service.controller.tasks.DailyUsageDistributorTask;
import io.egrow.frontend.scheduler_service.controller.tasks.MonthlyBillingDistributorTask;
import io.egrow.frontend.scheduler_service.entity.DailyUsageDistributorMessage;
import io.egrow.frontend.scheduler_service.entity.MonthlyBillingDistributorMessage;
import io.egrow.frontend.scheduler_service.entity.Task;
import io.egrow.frontend.scheduler_service.entity.TaskRecurrence;
import io.egrow.frontend.scheduler_service.util.DateUtils;

@SpringBootTest
class SchedulerServiceApplicationTests {

	@Autowired
	private DailyUsageDistributorMessage dailyUsageDistributorMessage;

	@Autowired
	private MonthlyBillingDistributorMessage monthlyBillingDistributorMessage;

	@Autowired
	private Map<TaskRecurrence, Set<Task>> taskExecutionRecurrence;

	@Autowired
	private TaskHandler taskHandler;

	@Autowired
	private DailyUsageDistributorTask dailyUsageDistributorTask;

	@Autowired
	private MonthlyBillingDistributorTask monthlyBillingDistributorTask;

	@Test
	void contextLoads() {
	}

	@Test
	void testStartDayOfUsageOfDailyUsage() {
		Long startDayOfUsage = dailyUsageDistributorMessage.getStartDayOfUsage();
		String startDayOfUsageDate = DateUtils.convertMillisecondsToString(startDayOfUsage);
		assertTrue(startDayOfUsageDate.contains("00:00:01"));
	}

	@Test
	void testEndDayOfUsageOfDailyUsage() {
		Long endDayOfUsage = dailyUsageDistributorMessage.getEndDayOfUsage();
		String endDayOfUsageDate = DateUtils.convertMillisecondsToString(endDayOfUsage);
		assertTrue(endDayOfUsageDate.contains("23:59:59"));
	}

	@Test
	void testStartDayOfMonthlyBilling() {
		Long startDayOfMonthlyBilling = monthlyBillingDistributorMessage.getStartOfBillingMonth();
		String startDayOfMonthlyBillingDate = DateUtils.convertMillisecondsToString(startDayOfMonthlyBilling);
		assertTrue(startDayOfMonthlyBillingDate.contains("00:00:01"));
	}

	@Test
	void testEndDayOfMonthlyBilling() {
		Long endDayOfMonthlyBilling = monthlyBillingDistributorMessage.getEndOfBillingMonth();
		String endDayOfMonthlyBillingDate = DateUtils.convertMillisecondsToString(endDayOfMonthlyBilling);
		assertTrue(endDayOfMonthlyBillingDate.contains("23:59:59"));
	}

	@Test
	void testRegisterTask() {
		taskHandler.registerTask(TaskRecurrence.DAILY, dailyUsageDistributorTask);
		taskHandler.registerTask(TaskRecurrence.ONCE_IN_A_MONTH, monthlyBillingDistributorTask);
		assertNotNull(taskExecutionRecurrence);
	}

	@Test
	void testDailyTaskRegistered() {
		assertNotNull(taskExecutionRecurrence.get(TaskRecurrence.DAILY));
	}

	@Test
	void testMonthlyTaskRegistered() {
		assertNotNull(taskExecutionRecurrence.get(TaskRecurrence.ONCE_IN_A_MONTH));
	}

}
