package io.egrow.frontend.scheduler_service.configuration.boundary.rabbit_mq;

/**
 * Created by anton on 13.11.19
 */
public class QueuesNameLocations {

    public static final String

    QUEUE_LOCATION_INVOKE_CALCULATION_DAILY_USAGE = "${rabbitmq.message-queues.invoke-calculation-daily-usage}",

    QUEUE_LOCATION_SAVE_DAILY_USER_MARKET_USAGE = "${rabbitmq.message-queues.save-daily-user-market-usage}",

    QUEUE_LOCATION_INVOKE_MONTHLY_BILLING = "${rabbitmq.message-queues.invoke-monthly-billing}",

    QUEUE_LOCATION_CALCULATE_MONTHLY_USER_BILLING = "${rabbitmq.message-queues.calculate-monthly-user-bill}";
}
