import browserManager.Browser;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import pages.*;

public class Test {

    public static HomePage homePage;
    public static Distribuidor distribuidor;
    public static DistribuidorCrear distribuidorCrear;
    public static DistribuidorEditar distribuidorEditar;
    public static DistribuidorEliminar distribuidorEliminar;

    @BeforeClass
    public static void openBrowser(){
        homePage = new HomePage();

        distribuidor = new Distribuidor();
        distribuidorCrear = new DistribuidorCrear();
        distribuidorEditar = new DistribuidorEditar();
        distribuidorEliminar = new DistribuidorEliminar();
    }


    @org.junit.Test
    public void crearDistribuidor() throws InterruptedException {

        Browser.getCurrentSession().driver.get("http://localhost:4200/home");
        homePage.buttonSingIn.click();
        distribuidor.crearDistribuidor.click();
        distribuidorCrear.textBoxNombre.set("Konami");
        distribuidorCrear.textBoxEmail.set("konami@gmail.com");
        distribuidorCrear.textBoxTelefono.set("1234567");
        distribuidorCrear.textBoxDireccion.set("Mi casa");
        distribuidorCrear.crearDistribuidor.click();


        //Verificaciones
        Assert.assertTrue("Error al crear distribuidor",
                distribuidor.crearDistribuidor.isDisplayed());


        Thread.sleep(2000);
  }

    @org.junit.Test
    public void editarDistribuidor() throws InterruptedException {

        Browser.getCurrentSession().driver.get("http://localhost:4200/home");
        homePage.buttonSingIn.click();
        distribuidor.editarDistribuidor.click();
        distribuidorEditar.textBoxID.set("1");
        distribuidorEditar.textBoxEmail.set("ea@gmail.com");
        distribuidorEditar.textBoxTelefono.set("12345678");
        distribuidorEditar.textBoxDireccion.set("Mi Casa 2");
        distribuidorEditar.textBoxNombre.set("EA");
        distribuidorEditar.editarDistribuidor.click();

        //Verificaciones
        Assert.assertTrue("Error al crear distribuidor",
                distribuidor.crearDistribuidor.isDisplayed());

        Thread.sleep(2000);
    }

    @org.junit.Test
    public void eliminarDistribuidor() throws InterruptedException {

        Browser.getCurrentSession().driver.get("http://localhost:4200/home");
        homePage.buttonSingIn.click();
        distribuidor.eliminarDistribuidor.click();
        distribuidorEliminar.textBoxID.set("2");
        distribuidorEliminar.eliminarDistribuidor.click();

        //Verificaciones
        Assert.assertTrue("Error al crear distribuidor",
                distribuidor.crearDistribuidor.isDisplayed());

        Thread.sleep(2000);
    }


    @AfterClass
    public static void closeBrowser(){
        Browser.getCurrentSession().closeBrowser();
    }
}
