package upb.webapp;

import upb.entity.Cliente;
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
	 */
	public void create( String nombre, String correo, String password ) {
		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		try {
			// empieza transaccon
			transaction = manager.getTransaction();
			transaction.begin();
			// crea objeto
			Cliente cliente= new Cliente(nombre,correo,password);

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

	/**
	 *Eliminar Libro
	 * @param id
	 */
	public void delete(int id) {
		// Create an EntityManager
		System.out.println("eliminar Cliente: "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();

			Cliente clienteborr = manager.find(Cliente.class, id);
			if(clienteborr != null) {
				manager.remove(clienteborr);
				transaction.commit();
			}
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
//		a.create("pepe","pepe@pepe.com","abc");
        //a.modificar(2,"Asdasd","asdas@sdv.vsdf","asdasd");
		//a.delete(19);
//		Cliente c = auth("pepe@pepe.com","abc");
//		System.out.println(c.getId_cliente());
//		Cliente d = auth("juan@pepfeh.com","abcd");
//		System.out.println(d.getId_cliente());
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
	public static Cliente auth(String email, String password) {
		List<Cliente> list = null;
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			transaction.begin();
			// Get Libros
			list = manager.createQuery("SELECT s FROM " + Cliente.class.getName() + " s WHERE correo = \'"
					+ email + "\'", Cliente.class)
					.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		Cliente cliente = null;
		try{
			cliente = list.get(0);
		}catch (Exception e){
			return new Cliente("error","error","error");
		}
		if (cliente.getPassword().equals(password)) {
			return cliente;
		} else {
			return new Cliente("error","error","error");
		}
	}

	public void modificar(int id, Cliente cliente) {
		// Create an EntityManager

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Cliente stu = manager.find(Cliente.class, id);
			stu.setNombre(cliente.getNombre());
			stu.setCorreo(cliente.getCorreo());
			stu.setPassword(cliente.getPassword());
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
}
