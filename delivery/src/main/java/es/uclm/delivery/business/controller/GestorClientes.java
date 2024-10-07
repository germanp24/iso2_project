package es.uclm.delivery.business.controller;

import java.util.List;

import es.uclm.delivery.persistency.RestauranteDAO;
import es.uclm.delivery.business.entity.Cliente;
import es.uclm.delivery.business.entity.Direccion;
import es.uclm.delivery.business.entity.Restaurante;
import es.uclm.delivery.business.entity.CodigoPostal;

public class GestorClientes {

	RestauranteDAO restauranteDAO;

	/**
	 * 
	 * @param zona
	 */
	public List<Restaurante> buscarRestaurante(CodigoPostal zona) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cadenaBusqueda
	 */
	public List<Restaurante> buscarRestaurante(String cadenaBusqueda) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cliente
	 * @param r
	 */
	public void favorito(Cliente cliente, Restaurante r) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 * @param apellido
	 * @param d
	 */
	public Cliente registrarCliente(String nombre, String apellido, Direccion d) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param calle
	 * @param numeero
	 * @param complemento
	 * @param cp
	 * @param municipio
	 */
	private Direccion altaDirecion(String calle, String numeero, String complemento, CodigoPostal cp, String municipio) {
		throw new UnsupportedOperationException();
	}

}