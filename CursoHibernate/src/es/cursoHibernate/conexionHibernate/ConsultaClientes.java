package es.cursoHibernate.conexionHibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConsultaClientes {

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
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();

		/*
		 * A partir del objeto SessionFactory podemos crear sesiones con el método .openSession()
		 * Como se debe cerrar la sesión una vez se termina de usar, usaremos un try-catch with resources para facilitar el trabajo en lugar de hacer un .close() explicito en el finally
		 */
		try (Session miSesion = miFactory.openSession()){

			// Comenzamos la transacción
			miSesion.beginTransaction();
			
			/* 
			 * Consulta de todos los clientes almacenados en la base de datos de tipo Clientes.
			 * 	Declaramos una lista de Clientes
			 * 	Con el método createQuery() podemos crear solicitudes en lenguaje HQL
			 * 	En este caso solicitamos todos los registros de clase Clientes con el método getResultList()
			 */
			List<Cliente> losClientes = miSesion.createQuery("from Cliente").getResultList();
			
			/*
			 * Mostrar los resultados con un forEach
			 */
			System.out.println("--- CONSULTA DE LA TABLA ENTERA ---");
			recorreClientesConsultados(losClientes);
			
			/*
			 * Consulta para sacar los clientes que se apelliden Sanchez
			 * Debemos dar un alias al resultado del from Cliente (en este caso cl)
			 * Una vez tenemos el alias, lo usaremos para indicar cual es el atributo de la clase (no confundir con el campo de la base de datos) que queremos obtener
			 * 	apellidos: Es el atributo de la clase
			 * 	Apellidos: Es el campo de la tabla de la base de datos
			 */
			List<Cliente> losSanchez = miSesion.createQuery("from Cliente cl where cl.apellidos='Sanchez'").getResultList();
			
			System.out.println("\n\n--- CONSULTA DE LOS QUE SE APELLIDAN Sanchez ---");
			recorreClientesConsultados(losSanchez);
			
			/*
			 * Consulta para sacar los que viven en Gran Via o se apellidan Delgado
			 */
			List<Cliente> losDelgadoOrLosGranVia = miSesion.createQuery("from Cliente cl where cl.apellidos='Delgado' or cl.direccion='Gran Via'").getResultList();
			
			System.out.println("\n\n--- CONSULTA DE LOS QUE SE APELLIDAN Delgado O VIVEN EN Gran Via ---");
			recorreClientesConsultados(losDelgadoOrLosGranVia);
			
			
			miSesion.getTransaction().commit();

			
		} catch (Exception e) {
			
		}
		
	}

	private static void recorreClientesConsultados(List<Cliente> losClientes) {
		for (Cliente unCliente : losClientes) {
			
			System.out.println(unCliente);
			
		}
	}

}
