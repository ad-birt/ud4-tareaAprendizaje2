package ejercicios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidad.Student;


public class Ud4TareaAprendizaje2Ejercicio3 {

	/**
	 * 3. Actualiza el nombre del primer estudiante introducido utilizando session.get
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
			int studentId = 1;
			
			session.beginTransaction();
			
			// obtiene el estudiante a partir de la id: primary key
			System.out.println("\nObteniendo estudiante con id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Actualizando estudiante");
			myStudent.setFirstName("Pako");
			
			// hace commit de la transaccion
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


