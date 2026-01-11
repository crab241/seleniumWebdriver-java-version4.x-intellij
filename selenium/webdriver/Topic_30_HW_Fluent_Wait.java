package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_30_HW_Fluent_Wait {
    WebDriver driver;
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        fluentWait = new FluentWait<>(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        driver.manage().window().maximize();
    }

    @Test
    public void TC_08_FluentWait(){
        driver.get("https://automationfc.github.io/fluent-wait/");

        // Wait for count down visible
        waitElementvisible("div#javascript_countdown_time");

        // Wait until count down reach to "00" and verify
        Assert.assertTrue(checkCoundDown("div#javascript_countdown_time"));
    }

    @Test
    public void TC_09_FluentWait(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // Wait for start button element visible and then click it
        waitElementvisible("div#start>button").click();

        // wait for text appear, then get text and verify it
        Assert.assertEquals(getElementText("div#finish>h4"), "Hello World!");
    }

    // Wait for element visible function
    private WebElement waitElementvisible(String cssLocator){
        FluentWait<WebDriver> driverFluentWait = new FluentWait(driver);

        return driverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class).until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector(cssLocator));
            }
        });
    }

    // check count down reach to 00
    private Boolean checkCoundDown(String cssLocator){
        FluentWait<WebDriver> driverFluentWait = new FluentWait(driver);

        return driverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector(cssLocator)).getText().contains("00");
                    }
                });
    }

    // Check Element text
    private String getElementText(String cssLocator){
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);

        return driverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, String>() {
                    @Override
                    public String apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector(cssLocator)).getText();
                    }
                });
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
