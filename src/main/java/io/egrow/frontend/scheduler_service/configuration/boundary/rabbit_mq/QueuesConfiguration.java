package io.egrow.frontend.scheduler_service.configuration.boundary.rabbit_mq;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Anton Lang on 2019-10-11
 *
 * This configuration contains all queues that are used by the application.
 *
 * If another queue needs to be added then add the queue name in the
 * application-rabbitmq.yml and add a @Bean with an appropriate name to this
 * configuration.
 */
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QueuesConfiguration {

    @Value(QueuesNameLocations.QUEUE_LOCATION_INVOKE_CALCULATION_DAILY_USAGE)
    String QUEUE_NAME_INVOKE_CALCULATION_DAILY_USAGE;

    @Value(QueuesNameLocations.QUEUE_LOCATION_SAVE_DAILY_USER_MARKET_USAGE)
    String QUEUE_NAME_SAVE_DAILY_USER_MARKET_USAGE;

    @Value(QueuesNameLocations.QUEUE_LOCATION_INVOKE_MONTHLY_BILLING)
    String QUEUE_NAME_INVOKE_MONTHLY_BILLING;

    @Value(QueuesNameLocations.QUEUE_LOCATION_CALCULATE_MONTHLY_USER_BILLING)
    String QUEUE_NAME_CALCULATE_MONTHLY_USER_BILLING;

    @Bean(name = "invokeCalculationDailyUsageQueue")
    EgrowQueue invokeCalculationDailyUsageQueue() {
        return new EgrowQueue(QUEUE_NAME_INVOKE_CALCULATION_DAILY_USAGE, "#.billing.daily.invoke-calculation-usage");
    }

    @Bean(name = "saveDailyUserMarketUsageQueue")
    EgrowQueue saveDailyUserMarketUsageQueue() {
        return new EgrowQueue(QUEUE_NAME_SAVE_DAILY_USER_MARKET_USAGE, "#.billing.daily.save-user-usage");
    }

    @Bean(name = "invokeMonthlyBilling")
    EgrowQueue invokeMonthlyBilling() {
        return new EgrowQueue(QUEUE_NAME_INVOKE_MONTHLY_BILLING, "#.billing.monthly.invoke-billing");
    }

    @Bean(name = "calculateMonthlyUserBillingQueue")
    EgrowQueue calculateMonthlyUserBillingQueue() {
        return new EgrowQueue(QUEUE_NAME_CALCULATE_MONTHLY_USER_BILLING, "#.billing.monthly.calculate-user-bill");
    }
}
