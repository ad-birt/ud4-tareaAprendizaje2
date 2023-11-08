package ejercicios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidad.Student;


public class Ejercicio2 {

	/**
	 * 2. Guarda un nuevo objeto Student y posteriormente recupéralo utilizando session.get
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
			// crea un nuevo objeto Student
			System.out.println("Creando nuevo objeto Student...");
			Student tempStudent = new Student("Ane", "Uriarte", "ane@birt.eus");
			
			// comienza la transacción
			session.beginTransaction();
			
			// guarda el estudiante
			System.out.println("Guardando el estudiante...");
			System.out.println(tempStudent);
			session.persist(tempStudent);
			
			// hace commit de la transacción
			session.getTransaction().commit();
			
			// busca el id (primary key del estudiante introducido)
			System.out.println("Estudiante guardado con id: " + tempStudent.getId());
			
			// comienza una nueva transaccion
			session.beginTransaction();
			
			// obtiene el estudiante en base a la id: primary key
			System.out.println("\nObteniendo estudiante con id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get completado: " + myStudent);
			
			// commit de la  transaccion
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





