import browserManager.Browser;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import pages.Distribuidor;
import pages.DistribuidorCrear;
import pages.HomePage;

public class Test {

    public static HomePage homePage;
    public static Distribuidor distribuidor;
    public static DistribuidorCrear distribuidorCrear;

    @BeforeClass
    public static void openBrowser(){
        homePage = new HomePage();

        distribuidor = new Distribuidor();
        distribuidorCrear = new DistribuidorCrear();
    }


    @org.junit.Test
    public void crearDistribuidor() throws InterruptedException {

        Browser.getCurrentSession().driver.get("http://localhost:4200/home");
        homePage.buttonSingIn.click();
        distribuidor.crearDistribuidor.click();
        distribuidorCrear.textBoxNombre.set("Konami");
        distribuidorCrear.textBoxEmail.set("konami@gmail.com");
        distribuidorCrear.textBoxTelefono.set("12345678456453423546576564");
        distribuidorCrear.textBoxDireccion.set("Mi casa");
        distribuidorCrear.crearDistribuidor.click();


        //Verificaciones
        Assert.assertTrue("ERROOOOOOOOOOOOOR !!!!!No se pudo ingresar al sistema",
                distribuidor.crearDistribuidor.isDisplayed());


        Thread.sleep(2000);
  }


    @AfterClass
    public static void closeBrowser(){
        Browser.getCurrentSession().closeBrowser();
    }
}
