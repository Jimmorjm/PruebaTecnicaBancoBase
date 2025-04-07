package com.mx.bancobase.pruebatecnica.service;

import java.util.List;

import com.mx.bancobase.pruebatecnica.model.Pago;

public interface PagosService {
	
	public String createPago(Pago pago)  throws Exception;
	
	public Pago getPago(String folioPago);
	
	public String changeStatusPago(String folioPago, String estatusPago);
	
	public List<Pago> getAllPagos();

}
