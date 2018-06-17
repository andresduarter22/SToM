package pages;

import control.Button;
import control.Link;
import org.openqa.selenium.By;

public class HomePage {

    public Button buttonSingIn;

    public HomePage(){
        buttonSingIn = new Button(By.xpath("/html/body/app-root/app-home/div/a[@routerlink = \"/distribuidor\"]"));
    }

}
