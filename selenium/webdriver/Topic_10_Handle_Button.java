package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Handle_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Fahasa(){
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");

        // Verify button is disabled
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        String loginButtonColorRGBA = driver.findElement(loginButton).getCssValue("background-color");
        Color loginButtonColor = Color.fromString(loginButtonColorRGBA);
        String loginButtonColorHexa = loginButtonColor.asHex().toUpperCase();

        // Verify button color when being disabled
        Assert.assertEquals(loginButtonColorHexa, "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654321");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("Memaybeo@123");

        // Verify if button is enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        // Verify button color when being enabled
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
    }

    @Test
    public void TC_01_Fahasa_redo(){
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");

        // Convert background color from rgba value to hex value
        String loginButtonColorRGBA = driver.findElement(loginButton).getCssValue("background-color");
        Color loginButtonColor = Color.fromString(loginButtonColorRGBA);
        String loginButtonColorHexa = loginButtonColor.asHex();

        // Verify button when being disabled
        Assert.assertEquals(loginButtonColorHexa.toUpperCase(), "#000000");
        // Or can be write as this
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654321");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("Meomaybe@123");

        // Verify button when being enabled
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
