package ar.com.delivery.servicios;

import java.util.List;

import ar.com.delivery.modelo.Producto;

public interface ServicioProducto {
	
	public Boolean grabarProducto(Producto producto);
	public List<Producto> buscarTodosLosProductos();
	public Producto buscarProducto(Long id);
	public Boolean borrarProducto(Producto producto);
	public Boolean actualizarProducto(Producto producto);
}
