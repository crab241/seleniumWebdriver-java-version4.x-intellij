package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_20_21_HW_Window_Tab {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver;

        actions = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_13(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Get parent window ID
        String parentWindowID = driver.getWindowHandle();

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='4']")));
        sleepInSecond(3);

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(2);

        // Switch to Google tab and Verify title
        switchWindowByTitle("Google");
        sleepInSecond(1);
        Assert.assertEquals(driver.getTitle(), "Google");
        switchWindowByTitle("Selenium WebDriver");
        sleepInSecond(1);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(2);

        // Switch to Facebook tab and Verify title
        switchWindowByTitle("Facebook");
        sleepInSecond(1);
        Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
        switchWindowByTitle("Selenium WebDriver");
        sleepInSecond(1);

        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        sleepInSecond(2);

        // Switch to Tiki tab and Verify title
        switchWindowByTitle("Tiki");
        sleepInSecond(2);
        Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        switchWindowByTitle("Selenium WebDriver");
        sleepInSecond(1);

        closeAllWindowsExceptParent(parentWindowID);

    }

    private void switchWindowByTitle(String expectedWindowTitle){
        // Store all the window IDs into a Set String
        Set<String> allWindowID = driver.getWindowHandles();

        // Loop through each window
        // If a window has a matched title -> break from the loop
        for (String id : allWindowID) {
            //System.out.println("Curent ID: " + id);
            driver.switchTo().window(id);
            sleepInSecond(2);

            if (driver.getTitle().contains(expectedWindowTitle)) {
                break;
            }
        }
    }

    private void closeAllWindowsExceptParent(String parentWindowID){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {

            if (!id.equals(parentWindowID)){
                driver.switchTo().window(id);
                driver.close();
                sleepInSecond(1);
            }
        }

        driver.switchTo().window(parentWindowID);
    }


    private void sleepInSecond(long timeWait) {
        try {
            Thread.sleep(timeWait * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
