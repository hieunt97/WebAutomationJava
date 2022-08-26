package pages;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonService;

import java.util.ArrayList;
import java.util.List;

public class EbayPage {
    private WebDriver driver;
    private CommonService commonService;

    public EbayPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonService = new CommonService(driver);
    }

    @FindBy(name = "_nkw")
    WebElement inputSearch;

    @FindBy(xpath = "//h1[contains(text(),'+ results for ')]")
    WebElement resultSearch;

    @FindBy(xpath = "//button[@aria-label='Sort selector. Best Match selected.']")
    WebElement btnSort;

    @FindBy(xpath = "//div[@class='srp-controls__sort srp-controls__control']//li[4]//a")
    WebElement href_Sort;

    By product_name = By.xpath("//li[@class='s-item s-item__pl-on-bottom s-item--watch-at-corner']//div[@class=\"s-item__info clearfix\"]");

    public void enterSearchValue(String value) {
        commonService.setTextValue(inputSearch, value);
        inputSearch.sendKeys(Keys.ENTER);
    }

//    public void verifyResult(){
//        String act_ResultDisplay = resultSearch.getText();
//        System.out.println(act_ResultDisplay);
//        Assert.assertEquals(act_ResultDisplay, "3,900+ results for iphone 11");
//    }

    public void isDisplayResult() {
        Assert.assertTrue(resultSearch.isDisplayed());
    }

    public void sortByPrice() throws InterruptedException {
        commonService.clickElement(btnSort);
        Thread.sleep(1000);
        commonService.clickElement(href_Sort);
    }

    public List<Product> getAllProducts(){
        Actions action = new Actions(driver);
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@id='srp-river-results']/ul/li[@class='s-item s-item__pl-on-bottom s-item--watch-at-corner']"));
        commonService.waitForPageLoad();
        System.out.println("Name web: " + driver.getTitle());
        List<Product> listProduct = new ArrayList<>();

        for (WebElement e : elementList) {
            action.moveToElement(e).build().perform();
            Product product = new Product();
            product.setProuductName(e.findElement(By.className("s-item__title")).getText());
            product.setProductPrice(e.findElement(By.className("s-item__price")).getText());
            product.setProductLink(e.findElement(By.className("s-item__link")).getAttribute("href"));
            listProduct.add(product);
        }

        for (Product product : listProduct) {
            product.inforProduct();
        }
        return listProduct;
    }

}
