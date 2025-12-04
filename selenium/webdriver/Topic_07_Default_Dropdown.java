package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_07_Default_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://www.facebook.com/r.php?entry_point=login");

        String day = "30";
        String month = "Dec";
        String year = "1996";
        // Select dropdown Day
        select = new Select(driver.findElement(By.cssSelector("select#day")));
        select.selectByVisibleText("30");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

        // Khởi tạo thư viện Select khi dropdown đc xuất hiện
        select = new Select(driver.findElement(By.cssSelector("select#month")));

        // Làm sao chọn đc 1 tháng bất kỳ trong Month Dropdown
        select.selectByIndex(3); // chọn tháng 4
        Thread.sleep(2000);

        select.selectByValue("9"); // chọn tháng 9
        Thread.sleep(2000);

        select.selectByVisibleText("Dec"); // Hàm này sử dụng tối ưu nhất
        Thread.sleep(2000);

        // Chọn r làm sao kiểm tra đã chọn thành công
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

        // Làm sao để kieerm tra dropdown đấy cos tổng cộng bao nhiêu item
        Assert.assertEquals(select.getOptions().size(), 12);

        // Dropdown đấy có cho phép chọn nhiều hay k?
        Assert.assertFalse(select.isMultiple());

        // Select Year
        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("1996");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
    }

    @Test
    public void TC_02_rode() throws InterruptedException {
        driver.get("https://rode.com/en-int/support/where-to-buy");

        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        Thread.sleep(3000);

        // Check if the Select Country Dropdown is multiple or not
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#country"))).isMultiple());

        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("Ho Chi Minh");
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div/div")).size(), 16);

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div/div"));

        for ( WebElement dealer : dealers) {
            System.out.println(dealer.getText());
        }

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
