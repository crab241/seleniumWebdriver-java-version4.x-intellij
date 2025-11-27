package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Element_Exercise {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01_isDisplayed_Enabled_Selected(){
        driver.get("https://www.fahasa.com/customer/account/create");

        // Element hiển thị: có thể nhìn thấy/ thao tasc đc và có kích thước cụ th
        // Mong đợi 1 element hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone_otp")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-register")).isDisplayed());

        // Mong đợi 1 element k hiên thị
        Assert.assertFalse(driver.findElement(By.cssSelector("input#login_username")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#login_password")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isDisplayed());

        // Element enabled: có thể thao tác đc ( ko bị read-only = disabled )
        Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#register_phone_otp")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-register")).isEnabled());

        driver.get("https://live.techpanda.org/customer/account/create/");

        // Element Selected: đã đc chọn
        Assert.assertTrue(driver.findElement(By.cssSelector("input#is_subscribed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#is_subscribed")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#is_subscribed")).isSelected());

    }


    @Test
    public void TC_02_MailChimp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("yubach04@gmail.com");

        // Lower-case
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("test");
        driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Upper-case
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("TEST");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Number
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Special character
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("!@#$%^");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // 8 character
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Automationfc");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Full
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Automationfc@123");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(2000);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

    }

    @Test
    public void TC_03_Login_Empty_EmailPassword(){
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#pass")).clear();

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");
    }

    @Test
    public void TC_04_Login_Invalid_Email(){
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("123434234@12312.123123");  // Invalid email

        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");  // valid password

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_05_Login_Invalid_Passwd(){
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com"); // valid password

        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");  // invalid password

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_06_Login_Incorrect() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com"); // correct email

        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123123");  // incorrect password

        driver.findElement(By.cssSelector("button#send2")).click();

        Thread.sleep(2000);
        // Thêm bước ấn nút Send away vì trang pop up cảnh báo bảo maatj
        driver.findElement(By.cssSelector("button#proceed-button")).click();

        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg>ul>li")).getText(), "Invalid login or password.");
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
