package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Default_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By dualzoneCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::span/input");
        By leathertrimCheckbox = By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input");
        By towbarCheckbox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input");

        // Click select
        if (!driver.findElement(dualzoneCheckbox).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("demo-runner")));
            driver.findElement(dualzoneCheckbox).click();
        }

        // Check
        Assert.assertTrue(driver.findElement(dualzoneCheckbox).isSelected());

        // Disabled + Selected
        Assert.assertFalse(driver.findElement(leathertrimCheckbox).isEnabled());
        Assert.assertTrue(driver.findElement(leathertrimCheckbox).isSelected());

        // Disabled + De-selected
        Assert.assertFalse(driver.findElement(towbarCheckbox).isEnabled());
        Assert.assertFalse(driver.findElement(towbarCheckbox).isSelected());

        // Click un-selected
        if (driver.findElement(dualzoneCheckbox).isSelected()) {
            driver.findElement(dualzoneCheckbox).click();
        }

        Assert.assertFalse(driver.findElement(dualzoneCheckbox).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By petrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        // Click select
        if (!driver.findElement(petrolRadio).isSelected()) {
            driver.findElement(petrolRadio).click();
        }

        Assert.assertTrue(driver.findElement(petrolRadio).isSelected());

        // Click to un-select
        if (driver.findElement(petrolRadio).isSelected()) {
            driver.findElement(petrolRadio).click();
        }

        Assert.assertTrue(driver.findElement(petrolRadio).isSelected());
    }

    @Test
    public void TC_02(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> everhadCheckboxes = driver.findElements(By.xpath("//label[contains(text(), ' Have you ever had (Please check all that apply) ')]/following-sibling::div//input[@type='checkbox']"));

        // Select all
        for (WebElement temp : everhadCheckboxes) {
            if (!temp.isSelected()) {
                temp.click();
            }
        }

        // Verify all
        for (WebElement temp : everhadCheckboxes) {
            Assert.assertTrue(temp.isSelected());
        }

        // De-select all
        for (WebElement temp : everhadCheckboxes) {
            if (temp.isSelected()) {
                temp.click();
            }
        }

        // Verify
        for (WebElement temp : everhadCheckboxes) {
            Assert.assertFalse(temp.isSelected());
        }

        // Select specific checkbox
        for (WebElement temp : everhadCheckboxes) {
            if (!temp.isSelected() && temp.getDomAttribute("value").equals("Gallstones")) {
                temp.click();
                break;
                // Nếu k break sẽ tiếp tục kieerm tra tiếp
                // Nếu break thì khi thâấy điều kiện sẽ dừng lại
            }
        }

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
