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
import java.util.Random;

public class Topic_05_Exercise_TextBox_TextArea {
    WebDriver driver;
    WebDriverWait explicitWait;
    String firstName = "John";
    String lastName = "Blaze";
    String randomEmail = "johnnyBlaze" + new Random().nextInt(9999) + "@gmail.com";
    String pssWord = "Memaybeovl@123";
    String passPortNum = "108376537";
    String comment = "Everything is great" + "\n" + "If not fantastic";

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_TextBox_TextArea(){
        driver.get("https://live.techpanda.org/");

        // Click on My Account
        waitElementVisible_Css("div.footer a[title='My Account']").click();

        // Go to create an account page
        waitElementVisible_Css("a[title='Create an Account']").click();

        // Enter fields
        waitElementVisible_Css("input#firstname").sendKeys(firstName);
        waitElementVisible_Css("input#lastname").sendKeys(lastName);
        waitElementVisible_Css("input#email_address").sendKeys(randomEmail);
        waitElementVisible_Css("input#password").sendKeys(pssWord);
        waitElementVisible_Css("input#confirmation").sendKeys(pssWord);

        // Click Register button
        waitElementVisible_Css("button[title='Register']").click();

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        // Accept alert
        driver.switchTo().alert().accept();

        // Verify registered successfully
        Assert.assertEquals(getTextElement_CssLocator("li.success-msg span"), "Thank you for registering with Main Website Store.");

        // Verify credentials
        Assert.assertTrue(getTextElement_Xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p").contains(firstName));
        Assert.assertTrue(getTextElement_Xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p").contains(lastName));
        Assert.assertTrue(getTextElement_Xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p").contains(randomEmail));

        // Go to Mobile tab
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        // Click choose Samsung Galaxy product
        waitElementVisible_Css("img[alt='Samsung Galaxy']").click();

        // Click to go to add review page
        waitElementVisible_Xpath("//a[text()='Add Your Review']").click();

        // Entering reviews
        waitElementVisible_Css("input[value='4']").click();
        waitElementVisible_Css("textarea#review_field").sendKeys("Nice product" + "\n" + "Worth to experience");
        waitElementVisible_Css("input#summary_field").sendKeys("Best phone");
        waitElementVisible_Css("input#nickname_field").clear();
        waitElementVisible_Css("input#nickname_field").sendKeys("Guest");

        // Click submit review
        waitElementVisible_Css("button[title='Submit Review']").click();

        // Accept alert
        driver.switchTo().alert().accept();

        // Verify message after submit
        Assert.assertEquals(getTextElement_CssLocator("li.success-msg span"), "Your review has been accepted for moderation.");

    }

    @Test
    public void TC_02_TextBox_TextArea_OrangeHRM(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        String loadingIconLocator = "div.oxd-loading-spinner";
        String successMsgLocator = "//p[text()='Successfully Saved']";

        // Login with admin account
        waitElementVisible_Css("input[name='username']").sendKeys("Admin");
        waitElementVisible_Css("input[name='password']").sendKeys("admin123");
        waitElementVisible_Css("button.orangehrm-login-button").click();

        // Wait for Loading icon to finish
        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        // Go to PIM page
        waitElementVisible_Xpath("//span[text()='PIM']/parent::a").click();

        // Wait for the PIM page to load
        Assert.assertTrue(waitElementVisible_Xpath("//h6[text()='PIM']").isDisplayed());

        // Go to Add Employee page
        waitElementVisible_Xpath("//a[text()='Add Employee']").click();

        // Wait for loading icon to disappear
        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        // Enter information
        waitElementVisible_Css("input[name='firstName']").sendKeys(firstName);
        waitElementVisible_Css("input[name='lastName']").sendKeys(lastName);
        String employeeID = waitElementVisible_Xpath("//label[text()='Employee Id']/parent::div/" +
                "following-sibling::div/input").getDomProperty("_value");

        waitElementVisible_Xpath("//p[text()='Create Login Details']/following-sibling::div/label/span").click();

        // Enter credentials
        waitElementVisible_Xpath("//label[text()='Username']/parent::div/following-sibling::div/input").sendKeys(randomEmail);
        waitElementVisible_Xpath("//label[text()='Password']/parent::div/following-sibling::div/input").sendKeys(pssWord);
        waitElementVisible_Xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input").sendKeys(pssWord);
        waitElementVisible_Xpath("//button[contains(string(), 'Save')]").click();

        Assert.assertTrue(waitElementVisible_Xpath(successMsgLocator).isDisplayed());
        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));
        // Wait for the second loading icon to disappear
        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        // Verify information
        Assert.assertEquals(waitElementVisible_Css("input[name='firstName']").getDomProperty("_value"), firstName);
        Assert.assertEquals(waitElementVisible_Css("input[name='lastName']").getDomProperty("_value"), lastName);
        Assert.assertEquals(waitElementVisible_Xpath("//label[text()='Employee Id']/parent::div/" +
                "following-sibling::div/input").getDomProperty("_value"), employeeID);

        // Go to Immigration tab
        waitElementVisible_Xpath("//a[text()='Immigration']").click();

        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        waitElementVisible_Xpath("//h6[text()='Assigned Immigration Records']/" +
                "following-sibling::button[contains(string(), 'Add')]").click();

        // Enter passport number and comments
        waitElementVisible_Xpath("//label[text()='Number']/parent::div/following-sibling::div/input").sendKeys(passPortNum);
        waitElementVisible_Css("textarea[placeholder='Type Comments here']").sendKeys(comment);
        waitElementVisible_Xpath("//button[contains(string(), 'Save')]").click();

        Assert.assertTrue(waitElementVisible_Xpath(successMsgLocator).isDisplayed());
        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        waitElementVisible_Css("i.bi-pencil-fill").click();

        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        Assert.assertEquals(waitElementVisible_Xpath("//label[text()='Number']/parent::div/" +
                "following-sibling::div/input").getDomProperty("_value"), passPortNum);
        Assert.assertEquals(waitElementVisible_Css("textarea[placeholder='Type Comments here']")
                .getDomProperty("_value"), comment);

        // Click on user and logout
        waitElementVisible_Css("li.oxd-userdropdown").click();
        waitElementVisible_Xpath("//a[text()='Logout']").click();

        // Login with new user account
        waitElementVisible_Css("input[name='username']").sendKeys(randomEmail);
        waitElementVisible_Css("input[name='password']").sendKeys(pssWord);
        waitElementVisible_Css("button.orangehrm-login-button").click();

        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        waitElementVisible_Xpath("//span[text()='My Info']/parent::a").click();

        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        // Verify user information
        Assert.assertEquals(waitElementVisible_Css("input[name='firstName']").getDomProperty("_value"), firstName);
        Assert.assertEquals(waitElementVisible_Css("input[name='lastName']").getDomProperty("_value"), lastName);
        Assert.assertEquals(waitElementVisible_Xpath("//label[text()='Employee Id']/parent::div/" +
                "following-sibling::div/input").getDomProperty("_value"), employeeID);

        // Go to immigration
        waitElementVisible_Xpath("//a[text()='Immigration']").click();
        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        waitElementVisible_Css("i.bi-pencil-fill").click();
        Assert.assertTrue(isLoadingIconDisappear(loadingIconLocator));

        // Verify passport number and comment
        Assert.assertEquals(waitElementVisible_Xpath("//label[text()='Number']/parent::div/" +
                "following-sibling::div/input").getDomProperty("_value"), passPortNum);
        Assert.assertEquals(waitElementVisible_Css("textarea[placeholder='Type Comments here']").getDomProperty("_value"), comment);

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebElement waitElementVisible_Css(String cssLocator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssLocator)));
    }

    private WebElement waitElementVisible_Xpath(String xpathLocator) {
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
    }

    private String getTextElement_CssLocator(String cssLocator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssLocator))).getText();
    }

    private String getTextElement_Xpath(String xpathLocator){
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator))).getText();
    }

    private Boolean isLoadingIconDisappear(String cssLocator){
        return explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(cssLocator))));
    }
}
