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

import ar.com.delivery.modelo.Cliente;
import ar.com.delivery.servicios.ServicioCliente;

@Controller
public class ControladorCliente {

	@Inject
	private ServicioCliente servicioCliente;

	@RequestMapping("/ver-cliente")
	public ModelAndView verCliente() {
		return new ModelAndView("ver-cliente");
	}

	@RequestMapping("/verificar-cliente")
	public ModelAndView verficaSiEsCliente(@RequestParam("telefono") String telefono, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		Cliente clienteNuevo = new Cliente();
		if (!telefono.isEmpty()) {
			try {
				Long tel = Long.parseLong(telefono);
			} catch (NumberFormatException e) {
				modelo.put("error", "error formato del telefono - debe ingresar solo numeros");
				return new ModelAndView("ver-cliente", modelo);
			}
			Long tel = Long.parseLong(telefono);
			Cliente cliente = servicioCliente.buscarCliente(tel);
			if (cliente != null) {
				request.getSession().setAttribute("cliente", cliente);
				modelo.put("cliente", cliente);
				modelo.put("telefono", telefono);
				return new ModelAndView("pedidos", modelo);
			} else {
				modelo.put("nocliente", "El Cliente no existe");
				modelo.put("clienteNuevo", clienteNuevo);
				modelo.put("telefono", telefono);
				return new ModelAndView("pedidos", modelo);
			}
		} else {
			modelo.put("error", "debe cargar un numero de telefono");
			return new ModelAndView("ver-cliente", modelo);
		}
	}

	@RequestMapping(path = "/nuevo-cliente", method = RequestMethod.POST)
	public ModelAndView crearCliente(@ModelAttribute("clienteNuevo") Cliente cliente, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		if (servicioCliente.crearCliente(cliente)) {
			servicioCliente.buscarCliente(cliente.getTelefono());
			request.getSession().setAttribute("cliente", servicioCliente.buscarCliente(cliente.getTelefono()));
			modelo.put("cliente", cliente);
			modelo.put("telefono", cliente.getTelefono());
			return new ModelAndView("pedidos", modelo);
		} else {
			modelo.put("error", "error al crear cliente");
			return new ModelAndView("ver-cliente", modelo);
		}

	}

	@RequestMapping("volver-cliente")
	public ModelAndView volverACargaDeCliente(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("cliente");
		return new ModelAndView("ver-cliente");

	}

	@RequestMapping("guardar-cambios-cliente")
	public ModelAndView guardarCambios(@RequestParam("telefono") String id, @RequestParam("nombre") String nombre,
			@RequestParam("calle") String calle, @RequestParam("localidad") String localidad,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		Cliente clienteModificado = new Cliente();
		clienteModificado.setTelefono(Long.parseLong(id));
		clienteModificado.setCalle(calle);
		clienteModificado.setNombre(nombre);
		clienteModificado.setLocalidad(localidad);
		if ( servicioCliente.crearCliente(clienteModificado) ){
		modelo.put("cliente", servicioCliente.buscarCliente(Long.parseLong(id)));
		modelo.put("telefono", clienteModificado.getTelefono());
		request.getSession().setAttribute("cliente", clienteModificado);
		return new ModelAndView("pedidos", modelo);
		}else{
			modelo.put("error", "No se pudo guardar el cambio");
			return new ModelAndView("pedidos",modelo);
		}

	}

}
