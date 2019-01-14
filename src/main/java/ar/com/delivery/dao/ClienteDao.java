package ar.com.delivery.dao;

import ar.com.delivery.modelo.Cliente;

public interface ClienteDao {
	
	public Cliente buscarCliente(Long telefono);
	public Boolean crearCliente(Cliente cliente);

}
