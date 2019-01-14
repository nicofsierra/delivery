package ar.com.delivery.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import ar.com.delivery.modelo.Producto;

@Service("productoDao")
public class ProductoDaoImpl implements ProductoDao {
	 
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public Boolean grabarProducto(Producto producto){
		final Session session = sessionFactory.getCurrentSession();
		try{
			session.save(producto);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> buscarTodosLosProductos(){
		final Session session = sessionFactory.getCurrentSession();
		return (List<Producto>) session.createCriteria(Producto.class)
				.list();
	}
	
	@Override
	public Producto buscarProducto(Long id){
		final Session session = sessionFactory.getCurrentSession();
		return (Producto) session.createCriteria(Producto.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	@Override
	public Boolean borrarProducto(Producto producto){
		final Session session = sessionFactory.getCurrentSession();
		try{
		session.delete(producto);
		return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public Boolean actualizarProducto(Producto producto){
		final Session session = sessionFactory.getCurrentSession();
		try{
			session.saveOrUpdate(producto);
			return true;
			}catch(Exception e){
				return false;
			}
	}
	
}
