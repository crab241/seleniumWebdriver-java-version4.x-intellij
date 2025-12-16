package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;

public class Topic_16_User_Interactions_III {
    WebDriver driver;
    Actions actions;
    String osName = System.getProperty("os.name");
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");
    String jQueryFilePath;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        actions = new Actions(driver);

        jQueryFilePath = projectPath + "\\dragAndDrop\\dragAndDrop.js";
    }

    @Test
    public void TC_01_Drag_And_Drop_HTML4(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(sourceCircle, targetCircle).perform();

        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex(), "#03a9f4");

        Assert.assertEquals(targetCircle.getText(), "You did great!");
    }

    @Test
    public void TC_02_Drag_And_Drop_HTML5() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String jQueryFileContent = getContentFile(jQueryFilePath);

        String sourceCss = "div#column-a";
        String targetCss = "div#column-b";

        jQueryFileContent = jQueryFileContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
        jsExecutor.executeScript(jQueryFileContent);
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");

        jsExecutor.executeScript(jQueryFileContent);
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    /*public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        org.openqa.selenium.Dimension sourceSize = source.getSize();
        org.openqa.selenium.Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        org.openqa.selenium.Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }*/

   /* public void TC_03_Drag_And_Drop_HTML5() throws InterruptedException, IOException, AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String jQueryFileContent = getContentFile(jQueryFilePath);

        String sourceXpath = "//div[@id='column-a]'";
        String targetXPath = "//div[@id='column-b]'";

        dragAndDropHTML5ByXpath(sourceXpath, targetXPath);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");

        dragAndDropHTML5ByXpath(sourceXpath, targetXPath);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
    }*/

    @Test
    public void HW_TC_08_Drag_And_Drop_Element_HTML4(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement droptarget = driver.findElement(By.cssSelector("div#droptarget"));
        WebElement dragsource = driver.findElement(By.cssSelector("div#draggable"));

        actions.dragAndDrop(dragsource, droptarget).pause(Duration.ofSeconds(3)).perform();

        // Verify message box in Drop target circle
        Assert.assertEquals(droptarget.getText(), "You did great!");
        // Verify background color of drop target turned to blue
        Assert.assertEquals(Color.fromString(droptarget.getCssValue("background-color")).asHex(), "#03a9f4");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
