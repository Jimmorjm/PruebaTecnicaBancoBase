package com.mx.bancobase.pruebatecnica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.bancobase.pruebatecnica.model.EstatusPagoEnum;
import com.mx.bancobase.pruebatecnica.model.Pago;
import com.mx.bancobase.pruebatecnica.service.PagosService;

@RestController
@RequestMapping(path = "/pagos")
public class PagosController {

	@Autowired
	private PagosService pagosService;

	@GetMapping(path = "/getPago/{folioPago}")
	public ResponseEntity<Pago> getPago(@PathVariable("folioPago") String folioPago) {
		Pago pago = pagosService.getPago(folioPago);
		if (pago == null) {
			return new ResponseEntity<Pago>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pago>(pago, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAllPagos")
	public ResponseEntity<List<Pago>> getAllPagos() {
		List<Pago> lst = pagosService.getAllPagos();
		if (lst == null) {
			return new ResponseEntity<List<Pago>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Pago>>(lst, HttpStatus.OK);
	}

	@PostMapping(path = "/crearPago")
	public ResponseEntity<String> crearPago(@RequestBody Pago pago) {
		try {
			String message = pagosService.createPago(pago);
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/updateStatusPago/{folioPago}")
	public ResponseEntity<String> changeStatusPago(@PathVariable("folioPago") String folioPago,
			@RequestBody Pago estatusPago) {
		String estatus = getEstatusPago(estatusPago.getEstausPago());
		if (estatus == null) {
			return new ResponseEntity<String>("El estatus enviado no se encuentra en el catalogo",
					HttpStatus.NOT_FOUND);
		}

		String message = pagosService.changeStatusPago(folioPago, estatus);
		if (message != null && !message.isEmpty()) {
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	public String getEstatusPago(String estatusPago) {
		EstatusPagoEnum estatusEnum = EstatusPagoEnum.getEstatusEnum(estatusPago);
		if (estatusEnum != null) {
			return estatusEnum.getNombre();
		}
		return null;
	}

}
