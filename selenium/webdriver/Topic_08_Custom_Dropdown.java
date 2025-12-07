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

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Job Title']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Automation Tester");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employment Status']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Full-Time Contract");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Include']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Past Employees Only");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Sub Unit']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Quality Assurance");

    }

    @Test
    public void TC_02_JQuery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInCustomDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li", "Slow");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(), "Slow");

        selectItemInCustomDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li", "Fast");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(), "Fast");

        selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "4");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "4");

        selectItemInCustomDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li", "Dr.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text']")).getText(), "Dr.");
    }

    @Test
    public void TC_03_ReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInCustomDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Matt");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");

        selectItemInCustomDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Christian");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");

        selectItemInCustomDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Elliot Fu");

        selectItemInCustomDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Justen Kitsune");
    }

    @Test
    public void TC_04_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");

        selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");

        selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
    }

    @Test
    public void TC_05_Editable_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", "Algeria");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Algeria");

        selectItemInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", "Bangladesh");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Bangladesh");
    }

    @Test
    public void TC_06_FinPeace() throws InterruptedException {
        driver.get("https://sps.finpeace.vn/tools/sktccn");

        selectItemInEditableDropdown("//input[@id='job_id']", "//div[@id='job_id_list']/following-sibling::div//div[@class='ant-select-item-option-content']", "Công nghệ thông tin");
        // Can use //label[@title='Nghề nghiệp']/parent::div/following-sibling::div//span[@class='ant-select-selection-item'] as another locator
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='job_id']/parent::span/following-sibling::span")).getText(), "Công nghệ thông tin");

        selectItemInEditableDropdown("//input[@id='gender']", "//div[@id='gender_list']/following-sibling::div//div[@class='ant-select-item-option-content']", "Nam");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='gender']/parent::span/following-sibling::span")).getText(), "Nam");


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

    private void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedTextItem) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).click();

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        for (WebElement temp : allItems) {
            String textItem = temp.getText();

            if ( textItem.equals(expectedTextItem)) {
                temp.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    private void selectItemInEditableDropdown(String editableParentXpath, String editabaleChildXpath, String expectedTextItem) throws InterruptedException {
        driver.findElement(By.xpath(editableParentXpath)).clear();
        driver.findElement(By.xpath(editableParentXpath)).sendKeys(expectedTextItem);

        Thread.sleep(2000);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(editabaleChildXpath)));

        for (WebElement temp : allItems) {
            String textItem = temp.getText();

            if ( textItem.equals(expectedTextItem)) {
                temp.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    private Boolean isLoadingIconDisappear() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
