package upb.webapp;

import upb.entity.Cliente;
import upb.entity.Distribuidor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/distribuidor")
public class DistribuidorWebApp {
    private static DatabaseDistribuidor db = new DatabaseDistribuidor();

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createTrackInJSON(Distribuidor Distribuidor) {
        Distribuidor res = db.create(Distribuidor.getNombre(), Distribuidor.getCorreo(), Distribuidor.getTelefono(), Distribuidor.getDireccion());
        if(res.getNombre().equals("Correo o telefono invalidos")){

            return javax.ws.rs.core.Response
                    .status(200)
                    .entity(new Integer(0))
                    .build();

        }else{

            return javax.ws.rs.core.Response
                    .status(200)
                    .entity(new Integer(1))
                    .build();
        }

    }

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
