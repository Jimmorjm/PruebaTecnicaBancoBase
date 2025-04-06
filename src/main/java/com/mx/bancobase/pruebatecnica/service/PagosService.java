package com.mx.bancobase.pruebatecnica.service;

import java.util.List;

import com.mx.bancobase.pruebatecnica.model.Pago;

public interface PagosService {
	
	public void createPago(Pago pago);
	
	public Pago getPago(String folioPago);
	
	public String changeStatusPago(String folioPago, String estatusPago);
	
	public List<Pago> getAllPagos();

}
