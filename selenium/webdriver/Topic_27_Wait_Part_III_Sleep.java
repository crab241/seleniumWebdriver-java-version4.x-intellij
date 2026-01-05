package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_Part_III_Sleep {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Less() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Equal() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_03_More() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Thread.sleep(10000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void HW_TC_01_Less() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        // Set static sleep less than the time the icon loading need to finish loading
        Thread.sleep(3000);

        // Verify message ( this should fail )
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void HW_TC_02_Equal() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        // Set static sleep equal the time the icon loading need to finish loading
        Thread.sleep(5000);

        // Verify message ( this should work )
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void HW_TC_03_More() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        // Set static sleep more than the time the icon loading need to finish loading
        Thread.sleep(10000);

        // Verify message ( this should work )
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

        // Despite the loading icon only need 5s to complete,
        // the driver would still wait for total of 10s sleep to finish before preceed to the Verify message step
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
