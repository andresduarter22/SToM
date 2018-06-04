package upb.webapp;


import com.sun.jersey.spi.resource.Singleton;
import upb.entity.Cliente;
import upb.entity.Distribuidor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Singleton
public class DatabaseDistribuidor {

    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("stom");

    public DatabaseDistribuidor() {
    }
    public void closeDataBase() {
        ENTITY_MANAGER_FACTORY.close();
    }

    public static int delete(int id) {
        // Create an EntityManager
        System.out.println("eliminar Distribuidor: " + id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean flag = true;
        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Distribuidor distribuidorborr = manager.find(Distribuidor.class, id);
            if (distribuidorborr != null) {
                manager.remove(distribuidorborr);
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

    public int modificar(int id, Distribuidor distribuidor) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean flag = true;
            try {
                transaction = manager.getTransaction();
                transaction.begin();
                Distribuidor stu = manager.find(Distribuidor.class, id);
                stu.setNombre(distribuidor.getNombre());
                stu.setCorreo(distribuidor.getCorreo());
                stu.setTelefono(distribuidor.getTelefono());
                stu.setDireccion(distribuidor.getDireccion());
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

    public static Distribuidor create(String nombre, String correo, String telefono, String direccion){
        // Create an EntityManager
        System.out.println("Crear Distribuidor: " );
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean flag = true;
        Distribuidor distribuidornuevo=null;
        if(Seguridad.infoCorrect(correo,telefono)){
        try {
            transaction = manager.getTransaction();
            transaction.begin();

            distribuidornuevo = new Distribuidor(correo,telefono,direccion,nombre);
            if (distribuidornuevo != null) {
                manager.persist(distribuidornuevo);
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


            return distribuidornuevo;
        }
        }else{
            return new Distribuidor("error","error","error", "Correo o telefono invalidos");
        }

    }

    public static void main(String[] args){
        delete(2);
        ENTITY_MANAGER_FACTORY.close();
    }

}
