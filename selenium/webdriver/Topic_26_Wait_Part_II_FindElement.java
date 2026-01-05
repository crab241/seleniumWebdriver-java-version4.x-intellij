package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_26_Wait_Part_II_FindElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_FindElement(){
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        // 1 - If only 1 element is found
        // Return the exact founded element
        // No need to wait for the implicit time to finish
        driver.findElement(By.cssSelector("input#FirstName"));

        // 2 - If more than 1 element are found
        // Only interact with the first element
        // NOTE: when getting the locator of an element, we should check it first
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Testing");

        // 3 - If no element is found ( Case hay nhat )
        // Mới đầu vào find element nhưng ko thấy
        // Tìm lại mà thấy element thì ko cần chờ hết tổng time còn lại
        // Tìm lại mà ko thấy hết tổng time đã set thì đánh fail testcase
        // Show error: NoSuchElementException
        driver.findElement(By.cssSelector("input#RememberMe"));

    }

    @Test
    public void TC_02_FindElements(){
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        List<WebElement> elements = null;

        // 1 - If only 1 element is found
        // Return only 1
        elements = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println(elements.size());  // 1

        // 2 - If more than 1 element are found
        // Return all matched elements
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());  // 4

        // 3 - If no element is found ( Case hay nhat )
        // Mới đầu vào find element nhưng ko thấy
        // Tìm lại mà thấy element thì ko cần chờ hết tổng time còn lại
        // Tìm lại mà ko thấy hết tổng time đã set thì:
        // + Return List WebElement = 0
        // + Ko danh fail test case
        elements = driver.findElements(By.cssSelector("input#RememberMe"));
        System.out.println(elements.size());  // 0
        Assert.assertEquals(elements.size(), 0);

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
