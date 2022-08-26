package pages;

import model.Product;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.CommonService;

import javax.xml.bind.Element;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class AmazonPage {
    private WebDriver driver;
    private CommonService commonService;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonService = new CommonService(driver);
    }

    @FindBy(name = "field-keywords")
    WebElement inputSearch;

    @FindBy(xpath = "//span[contains(text(),'results for')]")
    WebElement resultSearch;

    @FindBy(id = "s-result-sort-select")
    WebElement labelSort;

    By product_name = By.xpath("//span[@data-component-id='11']//div[2]//div[@data-component-type='s-search-result']//div[@class='a-section a-spacing-small a-spacing-top-small']");

    public void enterSearchValue(String value) {
        commonService.setTextValue(inputSearch, value);
        inputSearch.sendKeys(Keys.ENTER);
    }

    public void Result() {
        String act_ResultDisplay = resultSearch.getText();
        System.out.println(act_ResultDisplay);
//        Assert.assertEquals(act_ResultDisplay, "1-16 of 457 results for");
    }

    public void isDisplayResult() {
        //return resultSearch.isDisplayed();
        Assert.assertTrue(resultSearch.isDisplayed());
    }

    public void sortByPrice(String text) {
        commonService.selectOptionByVisibleText(labelSort, text);
        commonService.waitForPageLoad();
    }

    public List<Product> getAllProducts() {
        Actions action = new Actions(driver);
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//div[@class='a-section']//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        commonService.waitForPageLoad();
        System.out.println("Name web: " + driver.getTitle());
        List<Product> listProduct = new ArrayList<>();

        for (WebElement e : elementList) {
            action.moveToElement(e).build().perform();
            Product product = new Product();
            product.setProuductName(e.findElement(By.cssSelector("[class='a-size-medium a-color-base a-text-normal']")).getText());
            try {
                if (e.findElement(By.cssSelector("[class='a-price-whole']")).getText() != null) {
                    product.setProductPrice(e.findElement(By.cssSelector("[class='a-price-whole']")).getText().concat("$"));
                } else {
                    product.setProductPrice("0.0");
                }
            }catch (NotFoundException ex){
                product.setProductPrice("0.0");
            }

            product.setProductLink(e.findElement(By.cssSelector("[class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']")).getAttribute("href"));
            listProduct.add(product);
        }

        for (Product product : listProduct) {
            product.inforProduct();
        }
        return listProduct;
    }


}
