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

public class Topic_24_Handle_Upload_File {
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
    public void TC_01_Single_File() throws InterruptedException {
        // File để ở đâu
        // File cố địnht trên máy -> QUa máy khác k chạy dc
        // => Bất kì máy nào cũng chạy đc
        // => Để file trong chính bộ source code
        // => Lấy đường dẫn tương đối của file ra
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFileBy = By.cssSelector("input[type='file']");

        // Load files
        driver.findElement(uploadFileBy).sendKeys(astronautFilePath);
        driver.findElement(uploadFileBy).sendKeys(galaxyFilePath);
        driver.findElement(uploadFileBy).sendKeys(moonFilePath);

        // Verify loaded files
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + astronautFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + galaxyFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + moonFileName + "']")).isDisplayed());

        // Upload files
        List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement  startButton : startUploadButtons) {
            startButton.click();
            Thread.sleep(1);
        }

        // Verify uploaded files

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + astronautFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + galaxyFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + moonFileName + "']")).isDisplayed());

    }

    @Test
    public void TC_02_Multiple_Files() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFileBy = By.cssSelector("input[type='file']");

        // Load files
        driver.findElement(uploadFileBy).sendKeys(astronautFilePath + "\n" + galaxyFilePath + "\n" + moonFilePath);
        Thread.sleep(2000);

        // Verify loaded files
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + astronautFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + galaxyFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + moonFileName + "']")).isDisplayed());

        // Upload files
        List<WebElement> startUploadButtons = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement  startButton : startUploadButtons) {
            startButton.click();
            Thread.sleep(1);
        }

        // Verify uploaded files

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + astronautFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + galaxyFileName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + moonFileName + "']")).isDisplayed());
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
