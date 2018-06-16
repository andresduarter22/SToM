package upb.webapp;


import upb.entity.Juegos;
import upb.entity.Compra;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/juegos")
public class JuegosWebApp {

    private static DatabaseJuego db = new DatabaseJuego();
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createTrackInJSON(Juegos juego) {
        Juegos res = db.createJuego(juego.getNombre(),juego.getEstado(),juego.getCategoria()
                ,juego.getCosto(),juego.getVersion(),juego.getId_distribuidor(),juego.getDescripcion(),juego.getLinkImagen());
        return javax.ws.rs.core.Response
                .status(200)
                .entity(res)
                .build();
    }
    @POST
    @Path("/postCompra")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createTrackInJSONCompra(Compra compra) {
        Compra res = db.crearCompra(compra.getId_juego(),compra.getId_cliente());
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

    @GET
    @Path("/buscar/{str}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response busqueda(@PathParam("str") String str){
        List<Juegos> res= db.getLista(str);
        return javax.ws.rs.core.Response
                .status(200)
                .entity(res)
                .build();
    }
//    public static void main(String[] args){
//        createTrackInJSON(new Juegos("asdas","asdasdasd","asdasdasd",12,"12a",1,"asdasdas","asdasdas"));
//    }
}
