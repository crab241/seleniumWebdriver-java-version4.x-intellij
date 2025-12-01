package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_05_TextBox_TextArea {

    WebDriver driver;
    String firstName, lastName, fullName, emailAddress, password;

    @BeforeClass
    public void beforeClass(){
        // For FireFox to accept insecure form
        /*FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        driver = new FirefoxDriver(options);*/

        // For Chrome to accept insecure form
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);

        firstName = "Meo";
        lastName = "maybe";
        fullName = firstName + " " + lastName;
        emailAddress = "meo" + new Random().nextInt(9999) + "@yahoo.com";
        password = "123456";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_TextBox_TexArea() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        //driver.findElement(By.cssSelector("input#is_subscribed")).click();
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("button#proceed-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(fullName));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(emailAddress));

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//h2//a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Nice Phone.\n Better than Iphone");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Quality Phone");
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(firstName + new Random().nextInt(999));
        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button#proceed-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Your review has been accepted for moderation.");


    }

    @Test
    public void TC_02(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
