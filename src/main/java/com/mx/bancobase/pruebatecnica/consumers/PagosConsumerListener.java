package com.mx.bancobase.pruebatecnica.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.mx.bancobase.pruebatecnica.rabbitmq.model.PagoPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PagosConsumerListener {
	
	
	@RabbitListener(queues = { "${bancobase.queue.name}" })
	public void receive(@Payload PagoPublisher pago) {
		log.info("Pago procesado +"+ pago.getFolioPago() + " con el estatus: "+pago.getEstatusPago());
		makeSlow();
	}
	
	private void makeSlow() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
