package ar.com.delivery.servicios;

import java.util.List;

import ar.com.delivery.modelo.Cliente;
import ar.com.delivery.modelo.Pedido;
import ar.com.delivery.modelo.ProductoCantidad;

public interface ServicioImpresion {
	
	public Boolean imprimirComanda(List<ProductoCantidad> productoCantidad,Pedido pedido,Cliente cliente,String obs);

}
