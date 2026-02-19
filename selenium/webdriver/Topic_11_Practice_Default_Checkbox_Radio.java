package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import java.util.List;

public class Topic_11_Practice_Default_Checkbox_Radio {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_02_telerik(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By dualZoneCheckboxLocator = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // scroll dualzone checkbox into view, then select it
        WebElement dualzoneCheckbox = waitElementVisible(dualZoneCheckboxLocator);
        if(!dualzoneCheckbox.isSelected()){
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#demo-runner")));
            dualzoneCheckbox.click();
        }

        // Verify checkbox is selected
        Assert.assertTrue(dualzoneCheckbox.isSelected());

        // de-selec the checkbox
        dualzoneCheckbox.click();

        // verify checkbox is unselected
        Assert.assertFalse(dualzoneCheckbox.isSelected());

        // navigate to radio button page
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        // scroll element into view and click it
        WebElement petrol2kElement = waitElementVisible(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input"));
        if (!petrol2kElement.isSelected()) {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#demo-runner")));
            petrol2kElement.click();
        }

        // Verify radio button is selected
        Assert.assertTrue(petrol2kElement.isSelected());
    }

    // This is more like custom than default one
    @Test
    public void TC_03_Material(){
        driver.get("https://material.angular.dev/components/radio/examples");

        // CLick radio
        waitElementVisible(By.xpath("//label[text()='Summer']")).click();

        // Verify radio checkbox is selected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Summer']/preceding-sibling::div/input")).isSelected());

        driver.get("https://material.angular.dev/components/checkbox/examples");

        // Click checkbox button
        waitElementVisible(By.xpath("//label[text()='Checked']")).click();
        waitElementVisible(By.xpath("//label[text()='Indeterminate']")).click();

        // Verify checkbox is selected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());

    }

    @Test
    public void TC_04_Select_All(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        // scroll into view
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", waitElementVisible(By.xpath("//label[contains(text(), 'Have you ever had')]/parent::li")));

        // select all checkbox
        List<WebElement> checkboxItem = driver.findElements(By.cssSelector("span.form-checkbox-item input"));

        for (WebElement item : checkboxItem) {
            item.click();
        }

        // Verify every checkbox is selected
        for (WebElement item : checkboxItem) {
            Assert.assertTrue(item.isSelected());
        }

        // Only select checkbox with value Heart Attack
        for (WebElement item : checkboxItem) {
            if (!item.getDomProperty("value").equals("Heart Attack")){
                item.click();
            }
        }

        // Verify only Heart Attack checkbox is selected
        for (WebElement item : checkboxItem) {
            if (item.getDomProperty("value").equals("Heart Attack")){
                Assert.assertTrue(item.isSelected());
            } else {
                Assert.assertFalse(item.isSelected());
            }
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebElement waitElementVisible(By locator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
