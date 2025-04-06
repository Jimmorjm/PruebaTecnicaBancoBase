package com.mx.bancobase.pruebatecnica.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.bancobase.pruebatecnica.config.PublisherPagos;
import com.mx.bancobase.pruebatecnica.config.RabbitMQConfig;
import com.mx.bancobase.pruebatecnica.model.EstatusPagoEnum;
import com.mx.bancobase.pruebatecnica.model.Pago;
import com.mx.bancobase.pruebatecnica.rabbitmq.model.PagoPublisher;
import com.mx.bancobase.pruebatecnica.repository.PagosRepository;
import com.mx.bancobase.pruebatecnica.repository.impl.PagosRepositoryImpl;
import com.mx.bancobase.pruebatecnica.service.PagosService;

@Service
public class PagosServicesImpl implements PagosService {

	@Autowired
	private PagosRepository pagosRepository;
	
	@Autowired
	private PublisherPagos publisherPagos;

	@Autowired
	private PagosRepositoryImpl pagosRepositoryImpl;

	@Override
	public void createPago(Pago pago) {
		Pago nextPago = pagosRepositoryImpl.getLastPago();
		String newFolio = generateFolio(nextPago.getIdPago());
		pago.setIdPago(Long.valueOf(newFolio));
		pago.setFolio(newFolio);
		pago.setFechaCreacion(new Date());
		pago.setFechaUltimaActualizacion(new Date());
		pago.setEstausPago(EstatusPagoEnum.PAGO_PENDIENTE.getNombre());
		pagosRepository.save(pago);
	}

	public String generateFolio(Long idFolio) {
		if (idFolio == null ) {
			return "0000001";
		}
		idFolio= idFolio + 1L;
		String val = idFolio.toString();
		for (int i = 0; val.length() <= 7; i++) {
			if (val.length() == 7) {
				break;
			}
			val = "0" + val;
		}
		return val;
	}

	@Override
	public Pago getPago(String folioPago) {
		List<Pago> lst = pagosRepository.findByFolio(folioPago);
		return lst != null && !lst.isEmpty() ? lst.get(0) : null;
	}

	@Override
	public String changeStatusPago(String folioPago, String statusPago) {
		List<Pago> lst = pagosRepository.findByFolio(folioPago);

		if (lst == null || lst.isEmpty()) {
			return "Folio del pago no encontrado";
		}
		Pago pago = lst.get(0);
		pago.setEstausPago(statusPago);
		pagosRepository.save(pago);

		PagoPublisher pagoPublish = new PagoPublisher();
		pagoPublish.setFolioPago(folioPago);
		pagoPublish.setEstatusPago(statusPago);
		publisherPagos.send(pagoPublish);
		
		return null;
	}

	@Override
	public List<Pago> getAllPagos() {
		return pagosRepository.findAll();
	}

}
