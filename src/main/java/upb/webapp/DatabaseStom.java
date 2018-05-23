package upb.webapp;

import upb.entity.Cliente;
import upb.entity.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DatabaseStom {

	// Create an EntityManagerFactory when you start the application.
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("stom");

	public DatabaseStom(){}

	public void closeDataBase(){ENTITY_MANAGER_FACTORY.close();}
	/**
	 * Create a new Cliente.
	 * @param nombre
	 * @param correo
	 * @param password
	 * @param credito
	 */
	public void create(int id, String nombre, String correo, String password, double credito ) {
		// Create an EntityManager
		System.out.println("Creando Libro : " + nombre+ " id : "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		try {
			// empieza transaccon
			transaction = manager.getTransaction();
			transaction.begin();
			// crea objeto
			Cliente cliente= new Cliente(id,nombre,correo,password,credito);

			// guarda libro persistentemente
			manager.persist(cliente);
			// envia transaccion
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}


    public static void main(String[] args) {
        DatabaseStom a = new DatabaseStom();
        // Create two Students
		a.create(1,"Dini","dini.com ","patito",20.09);
      /*  a.create(1, "Libro1", "test"); // Alice will get an id 1
        a.create(2, "Libro2", "test1"); // Bob will get an id 2
        a.create(3, "Libro3", "test3"); // Charlie will get an id 3

        // Update the age of Bob using the id
        a.update(2, "Bob", "abc");

        // Delete the Alice from database
        a.delete(1);

        // Print all the Students
        List<Libro> libros = a.readAll();
        if (libros != null) {
            for (Libro stu : libros) {
                System.out.println(stu);
            }
        }*/

        // NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
        ENTITY_MANAGER_FACTORY.close();
    }
}
