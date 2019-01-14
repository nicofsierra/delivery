package ar.com.delivery.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import ar.com.delivery.modelo.Pedido;
import ar.com.delivery.modelo.PedidoProducto;
import ar.com.delivery.modelo.Producto;
import ar.com.delivery.modelo.ProductoCantidad;

@Service("pedidoDao")
public class PedidoDaoImpl implements PedidoDao{
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public Producto buscarProducto(Long idProducto){
		final Session session = sessionFactory.getCurrentSession();
		return (Producto) session.createCriteria(Producto.class)
				.add(Restrictions.eq("id",idProducto)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductoCantidad> buscarTodosLosTemporalesProductosCantidad(){
		final Session session = sessionFactory.getCurrentSession();
		return (List<ProductoCantidad>) session.createCriteria(ProductoCantidad.class)
				.list();
	}
	
	@Override
	public Boolean grabarPedidoProducto(PedidoProducto pedidoProducto){
		final Session session = sessionFactory.getCurrentSession();
		try{
			session.save(pedidoProducto);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Long grabarPedido(Pedido pedido){
		final Session session = sessionFactory.getCurrentSession();
		try{
			return (Long)session.save(pedido);
		}catch(Exception e){
			return 0L;
		}
		
	}
	
	@Override
	public Boolean grabarTemporalPedido(ProductoCantidad productoCantidad){
		final Session session = sessionFactory.getCurrentSession();
		try{
			session.save(productoCantidad);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Pedido buscarPedido(Long id){
		final Session session = sessionFactory.getCurrentSession();
		return (Pedido) session.createCriteria(Pedido.class)
				.add(Restrictions.eq("id",id)).uniqueResult();
	}
	
	@Override
	public void actualizarPrecio(Pedido pedido){
		final Session session = sessionFactory.getCurrentSession();
		Double precio = (Double) session.createCriteria(PedidoProducto.class)
				.setProjection(Projections.sum("precio"))
				.add(Restrictions.eq("pedido.id",pedido.getId()))
				.uniqueResult();
		pedido.setPrecio(precio.floatValue());
		session.saveOrUpdate(pedido);
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<PedidoProducto> listarTodosLosPedidosProductosDeUnPedido(Long id){
		final Session session = sessionFactory.getCurrentSession();
		return (List<PedidoProducto>) session.createCriteria(PedidoProducto.class)
				.add(Restrictions.eq("pedido.id",id)).list();
	}*/
	
	@Override
	public Boolean eliminarProductoCantidad(){
		final Session session = sessionFactory.getCurrentSession();
		try{
		session.createQuery("DELETE FROM ProductoCantidad").executeUpdate();
		return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Boolean eliminarPedido(Pedido pedido){
		final Session session = sessionFactory.getCurrentSession();
		try{
			session.delete(pedido);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Boolean eliminarPedidoProducto(Pedido pedido){
		final Session session = sessionFactory.getCurrentSession();
		try{
			session.createQuery("DELETE FROM PedidoProducto WHERE pedido= :idPedido" ).setParameter("idPedido", pedido).executeUpdate();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Boolean eliminarProductoCantidad(Long id){
		final Session session = sessionFactory.getCurrentSession();
		try{
			session.createQuery("DELETE FROM ProductoCantidad WHERE id= :idProdCant" ).setParameter("idProdCant", id).executeUpdate();
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
