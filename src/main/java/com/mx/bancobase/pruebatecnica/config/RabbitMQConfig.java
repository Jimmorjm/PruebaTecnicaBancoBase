package com.mx.bancobase.pruebatecnica.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String EXCHANGE = "message_exchange";
	public static final String ROUTING_KEY_PAGO = "pago_routing_key";
	public static final String QUEUE_PAGO = "queue_pago";
	
	@Value("${bancobase.queue.name}")
    private String queuePagos;

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connection) {
		final var template = new RabbitTemplate(connection);
		template.setMessageConverter(messageConverter());
		return template;
	}

    @Bean
    public Queue queuePagos() {
        return new Queue(queuePagos, true);
    }

	@Bean
	public Binding bindingPagos(Queue queuePagos, TopicExchange topicExchange) {
		return BindingBuilder.bind(queuePagos).to(topicExchange).with(ROUTING_KEY_PAGO);
	}

}
