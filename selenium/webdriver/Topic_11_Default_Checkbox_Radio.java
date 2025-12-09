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

    @Test
    public void HW_TC_02_Default_Checkbox_Radio(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By dualzoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Check if dual zone checkbox being select or not
        // If not selected, select it
        if (!driver.findElement(dualzoneCheckbox).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("demo-runner")));
            driver.findElement(dualzoneCheckbox).click();
        }

        // Verify dualzone checkbox is selected
        Assert.assertTrue(driver.findElement(dualzoneCheckbox).isSelected());

        // Check if dualzone checkbox is selected or not
        // If selected, de-select it
        if (driver.findElement(dualzoneCheckbox).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("demo-runner")));
            driver.findElement(dualzoneCheckbox).click();
        }

        // Verify dualzone checkbox is de-selected
        Assert.assertFalse(driver.findElement(dualzoneCheckbox).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By two_petrol_radio_checkbox = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        // Check if checkbox with value of "2.0 Petrol, 147kW' is selected or not
        // If not selected, select it
        if (!driver.findElement(two_petrol_radio_checkbox).isSelected()) {
            ((JavascriptExecutor) driver).executeScript(("arguments[0].scrollIntoView(true);"), driver.findElement(By.id("demo-runner")));
            driver.findElement(two_petrol_radio_checkbox).click();
        }

        // Verify checkbox is selected
        Assert.assertTrue(driver.findElement(two_petrol_radio_checkbox).isSelected());
    }

    @Test
    public void HW_TC_03_Default_Checkbox_Radio(){
        driver.get("https://material.angular.io/components/radio/examples");

        By summerRadio = By.xpath("//label[text()='Summer']/preceding-sibling::div/input");

        // Check if the summer radio is selected or not
        // If not selected yet, select it
        if (!driver.findElement(summerRadio).isSelected()) {
            ((JavascriptExecutor) driver).executeScript(("arguments[0].scrollIntoView(true);"), driver.findElement(By.xpath("//label[text()='Pick your favorite season']//ancestor::div[@class='docs-example-viewer-body']")));
            driver.findElement(summerRadio).click();
        }

        // Verify whether the summer radio is selected or not
        Assert.assertTrue(driver.findElement(summerRadio).isSelected());

        // Navigate to a different site
        driver.get("https://material.angular.dev/components/checkbox/examples");

        By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
        By indeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");

        if (!driver.findElement(checkedCheckbox).isSelected() && !driver.findElement(indeterminateCheckbox).isSelected()) {
            // No need for this below step
            //((JavascriptExecutor) driver).executeScript(("arguments[0].scrollIntoView(true);"), driver.findElement(By.xpath("//h2[text()='Checkbox configuration']//following-sibling::section[1][@class='example-section']")));
            driver.findElement(checkedCheckbox).click();
            driver.findElement(indeterminateCheckbox).click();
        }

        // Verify 2 checkboxs are selected
        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

        // De-selected
        driver.findElement(checkedCheckbox).click();
        driver.findElement(indeterminateCheckbox).click();

        // Verify that both the checkbox are de-selected
        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
    }

    @Test
    public void HW_TC_04_SelectAll(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        ((JavascriptExecutor) driver).executeScript(("arguments[0].scrollIntoView(true);"), driver.findElement(By.xpath("//label[contains(text(), ' Have you ever had (Please check all that apply) ')]/parent::li")));

        List<WebElement> allCheckboxItem = driver.findElements(By.xpath("//label[contains(text(), ' Have you ever had (Please check all that apply) ')]/following-sibling::div//span[@class='form-checkbox-item']/input"));

        for (WebElement item : allCheckboxItem) {
            if (!item.isSelected()) {
                item.click();
            }
        }

        // Verify every checkbox are selected
        for (WebElement item : allCheckboxItem) {
            Assert.assertTrue(item.isSelected());
        }

        // De-select every checkbox
        for (WebElement item : allCheckboxItem) {
            if (item.isSelected()) {
                item.click();
            }
        }

        // Select only checkbox Heart Attack
        for (WebElement item : allCheckboxItem) {
            if (!item.isSelected() && item.getDomAttribute("value").equals("Heart Attack")) {
                item.click();
                break;
            }
        }

        // Verify only checkbox Heart attack is selected
        for (WebElement item : allCheckboxItem) {
            if (item.isSelected()) {
                Assert.assertTrue(item.getDomAttribute("value").equals("Heart Attack"));
            } else {
                Assert.assertFalse(item.getDomAttribute("value").equals("Heart Attack"));
            }
        }

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
