package pages;

import control.Button;
import control.TextBox;
import org.openqa.selenium.By;

public class DistribuidorEditar {

    public Button editarDistribuidor;
    public TextBox textBoxNombre;
    public TextBox textBoxEmail;
    public TextBox textBoxTelefono;
    public TextBox textBoxDireccion;
    public TextBox textBoxID;

    public DistribuidorEditar(){
        textBoxID = new TextBox(By.xpath("/html/body/app-root/app-distribuidor-modificar/div/div/input[@placeholder=\"ID\"]"));
        textBoxDireccion = new TextBox(By.xpath("/html/body/app-root/app-distribuidor-modificar/div/div/input[@placeholder=\"DIRECCION\"]"));
        textBoxNombre = new TextBox(By.xpath("/html/body/app-root/app-distribuidor-modificar/div/div/input[@placeholder=\"NOMBRE\"]"));
        textBoxEmail = new TextBox(By.xpath("/html/body/app-root/app-distribuidor-modificar/div/div/input[@placeholder=\"CORREO\"]"));
        textBoxTelefono = new TextBox(By.xpath("/html/body/app-root/app-distribuidor-modificar/div/div/input[@placeholder=\"TELEFONO\"]"));
        editarDistribuidor = new Button(By.xpath("/html/body/app-root/app-distribuidor-modificar/div/div/input[@value=\"Confirmar\"]"));
    }
}
