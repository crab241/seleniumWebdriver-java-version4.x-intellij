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

public class Topic_08_Exercise_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_05_JQuery(){
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        // select item Medium in speed Dropdown
        String speedDropdown = "//span[@id='speed-button']";
        String speedItemSelect = "//li[@class='ui-menu-item']";
        String selectItem1 = "Medium";
        String selectItem2 = "Slower";
        String selectItem3 = "Faster";
        selectItemCustomDropdown(speedDropdown, speedItemSelect, selectItem1);

        // Verify selected item
        Assert.assertEquals(waitElementVisible_Xpath(speedDropdown).getText(), selectItem1);
    }

    @Test
    public void TC_05_React(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        String friendDropdown = "//div[@class='ui fluid selection dropdown']";
        String friendItemSelect = "//div[@class='visible menu transition']/div/span";
        String friendItemName1 = "Jenny Hess";
        String friendItemName2 = "Elliot Fu";
        String friendItemName3 = "Stevie Feliciano";
        String friendItemName4 = "Christian";
        String friendItemName5 = "Matt";
        String friendItemName6 = "Justen Kitsune";
        selectItemCustomDropdown(friendDropdown, friendItemSelect, friendItemName3);

        Assert.assertEquals(waitElementVisible_Xpath(friendDropdown).getText(), friendItemName3);
    }

    @Test
    public void TC_05_VueJS(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        String itemDropdown = "li.dropdown-toggle";
        String itemToSlect = "ul.dropdown-menu>li";
        String item1 = "First Option";
        String item2 = "Second Option";
        String item3 = "Third Option";
        selectItemInDropdown_Css(itemDropdown, itemToSlect, item2);

        // Verify selection
        Assert.assertEquals(waitElementVisible_Css(itemDropdown).getText(), item2);
    }

    @Test
    public void TC_05_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        String countryDropdown = "//input[@class='search']";   // //div[contains(@class, 'selection dropdown')]
        String countryItem = "//div[@class='visible menu transition']/div/span";
        String itemToSelect1 = "Bangladesh";
        String itemToSelect2 = "Algeria";

        selectItemEditable(countryDropdown, countryItem, itemToSelect1);
        // Verify selection
        Assert.assertEquals(waitElementVisible_Xpath("//div[contains(@class, 'selection dropdown')]")
                .getText(), itemToSelect1);

        selectItemEditable(countryDropdown, countryItem, itemToSelect2);
        // Verify selection
        Assert.assertEquals(waitElementVisible_Xpath("//div[contains(@class, 'selection dropdown')]")
                .getText(), itemToSelect2);
    }

    @Test
    public void TC_FinPeace(){
        driver.get("https://sps.finpeace.vn/tools/sktccn");

        // select job title
        String jobTitleInputDropDown = "//input[@id='job_id']";
        String itemTitleToSelect = "//div[@class='rc-virtual-list-holder-inner']/div";
        String titleSelect ="Công nghệ thông tin";

        selectItemEditable(jobTitleInputDropDown, itemTitleToSelect, titleSelect);
        // Verify selection
        Assert.assertEquals(waitElementVisible_Xpath("//input[@id='job_id']/parent::span/following-sibling::span").getText(), titleSelect);

        // select mariage
        String marriageInputDropdown = "//input[@id='gender']";   // //input[@id='gender']/parent::span/following-sibling::span
        String genderToSelect = "//div[@id='gender_list']/following-sibling::div//div[contains(@class,'ant-select-item ant-select-item-option')]";
        String gender = "Nam";

        selectItemEditable(marriageInputDropdown, genderToSelect, gender);
        // verify selection
        Assert.assertEquals(waitElementVisible_Xpath("//input[@id='gender']/parent::span/following-sibling::span").getText(), gender);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebElement waitElementVisible_Css(String cssLocator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssLocator)));
    }

    private WebElement waitElementVisible_Xpath(String xpathLocator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
    }

    private void selectItemCustomDropdown(String parentXpath, String childXpath, String selectItem){
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parentXpath))).click();

        List<WebElement> listItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        for (WebElement item : listItems) {
            if (item.getText().equals(selectItem)) {
                item.click();
                break;
            }
        }
    }

    private void selectItemInDropdown_Css(String parentCss, String childCss, String selectItem){
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentCss))).click();

        List<WebElement> listItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        for (WebElement item : listItem) {
            if (item.getText().equals(selectItem)) {
                item.click();
                break;
            }
        }
    }

    private void selectItemEditable(String parentXpath, String childXpath, String selectItem) {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parentXpath))).clear();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parentXpath))).sendKeys(selectItem);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> listItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        for (WebElement item : listItems) {
            if (item.getText().equals(selectItem)) {
                item.click();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                break;
            }
        }
    }
}
