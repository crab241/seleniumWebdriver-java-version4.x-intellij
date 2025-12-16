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


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
