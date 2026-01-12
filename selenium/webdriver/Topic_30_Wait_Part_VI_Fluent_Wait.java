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

    // Function to check/ wait for loading icon to disappear
    private Boolean isLoadingIconInvisible_ver1(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return !driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
            }
        });
    }

    private Boolean isLoadingIconInvisible_ver2(String cssLocator){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElements(By.cssSelector(cssLocator)).isEmpty();
            }
        });
    }

    private Boolean waitTextChange(String cssLocator, String textExpected){
        FluentWait<WebDriver> fluentWait = new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(cssLocator)).getText().equals(textExpected);
            }
        });
    }

    public boolean isAjaxBusyLoadingInvisible() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
    }

    public boolean isAjaxBusyIconInvisible() {
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
    public boolean isPageLoadSuccess_FluentWait() {
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

    // WebDriverWait way
    public boolean isPageLoadSuccess_WebDriverWait(){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // First condition
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window,jQuery != null) && (jQuery.active === 0");
            }
        };

        // Second condition
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
