package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_29_Wait_Part_V_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }

    @Test  // Happy Case
    public void TC_01_Element_Found(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Start time: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#small-searchterms"));
        System.out.println("End time: " + getDateTimeNow());

        System.out.println("Start time: " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small-searchterms")));
        System.out.println("End time: " + getDateTimeNow());

    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            driver.findElement(By.cssSelector("input#small"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }

    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small"))); // se ko tim duoc
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }

    }

    @Test // Equal/ Less than / More than
    public void TC_04_Element_Not_Found_Combine_By(){
        // Case này sẽ fail vì element k tìm được
        // Thời gian fail sẽ là 10-11s, tức là thời gian của explicitWait,
        // Khi truyền locator vào hàm visible, nó sẽ tìm Element, tức áp dụng wait cuar implicit trong 5s rồi fail
        // ( nó áp dụng findElement trong hàm visible của explicit )
        // nhưng vẫn phải chờ hết 10s của explicit heest mới đánh fail
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            // Tham số locator sẽ dùng cho findElement từ bên trong hàm visiblitity => chịu ảnh hưởng time của Explicit
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small")));  // se ko tim dc
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }

    }

    @Test
    public void TC_05_Element_Not_Found_Combine_Element(){
        // Trường hợp này cũng sẽ fail
        // Nhưng fail từ wait của implicit, chứ kf của explicit
        // do ở đây truyền WebElement vào thay vì locator
        // Về cơ bản driver.findElement coi như 1 block code chạy trước, rồi mới đến hàm visible
        // Khi driver.findElement fail trước, hàm visible sẽ chưa baoh được chạy.

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            // Tham số WebElement findElement truyền từ bên ngoài => chịu ảnh hưởng của Implicit
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#small"))));  // se ko tim dc
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }

        // Nếu chir dùng explicit thì các hàm có tham số là Element sẽ nhận timeout = 0
    }

    @Test
    public void HW_TC_01_Element_Found(){
        // Happy case
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        // Wait for ELement to visible using explict wait
        System.out.println("Start time: " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small-searchterms")));
        System.out.println("End time: " + getDateTimeNow());

        // Find Element with implicit wait
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#small-searchterms"));
        System.out.println("End time: " + getDateTimeNow());

    }

    @Test
    public void HW_TC_02_Element_NotFound_Only_Implicit(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            // intentionally choose a locator that doesn't exist to see the time the driver search for the element
            // The result should be 10s because implicitWait is being set to 10
            driver.findElement(By.cssSelector("input#small"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }

    }

    @Test
    public void HW_TC_03_Only_Explicit(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Start time: " + getDateTimeNow());
        // intentionally choose a locator that doesn't exist to see the time the driver search for the element
        // this should fail after 10s
        try {
            // Within the visibility function, there's a findElement function
            // Since we didn't set implicit Wait, the visibility function will fail from within
            // But it still have to wait until the 10s wait time is done, and the terminal will display error of the visibility function
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }

    }

    @Test
    public void HW_TC_04_Combine_By(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }

        // In this test case, if we set the explicitWait time < implicitWait time, or explicitWait > implicitWait
        // Meaning the time run will depend on which one that has a longer wait, and not specifically any
        // The total time test case run will depend on the one with the longer wait time
        // Because implicitWait only being use when the findElement from within visibility function,
        // The error log on terminal would be about timeoutException ( explicit ), but the time to finis test case would still be 10s of implicit
    }

    @Test
    public void HW_TC_05_Combine_Element(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#small"))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }

        // In this test case, because we set parameter to be driver.findElement, which is now basically a seperated block of code
        // the driver.findElement would be run first, if it fail, the visibility function would never even got the chance to run,
        // So the error will be about NoSuchElementException, meaning implicitWait error

        // The run time is this case would depend on the implicitWait
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private String getDateTimeNow(){
        return new Date().toString();
    }
}
