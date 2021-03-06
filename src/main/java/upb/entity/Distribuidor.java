package upb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "distribuidor")
public class Distribuidor implements Serializable {
    @Id
    @Column(name = "id_distribuidor", unique = true, nullable = false)
    private int id_distribuidor;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "nombre")
    private String nombre;

    public Distribuidor(){}
    public Distribuidor(String correo, String telefono, String direccion, String nombre) {
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public int getId_distribuidor() {
        return id_distribuidor;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {

        return correo;

    }

    public String  getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


