package control;

import org.openqa.selenium.By;

public class Image extends Control {

    public Image(By customLocator){
        this.locator = customLocator;
    }
}
