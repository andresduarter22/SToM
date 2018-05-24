package upb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    @Id
    @Column(name = "id_cliente",unique=true, nullable = false)
    private int id_cliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "password")
    private String password;

    @Column(name = "credito")
    private double credito;

    public  Cliente(){}

    public Cliente(String nombre, String correo, String password) {
        this.id_cliente=0;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.credito = 0.0;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public double getCredito() {
        return credito;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }
}
