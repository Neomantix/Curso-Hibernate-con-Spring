package es.cursoHibernate.conexionHibernate;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name = "forma_pago")
	private String formaPago;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
	private Cliente cliente;
	
	public Pedido () {}

	public Pedido (Date fecha, String formaPago, Cliente cliente ) {
		this.fecha = fecha;
		this.formaPago = formaPago;
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", formaPago=" + formaPago + "]";
	}
	
}
