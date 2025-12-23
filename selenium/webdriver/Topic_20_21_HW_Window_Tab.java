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

    @Test
    public void TC_14_Techpanda(){
        driver.get("http://live.techpanda.org/");

        // Go to Mobile
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        // Click Add to Compare on Sony Xperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2//" +
                "following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);

        // Verify Add to Compare successful
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");


        // Click Add to Compare on Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2//" +
                "following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();

        // Verify Add to Compare successful
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
        sleepInSecond(2);

        // Click Compare
        driver.findElement(By.xpath("//span[text()='Compare']")).click();

        switchWindowByTitle("Products Comparison List");
        sleepInSecond(2);

        // Verify swtiched window title
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

        // Close and swtich back to parent tab
        driver.close();
        switchWindowByTitle("Mobile");

        // Click on Clear all
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        sleepInSecond(1);

        // Accept alert
        driver.switchTo().alert().accept();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_15_Cambridge(){
        driver.get("https://dictionary.cambridge.org/vi/");

        // Get current/ homepage window id
        String homepageWindowID = driver.getWindowHandle();

        // CLick on Login
        driver.findElement(By.xpath("//div[@id='topNavBar']//span[text()='Đăng nhập']")).click();
        sleepInSecond(3);

        // Switch to Login tab
        swtichWindowByID(homepageWindowID);
        driver.manage().window().maximize();

        // Click on Login button
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        sleepInSecond(2);

        // Verify error messages
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Email *']/following-sibling::span")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password *']/following-sibling::span")).getText(), "This field is required");

        // Close window and return to parent window
        driver.close();
        switchWindowByTitle("Cambridge Dictionary");

        String keyword = "Selenium";

        driver.findElement(By.cssSelector("input#searchword")).sendKeys(keyword);
        driver.findElement(By.cssSelector("input#searchword")).sendKeys(Keys.ENTER);
        sleepInSecond(2);

        // Verify message
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='cald4-1']/following-sibling::div//div[@class='di-title']/span/span")).getText(), keyword.toLowerCase());

    }

    @Test
    public void TC_16_Harvard(){
        driver.get("https://courses.dce.harvard.edu/");

        // Get current window ID
        String homepageWindowID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        sleepInSecond(2);

        // Switch to Login window
        swtichWindowByID(homepageWindowID);
        driver.manage().window().maximize();

        // Verify Window
        Assert.assertEquals(driver.findElement(By.xpath("//img[@id='prompt-logo-center']/following-sibling::h1")).getText(), "DCE Login Portal");

        // Close window and switch back to parent page
        driver.close();
        switchWindowByTitle("DCE Course Search");

        // Verify Authentication popup is display
        Assert.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());
        sleepInSecond(1);

        // Dismiss authentication
        // It's not a popup or an alert, just a regular div
        driver.findElement(By.xpath("//div[@id='sam-wait']//button[text()='Cancel']")).click();
        sleepInSecond(1);


        // Enter information
        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("Data Science: An Artificial Ecosystem");
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2025");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Full Term");

        driver.findElement(By.cssSelector("button#search-button")).click();
        sleepInSecond(1);

        // Verify result
        Assert.assertEquals(driver.findElement(By.cssSelector("span.result__title")).getText(), "Data Science: An Artificial Ecosystem");

    }

    private void switchWindowByTitle(String expectedWindowTitle){
        // Store all the window IDs into a Set String
        Set<String> allWindowID = driver.getWindowHandles();

        // Loop through each window
        // If a window has a matched title -> break from the loop
        for (String id : allWindowID) {
            //System.out.println("Curent ID: " + id);
            driver.switchTo().window(id);
            sleepInSecond(1);

            if (driver.getTitle().contains(expectedWindowTitle)) {
                break;
            }
        }

        sleepInSecond(2);
    }

    private void swtichWindowByID(String windowID){
        // Store all the window IDs into a Set of Strings
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Loop through all the IDs
        // If any ID differ than the windowID, switch window to it and break
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }

        sleepInSecond(2);
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
