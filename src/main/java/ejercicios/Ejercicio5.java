package ejercicios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidad.Student;

/**
 * 5. Elimina el estudiante con studentId=1 utilizando session.get
 */
public class Ejercicio5 {

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
			int studentId = 1;
			
			session.beginTransaction();
			
			// recupera el estudiante 
			System.out.println("\nObteniendo estudiante con id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			// borra el estudiante
			 System.out.println("Borrando el estudiante: " + myStudent);
			 session.remove(myStudent);
						
			// commit de la transacción
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





