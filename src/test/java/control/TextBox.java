package control;

import org.openqa.selenium.By;

public class TextBox extends Control {

    public TextBox(By customLocator){
        this.locator = customLocator;
    }
}
