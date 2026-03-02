package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Practique_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver;

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_07_Accept(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Scroll to view
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", waitElement(By.xpath("//button[text()='Click for JS Alert']")));

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        Alert alert = driver.switchTo().alert();

        // Verify alert message
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();

        // Verify message
        Assert.assertEquals(waitElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_08_Confirm(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // scroll into view
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", waitElement(By.xpath("//button[text()='Click for JS Confirm']")));

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // Switch to alert to interact
        Alert alert = driver.switchTo().alert();

        // Verify message in alert
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        // Cancel alert
        alert.dismiss();

        // Verify success msg
        Assert.assertEquals(waitElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_09_Prompt() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // scroll to view
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", waitElement(By.xpath("//button[text()='Click for JS Prompt']")));

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        // switch to alert to interact
        Alert alert = driver.switchTo().alert();

        // Verify alert msg
        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        // Send text
        String text = "automationfc";
        alert.sendKeys(text);
        Thread.sleep(3000);
        alert.accept();

        // Verify msg result
        Assert.assertEquals(waitElement(By.cssSelector("p#result")).getText(), "You entered: " + text);

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebElement waitElement(By locator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
