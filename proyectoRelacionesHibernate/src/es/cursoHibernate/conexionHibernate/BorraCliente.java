package es.cursoHibernate.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BorraCliente {
	
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
		
		

			/*
			 * A partir del objeto SessionFactory podemos crear sesiones con el método .openSession()
			 * Como se debe cerrar la sesión una vez se termina de usar, usaremos un try-catch with resources para facilitar el trabajo en lugar de hacer un .close() explicito en el finally
			 */
			try (SessionFactory miFactory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Cliente.class)
					.addAnnotatedClass(DetallesCliente.class)
					.buildSessionFactory();
				 Session miSesion = miFactory.openSession()){
	
				// Comenzamos la transacción
				miSesion.beginTransaction();
				final int ID_A_ELIMINAR = 4; 
				
				// Instanciamos un objeto de clase Cliente recuperando los datos del cliente que queremos borrar de la base de datos por medio de su id
				Cliente cliente1 = miSesion.get(Cliente.class, ID_A_ELIMINAR);
	
				// Si el cliente con esa id no existe, en cliente1 se almacenará null
				if (cliente1 != null) {
					
					// Eliminamos el registro en la base de datos. Se borrará la información en cascada de las dos tablas relacionadas
					miSesion.delete(cliente1);
					
					System.out.println("Registro eliminado correctamente: " + cliente1);
					
				} else {
					
					System.out.println("No se ha podido encontrar al cliente con id: " + ID_A_ELIMINAR);
					
				}
				
				// Confirmamos la transacción con .commit()
				miSesion.getTransaction().commit();
	
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
		
	}
	
}
