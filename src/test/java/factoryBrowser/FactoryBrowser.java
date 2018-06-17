package factoryBrowser;

public class FactoryBrowser {

    public static final String CHROME = "chrome";

    /**
     *
     * @param typeBrowser
     * @return
     */
    public static IBrowser make (String typeBrowser){

        IBrowser browser;

        switch (typeBrowser)
        {


            case CHROME :
                browser = new Chrome();
                break;

             default:
                 browser = new Chrome();
                 break;
        }
        return browser;
    }
}
