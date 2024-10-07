package es.uclm.delivery.business.controller;

import es.uclm.delivery.persistency.*;
import es.uclm.delivery.business.entity.*;

import java.util.List;



public class GestorPedidos {

	PedidoDAO pedidoDAO;
	ServicioEntregaDAO servicioEntregaDAO;
	Pedido pedidoEnMarcha;

	/**
	 * 
	 * @param c
	 * @param r
	 * @param items
	 */
	public void realizarPedido(Cliente c, Restaurante r, List<ItemMenu> items) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 */
	private boolean realizarPago(Pedido p) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 * @param d
	 */
	private ServicioEntrega crearServicioEntrega(Pedido p, Direccion d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public void anadirItemMenu(ItemMenu item) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param item
	 */
	public void eliminarItemMenu(ItemMenu item) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param resaturante
	 */
	public void comenzarPedido(Restaurante resaturante) {
		throw new UnsupportedOperationException();
	}

}