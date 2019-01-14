package ar.com.delivery.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.delivery.dao.ProductoDao;
import ar.com.delivery.modelo.Producto;

@Service("servicioProducto")
@Transactional
public class ServicioProductoImpl implements ServicioProducto {
	
	@Inject
	private ProductoDao productoDao;
	
	public Boolean grabarProducto(Producto producto){
		return productoDao.grabarProducto(producto);
	}
	
	public List<Producto> buscarTodosLosProductos(){
		return productoDao.buscarTodosLosProductos();
	}
	
	public Producto buscarProducto(Long id){
		return productoDao.buscarProducto(id);
	}
	public Boolean borrarProducto(Producto producto){
		return productoDao.borrarProducto(producto);
	}
	
	public Boolean actualizarProducto(Producto producto){
		return productoDao.actualizarProducto(producto);
	}

}
