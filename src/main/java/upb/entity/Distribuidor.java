package upb.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "distribuidor")
public class Distribuidor implements Serializable{

    @Id
    @Column(name = "id_distribuidor", unique = true)
    private int id_distribuidor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "correo", nullable = false)
    private String correo;

    public int getId_distribuidor() {
        return id_distribuidor;
    }

    public void setId_distribuidor(int id_distribuidor) {
        this.id_distribuidor = id_distribuidor;
    }

    public String getNombre() { return nombre;    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String autor) { this.telefono = autor;  }

    public String getDireccion() {return direccion;   }

    public void setDireccion(String direccion) {this.direccion = direccion;  }

    public String getCorreo() { return correo;  }

    public void setCorreo(String correo) {  this.correo = correo;   }

    @Override
    public String toString() {
        return id_distribuidor + "\t" + nombre + "\t" + telefono + "\t" + direccion +"\t"+ correo;
    }

}
