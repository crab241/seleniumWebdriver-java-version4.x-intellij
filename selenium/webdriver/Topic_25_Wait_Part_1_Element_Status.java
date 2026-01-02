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

public class Topic_25_Wait_Part_1_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;

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

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private String getDateTimeNow(){
        return new Date().toString();
    }
}
