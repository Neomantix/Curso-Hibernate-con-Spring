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
	
	/*
	 * Como se pretende que la relación sea de muchos a uno (varios pedidos pueden tener un solo cliente) utilizamos la annotation @ManyToOne
	 * Usaremos todos los tipos de cascade excepto el REMOVE para evitar el borrado de un cliente cuando se borre un pedido
	 * Con @JoinColumn indicamos a Hibernate que esta columna será la clave foránea que relaciona ambas tablas
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	public Pedido () {}

	public Pedido (Date fecha) {
		this.fecha = fecha;
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
