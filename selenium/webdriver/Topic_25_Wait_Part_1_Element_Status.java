package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Topic_25_Wait_Part_1_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    String randomEmail = "johnwick" + new Random().nextInt(9999) + "@gmail.com";

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Visible(){

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Đk 1: Element hiển thị trên UI và có trong DOM/ cây HTML
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void TC_02_Invisible_In_HTML() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("test5@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        // Đk 2: Element ko hiển thị trên UI và có trong DOM/ cây HTML
        // An expectation for checking that an element is either invisible

        System.out.println("Start wait: " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        System.out.println("End wait: " + getDateTimeNow());

    }

    @Test
    public void TC_02_Invisible_Not_In_HTML(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("test5@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        // Đk 3: ELement ko hiển thị trên UI và ko có trong DOM/ cây HTML
        // not present on the DOM.
        System.out.println("Start wait: " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        System.out.println("End wait: " + getDateTimeNow());

    }

    @Test
    public void TC_03_Presence(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Đk 1: Element hiển thị trên UI và có trong DOM/ cây HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        driver.findElement(By.cssSelector("input#email")).sendKeys("test5@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        // Đk 2: Element ko hiển thị trên UI và có trong DOM/ cây HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

    }

    @Test
    public void TC_04_Stateless(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Element đã xuất hiện ở 1 thời điểm trước đó rồi
        WebElement emailErrorMessage = driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        driver.navigate().refresh();

        // Đk 3: ELement ko hiển thị trên UI và ko có trong DOM/ cây HTML
        // Tại thơời điểm này k còn xuất hiện nữa
        // Wait until an element is no longer attached to the DOM.
        explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
        // invisible cover luôn đc cho cả case stateless
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

    }

    @Test
    public void HW_TC_01_Visible(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Conditioin 1: element is visible on UI, and presence in DOM/HTML
        System.out.println("Start time: " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        System.out.println("End time: " + getDateTimeNow());
    }

    @Test
    public void HW_TC_02_Invisible_In_HTML(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Conditioin 1: element is visible on UI, and presence in DOM/HTML
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        driver.findElement(By.cssSelector("input#email")).sendKeys(randomEmail);
        driver.findElement(By.cssSelector("button#send2")).click();

        // Condition 2: Element is not visible on UI, but presence in DOM/HTML
        System.out.println("Start time: " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        System.out.println("End time: " + getDateTimeNow());

    }

    @Test
    public void HW_TC_02_Invisible_Not_In_HTML(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("input#email")).sendKeys(randomEmail);
        driver.findElement(By.cssSelector("button#send2")).click();

        // Condition 3: Element is not visible on UI + DOM/HTML
        System.out.println("Start time: " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        System.out.println("End time: " + getDateTimeNow());

    }

    @Test
    public void HW_TC_03_Presence(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        // Condition 1: Element visible on UI and DOM/ HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        driver.findElement(By.cssSelector("input#email")).sendKeys(randomEmail);
        driver.findElement(By.cssSelector("button#send2")).click();

        // Condition 2: Element is not visible on UI, but presence in DOM/HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void HW_TC_04_Stateness(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        WebElement errorEmailMessage = driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        // Refresh page
        driver.navigate().refresh();
        // The error message itself won't exist in the HTML any more after refresh

        // Condition 3: Element is not visible on both UI + DOM/HTML
        explicitWait.until(ExpectedConditions.stalenessOf(errorEmailMessage));

        // Somehow the invisible function also cover this
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private String getDateTimeNow(){
        return new Date().toString();
    }
}
