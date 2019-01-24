package ar.com.delivery.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import ar.com.delivery.modelo.Pedido;
import ar.com.delivery.modelo.PedidoClienteDTO;
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
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PedidoClienteDTO> buscarTodosLosPedidosDelDia(Date desde,Date hasta){
		final Session session = sessionFactory.getCurrentSession();
		return (List<PedidoClienteDTO>) session.createCriteria(Pedido.class,"p")
				.createAlias("cliente", "c")
				.setProjection(Projections.projectionList()
						.add(Projections.property("p.id"),"idPedido")
						.add(Projections.property("c.telefono"),"telefono")
						.add(Projections.property("c.nombre"),"nombre")
						.add(Projections.property("c.calle"),"domicilio")
						.add(Projections.property("c.localidad"),"localidad")
						.add(Projections.property("p.fecha"),"fecha")
						.add(Projections.property("p.precio"),"importe") )
				.add(Restrictions.ge("p.fecha",desde))
				.add(Restrictions.le("p.fecha", hasta))
				.setResultTransformer(Transformers.aliasToBean(PedidoClienteDTO.class))
				.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ProductoCantidad> buscarPedidoProductoPorIdPedido(Long id){
		final Session session = sessionFactory.getCurrentSession();
		return (List<ProductoCantidad>) session.createCriteria(PedidoProducto.class,"pP")
				.createAlias("producto", "prod")
				.createAlias("pedido", "ped")
				.setProjection(Projections.projectionList()
				.add(Projections.property("prod.id"),"productoId")
				.add(Projections.property("prod.nombre"),"nombre")
				.add(Projections.property("pP.cantidad"),"cantidad")
				.add(Projections.property("pP.precio"),"precio") )
				.add(Restrictions.eq("ped.id", id))
				.setResultTransformer(Transformers.aliasToBean(ProductoCantidad.class))
				.list();
	}
}
