package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Registry_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_EmptyData(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("");

        driver.findElement(By.cssSelector("form.frmRegister button.btn_pink_sm")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Vui lòng nhập họ tên");


    }

    @Test
    public void TC_02_InvalidEmail(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Doe");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("john@doe@us");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("john@doe@us");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0987654321");

        driver.findElement(By.cssSelector("form.frmRegister button.btn_pink_sm")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");

    }

    @Test
    public void TC_03_Incorrect_ConfirmEmail(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Doe");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("john@doe.us");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("john@doe.uk");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0987654321");

        driver.findElement(By.cssSelector("form.frmRegister button.btn_pink_sm")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Email nhập lại không hợp lệ");

    }

    @Test
    public void TC_04_InvalidPasswd(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Doe");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("john@doe.us");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("john@doe.us");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("12345");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("12345");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0987654321");

        driver.findElement(By.cssSelector("form.frmRegister button.btn_pink_sm")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void TC_05_Incorrect_ConfirmPasswd(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Doe");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("john@doe.us");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("john@doe.us");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123451");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0987654321");

        driver.findElement(By.cssSelector("form.frmRegister button.btn_pink_sm")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Invalid_PhoneNumber(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("John Doe");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("john@doe.us");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("john@doe.us");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("09876541");

        driver.findElement(By.cssSelector("form.frmRegister button.btn_pink_sm")).click();

        // Phone < 10 char
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        driver.findElement(By.cssSelector("input#txtPhone")).clear();
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("098765417425");
        driver.findElement(By.cssSelector("form.frmRegister button.btn_pink_sm")).click();

        // Phone > 11 char
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // Invalid Phone format
        driver.findElement(By.cssSelector("input#txtPhone")).clear();
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0687654174");
        driver.findElement(By.cssSelector("form.frmRegister button.btn_pink_sm")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        driver.findElement(By.cssSelector("input#txtPhone")).clear();
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("1687654174");
        driver.findElement(By.cssSelector("form.frmRegister button.btn_pink_sm")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
