package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_18_19_Handle_Popup {
    WebDriver driver;
    Actions actions;
    int shortTimeout = 3;
    int longTimeout = 15;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        actions = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_In_HTML() throws InterruptedException {
        driver.get("http://www.kmplayer.com/");

        Thread.sleep(8000);

        By popup = By.cssSelector("div.pop-container");

        // If popup is display, close => Click FAQ link
        // If not, click FAQ link

        // Dù element hiển thị / ko hiển thị vẫn loon còn trong HTML
        // findElement sẽ luôn luôn tìm thây
        if (driver.findElement(popup).isDisplayed()) {
            System.out.println("======== Popup displayed ========");
            driver.findElement(By.cssSelector("div.close")).click();
            Thread.sleep(2000);
        } else {
            System.out.println("======== Popup not displayed ========");
            Thread.sleep(2000);
        }

        // Expected to not display before click on FAQ
        Assert.assertFalse(driver.findElement(popup).isDisplayed());

        driver.findElement(By.xpath("//a[text()='FAQ']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='KMPlayer FAQ']")).isDisplayed());
    }

    @Test
    public void TC_02_Not_in_HTML() throws  InterruptedException{
        driver.get("https://tienganhcomaiphuong.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        By popup = By.cssSelector("div.custom-dialog-paper");

        // Verify popup hien thi
        Assert.assertTrue(driver.findElement(popup).isDisplayed());

        driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//form//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);


        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        driver.findElement(By.cssSelector("button.close-btn")).click();
        Thread.sleep(2000);

        //Verify popup k hien thi
        // isDisplayed() k kiem tra cho 1 element k co trong HTML dc
        //Assert.assertFalse(driver.findElement(popup).isDisplayed());

        // Khi xu ly cac element k ton tai trong HTML nen size lai timeout cua Implicit ve 1 con so ngan hon
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertEquals(driver.findElements(popup).size(), 0);

        // Tra lai trangj thai cho viec tim element khac
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_03_Tiki() throws  InterruptedException{
        driver.get("https://tiki.vn/");
        Thread.sleep(8000);

        // La 1 popup dang random
        // If popup is display, close => Click Dang Nhap/ Dang ky
        // If not, click Dang nhap/ Dang ky
        By bundlePopup = By.cssSelector("div#VIP_BUNDLE");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(shortTimeout));
        List<WebElement> bundlePopupElements = driver.findElements(bundlePopup);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));

        if (bundlePopupElements.size() > 0 && bundlePopupElements.get(0).isDisplayed()) {
            System.out.println("Popup displayed");
            // Close popup
            driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();
            System.out.printf("Popup is closed");
            Thread.sleep(1500);
            // Step else duoi la Optional
        } else {
            System.out.println("Popup not displayed");
            Thread.sleep(1500);
        }

        // CLick vao Dang nhap/ Dang ky
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();

        // La 1 popup dang fix cung
        By loginPopup = By.cssSelector("div[class*='ReactModal__Content']");

        // Verify login popup dang hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());

        driver.findElement(By.cssSelector("button.btn-close img")).click();
        Thread.sleep(1000);

        // Verify login popup dang hien thi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(shortTimeout));
        Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
