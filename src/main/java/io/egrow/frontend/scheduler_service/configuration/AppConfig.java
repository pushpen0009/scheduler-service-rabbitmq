package io.egrow.frontend.scheduler_service.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.egrow.frontend.scheduler_service.entity.DailyUsageDistributorMessage;
import io.egrow.frontend.scheduler_service.entity.MonthlyBillingDistributorMessage;
import io.egrow.frontend.scheduler_service.entity.Task;
import io.egrow.frontend.scheduler_service.entity.TaskRecurrence;
import io.egrow.frontend.scheduler_service.util.DateUtils;

@Configuration
public class AppConfig {

	@Bean(name = "dailyUsageDistributorMessage")
	public DailyUsageDistributorMessage getDailyUsageDistributorMessage() {
		DailyUsageDistributorMessage dailyUsageDistributorMessage = new DailyUsageDistributorMessage(
				DateUtils.getStartOfDayForPreviousDate().getTime(), DateUtils.getEndOfDayForPreviousDate().getTime());
		return dailyUsageDistributorMessage;
	}

	@Bean(name = "monthlyBillingDistributorMessage")
	public MonthlyBillingDistributorMessage getMonthlyBillingDistributorMessage() {
		MonthlyBillingDistributorMessage monthlyBillingDistributorMessage = new MonthlyBillingDistributorMessage(
				DateUtils.getStartOfFirstDayForPreviousMonth().getTime(),
				DateUtils.getEndOfLastDayForPreviousMonth().getTime());
		return monthlyBillingDistributorMessage;
	}

	@Bean(name = "taskExecutionRecurrence")
	public Map<TaskRecurrence, Set<Task>> getTaskExecutionRecurrence() {

		return new HashMap<>();
	}

}
