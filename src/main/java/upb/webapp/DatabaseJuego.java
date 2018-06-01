package upb.webapp;

import com.sun.jersey.spi.resource.Singleton;
import upb.entity.Juegos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DatabaseJuego {
    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("stom");

    public DatabaseJuego() {
    }
    public void closeDataBase() {
        ENTITY_MANAGER_FACTORY.close();
    }
    public static Juegos createJuego(String nombre,
                              String estado, String categoria, int costo, String version,int distribuidor) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Juegos juego= new Juegos(nombre, estado, categoria,costo,version, distribuidor);
        try {
            // empieza transaccon
            transaction = manager.getTransaction();
            transaction.begin();
            // crea objeto

            // guarda libro persistentemente
            manager.persist(juego);
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
        return juego;
    }
    public static void main(String[]args){
        createJuego("kaiba adventures","released","hentai",800, "1.23a45r", 1);
        ENTITY_MANAGER_FACTORY.close();
    }
}
