package io.egrow.frontend.scheduler_service.configuration.boundary.rabbit_mq;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.Queue;

/**
 * Created by Anton Lang on 2019-10-11
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class EgrowQueue extends Queue {

    @Getter
    String routingKey;

    EgrowQueue(String name, final String routingKey) {
        super(name);
        this.routingKey = routingKey;
    }
}
