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

public class Topic_08_Custom_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_OrangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        // could also use cssSelector with "button.orangehrm-login-button"
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner")))));

        // Verify Dashboard page is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        // Click PIM page displayed
        driver.findElement(By.xpath("//a//span[text()='PIM']")).click();

        // Check PIM page displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

        /*Chức nawng của Dropdown với hành vi tương ứng
        Chọn item là CTO trong Job Title Dropdown
        * 1 - Click vào element để cho Dropdown xoor ra ( Parent locator )
        * 2 - Chờ cho các item load ra hết ( Child locator )
        * 3 - Kiểm tra tất cả các item xem đâu là cái cần chọn
        * 4 - Nếu tìm thấy thì click vào item đó
        * 5 - Click vào r thì dropdown tự động close đi
        *  */

        // Select item CTO in Job Title Dropdown
        selectItemInOrangeHRMDropdown("Job Title", "Job Title", "Chief Technical Officer");


        // Redo the process the same for Employment Status
        // Select item Fulltime Contract in Employement Status dropdown
        selectItemInOrangeHRMDropdown("Employment Status", "Employment Status", "Full-Time Contract");


        // Redo the process the same for Include
        // Select item Past Employees Only in Include dropdown
        selectItemInOrangeHRMDropdown("Include", "Include", "Past Employees Only");
        

        // Redo the process the same for Sub Unit
        // Select item Administration in Sub Unit
        selectItemInOrangeHRMDropdown("Sub Unit", "Sub Unit", "Administration");


    }

    private void selectItemInOrangeHRMDropdown(String parentLocator, String childLocator, String expectedTextItem) throws InterruptedException {
        // 1 - Click vào element để cho Dropdown xoor ra ( Parent locator )
        driver.findElement(By.xpath("//label[text()='" + parentLocator + "']/parent::div//following-sibling::div//div[@class='oxd-select-wrapper']")).click();

        // 2 - Chờ cho các item load ra hết ( Child locator )
        // Môt locator đại diện cho tất cả các locator bên trong
        /* Hàm presenceofALl vừa đợi vừa tìm các element luôn, và nó trả về List Element, nghĩa là thay thế được cho driver.findElements
        * nên we can store kết quả nó trả veef thành List Element luôn, đỡ phải khai báo lại như ở cmt bước tiếp theo*/
        // Store all the Elements into a List Element
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[text()='" + childLocator + "']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-option')]/span")));

        // Find all the items on the Dropdown
        // This step can be replaced with the above/ previous code for optimization

        //List<WebElement> allItems = driver.findElements(By.xpath("//label[text()='Job Title']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"))''
        // Use for loop to check for all elements

        // For loop to check all the Items
        for (WebElement temp : allItems) {
            // Get text of each element
            String textItem = temp.getText();

            // Check to see which text is the one that we want
            // 3 - Kiểm tra tất cả các item xem đâu là cái cần chọn
            if ( textItem.equals(expectedTextItem)) {
                // Nếu tìm thấy thì click vào item đó
                temp.click();
                Thread.sleep(2000);

                // 5 - Click vào r thì dropdown tự động close đi
                break;
            }
        }
    }

    private Boolean isLoadingIconDisappear() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    @Test
    public void TC_02(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
