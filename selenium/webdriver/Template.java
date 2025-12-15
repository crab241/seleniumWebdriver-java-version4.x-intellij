package webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Template {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
        // Turn off web notification
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("dom.webnotifications.enabled", false);

        driver = new FirefoxDriver(option);

        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        actions = new Actions(driver);
    }

    @Test
    public void TC(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
