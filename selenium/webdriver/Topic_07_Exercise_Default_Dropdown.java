package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_07_Exercise_Default_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    String firstName = "John";
    String lastName = "Wick";
    String emailRandom = "johnWick" + new Random().nextInt(9999) + "@gmail.com";

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_04_Handle_Dropdown_list(){
        driver.get("https://rode.com/en-int/support/where-to-buy");

        // Check that dropdown doesn't support multi
        Assert.assertFalse(new Select(waitElementVisible_Css("select#country")).isMultiple());

        new Select(waitElementVisible_Css("select#country")).selectByVisibleText("Vietnam");
        waitElementVisible_Css("input#map_search_query").sendKeys("HO CHI MINH");
        waitElementVisible_Xpath("//button[text()='Search']").click();

        // Verify there is 16 dealers in Ho Chi Minh city, Viet Nam
        List<WebElement> dealers = waitMultipleElementVisible_Xpath("//h3[text()='Dealers']/following-sibling::div/div/div/div/h4");
        Assert.assertEquals(dealers.size(), 16);

        for (WebElement dealer : dealers) {
            System.out.println(dealer.getText());
        }

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebElement waitElementVisible_Css(String cssLocator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssLocator)));
    }

    private WebElement waitElementVisible_Xpath(String xpathLocator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
    }

    private List<WebElement> waitMultipleElementVisible_Xpath(String xpathLocator){
        return explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(xpathLocator))));
    }

}
