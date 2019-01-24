package ar.com.delivery.modelo;

import java.util.Date;

public class PedidoClienteDTO {
	
	private Long idPedido;
	private Long telefono;
	private String nombre;
	private String domicilio;
	private String localidad;
	private Date fecha;
	private Float importe;

	public PedidoClienteDTO(){}

	public PedidoClienteDTO(Long idPedido, Long telefono, String nombre, String domicilio, String localidad, Date fecha, Float importe) {
		super();
		this.idPedido = idPedido;
		this.telefono = telefono;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.localidad = localidad;
		this.fecha = fecha;
		this.importe = importe;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
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
	
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Float getImporte() {
		return importe;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}
}
