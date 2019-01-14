package ar.com.delivery.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.com.delivery.modelo.Producto;
import ar.com.delivery.modelo.Usuario;
import ar.com.delivery.servicios.ServicioLogin;

@Controller
public class ControladorLogin {

	@Inject
	ServicioLogin servicioLogin;

	@RequestMapping("/login")
	public ModelAndView irALogin() {
		return new ModelAndView("login");
	}

	@RequestMapping(path = "/validar-usuario", method = RequestMethod.POST)
	public ModelAndView validarUsuario(@ModelAttribute("usuario") Usuario usuarioValidar, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		Usuario validado = new Usuario();
		if (usuarioValidar.getNombre() == null || usuarioValidar.getPass() == null) {
			modelo.put("error", "debe ingresar nombre y password");
			return new ModelAndView("login", modelo);
		} else {
			validado = servicioLogin.validarLogin(usuarioValidar);
			if (validado == null) {
				modelo.put("error", "usuario incorrecto");
				return new ModelAndView("login", modelo);
			} else {
				if (validado.getAdm()) {
					request.getSession().setAttribute("usuario", validado);
					return new ModelAndView("abm-productos", modelo);
				} else {
					modelo.put("error", "Error usuario");
					return new ModelAndView("login", modelo);
				}

			}
		}

	}
}