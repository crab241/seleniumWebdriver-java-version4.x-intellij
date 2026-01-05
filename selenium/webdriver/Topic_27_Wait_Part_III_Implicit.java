package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_27_Wait_Part_III_Implicit {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Less(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Equal(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_03_More(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void HW_TC_01_Less(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        // Set implicit wait to be shorter than the time for the loading icon to finish loading
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // This will fail
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void HW_TC_02_Equal(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        // Set the implicit wait to be precisely equal the time loading icon need to finish loading
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // This should work
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void HW_TC_03_More(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        // Set the implicit wait to be precisely larger than the time loading icon need to finish loading
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // This should definitely work
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
