package control;

import browserManager.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Control {

    public By locator;
    public WebElement myControl;

    public Control(){

    }


    public void findControl(){

        WebDriverWait wait = new WebDriverWait(Browser.getCurrentSession().driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(this.locator));
        this.myControl = Browser.getCurrentSession().driver.findElement(this.locator);
    }

    public boolean isDisplayed(){
        try {
            Browser.getCurrentSession().driver.findElement(locator).isDisplayed();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTextValue(){
        findControl();
        return myControl.getText();
    }

    public String getTextValue(String attribute){
        findControl();
        return myControl.getAttribute(attribute);
    }

    public void click(){
        findControl();
        myControl.click();
    }

    public void set(String value){
        findControl();
        myControl.sendKeys(value);
    }
}
