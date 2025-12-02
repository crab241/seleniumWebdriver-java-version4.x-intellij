package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_05_TextBox_TextArea {

    WebDriver driver;
    String firstName, lastName, fullName, emailAddress, password, employeeID, passportNumber, passportComment;

    @BeforeClass
    public void beforeClass(){
        // For FireFox to accept insecure form
        /*FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);*/
        driver = new FirefoxDriver();

        // For Chrome to accept insecure form
        /*ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);*/

        firstName = "Meo";
        lastName = "maybe";
        fullName = firstName + " " + lastName;
        emailAddress = "meo" + new Random().nextInt(9999) + "@yahoo.com";
        password = "Memaybeo@123";
        passportNumber = "431276122";
        passportComment = "Nice one\n Almost perfect";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_TextBox_TexArea() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        //driver.findElement(By.cssSelector("input#is_subscribed")).click();
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("button#proceed-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(fullName));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(emailAddress));

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//h2//a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Nice Phone.\n Better than Iphone");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Quality Phone");
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(firstName + new Random().nextInt(999));
        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button#proceed-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Your review has been accepted for moderation.");


    }

    @Test
    public void TC_02_Textbox_TextArea_Topic_06() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        // could also use cssSelector with "button.orangehrm-login-button"
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        // Wait for all Loading Icon disappear
        // Cả cục bên trong sẽ trả về 1 kiểu boolean
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner")))));
        // This Wait is differ from implicitwait
        // Implicitwait will wait until an element is founded
        // This Wait will wait for a condition of an element is met

        // Verify Dashboard page is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        // Click PIM page displayed
        driver.findElement(By.xpath("//a//span[text()='PIM']")).click();

        // Check PIM page displayed
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

        // Click add employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner")))));

        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
        employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");
        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//label//span")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[contains(string(), 'Save')]")).click();
        Thread.sleep(2000);

        // Verify Sucessful message displayed
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed());

        // Loading icon at Add Employee page
        Assert.assertTrue(isLoadingIconDisappear());

        // Loading icon at Personal Detail page
        Assert.assertTrue(isLoadingIconDisappear());

        // Verify Personal Detail Page
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"), employeeID);

        // Go to Immigration page
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(), 'Add')]")).click();

        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(passportComment);
        driver.findElement(By.xpath("//button[contains(string(), 'Save')]")).click();
        Thread.sleep(2000);

        // Verify Sucessful message
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed());
        Assert.assertTrue(isLoadingIconDisappear());

        driver.findElement(By.cssSelector("button.oxd-table-cell-action-space i.bi-pencil-fill")).click();

        Assert.assertTrue(isLoadingIconDisappear());

        // Verify passport number and comment
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getDomProperty("value"), passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getDomProperty("value"), passportComment);

        // Log out
        driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        // Log in with the new user just add
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        // could also use cssSelector with "button.orangehrm-login-button"
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        // Go to My Info page
        // could replace with //span[@text()='My Info']/parent::a
        driver.findElement(By.xpath("//a/span[text()='My Info']")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        // Verify Personal Detail Page
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"), employeeID);

        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='firstName']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='lastName']")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        // Go to Immigration page
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Passport']/parent::div/following-sibling::div/div[text()='" + passportNumber + "']")).isDisplayed());
    }

    private Boolean isLoadingIconDisappear() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
