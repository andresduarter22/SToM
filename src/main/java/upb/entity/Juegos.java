package upb.entity;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "juego")
public class Juegos implements Serializable {
    @Id
    @Column(name = "id_juego",unique=true, nullable = false)
    private int id_juego;


    @Column(name = "id_distribuidor")
    private int id_distribuidor;

    @Column(name = "nombre")
    private String nombre;



    @Column(name = "linkimagen")
    private String linkimagen;



    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "costo")
    private int costo;

    @Column(name = "version")
    private String version;

    public  Juegos(){}

    public Juegos(String nombre, String estado, String categoria,int costo, String version, int id_distribuidor,String descripcion,
                  String linkimagen) {
        this.id_juego=0;
        this.id_distribuidor=id_distribuidor;
        this.nombre = nombre;
        this.estado = estado;
        this.categoria = categoria;
        this.costo = costo;
        this.version = version;
        this.descripcion=descripcion;
        this.linkimagen=linkimagen;
    }

    public int getId_juego() {
        return id_juego;
    }
    public int getId_distribuidor(){
        return id_distribuidor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCosto() {
        return costo;
    }
    public String getVersion(){return version;}
    public String getLinkImagen() { return linkimagen;}
    public String getDescripcion() { return descripcion; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(String correo) {
        this.estado = correo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    public void setVersion(String version){this.version = version;}

    public void setId_distribuidor(int id_distribuidor) {
        this.id_distribuidor = id_distribuidor;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public void setLinkImagen(String linkImagen) {
        this.linkimagen = linkImagen;
    }
}
