package io.egrow.frontend.scheduler_service.configuration.boundary.rabbit_mq;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.TopicExchange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Lang on 2019-10-11
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class EgrowTopicExchange extends TopicExchange {

    @Getter
    List<EgrowQueue> queues;

    /**
     * This constructor is used for when the bindings are unknown meaning
     * the application is only using the exchange to send messages.
     *
     * @param name is the name of the exchange
     */
    EgrowTopicExchange(String name) {
        super(name);
        this.queues = new ArrayList<>();
    }

    /**
     * This constructor is used for when the bindings are known meaning
     * the application is listening to a queue and defines which messages
     * should be routed to the queue with the routingKey.
     *
     * @param name is the name of the exchange
     * @param queues are the queues which are bound to the exchange
     */
    EgrowTopicExchange(String name, List<EgrowQueue> queues) {
        super(name);
        this.queues = queues;
    }
}
