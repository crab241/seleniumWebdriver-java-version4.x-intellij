package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Redo_Handle_Button {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fahasa(){
        driver.get("https://www.fahasa.com/customer/account/create");

        // Navigate to Dang nhap tab
        waitElementVisible(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");
        // Verify Dang nhap button is disabled
        Assert.assertEquals(waitElementVisible(loginButton).getDomProperty("disabled"), "true");
        // another way to verify this, a more simple way
        Assert.assertFalse(waitElementVisible(loginButton).isEnabled());

        // Verify background color
        Assert.assertEquals(waitElementVisible(loginButton).getCssValue("background-color"), "rgba(0, 0, 0, 0)");

        // Enter valid credentials
        waitElementVisible(By.cssSelector("input#login_username")).sendKeys("0987654321");
        waitElementVisible(By.cssSelector("input#login_password")).sendKeys("Memaybeovl@123");

        // Verify Dang nhap button is enable after entered valid credentials
        Assert.assertTrue(waitElementVisible(loginButton).isEnabled());

        // Verify Dang nhap button background color is red
        Assert.assertEquals(driver.findElement(loginButton).getCssValue("background-color"), "rgb(201, 33, 39)");
    }

    @Test
    public void TC_01_HexColor_Fahasa(){
        driver.get("https://www.fahasa.com/customer/account/create");

        // Navigate to Login tab
        waitElementVisible(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");
        // Verify Login button is inactive
        Assert.assertFalse(waitElementVisible(loginButton).isEnabled());

        // Verify login button color is grey when inactive
        String loginButtonColor = Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex();
        Assert.assertEquals(loginButtonColor, "#000000");

        // enter valid credentials
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654321");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("Memaybeovl@123");

        // Verify login button is now available
        Assert.assertTrue(waitElementVisible(loginButton).isEnabled());

        // Verify login button color is now red
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebElement waitElementVisible(By locator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
