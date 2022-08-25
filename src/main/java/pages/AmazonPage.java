package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.CommonService;

import javax.xml.bind.Element;
import java.security.Key;
import java.util.List;

public class AmazonPage {
    private WebDriver driver;
    private CommonService commonService;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonService = new CommonService(driver);
    }

    @FindBy(id="twotabsearchtextbox")
    WebElement inputSearch;

    @FindBy(xpath="//span[contains(text(),'results for')]")
    WebElement resultSearch;

    @FindBy(id="s-result-sort-select")
    WebElement labelSort;

    By product_name = By.xpath("//span[@data-component-id='11']//div[2]//div[@data-component-type='s-search-result']//div[@class='a-section a-spacing-small a-spacing-top-small']");
    By product_price = By.xpath("//span[@data-component-id='11']//div[2]//div[@data-component-type='s-search-result']//div[@class='a-row a-size-base a-color-base']//span[@class='a-price']");
    By product_link = By.xpath("//span[@data-component-id='11']//div[2]//div[@data-component-type='s-search-result']//a[@class='a-link-normal s-no-outline']");

    public void enterSearchValue(String value) {
        commonService.setTextValue(inputSearch, value);
        inputSearch.sendKeys(Keys.ENTER);
    }

    public void Result(){
        String act_ResultDisplay = resultSearch.getText();
        System.out.println(act_ResultDisplay);
//        Assert.assertEquals(act_ResultDisplay, "1-16 of 457 results for");
    }

    public void isDisplayResult(){
        //return resultSearch.isDisplayed();
        Assert.assertTrue(resultSearch.isDisplayed());
    }

    public void sortByPrice(String text){
        commonService.selectOptionByVisibleText(labelSort, text);
        commonService.waitForPageLoad();
    }

    public void getAllProducts(){
        List<WebElement> productName_list = driver.findElements(product_name);
        System.out.println("Total product:" + productName_list.size());
        Actions action = new Actions(driver);
        String nameURL = driver.getTitle();
        System.out.println(nameURL);
        System.out.println("---------------");

        for(WebElement product: productName_list) {
            action.moveToElement(product).build().perform();
            System.out.println(product.getText());
            System.out.println("---------------");
        }


    }


}
