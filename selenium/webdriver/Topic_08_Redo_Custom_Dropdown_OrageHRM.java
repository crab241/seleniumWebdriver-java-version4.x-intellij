package webdriver;

import org.openqa.selenium.By;
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

public class Topic_08_Redo_Custom_Dropdown_OrageHRM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_OrangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[contains(string(), 'Login')]")).click();

        // Wait until all the Loading Icon dissappear first
        Assert.assertTrue(isLoadingIconDisappear());

        // Verify Dashboard page is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        // Go to PIM page
        driver.findElement(By.xpath("//a//span[text()='PIM']")).click();

        // Verify PIM page is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

        // Select item Automation Tester from Sob Title Dropdown
        selectItemInDropdown("Job Title", "Job Title", "Automaton Tester");

        // Select item Full-Time Contract from Eployment Status Dropdown
        selectItemInDropdown("Employment Status", "Employment Status", "Full-Time Contract");

        // Select item Past Employees Only from Include Dropdown
        selectItemInDropdown("Include", "Include", "Past Employees Only");

        // Select item Quality Assurance from Sub Unit Dropdown
        selectItemInDropdown("Sub Unit", "Sub Unit", "Quality Assurance");




    }

    private void selectItemInDropdown(String parentLocator, String childLocator, String requiredItem) throws InterruptedException {
        // Click on a Dropdown
        driver.findElement(By.xpath("//label[text()='" + parentLocator + "']//parent::div/following-sibling::div//div[@class='oxd-select-wrapper']")).click();

        // Wait for all the items in the Dropdown to be presence and store them into a list
        /* The right side is wait for all the items to be fully presence, but it also returns a list of WebElements,
        which is simalare to findElements */
        // The left side declare a list of WebElement and store the WebElements that the right side returns
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[text()='" + childLocator + "']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-option')]/span")));

        // Loop the whole list of Elements until the one that we required
        for (WebElement temp : allItems) {
            if ( temp.getText().equals(requiredItem)){
                // Click on the desired value of the items
                temp.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    private Boolean isLoadingIconDisappear() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("div.oxd-loading-spinner"))));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
