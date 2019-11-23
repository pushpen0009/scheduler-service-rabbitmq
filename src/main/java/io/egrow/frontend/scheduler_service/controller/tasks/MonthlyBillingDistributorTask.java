package io.egrow.frontend.scheduler_service.controller.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egrow.frontend.scheduler_service.controller.TaskHandler;
import io.egrow.frontend.scheduler_service.entity.AbstractTask;
import io.egrow.frontend.scheduler_service.entity.MonthlyBillingDistributorMessage;
import io.egrow.frontend.scheduler_service.entity.TaskRecurrence;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthlyBillingDistributorTask extends AbstractTask {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Exchange billingActionsExchange;

	@Autowired
	private MonthlyBillingDistributorMessage monthlyBillingDistributorMessage;

	Logger LOGGER = LoggerFactory.getLogger(TaskHandler.class);

	public MonthlyBillingDistributorTask(TaskHandler taskHandler) {
		super(taskHandler);
		taskHandler.registerTask(TaskRecurrence.ONCE_IN_A_MONTH, this);
	}

	@Override
	protected void internalExecute() {
		LOGGER.info("Queued: {}", monthlyBillingDistributorMessage);

		this.rabbitTemplate.convertAndSend(billingActionsExchange.getName(), "billing.monthly.invoke-calculation-usage",
				monthlyBillingDistributorMessage);
	}

}
