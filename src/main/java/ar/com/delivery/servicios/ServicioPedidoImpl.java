package ar.com.delivery.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.delivery.dao.PedidoDao;
import ar.com.delivery.modelo.Pedido;
import ar.com.delivery.modelo.PedidoProducto;
import ar.com.delivery.modelo.Producto;
import ar.com.delivery.modelo.ProductoCantidad;

@Service("servicioPedido")
@Transactional
public class ServicioPedidoImpl implements ServicioPedido{
	
	@Inject
	private PedidoDao pedidoDao;
	
	public Producto buscarProducto(Long idProducto){
		return pedidoDao.buscarProducto(idProducto);
	}
	
	public List<ProductoCantidad> buscarTodosLosTemporalesProductosCantidad(){
		return pedidoDao.buscarTodosLosTemporalesProductosCantidad();
	}
	
	public Boolean grabarPedidoProducto(PedidoProducto pedidoProducto){
		return pedidoDao.grabarPedidoProducto(pedidoProducto);
	}
	
	public Long grabarPedido(Pedido pedido){
		return pedidoDao.grabarPedido(pedido);
	}
	
	public Boolean grabarTemporalPedido(ProductoCantidad productoCantidad){
		return pedidoDao.grabarTemporalPedido(productoCantidad);
	}
	
	public Pedido buscarPedido(Long id){
		return pedidoDao.buscarPedido(id);
	}
	
	public void actualizarPrecio(Pedido pedido){
		pedidoDao.actualizarPrecio(pedido);
	}
	
	/*public List<PedidoProducto> listarTodosLosPedidosProductosDeUnPedido(Long id){
		return pedidoDao.listarTodosLosPedidosProductosDeUnPedido(id);
	}*/
	
	public Boolean eliminarProductoCantidad(){
		return pedidoDao.eliminarProductoCantidad();
	}
	
	public Boolean eliminarPedido(Pedido pedido){
		return pedidoDao.eliminarPedido(pedido);
	}
	
	public Boolean eliminarPedidoProducto(Pedido pedido){
		return pedidoDao.eliminarPedidoProducto(pedido);
	}
	
	public Boolean eliminarProductoCantidad(Long id){
		return pedidoDao.eliminarProductoCantidad(id);
	}

}
