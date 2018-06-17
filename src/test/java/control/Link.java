package control;

import org.openqa.selenium.By;

public class Link extends Control {

    public Link(By customLocator){
        this.locator = customLocator;
    }
}
