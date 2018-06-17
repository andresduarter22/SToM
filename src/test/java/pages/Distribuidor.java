package pages;

import control.Button;
import org.openqa.selenium.By;

public class Distribuidor {

    public Button crearDistribuidor;

    public Distribuidor(){
        crearDistribuidor = new Button(By.xpath("/html/body/app-root/app-distribuidor/div/ul/li[1]/a"));
    }
}
