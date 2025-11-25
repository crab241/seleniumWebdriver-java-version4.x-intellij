package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_01_Assert {
    @Test
    public void TC_01() {
        // Kiểm tra dữ liệu đúng   Happy Case
        Assert.assertTrue(3 < 5);
        //Assert.assertTrue(driver.findElement(By.cssSelector("")).isEnabled());


        // Kiểm tra dữ liệu sai    Edge Case
        //Assert.assertFalse(driver.findElement(By.cssSelector("")).isEnabled());
        Assert.assertFalse(3 > 5);


        // Kiểm tra dữ liệu mong đợi vs thực tế bằng nhau
        Assert.assertEquals(15, 16);
        Assert.assertEquals("Testing", "Manual");

    }
}
