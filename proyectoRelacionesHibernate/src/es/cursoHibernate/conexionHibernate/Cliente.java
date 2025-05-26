package es.cursoHibernate.conexionHibernate;

import javax.persistence.*;

/* Anotaciones:
 *  @Entity: Indica que la clase es una entidad y será mapeada a una tabla de la base de datos por Hibernate.
 *  @Table: Especifica el nombre de la tabla en la base de datos a la que se mapeará la entidad.
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    /*
     * @Id: Marca el atributo como la clave primaria de la entidad.
     * @GeneratedValue: Define la estrategia de generación automática del valor de la clave primaria. 
     *                  En este caso, IDENTITY indica que la base de datos genera un valor autoincremental.
     *                  Hibernate recupera ese valor tras el insert y lo asigna al atributo id del objeto Java.
     * @Column: Mapea el atributo con una columna de la tabla. El nombre debe coincidir con el de la base de datos.
     * @OneToOne: Define una relación uno a uno con otra entidad.
     * @JoinColumn: Especifica la columna de unión para la relación; en este caso, el campo 'id'.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "direccion")
    private String direccion;

    /*
     * Relación uno a uno con la entidad DetallesCliente.
     * CascadeType.ALL indica que las operaciones realizadas sobre Cliente se propagarán a DetallesCliente.
     * La columna de unión es 'id', que actúa como clave foránea.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private DetallesCliente detallesCliente;

	
	/*
	 * Constructor vacio
	 */
	public Cliente() {
		
	}
	
	/*
	 * Constructor con todos los parámetros excepto la Id, ya que al ser autoincremental, no necesitamos interactuar con ella en el constructor
	 */
	public Cliente(String nombre, String apellido, String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public DetallesCliente getDetallesCliente() {
		return detallesCliente;
	}

	public void setDetallesCliente(DetallesCliente detallesCliente) {
		this.detallesCliente = detallesCliente;
	}

	/*
	 * Sobreescritura del método toString
	 */
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ "]" + detallesCliente;
	}
	
	
	
}
