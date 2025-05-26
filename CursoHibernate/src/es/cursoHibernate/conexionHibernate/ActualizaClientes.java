package es.cursoHibernate.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ActualizaClientes {

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
			
			int ClienteId = 6;
			
			/*
			 * Comenzamos una transacción
			 */
			miSesion.beginTransaction();
			
			/*
			 * Creamos una instancia del objeto Cliente del cual queremos modificar el nombre, en este caso el registro con Id=6
			 * Obtenemos este registro usando el método .get() de miSesion pasándole el nombre de la clase y el valor de la clave primaria a rescatar
			 */
			Cliente miCliente = miSesion.get(Cliente.class, ClienteId);
			
			/*
			 * Modificamos el nombre de la instancia obtenida con su setter conrrespondiente
			 * Modificar la instancia hará que Hibernate de forma automática actualice también la tabla de la base de datos con el nuevo valor
			 */
			miCliente.setNombre("Pedro");
			
			/*
			 * También podemos realizar el proceso utilizando el lenguaje HQL
			 * En este caso modificaremos todos los apellidos que empiecen con D para que todos se apelliden Dominguez
			 * Para ello usaremos el método .createQuery() donde definiremos la sentencia HQL, seguida del médodo .executeUpdate() para lanzar la query contra la base de datos
			 */
			miSesion.createQuery("update Cliente set Apellidos='Dominguez' where Apellidos like 'D%'").executeUpdate();
			
			/*
			 * Para borrar un registro podemos usar delete
			 * La estructura de la query es muy similar a la del update. OJO - USAR SIEMPRE LA CLAUSULA WHERE PARA EVITAR EL BORRADO INDESEADO DE REGISTROS
			 * Para ello usaremos el método .createQuery() donde definiremos la sentencia HQL, seguida del médodo .executeUpdate() para lanzar la query contra la base de datos
			 */
			miSesion.createQuery("delete Cliente where Direccion='Goya'").executeUpdate();
			
			/*
			 * Al tratarse de una transacción es necesario confirmar los cambios mediante el método .commit(), pero esto ha de aplicarse a la transacción, por lo tanto invocamos .getTransaction sobre nuestra sesión			
			 */
			miSesion.getTransaction().commit();
			
			System.out.println("Registros actualizados correctamente en base de datos");
		
			System.out.println("Terminado!!");
			
			
		} catch (Exception e) {
			
			System.out.println("Ha habido un problema durante la transacción: ");
			e.printStackTrace();
			
		} 
		
	}

}
