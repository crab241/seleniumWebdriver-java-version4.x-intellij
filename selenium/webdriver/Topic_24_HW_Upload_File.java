package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_24_HW_Upload_File {
    WebDriver driver;
    String uploadFilePath = System.getProperty("user.dir") + "\\uploadFiles\\";

    String astronautFileName = "astronaut.jpg";
    String galaxyFileName = "galaxy.jpg";
    String moonFileName = "moon_background.jpg";

    String astronautFilePath = uploadFilePath + astronautFileName;
    String galaxyFilePath = uploadFilePath + galaxyFileName;
    String moonFilePath = uploadFilePath + moonFileName;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_File(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputFile = By.xpath("//input[@type='file']");
        // add files
        driver.findElement(inputFile).sendKeys(astronautFilePath);
        driver.findElement(inputFile).sendKeys(galaxyFilePath);
        driver.findElement(inputFile).sendKeys(moonFilePath);

        // Verify added files
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + astronautFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + galaxyFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + moonFileName + "']")).isDisplayed());

        List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement button : startUploadButtons) {
            button.click();
            sleepInSecond(1);
        }

        // Verify uploaded files
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + astronautFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + galaxyFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + moonFileName + "']")).isDisplayed());

    }

    @Test
    public void TC_02_Multiple_File(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputFile = By.cssSelector("input[type='file']");

        // Add files
        driver.findElement(inputFile).sendKeys(astronautFilePath + "\n" + galaxyFilePath + "\n" + moonFilePath);

        // Verify added files
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + astronautFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + galaxyFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + moonFileName + "']")).isDisplayed());

        // Upload all files at one
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify uploaded files
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + astronautFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + galaxyFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + moonFileName + "']")).isDisplayed());

    }

    private static void sleepInSecond(long timeWait) {
        try {
            Thread.sleep(timeWait * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
