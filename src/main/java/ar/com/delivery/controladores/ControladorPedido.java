package ar.com.delivery.controladores;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.com.delivery.modelo.Cliente;
import ar.com.delivery.modelo.Pedido;
import ar.com.delivery.modelo.PedidoProducto;
import ar.com.delivery.modelo.Producto;
import ar.com.delivery.modelo.ProductoCantidad;
import ar.com.delivery.servicios.ServicioCliente;
import ar.com.delivery.servicios.ServicioImpresion;
import ar.com.delivery.servicios.ServicioPedido;

@Controller
public class ControladorPedido {

	@Inject
	private ServicioPedido servicioPedido;
	@Inject
	private ServicioCliente servicioCliente;
	@Inject
	private ServicioImpresion servicioImpresion;

	@RequestMapping("/preparar-pedido")
	public ModelAndView prepararPedido(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		modelo.put("cliente", request.getSession().getAttribute("cliente"));
		return new ModelAndView("preparar-pedido", modelo);
	}

	@RequestMapping("/procesar-pedido")
	public ModelAndView procesarPedido(@RequestParam("producto") String producto,
			@RequestParam("cantidad") String cantidad, @RequestParam("clienteId") String clienteId,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		ProductoCantidad productoCantidad = new ProductoCantidad();
		Producto productoBuscado = new Producto();
		modelo.put("cliente", request.getSession().getAttribute("cliente"));
		if (!(producto.isEmpty() || cantidad.isEmpty())) {
			productoBuscado = servicioPedido.buscarProducto(Long.parseLong(producto));
			if (productoBuscado != null) {
				productoCantidad.setNombre(productoBuscado.getNombre());
				productoCantidad.setCantidad(Integer.parseInt(cantidad));
				productoCantidad.setProductoId(productoBuscado.getId());
				productoCantidad.setPrecio(productoBuscado.getPrecioUnitario() * Long.parseLong(cantidad));
				servicioPedido.grabarTemporalPedido(productoCantidad);
				modelo.put("productoCantidad", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
				return new ModelAndView("preparar-pedido", modelo);
			} else {
				modelo.put("productoCantidad", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
				modelo.put("error", "el producto no existe");
				return new ModelAndView("preparar-pedido", modelo);
			}
		} else {
			modelo.put("productoCantidad", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
			modelo.put("error", "debe completar un producto/cantidad");
			return new ModelAndView("preparar-pedido", modelo);
		}
	}

	@RequestMapping("finalizar-pedido")
	public ModelAndView finalizaElPedido(@RequestParam("clienteId") Long cliente, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		modelo.put("cliente", request.getSession().getAttribute("cliente"));
		List<ProductoCantidad> productoCantidad = new ArrayList<>();
		Timestamp timestamp = new Timestamp(new Date().getTime());
		Pedido pedido = new Pedido();
		Pedido pedidoGuardado = new Pedido();
		PedidoProducto pedidoProducto = new PedidoProducto();
		productoCantidad = servicioPedido.buscarTodosLosTemporalesProductosCantidad();
		if (!productoCantidad.isEmpty()) {
			// grabo el pedido
			pedido.setFecha(timestamp);
			pedido.setCliente(servicioCliente.buscarCliente(cliente));
			pedidoGuardado = servicioPedido.buscarPedido(servicioPedido.grabarPedido(pedido));
			// VER ESTA VERGA DE ITERATOR : DONE
			for (ProductoCantidad pCant : productoCantidad) {
				// grabo PedidoProducto
				pedidoProducto.setCantidad(pCant.getCantidad());
				pedidoProducto.setPedido(pedidoGuardado);
				pedidoProducto.setPrecio(pCant.getPrecio());
				// VER PORQUE NO GRABA EL PRODUCTO : DONE
				pedidoProducto.setProducto(servicioPedido.buscarProducto(pCant.getProductoId()));
				servicioPedido.grabarPedidoProducto(pedidoProducto);
			}
			// calculo precio
			servicioPedido.actualizarPrecio(pedidoGuardado);
			modelo.put("pedidosProductos", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
			modelo.put("pedido", servicioPedido.buscarPedido(pedidoGuardado.getId()));
			return new ModelAndView("finalizar-pedido", modelo);
		} else {
			modelo.put("error", "debe ingresar al menos un producto para finalizar la compra");
			return new ModelAndView("preparar-pedido", modelo);
		}
	}

	@RequestMapping("imprimir-comanda")
	public ModelAndView imprimeComanda(@RequestParam("clienteId") String clienteId, @RequestParam("pedidoId") String id,
			@RequestParam("vuelto") String vuelto, @RequestParam("obs") String observac, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		Float verVuelto = 0F;
		modelo.put("detalles", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
		modelo.put("cliente", request.getSession().getAttribute("cliente"));
		modelo.put("cliente", servicioCliente.buscarCliente(Long.parseLong(clienteId)));
		modelo.put("pedido", servicioPedido.buscarPedido(Long.parseLong(id)));
		if (vuelto.isEmpty()) {
			modelo.put("error", "Debe ingresar un importe en vuelto");
			modelo.put("pedidosProductos", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
			modelo.put("pedido", servicioPedido.buscarPedido(Long.parseLong(id)));
			return new ModelAndView("finalizar-pedido", modelo);
		} else {
			try {
				verVuelto = Float.parseFloat(vuelto) - servicioPedido.buscarPedido(Long.parseLong(id)).getPrecio();
			} catch (Exception e) {
				modelo.put("error", "El vuelto DEBE SER UN NUMERO");
				modelo.put("pedidosProductos", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
				modelo.put("pedido", servicioPedido.buscarPedido(Long.parseLong(id)));
				return new ModelAndView("finalizar-pedido", modelo);
			}
		}
		if (verVuelto < 0) {
			modelo.put("error", "El vuelto no puede ser menor a 0");
			modelo.put("pedidosProductos", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
			modelo.put("pedido", servicioPedido.buscarPedido(Long.parseLong(id)));
			return new ModelAndView("finalizar-pedido", modelo);
		} else {
			Pedido pedidoActualizaVuelto = servicioPedido.buscarPedido(Long.parseLong(id));
			pedidoActualizaVuelto.setPagaCon(Float.parseFloat(vuelto));
			pedidoActualizaVuelto.setVuelto(verVuelto);
			if( !servicioPedido.actualizarPedidoVuelto(pedidoActualizaVuelto)){
				modelo.put("error", "Algo salio mal!!");
				modelo.put("pedidosProductos", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
				modelo.put("pedido", servicioPedido.buscarPedido(Long.parseLong(id)));
				return new ModelAndView("finalizar-pedido", modelo);
			}else{
			modelo.put("vuelto", verVuelto);
			servicioImpresion.imprimirComanda(servicioPedido.buscarTodosLosTemporalesProductosCantidad(),
					servicioPedido.buscarPedido(Long.parseLong(id)),
					servicioCliente.buscarCliente(Long.parseLong(clienteId)), 
					observac);
			return new ModelAndView("confirma-comanda", modelo);
			}
		}

	}

	@RequestMapping("volver-preparar-pedido")
	public ModelAndView volverAPrepararPedido(@RequestParam("clienteId") String clienteId,
			@RequestParam("pedidoId") Long id, HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		modelo.put("productoCantidad", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
		modelo.put("pedido", servicioPedido.buscarPedido(id));
		servicioPedido.eliminarPedido(servicioPedido.buscarPedido(id));
		servicioPedido.eliminarPedidoProducto(servicioPedido.buscarPedido(id));
		return new ModelAndView("preparar-pedido", modelo);
	}

	@RequestMapping("borrar-producto-pedido")
	public ModelAndView borrarProductoCantidad(@RequestParam("pCantId") String pCantId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelo = new ModelMap();
		modelo.put("cliente", request.getSession().getAttribute("cliente"));
		servicioPedido.eliminarProductoCantidad(Long.parseLong(pCantId));
		modelo.put("productoCantidad", servicioPedido.buscarTodosLosTemporalesProductosCantidad());
		return new ModelAndView("preparar-pedido", modelo);
	}

	@RequestMapping("cancelar-pedido")
	public ModelAndView cancelarPedido(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("cliente");
		servicioPedido.eliminarProductoCantidad();
		return new ModelAndView("ver-cliente");
	}

	@RequestMapping("ver-pedidos")
	public ModelAndView verTodosLosPedidosDelDia() {
		ModelMap modelo = new ModelMap();

		// Obtiene fecha de hoy - Formato yyyy-MM-dd
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String fechaDesde = dateFormat.format(date);
		String fechaHasta = dateFormat.format(date);

		// convierto el string yyyy-mm-dd a yyyy-mm-dd HH:mm:ss
		fechaDesde += " 00:00:00";
		fechaHasta += " 23:59:59";
		System.out.print(fechaDesde);
		System.out.print(fechaHasta);
		// CAST String To Date
		DateFormat formatoFechaDesde = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat formatoFechaHasta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateDesde = new Date();
		try {
			dateDesde = formatoFechaDesde.parse(fechaDesde);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date dateHasta = new Date();
		try {
			dateHasta = formatoFechaHasta.parse(fechaHasta);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelo.put("pedidos", servicioPedido.buscarTodosLosPedidosDelDia(dateDesde,dateHasta));

		

		return new ModelAndView("ver-pedidos", modelo);

	}
	
	@RequestMapping("reimprimir-comanda")
	public ModelAndView reimpresionComanda(@RequestParam("idPedido") Long id){
		Pedido pedido = new Pedido();
		pedido = servicioPedido.buscarPedido(id);
		Cliente cliente = servicioCliente.buscarCliente(pedido.getCliente().getTelefono());
		List<ProductoCantidad> productoCantidad = new ArrayList<>();
		productoCantidad = servicioPedido.buscarPedidoProductoPorIdPedido(id);
		servicioImpresion.imprimirComanda(productoCantidad,pedido,cliente,"");
		return new ModelAndView("index");
	}

}
