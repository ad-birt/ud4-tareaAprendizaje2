package ejercicios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidad.Student;

public class Ejercicio8 {

	/**
	 * 8. Realiza una consulta utilizando HQL para obtener el estudiante cuyo apellido es xxxx. Pasa el apellido utilizando .setParameter
	 */
	public static void main(String[] args) {

		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Student.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
		
		Session session = sessionFactory.openSession();
		try {			
			
			session.beginTransaction();
						
			// query de students: lastName='Lopez' como parámetro
		    List<Student> theStudents = session.createSelectionQuery(
		            "from Student s where s.lastName = :lastName", Student.class)
		        .setParameter("lastName", "Lopez")
		        .getResultList();
			
			System.out.println("\n\nEstudiantes que tienen el apellido Lopez pasado por parámetro");
			displayStudents(theStudents);
			
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
