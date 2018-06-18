package pages;

import control.Button;
import control.TextBox;
import org.openqa.selenium.By;

public class DistribuidorEliminar {

    public Button eliminarDistribuidor;
    public TextBox textBoxID;

    public DistribuidorEliminar(){
        textBoxID = new TextBox(By.xpath("/html/body/app-root/app-eliminar-distribuidor/div/div/input[@placeholder=\"ID\"]"));
        eliminarDistribuidor = new Button(By.xpath("/html/body/app-root/app-eliminar-distribuidor/div/div/input[@value=\"Confirmar\"]"));
    }

}
