package es.cursoHibernate.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InsertaCliente {

	public static void main(String[] args) {

		/*
		 * Explicación de esta línea:
		 * 
		 * new Configuration()	Crea un nuevo objeto de configuración de Hibernate. 
		 * 						Este objeto se encarga de gestionar toda la configuración necesaria para conectar y trabajar con la base de datos
		 * 
		 * .configure("hibernate.cfg.xml")	Indica a Hibernate que debe cargar la configuración desde el archivo hibernate.cfg.xml. 
		 * 									Este archivo contiene detalles como el driver JDBC, la URL de la base de datos, usuario, contraseña, dialecto, etc.
    	 *									Si no se especifica el nombre, busca por defecto hibernate.cfg.xml en el classpath.
		 *
		 * .addAnnotatedClass(Clientes.class)	Registra la clase Clientes como una entidad que Hibernate debe gestionar. 
		 * 										Esto le dice a Hibernate que busque las anotaciones JPA (@Entity, @Table, etc.) en esa clase para mapearla a la tabla correspondiente en la base de datos
		 *
	 	 * .buildSessionFactory()	Con toda la configuración y las entidades registradas, crea el objeto SessionFactory. 
	 	 * 							El SessionFactory es un objeto pesado y seguro para múltiples hilos, encargado de crear sesiones (Session) para interactuar con la base de datos
		 *
		 * Normalmente, se crea una sola vez durante la vida de la aplicación y se reutiliza.
		 */
		SessionFactory miFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Cliente.class)
										.addAnnotatedClass(DetallesCliente.class)
										.addAnnotatedClass(Pedido.class)
										.buildSessionFactory();

		/*
		 * A partir del objeto SessionFactory podemos crear sesiones con el método .openSession()
		 * Como se debe cerrar la sesión una vez se termina de usar, usaremos un try-catch with resources para facilitar el trabajo en lugar de hacer un .close() explicito en el finally
		 */
		try (Session miSesion = miFactory.openSession()){

			// Comenzamos la transacción
			miSesion.beginTransaction();

			// Instanciamos un objeto de clase Cliente que será insertado en la base de datos
			Cliente cliente1 = new Cliente("Ana", "Marín", "Gran Vía");
			
			// Instanciamos un objeto de la clase DetallesClientes
			DetallesCliente detallesCliente1 = new DetallesCliente("www.anamarin.com", "694126587", "Otro cliente");
			
			// Asociar los objetos
			cliente1.setDetallesCliente(detallesCliente1);
			
			// Guardamos el registro en la base de datos. Se guardará la información en las dos tablas relacionadas
			miSesion.persist(cliente1);
			
			// Confirmamos la transacción con .commit()
			miSesion.getTransaction().commit();

			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
