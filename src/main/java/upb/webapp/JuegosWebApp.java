package upb.webapp;


import upb.entity.Juegos;
import upb.entity.Compra;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/juegos")
public class JuegosWebApp {

    private static DatabaseJuego db = new DatabaseJuego();


    @POST
    @Path("/postCompra")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createTrackInJSONCompra(Compra compra) {
        Compra res = db.crearCompra(compra.getId_juego(),compra.getId_cliente(),compra.getPrecio());
        return javax.ws.rs.core.Response
                .status(200)
                .entity(res)
                .build();
    }

    /**
     * metodo post para devolver juego
     */

    @POST
    @Path("/post/devolucion/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //si no funciona usar javax.ws
    public String deleteJuegofromUser(@PathParam("id") int id) {
        String res = db.devolverJuego(id);
        return res;
    }
}

