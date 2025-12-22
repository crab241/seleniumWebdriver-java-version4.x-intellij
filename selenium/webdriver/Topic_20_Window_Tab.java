package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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
        sleepInSecond(2);

        // Accept alert
        Alert alert = driver.switchTo().alert();
        alert.accept();
        sleepInSecond(2);

        // Or we can accept alert like this
        // driver.switchTo().alert().accept();

        // Verify clear message
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_03_Cambrigde(){
        driver.get("https://dictionary.cambridge.org/vi/");

        String windowName = driver.getWindowHandle();

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
        sleepInSecond(3);

        switchWindowByTitle("Login");
        driver.manage().window().maximize();

        // Click on Login button without entering credentials
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("span[id*='login-form-loginID']")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("span[id*='login-form-password']")).getText(), "This field is required");

        // Close login window
        driver.close();
        // Or we can use the close all Windows function
        closeAllWindowsWithoutParent(windowName);

        switchWindowByTitle("Cambridge Dictionary");

        String keyword = "Selenium";

        driver.findElement(By.cssSelector("input#searchword")).sendKeys(keyword);
        driver.findElement(By.cssSelector("input#searchword")).sendKeys(Keys.ENTER);
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='cald4-1']/following-sibling::div" +
                "//div[@class='di-title']/span/span")).getText(), keyword.toLowerCase());

    }

    @Test
    public void TC_04_Harvard(){
        driver.get("https://courses.dce.harvard.edu/");

        String homePageWindowID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[contains(string(), 'Student Login')]")).click();
        sleepInSecond(3);

        switchWindowByID(homePageWindowID);

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='DCE Login Portal']")).isDisplayed());

        // Close window
        closeAllWindowsWithoutParent(homePageWindowID);

        switchWindowByTitle("DCE Course Search");

        Assert.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());

        driver.findElement(By.cssSelector("button[class*='wait__button--cancel']")).click();
        sleepInSecond(2);

        // Enter Information
        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("Data Science: An Artificial Ecosystem");
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2025");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Full Term");
        driver.findElement(By.cssSelector("button#search-button")).click();
        sleepInSecond(2);

        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("span.result__headline span.result__title")).getText(), "Data Science: An Artificial Ecosystem");
        
    }

    @Test
    public void TC_05_Selenium_4x(){
        // Trang user vao dang ky tai khoan/ mua hang/ thanh toan
        driver.get("https://demo.nopcommerce.com/");

        // Qua trang admin de kich hoat tai khoan/ verify don hang/ ship hang
        // Driver se tu switch qua luon, k can phai dung ham switchWindow nua
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://admin-demo.nopcommerce.com/");

        switchWindowByTitle("Home page title");
        sleepInSecond(2);
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

        driver.manage().window().maximize();
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
