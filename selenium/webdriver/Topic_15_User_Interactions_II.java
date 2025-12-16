package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_User_Interactions_II {
    WebDriver driver;
    Actions actions;
    String osName = System.getProperty("os.name");
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
       /* FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);*/

        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        actions = new Actions(driver);
    }

    @Test
    public void TC_01_Click_And_Hold_Block() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        //Thread.sleep(3000);
        List<WebElement> allItems = driver.findElements(By.cssSelector("ol#selectable>li"));

        // Làm từ từ thì làm như này
        /*actions.clickAndHold(allItems.getFirst()).pause(Duration.ofSeconds(3)).perform();
        actions.moveToElement(allItems.get(11)).pause(Duration.ofSeconds(3)).perform();
        actions.release().perform();
        actions.pause(Duration.ofSeconds(3)).perform();*/

        // Select from 1 to 12
        actions.clickAndHold(allItems.getFirst()).moveToElement(allItems.get(11)).release().perform();

        // Verify selected
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 12);
    }

    @Test
    public void TC_02_Click_And_Hold_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allItems = driver.findElements(By.cssSelector("ol#selectable>li"));

        // Verify list size
        Assert.assertEquals(allItems.size(), 30);

        Keys key = null; // key for keycap
        if (osName.contains("Windows")){
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }

        // Click Ctrl down ( chua nha ra )
        actions.keyDown(key).perform();

        // Select  3 5 7 9 13 17 21 25 29
        actions.click(allItems.get(2)).click(allItems.get(4)).click(allItems.get(6)).click(allItems.get(8)).click(allItems.get(12)).click(allItems.get(16)).click(allItems.get(20)).click(allItems.get(24)).click(allItems.get(28)).perform();

        // Release Ctrl
        actions.keyUp(key).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        // Verify
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 9);
    }

    @Test
    public void TC_03_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        System.out.println(driver.toString());
        if (driver.toString().contains("Firefox")) {
            // Scroll to the button
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
            actions.pause(Duration.ofSeconds(3)).perform();
        }

        actions.pause(Duration.ofSeconds(3)).perform();

        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_04_Right_click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        // Verify that Quit menu is not display yet
        // Before click on "right click me"
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
        //actions.pause(Duration.ofSeconds(3)).perform();

        // Click on "right click me"
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        // Verify that Quit menu display
        // After click on "rght click me"
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        // Hover on Quit menu
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        // Quit menu have a hover state when hover on
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
        //actions.pause(Duration.ofSeconds(3)).perform();

        // Click on quit
        driver.findElement(By.cssSelector("li.context-menu-icon-quit")).click();
        //Thread.sleep(2000);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent()).accept();

        // Required sleep before moving on to Assert step
        Thread.sleep(2000);

        // Return to no display
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }

    @Test
    public void HW_TC_04_ClickAndHold_SelectMultipleItem(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Put all the number elements into a list
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        //Click and Hold from 1 -> 4
        actions.clickAndHold(allNumbers.getFirst()).moveToElement(allNumbers.get(3)).release().perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        // Verify that number 1 to 4 are selected
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 4);

    }

    @Test
    public void HW_TC_05_ClickAndSelect_RandomItem(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        Keys key = null;
        if (osName.contains("Windows")){
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }

        // Push down Ctrl key for multiple item select
        actions.keyDown(key).perform();

        // Click and Select random item 1 3 6 11
        actions.click(allNumbers.getFirst()).click(allNumbers.get(2)).click(allNumbers.get(5)).click(allNumbers.get(10)).perform();

        // Release Ctrl key
        actions.keyUp(key).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        // Verify numbers that are selected
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 4);
    }

    @Test
    public void HW_TC_06_DoubleClick(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.toString().contains("Firefox")) {
            // scroll to Double click button element
            jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//button[text()='Double click me']")));
        }

        actions.pause(Duration.ofSeconds(2)).perform();

        // Double click on double click button
        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        // Verify text after successfully click on button
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");

    }

    @Test
    public void HW_TC_07_RightClick() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        // Check that Quit menu not display before click on button
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        // Right click on button
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        // Verify Quit menu is displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        // Hover on Quit menu
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();

        // Verify Quit menu have been hover on
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        // Click on Quit menu
        driver.findElement(By.cssSelector("li.context-menu-icon-quit")).click();

        // Wailt until alert is displayed, then accept it immediately
        // alertisPresent retuns Alert type
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent()).accept();

        // Optional sleep for better check look
        Thread.sleep(2000);

        // Check to see Quit menu no longer display
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
