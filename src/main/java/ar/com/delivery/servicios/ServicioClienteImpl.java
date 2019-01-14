package ar.com.delivery.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.delivery.dao.ClienteDao;
import ar.com.delivery.modelo.Cliente;

@Service("servicioCliente")
@Transactional
public class ServicioClienteImpl implements ServicioCliente{
	
	@Inject
	private ClienteDao clienteDao;
	
	public Cliente buscarCliente(Long telefono){
		return clienteDao.buscarCliente(telefono);
	}
	
	public Boolean crearCliente(Cliente cliente){
		return clienteDao.crearCliente(cliente);
	}

}
