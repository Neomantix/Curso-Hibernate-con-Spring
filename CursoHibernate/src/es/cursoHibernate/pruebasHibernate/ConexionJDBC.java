package es.cursoHibernate.pruebasHibernate;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionJDBC {

	public static void main(String[] args) {

		final String JDBC_URL = "jdbc:mysql://localhost:3307/curso_hibernate?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "password";;
		
		try {
			
			System.out.println("Intentando conectar con la base de datos");
			
			Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			
			System.out.println("La conexión se ha realizado con éxito");
			
		} catch (Exception e) {
			
			System.out.println("Ha habido un problema durante la conexión a la base de datos: ");
			
			e.printStackTrace();
			
		}
		
	}

}
