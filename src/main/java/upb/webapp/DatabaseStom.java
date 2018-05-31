package upb.webapp;

import com.sun.jersey.spi.resource.Singleton;
import upb.entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class DatabaseStom {

    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("stom");

    private static ConcurrentHashMap<Integer, Queue<Long>> failedLoginAttempts = new ConcurrentHashMap<>();

    private static final int ATTEMPTS_TO_LOCKDOWN = 5;
    private static final int TIME_FRAME = 3000;

    public DatabaseStom() {
    }

    public void closeDataBase() {
        ENTITY_MANAGER_FACTORY.close();
    }

    /**
     * Create a new Cliente.
     *
     * @param nombre
     * @param correo
     * @param password
     */
    public Cliente create(String nombre, String correo, String password) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        final String PASSWORD_NOT_ACCEPTED = "Password must be 8 or more characters, cointain at least " +
                "one upper case letter, one number and one lower case letter.";
        boolean flag = true;
        if (passwordIsSecure(nombre, correo, password)) {
            try {
                // empieza transaccon
                transaction = manager.getTransaction();
                transaction.begin();
                // crea objeto
                Cliente cliente = new Cliente(nombre, correo, generateHash(nombre, password));

                // guarda libro persistentemente
                manager.persist(cliente);
                // envia transaccion
                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                ex.printStackTrace();
                flag = false;
            } finally {
                manager.close();
                if (flag){
                    return auth(correo,password);
                }else{
                    return new Cliente("No se pudo crear el cliente","error","error");
                }
            }
        } else {
            return new Cliente(PASSWORD_NOT_ACCEPTED,"error","error");
        }
    }


    /**
     * Eliminar Libro
     *
     * @param id
     */
    public int delete(int id) {
        // Create an EntityManager
        System.out.println("eliminar Cliente: " + id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean flag = true;
        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Cliente clienteborr = manager.find(Cliente.class, id);
            if (clienteborr != null) {
                manager.remove(clienteborr);
                transaction.commit();
            }
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            flag = false;
        } finally {
            manager.close();
            if(flag){
                return id;
            }else{
                return 0;
            }
        }
    }


    public static void main(String[] args) {
//      DatabaseStom a = new DatabaseStom();
//      Create two Students
//      a.modificar(2, "Asdasd", "asdas@sdv.vsdf", "asdasd");
//      a.delete(19);
//      for (int i = 0; i < 7; i++) {
//          Cliente c = auth("pepe@pepe.com", "abc");
//          System.out.println(c.getNombre());
//       }
//		Cliente d = auth("pepe@pepe.com","HuevosconAceite1");
//		System.out.println(d.getNombre());
        // NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
        ENTITY_MANAGER_FACTORY.close();
    }

    public static Cliente auth(String email, String password) {
        List<Cliente> list = null;
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        //Request client to database
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            transaction.begin();
            // Get client list
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
        //verify if the list is empty, in that case, the client does not exist
        //but application wont specify whether the email or the password are incorrect
        if(list != null && (list.size() > 0)){
            Cliente cliente = list.get(0);
            //verify failed attempts
            int idCliente = cliente.getId_cliente();
            Queue<Long> attempts = failedLoginAttempts.get(idCliente);
            if (attempts != null) {
                synchronized(attempts) {
                    if ((attempts.size() == ATTEMPTS_TO_LOCKDOWN)
                            &&  TIME_FRAME > (System.currentTimeMillis() -
                            attempts.peek())) {
                        //this resets the timer everytime a new login is requested for a locked user
//                        attempts.offer(System.currentTimeMillis());
//                        if (attempts.size() > ATTEMPTS_TO_LOCKDOWN) {
//                            attempts.poll();
//                        }
                        return new Cliente("Cuenta bloqueada", "error", "error");
                    }
                }
            }
            //verify password
            if (cliente.getPassword().equals(generateHash(cliente.getNombre(), password))) {
                failedLoginAttempts.remove(idCliente);
                return cliente;
            } else {
                //register failed attempt
                if (attempts == null) {
                    failedLoginAttempts.putIfAbsent(idCliente, new LinkedList<Long>());
                    attempts = failedLoginAttempts.get(idCliente);
                }
                synchronized (attempts) {
                    attempts.offer(System.currentTimeMillis());
                    if (attempts.size() > ATTEMPTS_TO_LOCKDOWN) {
                        attempts.poll();
                    }
                }
                return new Cliente("password o correo invalidos", "error", "error");
            }
        }else{
            return new Cliente("password o correo invalidos", "error", "error");
        }
    }

    public int modificar(int id, Cliente cliente) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean flag = true;
        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Cliente stu = manager.find(Cliente.class, id);
            stu.setNombre(cliente.getNombre());
            stu.setCorreo(cliente.getCorreo());
            stu.setPassword(generateHash(cliente.getNombre(),cliente.getPassword()));
            manager.persist(stu);

            // envia transaccion
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            flag = false;
        } finally {
            manager.close();
            if(flag){
                return id;
            }else{
                return 0;
            }
        }
    }
    private static String generateHash(String name, String password){
        String hash = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(name.getBytes(StandardCharsets.UTF_8));
            StringBuilder str = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                str.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
            }
            hash = str.toString();
        }catch (Exception e){
            System.err.println("aaaaaaaaaaaaaah!");
        }
        return hash;
    }
    private static boolean passwordIsSecure(String nombre, String correo, String password){
        boolean res = (null != password)
                && (password.matches(".*[A-Z]+.*")
                && password.matches(".*[a-z]+.*")
                && password.matches(".*[0-9]+.*")
                && (password.length() >= 8)
                && !password.matches(".*" + nombre + "+.*")
                && !password.matches(".*" + correo + "+.*"));
        return res;
    }
}
