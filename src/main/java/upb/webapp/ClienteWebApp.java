package upb.webapp;


import upb.entity.Cliente;
import upb.entity.Libro;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

@Path("/cliente")
public class ClienteWebApp {
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createTrackInJSON(Cliente cliente) {
        String result = "Libro Guardado : " + cliente;
        DatabaseStom b = new DatabaseStom();
        b.create(cliente.getNombre(), cliente.getCorreo(), cliente.getPassword());
        return javax.ws.rs.core.Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(cliente)
                .build();
    }


    @DELETE
    @Path("/borrar/{id_cliente}")
    public Response borrarCliente(@PathParam("id_cliente") int id_cliente) {
        DatabaseStom b = new DatabaseStom();
        b.delete(id_cliente);
        return (Response) javax.ws.rs.core.Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();

    }

    @PUT
    @Path("/PUT/{id}")
    public javax.ws.rs.core.Response modificarUser(@PathParam("id") int id, String nombre, String correo, String password) {
        DatabaseStom b = new DatabaseStom();
        b.modificar(id, nombre, correo, password);
        return javax.ws.rs.core.Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")

                .build();

    }

}
