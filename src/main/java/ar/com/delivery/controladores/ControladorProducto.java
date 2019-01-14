package ar.com.delivery.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.com.delivery.modelo.Producto;
import ar.com.delivery.servicios.ServicioProducto;

@Controller
public class ControladorProducto {

	@Inject
	private ServicioProducto servicioProducto;

	@RequestMapping("abm-productos")
	public ModelAndView abmProductos(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("usuario", request.getSession().getAttribute("usuario"));
		return new ModelAndView("abm-productos");
	}

	@RequestMapping("prepara-alta")
	public ModelAndView irAAltaProducto(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		request.getSession().setAttribute("usuario", request.getSession().getAttribute("usuario"));
		Producto producto = new Producto();
		modelo.put("producto", producto);
		return new ModelAndView("alta-productos", modelo);
	}

	@RequestMapping(path = "alta-producto", method = RequestMethod.POST)
	public ModelAndView altaProducto(@ModelAttribute("producto") Producto producto, HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().setAttribute("usuario", request.getSession().getAttribute("usuario"));
		Producto productoNuevo = new Producto();
		ModelMap modelo = new ModelMap();
		if (servicioProducto.grabarProducto(producto)) {
			modelo.put("producto", productoNuevo);
			return new ModelAndView("alta-productos", modelo);
		} else {
			modelo.put("error", "El producto ya existe");
			return new ModelAndView("alta-productos", modelo);
		}
	}

	@RequestMapping("ver-todos")
	public ModelAndView verTodosLosProductos(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		request.getSession().setAttribute("usuario", request.getSession().getAttribute("usuario"));
		modelo.put("productos", servicioProducto.buscarTodosLosProductos());
		return new ModelAndView("ver-todos", modelo);
	}

	@RequestMapping("borrar-producto")
	public ModelAndView borrarProducto(@RequestParam("id") Long idProducto, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		request.getSession().setAttribute("usuario", request.getSession().getAttribute("usuario"));
		if (servicioProducto.borrarProducto(servicioProducto.buscarProducto(idProducto))) {
			modelo.put("productos", servicioProducto.buscarTodosLosProductos());
			return new ModelAndView("ver-todos", modelo);
		} else {
			return new ModelAndView("ver-todos");
		}
	}

	@RequestMapping("editar")
	public ModelAndView irAEditarProducto(@RequestParam("id") Long idProducto, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		Producto producto = new Producto();
		request.getSession().setAttribute("usuario", request.getSession().getAttribute("usuario"));
		producto = servicioProducto.buscarProducto(idProducto);
		if (producto == null) {
			modelo.put("productos", servicioProducto.buscarTodosLosProductos());
			modelo.put("error", "el producto no existe");
			return new ModelAndView("ver-todos", modelo);
		} else {
			modelo.put("producto", producto);
			return new ModelAndView("editar", modelo);
		}
	}

	@RequestMapping(path = "graba-edicion", method = RequestMethod.GET)
	public ModelAndView grabarEdicion(@RequestParam("id") String id,
									  @RequestParam("nombre") String nombre,
									  @RequestParam("precioUnitario") String precioU, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		Producto productoAct = new Producto();
		request.getSession().setAttribute("usuario", request.getSession().getAttribute("usuario"));
		productoAct.setId(Long.parseLong(id));
		productoAct.setNombre(nombre);
		productoAct.setPrecioUnitario(Float.parseFloat(precioU));
		if (servicioProducto.actualizarProducto(productoAct)) {
			modelo.put("productos", servicioProducto.buscarTodosLosProductos());
			return new ModelAndView("ver-todos",modelo);
		} else {
			modelo.put("productos", servicioProducto.buscarTodosLosProductos());
			modelo.put("error", "hubo algun error al grabar el cambio");
			return new ModelAndView("ver-todos", modelo);
		}

	}

}
