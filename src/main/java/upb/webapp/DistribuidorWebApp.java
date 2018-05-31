package upb.webapp;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/distribuidor")
public class DistribuidorWebApp {
    private static DatabaseStom db = new DatabaseStom();





    @DELETE
    @Path("/borrar/{id_distribuidor}")
    public javax.ws.rs.core.Response borrarCliente(@PathParam("id_distribuidor") int id_distribuidor) {

        db.delete(id_distribuidor);
        return  javax.ws.rs.core.Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();

    }


}
