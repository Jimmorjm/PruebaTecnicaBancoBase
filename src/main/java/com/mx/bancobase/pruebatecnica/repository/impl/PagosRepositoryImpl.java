package com.mx.bancobase.pruebatecnica.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mx.bancobase.pruebatecnica.model.Pago;

@Repository
public class PagosRepositoryImpl {

	@Autowired
	MongoOperations mongoOperations;

	public Pago getLastPago() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "idPago"));
		query.limit(1);
		Pago pago = mongoOperations.findOne(query, Pago.class);
		return pago;
	}
}
