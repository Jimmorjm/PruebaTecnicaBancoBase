package com.mx.bancobase.pruebatecnica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mx.bancobase.pruebatecnica.model.Pago;
import com.mx.bancobase.pruebatecnica.service.PagosService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PruebaTecnicaTests {

	@Autowired
	private PagosService pagosService;

	@Test
	public void createPagoTest() throws Exception {
		assertEquals(pagosService.createPago(getPago()), "OK");
	}

	public Pago getPago() {
		Pago pago = new Pago();
		pago.setIdPago(24L);
		pago.setCantidadProductos(12);
		pago.setConcepto("Unit Test Pago");
		pago.setDestinatarios("Test Pago Destinatario");
		pago.setEstausPago("PENDIENTE");
		pago.setFechaCreacion(new Date());
		pago.setFechaUltimaActualizacion(new Date());
		pago.setFolio("0000024");
		pago.setMonto(new BigDecimal(4500));
		pago.setReceptor("Test Pago Receptor");
		return pago;

	}

	@Test
	public void getPagoTest() {
		assertNotEquals(pagosService.getPago("0000001"), null);
	}

	@Test
	public void changeStatusPagoTest() {
		assertEquals(pagosService.changeStatusPago("0000001", "RECHAZADO"), null);
	}

	@Test
	public void getAllPagosTest() {
		assertNotEquals(pagosService.getAllPagos(), null);
	}

}
