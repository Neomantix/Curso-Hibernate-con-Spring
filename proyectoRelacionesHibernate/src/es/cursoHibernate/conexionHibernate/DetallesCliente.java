package es.cursoHibernate.conexionHibernate;

import javax.persistence.*;

/* Anotaciones:
 * 	@Entity: Transforma la clase en una entidad para que hibernate sepa que esta clase va a representar una tabla de la base de datos
 *  @Table: Se le indica a Hibernate la tabla a la que se va a mapear esta clase espeficicando el nombre de la tabla en la base de datos
*/
@Entity
@Table(name="detalles_cliente")
public class DetallesCliente {

	/*
	 * @Id: Se le indica a Hibernate que este atributo será la clave primaria de la tabla
	 * @GeneratedValue: Le indicamos a Hibernate la estrategia a seguir para la asignación del valor de la clave primaria. En este caso le decimos que es una campo autoincremental.
	 * 					De este modo Hibernate, una vez insertado un registro en la base de datos con save/persist, obtendrá el valor del id que la base de datos asignó 
	 * 					de forma automática a este campo, y le dará este mismo valor al atributo id del objeto en Java.
	 * @Column: Mapea cada propiedad de la clase con un campo de la tabla indicada en la anotación Table. Los nombres entre comillas deben 
	 * 			coincidir exactamente con los nombres de los campos de la base de datos 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="web")
	private String web;
	@Column(name="telefono")
	private String telefono;
	@Column(name="comentarios")
	private String comentarios;
	
	// Para que las clases sean bidireccionales, debemos mapear un objeto de clase Cliente con esta anotación
	@OneToOne(mappedBy = "detallesCliente", cascade = CascadeType.ALL)
	private Cliente cliente;
	
	
	
	/*
	 * Constructor vacio
	 */
	public DetallesCliente() {
		
	}
	
	/*
	 * Constructor con todos los parámetros excepto la Id, ya que al ser autoincremental, no necesitamos interactuar con ella en el constructor
	 */
	public DetallesCliente(String web, String telefono, String comentarios) {
		this.web = web;
		this.telefono = telefono;
		this.comentarios = comentarios;
	}

	/*
	 * Getters y Setters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "DetallesCliente [id=" + id + ", web=" + web + ", telefono=" + telefono + ", comentarios=" + comentarios
				+ "]";
	}

}
