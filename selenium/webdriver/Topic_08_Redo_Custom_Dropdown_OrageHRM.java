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
    public void TC_01_JQuery() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInJQueryCustomDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li", "Medium");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(), "Medium");

        selectItemInJQueryCustomDropdown("//span[@id='files-button']", "//ul[@id='files-menu']/li", "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(), "ui.jQuery.js");

        selectItemInJQueryCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "6");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "6");

        selectItemInJQueryCustomDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li", "Mr.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text']")).getText(), "Mr.");

    }

    @Test
    public void TC_02_ReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInReactJSCustomDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']//div[@class='divider text']")).getText(), "Elliot Fu");

        selectItemInReactJSCustomDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div", "Jenny Hess");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']//div[@class='divider text']")).getText(), "Jenny Hess");

        selectItemInReactJSCustomDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div", "Christian");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']//div[@class='divider text']")).getText(), "Christian");

        selectItemInReactJSCustomDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div", "Matt");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']//div[@class='divider text']")).getText(), "Matt");
    }

    @Test
    public void TC_03_Vue() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInVueJSDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "First Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");

        selectItemInVueJSDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");

        selectItemInVueJSDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Third Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
    }

    @Test
    public void TC_04_EditableDropdown() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", "Australia");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Australia");

        selectItemInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", "Armenia");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Armenia");

        selectItemInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", "Bahamas");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Bahamas");

        selectItemInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", "Bangladesh");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Bangladesh");
    }

    @Test
    public void TC_05_FinPeace_EditableDropdown() throws InterruptedException {
        driver.get("https://sps.finpeace.vn/tools/sktccn");

        selectItemInFinPeaceEditableDropdown("//input[@id='job_id']", "//div[@id='job_id_list']/following-sibling::div//div[@class='ant-select-item-option-content']", "Công nghệ thông tin");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='job_id']/parent::span/following-sibling::span")).getText(), "Công nghệ thông tin");

        selectItemInFinPeaceEditableDropdown("//input[@id='gender']", "//div[@id='gender_list']//following-sibling::div[@class='rc-virtual-list']//div[@class='ant-select-item-option-content']", "Nam");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='gender']//parent::span/following-sibling::span")).getText(), "Nam");

        selectItemInFinPeaceEditableDropdown("//input[@id='married_status']", "//div[@id='married_status_list']/following-sibling::div//div[@class='ant-select-item-option-content']", "Độc thân, chưa có con");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='married_status']/parent::span/following-sibling::span")).getText(), "Độc thân, chưa có con");
    }

    @Test
    public void TC_06_OrangeHRMDropDown() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[contains(string(), 'Login')]")).click();

        // Wait for the Loading Icons to complete load
        Assert.assertTrue(isLoadingIconDisappear());

        // Check if the Dashboard page is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        // Navigate to PIM page
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();

        // Check if the PIM page is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

        selectItemInOrangeHRMDropdown("Employment Status", "Full-Time Contract");
        selectItemInOrangeHRMDropdown("Include", "Past Employees Only");
        selectItemInOrangeHRMDropdown("Job Title", "Automation Tester");
        selectItemInOrangeHRMDropdown("Sub Unit", "Quality Assurance");

    }

    private void selectItemInOrangeHRMDropdown(String nameDropdown, String selectItem) throws InterruptedException {
        driver.findElement(By.xpath("//label[text()='" + nameDropdown + "']/parent::div/following-sibling::div/div[@class='oxd-select-wrapper']")).click();

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[text()='" + nameDropdown + "']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-dropdown')]/div/span")));

        for (WebElement item : allItems) {
            if (item.getText().equals(selectItem)) {
                item.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    private Boolean isLoadingIconDisappear() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    private void selectItemInFinPeaceEditableDropdown(String parentXpath, String childXpath, String selectItem) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).clear();
        driver.findElement(By.xpath(parentXpath)).sendKeys(selectItem);
        Thread.sleep(2000);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        for (WebElement item : allItems) {
            if (item.getText().equals(selectItem)) {
                item.click();
                Thread.sleep(2000);
                break;
            }
        }

    }

    private void selectItemInEditableDropdown(String parentXpath, String childXpath, String selectItm) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).clear();
        driver.findElement(By.xpath(parentXpath)).sendKeys(selectItm);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        for (WebElement item : allItems) {
            if (item.getText().equals(selectItm)) {
                item.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    private void selectItemInVueJSDropdown(String parentXpath, String childXpath, String selectItem) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).click();

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        for (WebElement item : allItems) {
            if (item.getText().equals(selectItem)) {
                item.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    private void selectItemInReactJSCustomDropdown(String parentXpath, String childXpath, String selectItem) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).click();

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        for (WebElement item : allItems) {
            if (item.getText().equals(selectItem)) {
                item.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    private void selectItemInJQueryCustomDropdown(String parentXpath, String childXpath, String selectItem) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).click();

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        for (WebElement item : allItems) {
            if (item.getText().equals(selectItem)) {
                item.click();
                Thread.sleep(2000);
                break;
            }
        }
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
