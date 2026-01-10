package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.function.Function;

public class Topic_30_Wait_Part_VI_Fluent_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;
    FluentWait<WebElement> elementFluentWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        fluentWait = new FluentWait(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofMillis(200));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        clickElement("div#start>button");

        String helloText = getElementText("div#finish>h4");

        Assert.assertEquals(helloText, "Hello World!");

    }

    @Test
    public void TC_02(){
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countdownElement = getElement("div#javascript_countdown_time");

        elementFluentWait = new FluentWait<>(countdownElement);

        elementFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        Assert.assertTrue(elementFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                System.out.println("Text = " + text);
                return text.endsWith("00");
            }
        }));

    }

    @Test
    public void TC_03_WebDriverWait(){
        driver.get("https://automationfc.github.io/fluent-wait/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")));
    }

    // function to find element
    private WebElement getElement(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector(cssLocator));
            }
        });
    }

    // click element function
    private void clickElement(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector(cssLocator));
            }
        }).click();
    }

    // get Element text function
    private String getElementText(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        return fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector(cssLocator));
                    }
                }).getText();
    }

    // check if Element is displayed function
    private Boolean isElementDisplayed(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);

        return fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector(cssLocator));
            }
        }).isDisplayed();
    }

    private boolean waiVisible(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        return fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
                    }
                });
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
