package ar.com.delivery.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.delivery.dao.LoginDao;
import ar.com.delivery.modelo.Usuario;

@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin{

	@Inject
	private LoginDao loginDao;
	
	@Override
	public Usuario validarLogin(Usuario usuario){
		return loginDao.validarUsuario(usuario);
		}

	
	

}
