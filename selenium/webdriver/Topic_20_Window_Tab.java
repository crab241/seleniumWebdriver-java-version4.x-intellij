package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_20_Window_Tab {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        actions = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Github() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Lay ra ID cua tab hien tai dang dung
        String githubWindowID = driver.getWindowHandle();
        System.out.println(githubWindowID);

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);

        switchWindowByID(githubWindowID);

        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("AutomationFC");
        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(Keys.ENTER);
        sleepInSecond(3);

        String googleWindowID = driver.getWindowHandle();

        switchWindowByID(googleWindowID);

        sleepInSecond(3);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);

        switchWindowByTitle("Facebook");

        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("input[name='pass']")).sendKeys("test@gmail.com");

        switchWindowByTitle("Selenium WebDriver");
        sleepInSecond(2);

        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
        sleepInSecond(3);

    }

    @Test
    public void TC_02_Techpanda(){
        driver.get("http://live.techpanda.org/");

        // Click/ Go to Mobile
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        // Add Sony Xperia product to compare
        driver.findElement(By.xpath("//h2[contains(string(), 'Sony Xperia')]//following-sibling::div[@class='actions']" +
                "//a[text()='Add to Compare']")).click();
        sleepInSecond(2);

        // Verify product successfully added
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        // Add Samsung Galaxy product add to compare
        driver.findElement(By.xpath("//h2[contains(string(), 'Samsung Galaxy')]//following-sibling::div[@class='actions']" +
                "//a[text()='Add to Compare']")).click();
        sleepInSecond(2);

        // Verify product added
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        // Click on Compare to open Compare Window Tab
        driver.findElement(By.xpath("//button//span[text()='Compare']")).click();

        // Switch to Compare Window
        switchWindowByTitle("Product Comparison List");

        // Verify title of Compare window
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

        // Close Compare Window
        driver.close();

        // Switch back to initial window/ tab
        switchWindowByTitle("Mobile");

        // Click on Clear All
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        // Accept alert
        Alert alert = driver.switchTo().alert();
        alert.accept();
        sleepInSecond(2);

        // Verify clear message
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
    }

    private void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void switchWindowByID(String windowID) {
        // Lay ra ID cua tat ca cac tab dang co
        Set<String> allWindowIDs = driver.getWindowHandles();
        // 2ID
        // Biet truoc 1 cai roi

        // Use loop to go through every windows
        for (String id : allWindowIDs) {
            // If any window ID differ than the first window's ID
            if (!id.equals(windowID)) {
                // Switch to it
                driver.switchTo().window(id);
                break;
            }
        }
    }

    // Cover luôn cho cả cách làm của switch ID
    private void switchWindowByTitle(String expectedTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            sleepInSecond(1);

            if (driver.getTitle().contains(expectedTitle)){
                break;
            }
        }
    }

    private void closeAllWindowsWithoutParent(String windowID){
        Set<String> allWindows = driver.getWindowHandles();

        for (String id : allWindows) {
            if (!id.equals(windowID)){
                driver.switchTo().window(id);
                // Đóng Tab/Window của driver and Active
                driver.close();
            }
        }

        driver.switchTo().window(windowID);
    }

    @AfterClass
    public void afterClass(){
        // Close browser
        driver.quit();
    }
}
