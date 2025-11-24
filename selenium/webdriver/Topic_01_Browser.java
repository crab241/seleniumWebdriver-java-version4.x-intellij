package webdriver;

import graphql.relay.Edge;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_01_Browser {
    WebDriver driver;

    @Test
    public void TC_01_Chrome(){
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.quit();
    }


    @Test
    public void TC_02_Edge(){
        driver = new EdgeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.quit();
    }


    @Test
    public void TC_03_Firefox(){
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.quit();
    }
}