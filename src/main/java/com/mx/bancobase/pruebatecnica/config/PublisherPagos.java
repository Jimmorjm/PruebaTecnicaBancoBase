package com.mx.bancobase.pruebatecnica.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.bancobase.pruebatecnica.rabbitmq.model.PagoPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableRabbit
public class PublisherPagos {
	
	 @Autowired
	 private RabbitTemplate rabbitTemplate;

     @Autowired
     private Queue queuePagos;
     
     public void send(PagoPublisher pagoPublish) {
    	 log.info(" Se envia pago para confirmacion " + pagoPublish.getFolioPago());
         rabbitTemplate.convertAndSend(queuePagos.getName(), pagoPublish);
     }
     
}
