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

public class Topic_31_HW_Wait_Part_VII_Page_Ready {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_10_Page_Ready(){
        driver.get("https://admin-demo.nopcommerce.com");

        WebElement inputEmail = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#Email")));
        inputEmail.clear();
        inputEmail.sendKeys("admin@yourstore.com");

        WebElement inputPassword = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#Password")));
        inputPassword.clear();
        inputPassword.sendKeys("admin");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.login-button"))).click();

        // Wait and verify that loading icon disappear
        Assert.assertTrue(isLoadingIconInvisible("div#ajaxBusy"));
        //Assert.assertTrue(isLoadingIconNoLongerExist("div#ajaxBusy"));
        //Assert.assertTrue(waitLoadingIconDisappear("div#ajaxBusy"));

        // Logout
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//a[text()='Logout']"))).click();

        // Wait and verify logout successfully
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.title>strong"))).getText(), "Welcome, please sign in!");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private boolean isLoadingIconInvisible(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        return fluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver input) {
                        return driver.findElements(By.cssSelector(cssLocator)).isEmpty();
                    }
                });
    }

    private boolean isLoadingIconNoLongerExist(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        return fluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver input) {
                        return !driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
                    }
                });
    }

    private boolean waitLoadingIconDisappear(String cssLocator){
        return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssLocator)));
    }
}
