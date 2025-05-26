package es.cursoHibernate.conexionHibernate;

import javax.persistence.*;

/* Anotaciones:
 * 	@Entity: Transforma la clase en una entidad para que hibernate sepa que esta clase va a representar una tabla de la base de datos
 *  @Table: Se le indica a Hibernate la tabla a la que se va a mapear esta clase espeficicando el nombre de la tabla en la base de datos
*/
@Entity
@Table(name="cliente")
public class Cliente {

	/*
	 * @Id: Se le indica a Hibernate que este atributo será la clave primaria de la tabla
	 * @GeneratedValu: Le indicamos a Hibernate la estrategia a seguir para la asignación del valor de la clave primaria. En este caso le decimos que es una campo autoincremental.
	 * 					De este modo Hibernate, una vez insertado un registro en la base de datos con save/persist, obtendrá el valor del id que la base de datos asignó 
	 * 					de forma automática a este campo, y le dará este mismo valor al atributo id del objeto en Java.
	 * @Column: Mapea cada propiedad de la clase con un campo de la tabla indicada en la anotación Table. Los nombres entre comillas deben 
	 * 			coincidir exactamente con los nombres de los campos de la base de datos 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Column(name="Nombre")
	private String nombre;
	@Column(name="Apellidos")
	private String apellidos;
	@Column(name="Direccion")
	private String direccion;
	
	
	
	/*
	 * Constructor vacio
	 */
	public Cliente() {
		
	}
	
	/*
	 * Constructor con todos los parámetros excepto la Id, ya que al ser autoincremental, no necesitamos interactuar con ella en el constructor
	 */
	public Cliente(String nombre, String apellidos, String direccion) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/*
	 * Sobreescritura del método toString
	 */
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ "]";
	}
	
	
	
}
