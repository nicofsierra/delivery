package ar.com.delivery.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date fecha;
	
	private Float precio;
	
	private Float pagaCon;
	
	private Float vuelto;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente ;
	@OneToMany(mappedBy="pedido",cascade = CascadeType.ALL)
	private List<PedidoProducto> pedidoProducto = new ArrayList<>();
	
	public Pedido(){}

	public Pedido(Long id, Date fecha, Cliente cliente, List<PedidoProducto> pedidoProducto, Float precio, Float pagaCon, Float vuelto) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
		this.pedidoProducto = pedidoProducto;
		this.precio = precio;
		this.pagaCon = pagaCon;
		this.vuelto = vuelto;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Float getPagaCon() {
		return pagaCon;
	}

	public void setPagaCon(Float pagaCon) {
		this.pagaCon = pagaCon;
	}

	public Float getVuelto() {
		return vuelto;
	}

	public void setVuelto(Float vuelto) {
		this.vuelto = vuelto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PedidoProducto> getPedidoProducto() {
		return pedidoProducto;
	}

	public void setPedidoProducto(List<PedidoProducto> pedidoProducto) {
		this.pedidoProducto = pedidoProducto;
	}
	
}
