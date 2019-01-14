package ar.com.delivery.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import ar.com.delivery.modelo.Usuario;

@Service("loginDao")
public class LoginDaoImpl implements LoginDao{
	
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public Usuario validarUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.and( Restrictions.eq("nombre",usuario.getNombre()),
									   Restrictions.eq("pass",usuario.getPass()))).uniqueResult();
	}
}
