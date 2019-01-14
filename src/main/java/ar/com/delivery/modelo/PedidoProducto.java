package ar.com.delivery.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PedidoProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	private Float precio;
	
	@ManyToOne
	@JoinColumn(name="pedido")
	private Pedido pedido;
	@ManyToOne
	@JoinColumn(name="producto")
	private Producto producto;
	
	public PedidoProducto(){}

	public PedidoProducto(Long id, Integer cantidad, Pedido pedido, Producto producto, Float precio) {
		super(); 
		this.id = id;
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.producto = producto;
		this.precio = precio;
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
