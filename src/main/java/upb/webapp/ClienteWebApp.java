package upb.webapp;


import com.sun.jersey.spi.resource.Singleton;
import upb.entity.Cliente;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cliente")

public class ClienteWebApp {
    private static DatabaseStom db = new DatabaseStom();




    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createTrackInJSON(Cliente cliente) {
        Cliente res = db.create(cliente.getNombre(), cliente.getCorreo(), cliente.getPassword());
        return javax.ws.rs.core.Response
                .status(200)
                .entity(res)
                .build();
    }


    @DELETE
    @Path("/borrar/{id_cliente}")
    public javax.ws.rs.core.Response borrarCliente(@PathParam("id_cliente") int id_cliente) {
        int res = db.delete(id_cliente);
        return  javax.ws.rs.core.Response
                .status(200)
                .entity(new Integer(res))
                .build();

    }

    @PUT
    @Path("/PUT/{id}")
    public javax.ws.rs.core.Response modificarUser(@PathParam("id") int id, Cliente cliente) {
        int res = db.modificar(id, cliente);
        return javax.ws.rs.core.Response
                .status(200)
                .entity(new Integer(res))
                .build();

    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response login(Cliente cliente){
        Cliente res = db.auth(cliente.getCorreo(), cliente.getPassword());
        return javax.ws.rs.core.Response
                .status(200)
                .entity(res)
                .build();
    }
}