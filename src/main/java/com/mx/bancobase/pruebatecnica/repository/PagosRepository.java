package com.mx.bancobase.pruebatecnica.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mx.bancobase.pruebatecnica.model.Pago;

@Repository
public interface PagosRepository extends MongoRepository<Pago, Long>{

	List<Pago> findByFolio(String folio);

}
