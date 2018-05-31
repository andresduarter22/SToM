package upb.webapp;

import upb.entity.Cliente;
import upb.entity.Distribuidor;

import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/distribuidor")
public class DistribuidorWebApp {
    private static DatabaseDistribuidor db = new DatabaseDistribuidor();

    @DELETE
    @Path("/borrar/{id_distribuidor}")
    public javax.ws.rs.core.Response borrarCliente(@PathParam("id_distribuidor") int id_distribuidor) {

        int res = db.delete(id_distribuidor);
        return  javax.ws.rs.core.Response
                .status(200)
                .entity(new Integer(res))
                .build();
    }

    @PUT
    @Path("/PUT/{id}")
    public javax.ws.rs.core.Response modificarUser(@PathParam("id") int id, Distribuidor distribuidor) {
        int res = db.modificar(id, distribuidor);
        return javax.ws.rs.core.Response
                .status(200)
                .entity(new Integer(res))
                .build();

    }



}
