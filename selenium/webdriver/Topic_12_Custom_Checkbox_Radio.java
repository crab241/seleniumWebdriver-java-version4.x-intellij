package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_12_Custom_Checkbox_Radio {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");

        By newUserRadio = By.cssSelector("input#id_new_user");
        By acceptToCheckbox = By.cssSelector("input#id_accept_tos");
        // 1 - Nếu dùng thẻ input để click => Failed
        // Dùng thẻ input để verify thì đc: isSelected() chỉ dùng cho thẻ input
        // Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());

        // 2 - K dùng thẻ input để click nữa
        // Ther nào đè lên thẻ input thì click
        // K verify ddc vì thẻ label k áp dụng hàm isSelected đc
        //driver.findElement(By.cssSelector("label[class='new-user']")).click();
        //Thread.sleep(2000);

        //Assert.assertTrue(driver.findElement(By.cssSelector("label[class='new-user']")));

        // 3 - Click thì dùng thẻ label
        // Verify thì dùng thẻ input
        /*driver.findElement(By.cssSelector("label[class='new-user']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());


        driver.findElement(By.cssSelector("label[for='id_accept_tos']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(acceptToCheckbox).isSelected());*/

        // 4 - Sử dụng thẻ input cho vừa select vừa verify
        // K dùng hàm click của element
        // Dùng hàm click của JavaScriptExecutor: sai hành vi của end user
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(newUserRadio));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

        jsExecutor.executeScript("arguments[0].click()", driver.findElement(acceptToCheckbox));
        Assert.assertTrue(driver.findElement(acceptToCheckbox).isSelected());

        // Only apply JE for Custom Checkbox/Radio
        // Not recommend for Default - just use WebElement to click()

    }

    @Test
    public void TC_02_GoogleForm() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform?pli=1");

        By canthoRadio = By.cssSelector("div[aria-label='Cần Thơ']");
        By quangbinhCheckbox = By.cssSelector("div[aria-label='Quảng Bình']");

        Thread.sleep(2000);

        driver.findElement(canthoRadio).click();
        driver.findElement(quangbinhCheckbox).click();

        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(canthoRadio).getDomAttribute("aria-checked"), "true");
        Assert.assertEquals(driver.findElement(quangbinhCheckbox).getDomAttribute("aria-checked"), "true");

    }

    @Test
    public void Redo_TC_05_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");

        By noUbuntuAccountRadio = By.xpath("//span[text()='I don’t have an Ubuntu One account']/parent::label/preceding-sibling::input");

        By acceptTermsCheckbox = By.cssSelector("input#id_accept_tos");

        // Select I don't have an Ubuntu One account
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(noUbuntuAccountRadio));

        // Verify that radio is selected
        Assert.assertTrue(driver.findElement(noUbuntuAccountRadio).isSelected());

        Thread.sleep(2000);

        // Select the Accept terms checkbox
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(acceptTermsCheckbox));

        // Verify it is selected
        Assert.assertTrue(driver.findElement(acceptTermsCheckbox).isSelected());
    }

    @Test
    public void Redo_TC_06_GGDocs() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        // Can use //span[text()='Cần Thơ']/parent::div/parent::div/preceding-sibling::div/div as a replacement locator
        By canthoRadio = By.xpath("//div[@data-value='Cần Thơ']");

        Thread.sleep(2000);
        // Check to see Can THo radio is not select yet
        Assert.assertEquals(driver.findElement(canthoRadio).getDomAttribute("aria-checked"), "false");

        //Thread.sleep(2000);
        // Click on Can Tho radio
        driver.findElement(canthoRadio).click();

        // Thread.sleep(2000);
        // Verify that Can Tho radio is selected
        Assert.assertEquals(driver.findElement(canthoRadio).getDomAttribute("aria-checked"), "true");


    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
