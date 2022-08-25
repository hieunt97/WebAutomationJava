package automation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AmazonPage;
import pages.EbayPage;
import utils.CommonService;

/**
 * @author hieunt
 * @since 24/08/2022
 */
public class EbayTest extends BaseTest {

    private EbayPage ebayPage;
    private CommonService commonService;

    String baseUrl = "https://www.ebay.com/";

    @BeforeClass
    public void setup(){
        goToHomepage(baseUrl);
        commonService = new CommonService(driver);
    }

    @Test
    public void searchEbay() throws InterruptedException {
        ebayPage = new EbayPage(driver);
        ebayPage.enterSearchValue("iPhone 11");
        Thread.sleep(1000);
        //Check result
        ebayPage.isDisplayResult();
        //Sort
        ebayPage.sortByPrice();
        ebayPage.getAllProducts();

    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        closeBrowser();
    }
}
