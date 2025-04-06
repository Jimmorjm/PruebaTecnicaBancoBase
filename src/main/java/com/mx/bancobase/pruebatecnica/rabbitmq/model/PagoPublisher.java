package com.mx.bancobase.pruebatecnica.rabbitmq.model;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PagoPublisher implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String folioPago;
	private String estatusPago;

}
