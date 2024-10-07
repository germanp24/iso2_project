package es.uclm.delivery.business.entity;

import java.util.Date;
import java.util.UUID;

public class Pago {

	Pedido pedido;
	MetodoPago tipo;
	private UUID idTransaccion;
	private Date fechaTransaccion;

}