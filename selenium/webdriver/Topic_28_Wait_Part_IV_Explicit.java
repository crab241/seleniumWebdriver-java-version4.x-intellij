package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_28_Wait_Part_IV_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFilePath = System.getProperty("user.dir") + "\\uploadFiles\\";

    String astronautFileName = "astronaut.jpg";
    String galaxyFileName = "galaxy.jpg";
    String moonFileName = "moon_background.jpg";

    String astronautFilePath = uploadFilePath + astronautFileName;
    String galaxyFilePath = uploadFilePath + galaxyFileName;
    String moonFilePath = uploadFilePath + moonFileName;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Can also use this way
        // explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button"))).click();


        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());
        // Can also use this to assert the loading icon
        // since the invisibility function also return a boolean data type
        // wait for Loading Icon invisible
        // 1 - Wait for the first step to complete, before preceed to the next one
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading"))));

        // Wait for the text to be visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
        // visibility return a WebElement data type
        // Hence we can shorten the 2 above code into a single line of code
        // 2 - After the first step began, wait until object of the next one appear then continue the next step right away
        // ( dont care if the first step is finish or not )
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4"))).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Ajax(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        // Wait for 30 seconds for the Date picker to display after load the page
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

        // Wait for text to display in 30 seconds
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display.")));

        // Wait for element to be clickable and then click it
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='18']"))).click();

        // Wait for loading icon disappear
        // div:not([style='display:none;'])>div.raDiv
        // //div[not(@style='display:none;')]/div[@class='raDiv']
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div:not([style='display:none;'])>div.raDiv"))));

        // Wait for the text is updated
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Sunday, January 18, 2026")));

    }

    @Test
    public void TC_03_Upload(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://gofile.io/");

        // Wait for all loading icons on the current page to disappear
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.animate-spin")))));

        // Wait for the input element to be presence, then send key to it
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")))
                .sendKeys(astronautFilePath + "\n" + galaxyFilePath + "\n" + moonFilePath);

        // Wait for all the progress bar to finish/ disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.file-progressbar"))));
        // Wait for all loading icons on the current page to disappear
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.animate-spin")))));

        // Get the URL
        String uploadURL = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.linkSuccessCard"))).getDomAttribute("href");

        driver.get(uploadURL);

        // Wait for all loading icons on the current page to disappear
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.animate-spin")))));

        // Wait for the uploaded files to be visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@class, 'item_open') and text()='" + astronautFileName + "']"))).isDisplayed();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class, 'item_open') and text()='" + galaxyFileName + "']"))).isDisplayed();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class, 'item_open') and text()='" + moonFileName + "']"))).isDisplayed();
    }

    @Test
    public void HW_TC_04_Only_Explicit_Invisible(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#start>button"))).click();

        // Wait for loading icon to disappear
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        // Wait and Verify text
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4"))).getText(), "Hello World!");
    }

    @Test
    public void HW_TC_05_OnlyExplicit_Invisible(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#start>button"))).click();

        // Wait for text to appear and verify it
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4"))).getText(), "Hello World!");
    }

    @Test
    public void HW_TC_06_Explicit(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        // Wait for date picker to appear
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

        // Verify text before picking date
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"))).getText(), "No Selected Dates to display.");

        // Pick a date
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td//a[text()='7']"))).click();

        // Wait for loading icon to disappear
        // Using CssSelector: div#ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1>div.raDiv
        // Another locator: div:not([style='display:none;'])>div[class='raDiv']
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div:not([style='display:none;'])>div[class='raDiv']"))));

        // Wait and verify text after selected date
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"))).getText(), "Wednesday, January 7, 2026");
    }

    @Test
    public void HW_TC_07_Explicit(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://gofile.io/?t=uploadFiles");

        // Wait for all the loading icons to disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.animate-spin"))));

        // Upload files
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']"))).
                sendKeys(astronautFilePath + "\n" + galaxyFilePath + "\n" + moonFilePath);

        // Wait for the all the progress bars and loading icons to finish and disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-container"))));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.animate-spin"))));

        // Get the Uploaded files link
        String uploadLink = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.linkSuccessCard"))).getDomProperty("href");

        // Navigate to the uploaded link
        driver.get(uploadLink);
        // or we can just click the link
        //explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.linkSuccessCard"))).click();

        // Wait for all the loading icons to disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.animate-spin"))));

        // Verify uploaded files
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class, 'item_open') and text()='" + astronautFileName + "']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class, 'item_open') and text()='" + galaxyFileName + "']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class, 'item_open') and text()='" + moonFileName + "']"))).isDisplayed());

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
