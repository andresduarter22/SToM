package upb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "administrador")
public class Administrador implements  Serializable{

    @Id
    @Column(name = "id_administrador", unique = true)
    private int id_cliente;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "password", nullable = false)
    private String password;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id_cliente=" + id_cliente +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
