package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_22_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Home_Shop(){
        driver.get("https://shop.polymer-project.org/");

        WebElement shopAppShadowHost = driver.findElement(By.cssSelector("shop-app"));
        // SearchContext là 1 kiểu template mà WebDriver kế thừa
        // Về cơ bản là biến shopAppShadowRoot, hay bất kì biến shadow root nào cũng có thể sử dụng tương tự như biến driver
        SearchContext shopAppShadowRoot = shopAppShadowHost.getShadowRoot();

        // Get shadow host bên trong
        WebElement shopHomeShadowHost = shopAppShadowRoot.findElement(By.cssSelector("iron-pages>shop-home"));
        // Đi vào shadow root, tương tự step đầu
        SearchContext shopHomeShadowRoot =  shopHomeShadowHost.getShadowRoot();

        shopHomeShadowRoot.findElement(By.cssSelector("shop-button>a[aria-label=\"Men's Outerwear Shop Now\"]")).click();
        // Alternative way to click it with different CSS selector
        //shopHomeShadowRoot.findElement(By.cssSelector("shop-button>a[href*='mens_outwear']")).click();

    }

    @Test
    public void TC_02_Nested(){
        driver.get("https://automationfc.github.io/shadow-dom");

        // Driver is interacting with outbound DOM, not in Shadow DOM yet
        Assert.assertEquals(driver.findElement(By.xpath("//a")).getText(), "scroll.html");
        Assert.assertEquals(driver.findElements(By.xpath("//a")).size(), 1);

        // Switch to the first Shadow DOM
        WebElement firstShadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext firstShadowRoot = firstShadowHost.getShadowRoot();

        // Interacting the first Shadow DOM from the div element above and able to interact with its elements
        Assert.assertEquals(firstShadowRoot.findElement(By.cssSelector("span#shadow_content>span")).getText(), "some text");
        Assert.assertEquals(firstShadowRoot.findElements(By.cssSelector("a")).size(), 1);
        Assert.assertEquals(firstShadowRoot.findElement(By.cssSelector("a")).getText(), "nested scroll.html");

        // Switch to the nested Shadow DOM
        WebElement secondShadowHost = firstShadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext secondShadowRoot = secondShadowHost.getShadowRoot();

        // Interacting with the second Shadow DOM
        Assert.assertEquals(secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");
    }

    @Test
    public void TC_03_Books_Pwakit(){
        driver.get("https://books-pwakit.appspot.com/");
        sleepInSecond(4);

        WebElement bookappShadowHost = driver.findElement(By.cssSelector("book-app"));
        SearchContext bookappShadowRoot = bookappShadowHost.getShadowRoot();

        bookappShadowRoot.findElement(By.cssSelector("book-input-decorator>input#input")).sendKeys("Harry Potter");

        WebElement bookdecoratorShadowHost = bookappShadowRoot.findElement(By.cssSelector("book-input-decorator"));
        SearchContext bookdecoratorShadowRoot = bookdecoratorShadowHost.getShadowRoot();

        bookdecoratorShadowRoot.findElement(By.cssSelector("div.icon")).click();
        sleepInSecond(5);

        bookappShadowHost = driver.findElement(By.cssSelector("book-app"));
        bookappShadowRoot = bookappShadowHost.getShadowRoot();

        WebElement bookExplorerShadowHost = bookappShadowRoot.findElement(By.cssSelector("book-explore"));
        SearchContext bookExplorerShadowRoot = bookExplorerShadowHost.getShadowRoot();

        List<WebElement> listBookItems = bookExplorerShadowRoot.findElements(By.cssSelector("section>ul.books>li>book-item"));

        for (WebElement bookItem : listBookItems) {
            SearchContext bookItemShadowRoot = bookItem.getShadowRoot();
            System.out.println(bookItemShadowRoot.findElement(By.cssSelector("div.title-container>h2.title")).getText());;
        }

    }

    private static void sleepInSecond(long timeWait) {
        try {
            Thread.sleep(timeWait * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
