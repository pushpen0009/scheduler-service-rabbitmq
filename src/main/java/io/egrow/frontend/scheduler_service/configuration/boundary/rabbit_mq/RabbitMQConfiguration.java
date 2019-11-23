package io.egrow.frontend.scheduler_service.configuration.boundary.rabbit_mq;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Lang on 2019-10-04
 */
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RabbitMQConfiguration {

    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.password}")
    private String password;

    @Value("${rabbitmq.vhost}")
    private String virtualHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    /**
     * It creates all bindings of exchanges which are necessary for the
     * application. The application only binds exchanges to the queues to
     * which it is listening itself.
     *
     * @param allExchanges represent all exchanges that are used by the
     *                     application
     * @return
     */
    @Bean
    public Declarables allBindings(List<EgrowTopicExchange> allExchanges) {

        List<Declarable> bindings = new ArrayList<>();

        for (EgrowTopicExchange exchange : allExchanges) {
            for (EgrowQueue queue : exchange.getQueues()) {
                bindings.add(
                        BindingBuilder
                                .bind(queue)
                                .to(exchange)
                                .with(queue.getRoutingKey())
                );
            }
        }

        return new Declarables(bindings);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
