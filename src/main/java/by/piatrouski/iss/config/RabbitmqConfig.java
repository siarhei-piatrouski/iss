package by.piatrouski.iss.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.queue}")
    private String queue;
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Bean
    public Queue createQueue() {
        return new Queue(queue, true);
    }

    @Bean
    public Exchange createExchange() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Binding createBinding() {
        return BindingBuilder.bind(createQueue()).to(createExchange()).with(routingKey).noargs();
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);
        amqpAdmin.declareQueue(createQueue());
        amqpAdmin.declareExchange(createExchange());
        amqpAdmin.declareBinding(createBinding());
        return amqpAdmin;
    }

}
