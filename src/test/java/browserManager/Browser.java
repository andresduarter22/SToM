package browserManager;

import factoryBrowser.Chrome;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 *
 */
public class Browser {
    private static Browser ourInstance = null;
    public WebDriver driver;

    /**
     *
     * @return
     */
    public static Browser getCurrentSession() {
        if (ourInstance == null)
            ourInstance = new Browser();

        return ourInstance;
    }

    private Browser() {
        Chrome cr= new Chrome();
        driver =   cr.create();
    }

    public void closeBrowser(){
        driver.quit();
        ourInstance = null;
        try{
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
