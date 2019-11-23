package io.egrow.frontend.scheduler_service.configuration.boundary.rabbit_mq;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Anton Lang on 2019-10-11
 *
 * This configuration contains all exchanges which are used by the application.
 * It also provides the connection between exchanges and queues for the queues
 * this application is listening to.
 *
 * To add a new exchange create a new @Bean with a meaningful name and add it to
 * allExchanges. Keep in mind that only the application that listens to queues
 * should provide the queues to the exchange (define the binding).
 */
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExchangesConfiguration {

    @Value("${rabbitmq.exchanges.billing-actions}")
    String EXCHANGE_NAME_BILLING_ACTIONS;

    @Value("${rabbitmq.exchanges.async-api-actions}")
    String EXCHANGE_NAME_ASYNC_API_ACTIONS;

    @Bean(name = "billingActionsExchange")
    EgrowTopicExchange billingActionsExchange(
            EgrowQueue invokeCalculationDailyUsageQueue,
            EgrowQueue saveDailyUserMarketUsageQueue,
            EgrowQueue invokeMonthlyBilling,
            EgrowQueue calculateMonthlyUserBillingQueue
    ) {
        return new EgrowTopicExchange(EXCHANGE_NAME_BILLING_ACTIONS,
                Arrays.asList(
                        invokeCalculationDailyUsageQueue,
                        saveDailyUserMarketUsageQueue,
                        invokeMonthlyBilling,
                        calculateMonthlyUserBillingQueue
                )
        );
    }

    @Bean(name = "asyncApiActionsExchange")
    EgrowTopicExchange asyncApiActionsExchange() {
        return new EgrowTopicExchange(EXCHANGE_NAME_ASYNC_API_ACTIONS);
    }

    @Bean(name = "allExchanges")
    List<EgrowTopicExchange> allExchanges(EgrowTopicExchange billingActionsExchange,
                                          EgrowTopicExchange asyncApiActionsExchange) {
        return Arrays.asList(
                billingActionsExchange,
                asyncApiActionsExchange
        );
    }
}
