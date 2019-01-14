package ar.com.delivery.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.delivery.modelo.Usuario;

@Controller
public class ControladorIndex {
	
	@RequestMapping("/alta-productos")
	public ModelAndView altaProductos(HttpServletResponse response,HttpServletRequest request){
		request.getSession().removeAttribute("usuario");
		Usuario usuario = new Usuario();
		ModelMap modelo = new ModelMap();
		modelo.put("usuario", usuario);
		return new ModelAndView("login",modelo);
	}
	
	@RequestMapping("/index")
	public ModelAndView irAIndex(HttpServletResponse response,HttpServletRequest request){
		request.getSession().removeAttribute("usuario");
		return new ModelAndView("index");
	}
	
	@RequestMapping("/")
	public ModelAndView irAinicio(HttpServletResponse response,HttpServletRequest request){
		request.getSession().removeAttribute("usuario");
		return new ModelAndView("index");
	}
	

}

	