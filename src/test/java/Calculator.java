import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Calculator {

    private WindowsDriver calcSession = null;
    private WebElement calcResult = null;

    @BeforeClass
    public void setUp(){
        System.out.println("setup");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app",
                "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        try {
            calcSession = new WindowsDriver(new URL("http://127.0.0.1:4723/"),capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterClass
    public void tearDown(){
        System.out.println("teardown");
        //calcSession.quit();
    }

    //@BeforeMethod
    //public void clear(){
      //  System.out.println("CLEAR");
        //calcSession.findElementByName("Clear").click();
    //}

    @Test
    public void addition(){
        System.out.println("running addition");
        calcSession.findElementByName("One").click();
        calcSession.findElementByName("Two").click();
        calcSession.findElementByName("Plus").click();
        calcSession.findElementByName("Nine").click();
        calcSession.findElementByName("Equals").click();

        Assert.assertEquals(getDisplayResult(),"21");

    }

    @Test
    public void multiplication(){
        System.out.println("running multiplication");
        calcSession.findElementByName("Three").click();
        calcSession.findElementByName("Three").click();
        calcSession.findElementByName("Multiply by").click();
        calcSession.findElementByName("Three").click();
        calcSession.findElementByName("Equals").click();
        Assert.assertEquals(getDisplayResult(), "99");

    }
    @Test
    public void subtraction(){
        System.out.println("running subtraction");
        calcSession.findElementByName("Nine").click();
        calcSession.findElementByName("Three").click();
        calcSession.findElementByName("Minus").click();
        calcSession.findElementByName("Three").click();
        calcSession.findElementByName("Equals").click();
        Assert.assertEquals(getDisplayResult(), "90");
    }
    @Test
    public void division(){
        System.out.println("running division");
        calcSession.findElementByName("Three").click();
        calcSession.findElementByName("Three").click();
        calcSession.findElementByName("Divide by").click();
        calcSession.findElementByName("Three").click();
        calcSession.findElementByName("Equals").click();
        Assert.assertEquals(getDisplayResult(), "11");
    }
    public String getDisplayResult(){
        calcResult = calcSession.
                findElementByAccessibilityId("CalculatorResults");
        return calcResult.getText().replace("Display is","").trim();

    }

    public void ChooseCalculator(String locator){
        System.out.println(locator);
        calcSession.findElementByAccessibilityId("TogglePaneButton").click();
        List<WebElement> listOfElements = calcSession.findElementsByClassName("Microsoft.UI.Xaml.Controls.NavigationViewItem");
        System.out.println(listOfElements.size());
        for (int i=0; i< listOfElements.size();i++){
            if (listOfElements.get(i).getAttribute("Name").
                    equals(locator)){
                listOfElements.get(i).click();
                break;
            }
        }
    }

    @Test
    public void SelectAnotherCalculator(){
        System.out.println("Selecting Another Calculator");
        ChooseCalculator("Scientific Calculator");
    }

    public void ChooseCalculatorXpath(String locator){
        System.out.println(locator);
        calcSession.findElementByAccessibilityId("TogglePaneButton").click();
        List<WebElement> listOfElements = calcSession.findElementsByXPath
                ("//ListItem");
        System.out.println(listOfElements.size());
        for (int i=0; i< listOfElements.size();i++){
            if (listOfElements.get(i).getAttribute("Name").
                    equals(locator)){
                listOfElements.get(i).click();
                break;
            }
        }
    }

    @Test
    public void SelectAnotherCalculatorXpath(){
        System.out.println("Selecting Another Calculator");
        ChooseCalculator("Scientific Calculator");
    }

    public void ChooseCalculatorXpathSimple(String locator){
        System.out.println(locator);
        calcSession.findElementByAccessibilityId("TogglePaneButton").click();
        calcSession.findElementByXPath("//ListItem[contains(@Name,\"" + locator + "\")]").click();

    }

    @Test
    public void SelectAnotherCalculatorXpathSimple(){
        System.out.println("Selecting Anther Calculator");
        ChooseCalculatorXpathSimple("Scientific Calculator");
    }
}
