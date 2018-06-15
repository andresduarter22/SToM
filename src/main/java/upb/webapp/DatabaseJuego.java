package upb.webapp;

import com.sun.jersey.spi.resource.Singleton;
import upb.entity.Compra;
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

    public static Compra crearCompra(int id_juego, int id_cliente, int precio){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Compra compra= new Compra(id_juego,id_cliente,precio);
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

    public static void main(String[] args0) {
        //    createJuego("kaiba adventures 2","released","hentai",800, "1.23a45r", 1,
        //            "Est es la historia del grandiosisimo kaiba, maestro del hentai que busca conquistar a la princesa valeria.",
        //           "http://i0.kym-cdn.com/entries/icons/original/000/025/897/kaiba.jpg");
        crearCompra(4, 1, 50);
    }
    public static String devolverJuego(int id){
        System.out.println("devlover comprar: " + id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        String estado= "fallido";
        boolean flag = true;
        try {
            // empieza transaccon
            transaction = manager.getTransaction();
            transaction.begin();
            // crea objeto

            // guarda libro persistentemente
            Compra compraABorrar= manager.find(Compra.class,id);
            // envia transaccion

            if (compraABorrar != null) {
                manager.remove(compraABorrar);
                transaction.commit();
                estado="exito";
            }

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();

        } finally {
            manager.close();
        }
        return estado;
    }

}
