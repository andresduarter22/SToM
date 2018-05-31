package upb.webapp;


import com.sun.jersey.spi.resource.Singleton;
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
    public static void main(String[] args){
        delete(2);
        ENTITY_MANAGER_FACTORY.close();
    }

}
