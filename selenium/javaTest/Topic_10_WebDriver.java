package javaTest;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Topic_10_WebDriver {

    // 1
    WebDriver driver;

    // 2
    FirefoxDriver ffDriver;

    // 3
    RemoteWebDriver rmDriver;

    Capabilities capabilities;

    @Test
    public void testDriver(){

        // 1: Ko khởi tạo Interface được

        // 2
        ffDriver = new FirefoxDriver();

        // 3
        rmDriver = new RemoteWebDriver(capabilities);
    }
}
