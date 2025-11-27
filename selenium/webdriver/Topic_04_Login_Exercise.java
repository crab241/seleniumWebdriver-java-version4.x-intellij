package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Login_Exercise {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();

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
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg>ul>li")).getText(), "Invalid login or password.");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
