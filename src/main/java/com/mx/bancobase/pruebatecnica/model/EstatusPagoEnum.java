package com.mx.bancobase.pruebatecnica.model;

public enum EstatusPagoEnum {

	PAGO_APROBADO("APROBADO"), PAGO_RECHAZADO("RECHAZADO"), PAGO_PENDIENTE("PENDIENTE");

	private String nombre;

	EstatusPagoEnum(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static EstatusPagoEnum getEstatusEnum(String estatusPago) {
		if (estatusPago.equals(PAGO_APROBADO.getNombre())) {
			return PAGO_APROBADO;
		} else if (estatusPago.equals(PAGO_RECHAZADO.getNombre())) {
			return PAGO_RECHAZADO;
		} else if (estatusPago.equals(PAGO_PENDIENTE.getNombre())) {
			return PAGO_PENDIENTE;
		}
		return null;
	}

}
