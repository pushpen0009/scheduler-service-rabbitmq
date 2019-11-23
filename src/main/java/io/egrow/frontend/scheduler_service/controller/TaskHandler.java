package io.egrow.frontend.scheduler_service.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.egrow.frontend.scheduler_service.entity.Task;
import io.egrow.frontend.scheduler_service.entity.TaskRecurrence;

/**
 * This class handles the execution of scheduled tasks. It includes cron tasks
 * for hourly, daily, weekly and monthly tasks.
 *
 * It automatically registers a task whenever a task is instantiated and puts it
 * to the correct task collection based on the frequency of executions.
 */
@Component
public class TaskHandler {

	@Autowired
	private Map<TaskRecurrence, Set<Task>> taskExecutionRecurrence;

	Logger LOGGER = LoggerFactory.getLogger(TaskHandler.class);

	public void registerTask(TaskRecurrence taskRecurrence, Task task) {
		if (!taskExecutionRecurrence.containsKey(taskRecurrence))
			taskExecutionRecurrence.put(taskRecurrence, new HashSet<Task>());

		taskExecutionRecurrence.get(taskRecurrence).add(task);
	}

	@Scheduled(cron = "1 0 0 * * *") // run at 00:01 AM daily
	public void runJobForDailyUsageDistributorMessage() {
		LOGGER.info("Start:: runJobForDailyUsageDistributorMessage");
		long start = System.currentTimeMillis();

		if (taskExecutionRecurrence.containsKey(TaskRecurrence.DAILY)) {
			taskExecutionRecurrence.get(TaskRecurrence.DAILY).forEach(Task::execute);
		}

		long executionTime = System.currentTimeMillis() - start;
		LOGGER.info("End:: runJobForDailyUsageDistributorMessage executed in " + executionTime + "ms");
	}

	@Scheduled(cron = "1 0 0 1 * *") // run at 00:01 AM first day of every month.
	public void runJobForMonthlyUsageDistributorMessage() {
		LOGGER.info("Start:: runJobForMonthlyUsageDistributorMessage");
		long start = System.currentTimeMillis();

		if (taskExecutionRecurrence.containsKey(TaskRecurrence.ONCE_IN_A_MONTH)) {
			taskExecutionRecurrence.get(TaskRecurrence.ONCE_IN_A_MONTH).forEach(Task::execute);
		}

		long executionTime = System.currentTimeMillis() - start;
		LOGGER.info("End:: runJobForMonthlyUsageDistributorMessage executed in " + executionTime + "ms");
	}
}
