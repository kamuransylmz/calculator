import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CalculatorPO_test {

    private WindowsDriver calcSession = null;
    private WebElement calcResult = null;
    private Calc_PO c = null;

    @BeforeClass
    public void setUp() {
        System.out.println("setup");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app",
                "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        try {
            calcSession = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capabilities);
            c = new Calc_PO(calcSession);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown() {
        System.out.println("teardown");
        //calcSession.quit();
    }

    @BeforeMethod
    public void clear() {
        System.out.println("CLEAR");
        calcSession.findElementByName("Clear").click();
    }

    @Test
    public void addition() {
        System.out.println("running addition");
        c.One().click();
        c.Two().click();
        c.Plus().click();
        c.One().click();
        c.Equals().click();

        Assert.assertEquals(c.getDisplayResult(), "13");
    }

    @Test
    public void move() throws InterruptedException {
        Actions move = new Actions(calcSession);
        move.clickAndHold(calcSession.findElementByAccessibilityId("AppName"))
                .moveByOffset(50, 50)
                .release()
                .build().perform();
        Thread.sleep(3000);
        move.clickAndHold(calcSession.findElementByAccessibilityId("AppName"))
                .moveByOffset(-50, -50)
                .release()
                .build().perform();
        move.clickAndHold(calcSession.findElementByAccessibilityId("AppName"))
                .moveToElement(c.One())
                .release()
                .build().perform();
        move.contextClick(calcSession.findElementByAccessibilityId("AppName"))
                .build()
                .perform();
        Thread.sleep(5000);
    }
}
