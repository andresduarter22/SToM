package pages;

import control.Button;
import control.TextBox;
import org.openqa.selenium.By;

public class DistribuidorCrear {

    public Button crearDistribuidor;
    public TextBox textBoxNombre;
    public TextBox textBoxEmail;
    public TextBox textBoxTelefono;
    public TextBox textBoxDireccion;

    public DistribuidorCrear(){
        textBoxDireccion = new TextBox(By.xpath("/html/body/app-root/app-add-distribuidor/div/div/input[@placeholder=\"direccion\"]"));
        textBoxNombre = new TextBox(By.xpath("/html/body/app-root/app-add-distribuidor/div/div/input[@placeholder=\"nombre\"]"));

        textBoxEmail = new TextBox(By.xpath("/html/body/app-root/app-add-distribuidor/div/div/input[@placeholder=\"E-mail\"]"));
        textBoxTelefono = new TextBox(By.xpath("/html/body/app-root/app-add-distribuidor/div/div/input[@placeholder=\"telefono\"]"));
        crearDistribuidor = new Button(By.xpath("/html/body/app-root/app-add-distribuidor/div/div/input[@class=\"btn btn-primary btn-block btn-large\"]"));
    }
}
