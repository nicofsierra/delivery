package ar.com.delivery.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Producto {
	@Id
	private Long id;
	private String nombre;
	private Float precioUnitario;
	
	@OneToMany(mappedBy="producto",cascade = CascadeType.ALL)
	private List<PedidoProducto> pedidoProducto = new ArrayList<>();
	
	public Producto (){}

	public Producto(Long id, String nombre, List<PedidoProducto> pedidoProducto,Float precioUnitario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pedidoProducto = pedidoProducto;
		this.precioUnitario = precioUnitario;
	}

	public Float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PedidoProducto> getPedidoProducto() {
		return pedidoProducto;
	}

	public void setPedidoProducto(List<PedidoProducto> pedidoProducto) {
		this.pedidoProducto = pedidoProducto;
	}
	
}
