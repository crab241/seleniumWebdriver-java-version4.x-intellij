package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class Topic_27_Wait_Part_III_Explicit_Function {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

    }

    @Test
    public void TC_01() {
        // Wait until satisfie condition: alert is present
        explicitWait.until(ExpectedConditions.alertIsPresent());

        // Element visible ( for 1 or for many / what is(are) the paremeter(s) )
        // visibilityOfElementLocated already return a WebElement data type,
        // so we can store it like this
        WebElement emailTextBox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#example")));
        emailTextBox.sendKeys("Automation FC");
        // These two functions are the same, just one is obsolete/ deprecated ( getAttribute )
        emailTextBox.getDomAttribute("");
        emailTextBox.getAttribute("");

        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#example"))));
        // This findElement step is somewhat repeat step since the visibilityOf function already findElement
        driver.findElement(By.cssSelector("input#example")).sendKeys("Automation FC");

        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElement(By.cssSelector("input#email")),
                driver.findElement(By.cssSelector("input#email")),
                driver.findElement(By.cssSelector("input#name"))
        ));

        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElements(By.cssSelector("input[type='text']"))
        ));

        // ELement invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input#example")));

        // Element presence
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#example")));

        // Element stateless
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("input#example"))));

        // Element clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#example")));

        // Element selected
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("input#example")));

        // Element has quantity = ? ( less/ equal/ more )
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("input#example"), 5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("input#example"), 5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("input#example"), 5));

        // Combine 2 conditions ( AND/ OR/ NOT )
        // both need to be correct
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

        // Only 1 of the conditions need to be true
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

        // Phu dinh
        // = invisibility
        explicitWait.until(ExpectedConditions.not(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input#example")));

        // URL/ Title/ Text
        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlContains(""));
        explicitWait.until(ExpectedConditions.urlMatches(""));

        explicitWait.until(ExpectedConditions.titleIs(""));
        explicitWait.until(ExpectedConditions.titleContains(""));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "Automation"));
        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("")), "Automation"));
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("")));

        // Attribute/ DOM Property/ Frame
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "class", "email"));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""), "class", "email"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")), "textContent", "Hello World!"));
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")), "id", "finish"));

        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("facebook"));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));


    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
