package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_12_Practique_Custom_Checkbox_Radio {
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
    public void TC_05_Ubuntu(){
        driver.get("https://login.ubuntu.com/");

        By noAccountLocatorRadioLocator = By.xpath("//label[contains(string(), 'I don’t have an Ubuntu One account')]");
        waitElementVisible(noAccountLocatorRadioLocator).click();

        By acceptTermsLocator = By.xpath("//label[contains(string(), 'I have read and accept the')]");
        waitElementVisible(acceptTermsLocator).click();
    }

    @Test
    public void TC_06_GGForm(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By CanThoRadioLocator = By.xpath("//div[@data-value='Cần Thơ']");
        // Verify Can Tho radio is not selected
        Assert.assertEquals(waitElementVisible(CanThoRadioLocator).getDomAttribute("aria-checked"), "false");

        // Select Can Tho radio
        driver.findElement(CanThoRadioLocator).click();

        // Verify selection
        Assert.assertEquals(driver.findElement(CanThoRadioLocator).getDomAttribute("aria-checked"), "true");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebElement waitElementVisible(By locator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
