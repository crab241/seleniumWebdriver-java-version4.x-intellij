package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Handle_Frame_Iframe {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        actions = new Actions(driver);

        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_WordPress() throws InterruptedException {
        driver.get("https://toidicodedao.com/");

        // Swith to iframe / frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));

        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText(), "399,211 followers");

        // Return to the actual/ host page / main document
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("div#content-sidebar input.search-field")).sendKeys("puppeteer");
        driver.findElement(By.cssSelector("div#content-sidebar input.search-field")).sendKeys(Keys.ENTER);

    }

    @Test
    public void TC_02_FormSite(){
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();

        // We can use //div[@class='details__form-preview-wrapper' and contains(string(), 'Interactive form loaded. Try filling out below.')] for Xpath
        Assert.assertTrue(driver.findElement(By.cssSelector("p#tooltip")).isDisplayed());

        // Swith to iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Senior");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("West Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();

        driver.switchTo().defaultContent();

        driver.findElement(By.xpath("//a[@title='Get this form']")).click();

    }

    @Test
    public void HW_TC_10_Iframe(){
        driver.get("https://toidicodedao.com/");

        Assert.assertTrue(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")).isDisplayed());

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));

        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText(), "399,209 followers");

    }

    @Test
    public void HW_TC_11_Iframe() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        // Click on the image to make the iframe form appear
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        Thread.sleep(2000);

        //jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
        // Switch to the iframe to interact
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        // Select stuff in the drop down of the form
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Senior");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.findElement(By.cssSelector("input#FSsubmit")).click();

        Thread.sleep(2000);

        // Switch back to defaut page
        driver.switchTo().defaultContent();

        // Click on get this form
        driver.findElement(By.xpath("//a[@title='Get this form']")).click();
        //Thread.sleep(3000);

        // Click on log in button
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        // Click on login button without entering username and password
        driver.findElement(By.cssSelector("button#login")).click();
        //Thread.sleep(2000);

        // Verify error messages appear after clicking login button without entering username and password
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
