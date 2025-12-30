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
import java.util.Random;

public class Topic_23_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String firstName = "John";
    String lastName = "Doe";
    String ranEmail = "johndoe" + new Random().nextInt(9999) + "@hotmail.com";
    String password = "Johndoe@1234";

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_LiveTechPanda(){
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSecond(2);

        Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
        Assert.assertEquals(executeForBrowser("return document.URL"), "https://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSecond(2);

        // highlight elemnt before click
        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        sleepInSecond(2);

        String samsungMessage = getInnerText();
        Assert.assertTrue(samsungMessage.contains("Samsung Galaxy was added to your shopping cart."));
        // another way to assert
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        sleepInSecond(2);

        Assert.assertEquals(executeForBrowser("return document.title"), "Customer Service");

        scrollToElementOnTop("//input[@id='newsletter']");
        hightlightElement("//input[@id='newsletter']");

        sendkeyToElementByJS("//input[@id='newsletter']", "peter" + new Random().nextInt(9999) + "@hotmail.com");
        sleepInSecond(2);

        // Click subcribe button
        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");
        sleepInSecond(2);

        driver.switchTo().alert().accept();
        sleepInSecond(2);

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com/");

        Assert.assertEquals(executeForBrowser("return document.domain"), "www.facebook.com");

    }

    @Test
    public void TC_02_Validation_Message(){
        driver.get("https://login.ubuntu.com/");

        driver.findElement(By.xpath("//form[@id='login-form']//button[@name='continue']")).click();

        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Please fill out this field.");

        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("123@123.456@123");
        driver.findElement(By.xpath("//form[@id='login-form']//button[@name='continue']")).click();
        sleepInSecond(2);

        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Please enter an email address.");

        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).clear();
        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("beck@gmail.com");
        driver.findElement(By.xpath("//form[@id='login-form']//button[@name='continue']")).click();
        sleepInSecond(2);

        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_password']"), "Please fill out this field.");

    }

    @Test
    public void HW_TC_01_Techpanda(){
        navigateToUrlByJS("http://live.techpanda.org/");

        // Verify domain
        Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");

        // Verify URL
        Assert.assertEquals(executeForBrowser("return document.URL;"), "https://live.techpanda.org/");

        // Highlight and open Mobile page
        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSecond(2);

        // Add Samsung Galaxy product into Cart
        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']/button");
        sleepInSecond(2);

        // Verify Succesul message display
        String samgsungMessage = getInnerText();
        // First way to verify
        Assert.assertTrue(samgsungMessage.contains("Samsung Galaxy was added to your shopping cart."));
        // Second way to verify
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        // Highlight and open Customer Service page
        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        sleepInSecond(2);

        // Verify Customer Service title page
        Assert.assertEquals(executeForBrowser("return document.title;"), "Customer Service");

        // Scroll to Newsletter textbox at the bottom page
        scrollToElementOnTop("//input[@id='newsletter']");
        sleepInSecond(2);

        // Random email generate
        String randomEmail = "john" + new Random().nextInt(9999) + "yahoo@gmail.com";

        // Input valid email
        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']", randomEmail);
        sleepInSecond(1);

        // Click on Subscribe button
        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");
        sleepInSecond(1);

        // Accept alert
        driver.switchTo().alert().accept();
        sleepInSecond(2);

        // Verify successful message
        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSecond(2);

        // Verify domain
        Assert.assertEquals(executeForBrowser("return document.domain;"), "www.facebook.com");

    }

    @Test
    public void HW_TC_03_Verify_HTML5_Message(){
        driver.get("https://login.ubuntu.com/");

        driver.findElement(By.xpath("//form[@id='login-form']//button[@name='continue']")).click();
        sleepInSecond(1);

        // Verify message appear
        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Please fill out this field.");
        sleepInSecond(2);

        // Enter invalid email
        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("123@123.456@123");
        driver.findElement(By.xpath("//form[@id='login-form']//button[@name='continue']")).click();
        sleepInSecond(1);

        // Verify message appear
        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Please enter an email address.");
        sleepInSecond(2);

        // Enter valid email format
        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).clear();
        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("beck@gmail.com");
        driver.findElement(By.xpath("//form[@id='login-form']//button[@name='continue']")).click();
        sleepInSecond(1);

        // Verify message at password text
        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_password']"), "Please fill out this field.");

    }

    @Test
    public void HW_TC_04_CreateAnAccount_Techpanda(){
        navigateToUrlByJS("https://live.techpanda.org/");

        // Click on hidden My Account on the dropdown without click on Account to open the dropdown first
        hightlightElement("//div[@id='header-account']//a[text()='My Account']");
        clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
        sleepInSecond(2);

        // Hightlight and click on Create an account
        hightlightElement("//a[@title='Create an Account']");
        clickToElementByJS("//a[@title='Create an Account']");
        sleepInSecond(2);

        // Enter valid credentials
        hightlightElement("//input[@id='firstname']");
        sendkeyToElementByJS("//input[@id='firstname']", firstName);
        sleepInSecond(1);

        hightlightElement("//input[@id='lastname']");
        sendkeyToElementByJS("//input[@id='lastname']", lastName);
        sleepInSecond(1);

        hightlightElement("//input[@id='email_address']");
        sendkeyToElementByJS("//input[@id='email_address']", ranEmail);
        sleepInSecond(1);

        hightlightElement("//input[@id='password']");
        sendkeyToElementByJS("//input[@id='password']", password);
        sleepInSecond(1);

        hightlightElement("//input[@id='confirmation']");
        sendkeyToElementByJS("//input[@id='confirmation']", password);
        sleepInSecond(1);

        // Click register
        hightlightElement("//button[@title='Register']");
        clickToElementByJS("//button[@title='Register']");
        sleepInSecond(1);

        // Accept alert
        driver.switchTo().alert().accept();
        sleepInSecond(2);

        // Verify message
        Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));

        // Log out
        hightlightElement("//a[text()='Log Out']");
        clickToElementByJS("//a[text()='Log Out']");
        sleepInSecond(8);

        // Verify return to home page
        Assert.assertFalse(driver.findElement(By.xpath("//title")).isDisplayed());
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        return  (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined'" +
                " && arguments[0].naturalWidth > 0", getElement(locator));
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
