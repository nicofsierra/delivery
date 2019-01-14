package ar.com.delivery.servicios;

import ar.com.delivery.modelo.Cliente;

public interface ServicioCliente {
	
	public Cliente buscarCliente(Long telefono);
	public Boolean crearCliente(Cliente cliente);

}
