package es.uclm.delivery.business.controller;

//import es.uclm.delivery.persistency.*;
import es.uclm.delivery.business.entity.*;

import java.util.List;

public class GestorRestaurantes {

	/**
	 * 
	 * @param nombre
	 * @param cif
	 * @param d
	 */
	public Restaurante registrarRestaurante(String nombre, String cif, Direccion d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 * @param items
	 */
	public void editarCarta(String nombre, List<ItemMenu> items) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 * @param precio
	 * @param tipo
	 */
	private ItemMenu crearItem(String nombre, double precio, TipoItemMenu tipo) {
		throw new UnsupportedOperationException();
	}

}