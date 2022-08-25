package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author hieunt
 * @since 24/08/2022
 */
public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        js = (JavascriptExecutor) driver;
    }

    protected WebDriver driver(){
        return driver;
    }

}