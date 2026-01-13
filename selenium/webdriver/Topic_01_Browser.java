package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_01_Browser {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Chrome(){
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.quit();
    }


    @Test
    public void TC_02_Edge(){
        driver = new EdgeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.quit();
    }


    @Test
    public void TC_03_Firefox(){
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.quit();
    }

    @Test
    public void Redo_TC_01_Verify_URL(){
        driver.get("http://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='footer'] a[title='My Account'] "))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(), 'Login or Create an Account')]")));

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Create an Account']"))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Create an Account']")));

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

    }

    @Test
    public void Redo_TC_02_Verify_Title(){
        driver.get("http://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='footer'] a[title='My Account'] "))).click();

        Assert.assertFalse(explicitWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//title[text()='Customer Login']"))).isDisplayed());

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Create an Account']"))).click();

        Assert.assertFalse(explicitWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//title[text()='Create New Customer Account']"))).isDisplayed());
    }

    @Test
    public void Redo_TC_03_Navigate_Function(){
        driver.get("http://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='footer'] a[title='My Account'] "))).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Create an Account']"))).click();

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[text()='Create New Customer Account']")));

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

        driver.navigate().back();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(), 'Login or Create an Account')]")));

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        driver.navigate().forward();

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[text()='Create New Customer Account']")));

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

    }

    @Test
    public void Redo_TC_04_GetPageSource(){
        driver.get("http://live.techpanda.org/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='footer'] a[title='My Account'] "))).click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(), 'Login or Create an Account ')]"))).isDisplayed());

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Create an Account']"))).click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(), 'Create an Account')]"))).isDisplayed());
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}