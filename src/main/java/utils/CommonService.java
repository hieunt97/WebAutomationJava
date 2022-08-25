package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonService {
    private WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    private final int timeoutWaitForPageLoaded = 20;

    public CommonService(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        js = (JavascriptExecutor) driver;
    }

    public boolean veryfiPageTitle(String pageTitle) {
        String title = driver.getTitle();
        return title.contains(pageTitle);
    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(expectation);
        } catch (Exception e) {
            Assert.fail("Timeout waiting for Page Load request");
        }
    }

    public void setTextValue(WebElement element, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(value);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void selectOptionByVisibleText(WebElement element, String value) {
        //Convert BY to WebElemnt
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

}
