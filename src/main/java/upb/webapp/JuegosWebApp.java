package upb.webapp;

import upb.entity.Juegos;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/juegos")
public class JuegosWebApp {

    private static DatabaseJuego db = new DatabaseJuego();
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createTrackInJSON(Juegos juego) {
        Juegos res = db.createJuego(juego.getNombre(),juego.getEstado(),juego.getCategoria()
                ,juego.getCosto(),juego.getVersion(),juego.getId_distribuidor());
        return javax.ws.rs.core.Response
                .status(200)
                .entity(res)
                .build();
    }
}
