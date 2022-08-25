package automation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.AmazonPage;
import utils.CommonService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author hieunt
 * @since 24/08/2022
 */

public class AmazonTest extends BaseTest{
    private AmazonPage amazonPage;
    private CommonService commonService;
    String baseUrl = "https://www.amazon.com";

    @BeforeClass
    public void setup(){
        goToHomepage(baseUrl);
        commonService = new CommonService(driver);
    }

    @Test(priority = 1)
    public void searchAmazon() throws InterruptedException {
        amazonPage = new AmazonPage(driver);
        amazonPage.enterSearchValue("iPhone 11");
        Thread.sleep(1000);
        //Check result
        amazonPage.isDisplayResult();
        //Sort
        amazonPage.sortByPrice("Price: Low to High");
        amazonPage.getAllProducts();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        closeBrowser();
    }


}

