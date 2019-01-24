package ar.com.delivery.dao;

import java.util.Date;
import java.util.List;

import ar.com.delivery.modelo.Pedido;
import ar.com.delivery.modelo.PedidoClienteDTO;
import ar.com.delivery.modelo.PedidoProducto;
import ar.com.delivery.modelo.Producto;
import ar.com.delivery.modelo.ProductoCantidad;

public interface PedidoDao {
	
	public Producto buscarProducto(Long idProducto);
	public List<ProductoCantidad> buscarTodosLosTemporalesProductosCantidad();
	public Boolean grabarPedidoProducto(PedidoProducto pedidoProducto);
	public Long grabarPedido(Pedido pedido);
	public Boolean grabarTemporalPedido(ProductoCantidad productoCantidad);
	public Pedido buscarPedido(Long id);
	public void actualizarPrecio(Pedido pedido);
	//public List<PedidoProducto> listarTodosLosPedidosProductosDeUnPedido(Long id);
	public Boolean eliminarProductoCantidad();
	public Boolean eliminarPedido(Pedido pedido);
	public Boolean eliminarPedidoProducto(Pedido pedido);
	public Boolean eliminarProductoCantidad(Long id);
	public List<PedidoClienteDTO> buscarTodosLosPedidosDelDia(Date desde,Date hasta);
	public List<ProductoCantidad> buscarPedidoProductoPorIdPedido(Long id);
	
}
