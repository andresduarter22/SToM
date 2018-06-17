package control;

import org.openqa.selenium.By;

public class CheckBox extends Control {

    public CheckBox(By customLocator){
        this.locator = customLocator;
    }
}
