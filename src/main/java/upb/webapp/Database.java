package upb.webapp;

import upb.entity.Distribuidor;
import upb.entity.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Database {
	// Create an EntityManagerFactory when you start the application.
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Stom");



    public Database(){}

    public void closeDataBase(){ENTITY_MANAGER_FACTORY.close();}
	/**
	 * Create a new Distribuidor.
	 * @param nombre
	 * @param direccion
	 * @param telefono
	 * @param correo
	 */
	public void createDistribuidor(int id, String nombre, String direccion,String telefono,String correo) {
		// Create an EntityManager
		//TODO modificar
        System.out.println("Creando distrib : " + nombre+ " id : "+id + direccion + telefono + correo);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// empieza transaccon
			transaction = manager.getTransaction();
			transaction.begin();
			// crea objeto
			Distribuidor dis= new Distribuidor();
			dis.setId(id);
			dis.setCorreo(correo);
			dis.setDireccion(direccion);
			dis.setNombre(nombre);
			dis.setTelefono(telefono);
			// guarda libro persistentemente
			manager.persist(dis);
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

	/**
	 * leer Libros.
	 * 
	 * @return a List of Libros
	 */
	public List<Libro> readAll() {

		List<Libro> libros = null;

		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			transaction.begin();
			// Get Libros
			libros = manager.createQuery("SELECT s FROM libro s", Libro.class).getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return libros;
	}

	/**
	 *Eliminar Libro
	 * @param id
	 */
	public void delete(int id) {
		// Create an EntityManager
        System.out.println("eliminar Libro: "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
            Libro stu = manager.find(Libro.class, id);
            manager.remove(stu);
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

	/**
	 * actualizando  Libro.
	 * 
	 * @param id
	 * @param name
	 * @param age
	 */
	public void update(int id, String name, String age) {
		// Create an EntityManager
        System.out.println("Actualizando Libro : "+name+ " con id : "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
            Libro stu = manager.find(Libro.class, id);
			stu.setName(name);
			stu.setAutor(age);
            manager.persist(stu);

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
       Database a = new Database();
        // Create two Students
		//a.createDistribuidor(1,"bethesda", "Silicon Valley", "70577763", "papas@hotmail.com");
		a.createDistribuidor(1,"bethesda", "Silicon Valley 2", "666666", "papas@hotmail.com");

		/*        a.create(2, "Libro2", "test1"); // Bob will get an id 2
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
        }
*/
        // NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
        ENTITY_MANAGER_FACTORY.close();
    }
}
