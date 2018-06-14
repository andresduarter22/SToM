package upb.webapp;

import com.sun.jersey.spi.resource.Singleton;
import upb.entity.Juegos;
import upb.entity.Compra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
                              String estado, String categoria, int costo, String version,int distribuidor, String descripcion, String linkImagen) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Juegos juego= new Juegos(nombre, estado, categoria,costo,version, distribuidor,descripcion,linkImagen);
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
    public static Compra crearCompra(int id_juego,int id_cliente){
        List<Juegos> list = null;
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        list = manager.createQuery("SELECT s FROM " + Juegos.class.getName() + " s WHERE id_juego = \'"
                + id_juego + "\'", Juegos.class)
                .getResultList();
        Compra compra= new Compra(id_juego,id_cliente,list.get(0).getCosto());

        try {
            // empieza transaccon
            transaction = manager.getTransaction();
            transaction.begin();
            // crea objeto

            // guarda libro persistentemente
            manager.persist(compra);
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
        return compra;
    }

    public static void main(String[] args){
    //    createJuego("kaiba adventures 2","released","hentai",800, "1.23a45r", 1,
    //            "Est es la historia del grandiosisimo kaiba, maestro del hentai que busca conquistar a la princesa valeria.",
    //           "http://i0.kym-cdn.com/entries/icons/original/000/025/897/kaiba.jpg");
        crearCompra(4,5);
        ENTITY_MANAGER_FACTORY.close();
    }
}
