package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_04_Login_Exercise {
    WebDriver driver;
    WebDriverWait explicitWait;
    String randomEmail = "johnnycage" + new Random().nextInt(9999) + "@gmail.com";

    @BeforeClass
    public void beforeClass(){
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Login_Empty_EmailPassword(){
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#pass")).clear();

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");
    }

    @Test
    public void TC_02_Login_Invalid_Email(){
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("123434234@12312.123123");  // Invalid email

        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");  // valid password

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_Login_Invalid_Passwd(){
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com"); // valid password

        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");  // invalid password

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Login_Incorrect() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com"); // correct email

        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123123");  // incorrect password

        driver.findElement(By.cssSelector("button#send2")).click();

        Thread.sleep(2000);
        // Thêm bước ấn nút Send away vì trang pop up cảnh báo bảo maatj
        driver.findElement(By.cssSelector("button#proceed-button")).click();

        Thread.sleep(2000);
        // li.error-msg>ul>li can be raplace with li-error-msg span
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg>ul>li")).getText(), "Invalid login or password.");
    }

    @Test
    public void Redo_TC_01_Login_With_Empty_Email_Pass(){
        driver.get("http://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='footer'] a[title='My Account']"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#send2"))).click();

        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div#advice-required-entry-email"))).getText(), "This is a required field.");
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div#advice-required-entry-pass"))).getText(), "This is a required field.");
    }

    @Test
    public void Redo_TC_02_Login_With_Invalid_Email(){
        driver.get("http://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='footer'] a[title='My Account']"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email"))).sendKeys("123434234@12312.123123");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#pass"))).sendKeys("123456");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#send2"))).click();

        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div#advice-validate-email-email"))).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void Redo_TC_03_Login_With_Invalid_Password(){
        driver.get("http://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='footer'] a[title='My Account']"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email"))).sendKeys(randomEmail);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#pass"))).sendKeys("123");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#send2"))).click();

        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div#advice-validate-password-pass"))).getText(), "Please enter 6 or more characters without leading or trailing spaces.");

    }

    @Test
    public void Redo_TC_04_Login_With_Incorrect_Email_Pass(){
        driver.get("http://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='footer'] a[title='My Account']"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email"))).sendKeys("automation@gmail.com");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#pass"))).sendKeys("123123123");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#send2"))).click();

        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("li.error-msg span"))).getText(), "Invalid login or password.");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
