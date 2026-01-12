package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_31_Wait_Part_VII_Page_Ready {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NopCommerce(){
        driver.get("https://admin-demo.nopcommerce.com");

        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();

        Assert.assertTrue(isPageLoadSuccess());

        driver.findElement(By.xpath("//li[@class='nav-item has-treeview']/a[@class='nav-link']/p[contains(string(), 'Sales')]")).click();
        // Another Xpath: //p[contains(string(), 'Dashboard')]/ancestor::li/following-sibling::li/a[@class='nav-link']/p[contains(string(), 'Sales')]

        driver.findElement(By.xpath("//ul[@class='nav nav-treeview']/li/a[@class='nav-link']/p[contains(string(), 'Orders')]")).click();

        Assert.assertTrue(isPageLoadSuccess());

        Assert.assertEquals(driver.findElement(By.cssSelector("div.content-header>h1")).getText(), "Orders");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public boolean isAjaxBusyLoadingInvisible(){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
    }

    private boolean waitAjaxBusyIconInvisible(){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return !driver.findElement(By.cssSelector("div#ajaxBusy")).isDisplayed();
            }
        });
    }
    // WebDriverWait
    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Điều kiện 1
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        // Điều kiện 2
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    // Fluent Wait
    public boolean isPageLoadSuccess() {
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(JavascriptException.class);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Điều kiện 1
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        // Điều kiện 2
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return fluentWait.until(jQueryLoad) && fluentWait.until(jsLoad);
    }

}
