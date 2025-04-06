package com.mx.bancobase.pruebatecnica.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Pago")
public class Pago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Transient
	 public static final String SEQUENCE_NAME = "pagos_sequence";


	@Id
	@NonNull
	private Long idPago;
	private String concepto;
	private Integer cantidadProductos;
	private String receptor;
	private String destinatarios;
	private BigDecimal monto;
	private String estausPago;
	private String folio;
	private Date fechaCreacion;
	private Date fechaUltimaActualizacion;
	
	
	public Pago() {}

	public Pago(Long idPago, String concepto, Integer cantidadProductos, String receptor, String destinatarios,
			BigDecimal monto, String estausPago, String folio, Date fechaCreacion, Date fechaUltimaActualizacion) {
		this.idPago=idPago;
		this.concepto= concepto;
		this.cantidadProductos = cantidadProductos;
		this.receptor= receptor;
		this.destinatarios= destinatarios;
		this.monto = monto;
		this.estausPago = estausPago;
		this.folio = folio;
		this.fechaCreacion= fechaCreacion;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

}
