package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_WebBrowserCommands {
    // biến non-static
    WebDriver driver;

    // biến static
    static WebDriver firstDriver;

    // biến final - ko đc gán laij
    final WebDriver secondDriver = new ChromeDriver();

    // biến static + final = constant ( hằng số ) = Giá trị k đổi và đc sử dụng treen toàn bộ heej thống
    static final double PI = 3.14159265;


    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01(){
        driver.get("https://demo.nopcommerce.com/");

        // Close browser ( k qtam có bao nhiêu tab/window đang open, đóng tất cả )
        driver.quit();

        // Close browser ( chỉ close tab/window đang active, đang sử dụng )
        driver.close();

        // Tìm 1 element
        driver.findElement(By.cssSelector(""));

        // Tìm nhiều element
        driver.findElements(By.cssSelector(""));

        // Lấy URL của page hiện tại
        driver.getCurrentUrl();

        // Get toàn bộ HTML Source Code hiện tại
        driver.getPageSource();

        Assert.assertTrue(driver.getPageSource().contains("Computer"));

        // Return title của page hiện tại
        driver.getTitle();

        // Return ID của tab hoặc window hiện tại đang active
        driver.getWindowHandle();

        // Return ID của tất cả các tab hoặc windows đang active
        driver.getWindowHandles();

        // Expand, mở rộng cửa so trình duyệt
        driver.manage().window().maximize();

        // Thu nhỏ cửa sổ về dưới Taskbar
        driver.manage().window().minimize();

        // Full screen mode
        driver.manage().window().fullscreen();

        // Test GUI ( Giao diện ) = Size/ Font/ Color/ Position ...
        // Set size của browser
        // Test Manual 1 app có nhiều kích thước ( Responsive )
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // Lấy ra kích thước hiện tại cuủa browser bằng bnh
        driver.manage().window().getSize();

        // Set màn hinhf nằm ở vị trí nào so với độ phân giải màn hình hiện tại
        driver.manage().window().setPosition(new Point(0, 50));

        // lấy ra tọa độ browser đang đứng
        driver.manage().window().getPosition();

        // Set time out cho viec tim kiem element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30000));

        driver.manage().timeouts().getImplicitWaitTimeout();

        // áp dụng cho JavascriptExecutor
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().getScriptTimeout();

        // Áp dụng cho page load đc load tối đa bao lâu
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().getPageLoadTimeout();

        // Lấy ra cookie theo tên/ xóa cookie đi/ xóa toàn bộ
        driver.manage().getCookies();

        driver.navigate().to("https://demo.nopcommerce.com/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        // Áp dụng cho 3 cái: Handle alert, window/tab, frame/iframe
        driver.switchTo().alert();
        driver.switchTo().window("");
        driver.switchTo().frame("");

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
