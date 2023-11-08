package ejercicios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidad.Student;


public class Ejercicio4 {

	/**
	 * 4. Actualiza el email a todos los estudiantes utilizando session.createQuery
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
			
			// Actualiza el email a todos los estudiantes
			System.out.println("Actualiza el email a todos los estudiantes");
			
			session.createMutationQuery("update Student set email = :email")
		    .setParameter("email", "birt@gmail.com")
		    .executeUpdate();
						
			session.getTransaction().commit();

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

}


