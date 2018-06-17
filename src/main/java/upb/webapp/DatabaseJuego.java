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
                              String estado, String categoria, int costo, String version,int distribuidor, String descripcion,
                                     String linkimagen) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Juegos juego= new Juegos(nombre, estado, categoria,costo,version, distribuidor,descripcion,linkimagen);
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
        list = manager.createQuery("SELECT s FROM " + Juegos.class.getName() + " s WHERE id_juego = \'" + id_juego + "\'", Juegos.class)
                .getResultList();
        Compra compra= new Compra(id_juego,id_cliente,list.get(0).getCosto());

        try {
            // empieza transaccon
            transaction = manager.getTransaction();
            transaction.begin();
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
    public static List<Juegos> getLista(String str) {
        List<Juegos> list = null;
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        //Request client to database
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            transaction.begin();
            // Get client list
            list = manager.createQuery("SELECT j FROM " + Juegos.class.getName() + " j WHERE nombre LIKE \'%"
                    + str + "%\'", Juegos.class).getResultList();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return list;

    }

    public  static String devolverJuego(int id_compra){
        // Create an EntityManager
        System.out.println("Devolver juego: " + id_compra);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean flag = true;
        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Compra compraborr = manager.find(Compra.class, id_compra);
            if (compraborr != null) {
                manager.remove(compraborr);
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
                return "exito";
            }else{
                return "failure";
            }
        }

    }

    public static void main(String[] args){
    //    createJuego("kaiba adventures 2","released","hentai",800, "1.23a45r", 1,
    //            "Est es la historia del grandiosisimo kaiba, maestro del hentai que busca conquistar a la princesa valeria.",
    //           "http://i0.kym-cdn.com/entries/icons/original/000/025/897/kaiba.jpg");
    //    createJuego("kaiba adventures 2","released","hentai",800, "1.23a45r", 1,
     //           "Est es la historia del grandiosisimo kaiba, maestro del hentai que busca conquistar a la princesa valeria.",
      //         "http://i0.kym-cdn.com/entries/icons/original/000/025/897/kaiba.jpg");
      //  crearCompra(1,1);
        //        createJuego("kaiba adventures","released","hentai",800, "1.23a45r", 1);
//        DatabaseJuego db = new DatabaseJuego();
//        List<Juegos> a = getLista("craft");
//        createJuego("call of duty", "Released", "fps", 1000, "12s", 1);
//        System.out.println(a.get(0).getNombre());
//        System.out.println(a.get(1).getNombre());
        devolverJuego(1);
  ENTITY_MANAGER_FACTORY.close();
  }

    public Juegos getJuego(int str) {
        List<Juegos> list = null;
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        //Request client to database
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            transaction.begin();
            // Get client list
            list = manager.createQuery("SELECT j FROM " + Juegos.class.getName() + " j WHERE id_juego = \'"
                    + str + "\'", Juegos.class).getResultList();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return list.get(0);
    }
}
