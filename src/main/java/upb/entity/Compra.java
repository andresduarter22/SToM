package upb.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "compra")
public class Compra implements Serializable{

    @Id
    @Column(name = "id_compra",unique=true, nullable = false)
    private int id_compra;

    @Column(name = "id_juego")
    private int id_juego;

    @Column(name = "id_cliente")
    private int id_cliente;

    @Column(name = "precio")
    private int precio;

    public Compra(){

    }
    public Compra(int id_juego,int id_cliente,int precio){
    this.id_compra=0;
    this.id_juego=id_juego;
    this.id_cliente=id_cliente;
    this.precio=precio;

    }
    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
