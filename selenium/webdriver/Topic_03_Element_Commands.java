package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Element_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_(){
        // click vao element dang Button/ Checkbox/ Radio/ Link/ Image...
        driver.findElement(By.cssSelector("")).click(); //**

        // Nhap lieu vao Element allow edit ( Editable ) : Textbox/ TextArea/ Editable Dropdown
        driver.findElement(By.cssSelector("")).clear(); //**
        driver.findElement(By.cssSelector("")).sendKeys(); //**

        driver.findElement(By.cssSelector("form#small-search-box-form")).findElement(By.cssSelector("input#small-searchterms"));
        driver.findElement(By.cssSelector("form#small-search-box-form>input#small-searchterms"));

        // Kiểm tra 1 element là enabled hay disabled
        // Áp dụng cho tất cả các loại element
        driver.findElement(By.cssSelector("")).isEnabled(); // Function/ Hàm bắt dầu từ is hầu như luôn trả về kiểu dữ liệu boolean
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isEnabled()); //*
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isEnabled());

        // Kiểm tra 1 element là hiển thị hay k hiển thị
        // Hiển thị: Có thể nhìn thấy được trn UI, kích tước ( rộng x cao ) > 0
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed()); //**   // Kiểm tra hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());  //** // Kiểm tra k hiển thị

        // Kiểm tra 1 element là đc chọn hay chưa
        // Áp dụng cho Checkbox/ Radio/ Dropdown ( Selectable )
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected()); //*
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isSelected());

        // Các function/ hàm có tiền tố getXXX sẽ luôn trả về dữ liệu
        // getter - lấy dữ liệu
        // setter - gán dữ liệu

        // Laays dữ liệu dạng text ra các element
        // Link/ Header/ Error Messages/ Success Message/ ...
        driver.findElement(By.cssSelector("")).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(), "This is a required field. "); //**

        // Hàm getAttribute lấy ra giá trị của thuộc tính ( attribute ) HTML
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAttribute("placeholder"), "Search entire store here... ");
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAttribute("class"), "input-text required-entry");

        // Hàm getAttribute lấy ra giá trị của thuộc tính ( attribute ) HTML
        // ( Phiên bản update function getAttribute thành getDomAttribute )
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomAttribute("placeholder"), "Search entire store here... "); //*
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomAttribute("class"), "input-text required-entry");

        // Lấy ra giá trin thuộc tính trong DOM ( Document Object Model )
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomProperty("value"), "Automation");
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomProperty("placeholder"), "Search entire store here...");
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomProperty("className"), "input-text required-entry");

        // Lấy ra giá trị của CSS ( ngôn ngữ cho FE )
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getCssValue("background-color"), "rgb(51, 153, 204)"); //*
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getCssValue("font-size"), "13px");

        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAccessibleName(), "");
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAriaRole(), "");

        Assert.assertEquals(driver.findElement(By.cssSelector("")).getLocation(), "");

        // Lấy ra chiêù rộng, chiều cao của 1 element
        Dimension loginButtonSize = driver.findElement(By.cssSelector("")).getSize();
        loginButtonSize.getWidth();
        loginButtonSize.getHeight();

        // Lấy ra vị trí của element so với màn hình
        Point loginButtonLocation = driver.findElement(By.cssSelector("")).getLocation();
        loginButtonLocation.getX();
        loginButtonLocation.getY();

        // Bao gồm cả Size và Location
        Rectangle loginButtonRectangle = driver.findElement(By.cssSelector("")).getRect(); // bao gồm both 2 thằng trên
        loginButtonRectangle.getHeight();
        loginButtonRectangle.getWidth();
        loginButtonRectangle.getY();
        loginButtonRectangle.getX();

        loginButtonSize = loginButtonRectangle.getDimension();
        loginButtonLocation = loginButtonRectangle.getPoint();

        // Lấy ra thẻ HTML của element ấy
        String elementA = driver.findElement(By.cssSelector("advice-required-entry-email")).getTagName(); // Ví dụ từ đây cos thể lấy ra thẻ HTML laf div/input/..
        driver.findElement(By.cssSelector(elementA + "#advice-required-entry-pass"));

        // Handle Shadow DOM
        driver.findElement(By.cssSelector("")).getShadowRoot(); //*

        // Chỉ áp dụng cho element nằm trong form
        // Tương tự hành vi gửi request lên server
        driver.findElement(By.cssSelector("input#newsletter")).submit();

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
