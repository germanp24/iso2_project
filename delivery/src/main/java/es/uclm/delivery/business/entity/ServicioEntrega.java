package es.uclm.delivery.business.entity;
import java.time.LocalDateTime;
public class ServicioEntrega {

	Pedido pedido;
	Direccion direccion;
	Repartidor repartidor;
	private LocalDateTime  fechaRecepcion;
	private LocalDateTime  fechaEntrega;

}