package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Chrome implements IBrowser {

    @Override
    public WebDriver create() {
        String PATH_PROJECT=new File(".").getAbsolutePath().replace(".","");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Desktop\\upb\\PRIMERO2018\\Proyecto de ing. de Software\\automatizacion\\BasicAutomationExample\\src\\test\\java\\drivers\\chromedriver.exe");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--enable-memory-info");
        options.addArguments("--no-sandbox");
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }
}
