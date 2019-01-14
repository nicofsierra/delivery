package ar.com.delivery.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import ar.com.delivery.modelo.Cliente;

@Service("clienteDao")
public class ClienteDaoImpl implements ClienteDao{
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public Cliente buscarCliente(Long telefono){
		final Session session = sessionFactory.getCurrentSession();
		return (Cliente) session.createCriteria(Cliente.class)
				.add(Restrictions.eq("telefono",telefono)).uniqueResult();
	}
	
	@Override
	public Boolean crearCliente(Cliente cliente){
		final Session session = sessionFactory.getCurrentSession();
		try{
			session.saveOrUpdate(cliente);
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
