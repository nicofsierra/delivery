package ar.com.delivery.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Cliente {
	@Id
	private Long telefono;
	private String nombre;
	private String calle;
	private String localidad;
	

	@OneToMany(mappedBy="cliente" , cascade = CascadeType.ALL)
	private List<Pedido> pedido = new ArrayList<>();
	
	public Cliente(){}

	

	public Cliente(Long telefono, String nombre, String calle, String localidad, List<Pedido> pedido) {
		super();
		this.telefono = telefono;
		this.nombre = nombre;
		this.calle = calle;
		this.localidad = localidad;
		this.pedido = pedido;
	}
	
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

}
